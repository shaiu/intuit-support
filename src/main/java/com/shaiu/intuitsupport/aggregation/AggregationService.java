package com.shaiu.intuitsupport.aggregation;


import com.shaiu.intuitsupport.aggregation.crm.CrmInterface;
import com.shaiu.intuitsupport.aggregation.entity.Aggregation;
import com.shaiu.intuitsupport.aggregation.entity.CrmCase;
import com.shaiu.intuitsupport.aggregation.model.CaseModel;
import com.shaiu.intuitsupport.aggregation.model.Data;
import com.shaiu.intuitsupport.aggregation.data.AggregationDataInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class AggregationService {

    private final List<CrmInterface> crmList;
    private final AggregationDataInterface aggregationData;

    public AggregationService(List<CrmInterface> crmList, AggregationDataInterface aggregationData) {
        this.crmList = crmList;
        this.aggregationData = aggregationData;
    }


    /**
     * The aggregation itself.
     * this is scheduled every 4 hours (can be changed in configuration)
     * The process is to fetch data from all CRMs and aggregate them in our repository along with the original cases
     */
    @Scheduled(fixedDelayString = "${fixedDelay.in.hours}", timeUnit = TimeUnit.HOURS)
    public void aggregation() {
        List<Data> dataFromCrms = getDataFromCrms();

        Map<Integer, List<CaseModel>> dataByErrorCode = groupDataByErrorCode(dataFromCrms);

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        List<Aggregation> aggregationList = transformToAggregations(dataByErrorCode, timestamp);

        aggregationData.deleteAllData();
        aggregationData.saveData(aggregationList);
    }

    /**
     * @param dataByErrorCode - the data grouped by error code
     *                        The method creates new aggregation entities per error code
     * @param timestamp       - add a timestamp so we can know how "old" that data is
     * @return - list of Aggregation entities
     */
    private static List<Aggregation> transformToAggregations(Map<Integer, List<CaseModel>> dataByErrorCode, Timestamp timestamp) {
        return dataByErrorCode.entrySet().stream()
                .map(dataErrorCodeEntry -> {
                    List<CrmCase> crmCaseList = getCrmCases(dataErrorCodeEntry);
                    Aggregation aggregation = new Aggregation(dataErrorCodeEntry.getKey(), dataErrorCodeEntry.getValue().size(), crmCaseList, timestamp);
                    crmCaseList.forEach(crmCase -> crmCase.setAggregation(aggregation));
                    return aggregation;
                })
                .collect(Collectors.toList());
    }

    private static List<CrmCase> getCrmCases(Map.Entry<Integer, List<CaseModel>> dataErrorCodeEntry) {
        return dataErrorCodeEntry.getValue().stream()
                .map(AggregationService::getCrmCase)
                .toList();
    }

    private static CrmCase getCrmCase(CaseModel aCaseModel) {
        CrmCase crmCaseEntity = new CrmCase();
        BeanUtils.copyProperties(aCaseModel, crmCaseEntity);
        return crmCaseEntity;
    }

    /**
     * @param dataFromCrms - list of data from several CRMs
     *                     This method groups them by the error code
     * @return
     */
    private static Map<Integer, List<CaseModel>> groupDataByErrorCode(List<Data> dataFromCrms) {
        return dataFromCrms.stream()
                .map(Data::getData)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(CaseModel::getCreatedErrorCode));
    }

    private List<Data> getDataFromCrms() {
        List<Data> dataList = new ArrayList<>();
        crmList.forEach(crm -> dataList.add(getDataAndAddCrmName(crm)));
        return dataList;
    }

    /**
     * @param crm - crm service to fetch data from
     *            This method also enriches the data with the CRM name to distinguish cases
     * @return
     */
    private static Data getDataAndAddCrmName(CrmInterface crm) {
        Data casesData = crm.getCasesData();
        casesData.getData().forEach(caseModel -> caseModel.setCrmName(crm.getName()));
        return casesData;
    }
}
