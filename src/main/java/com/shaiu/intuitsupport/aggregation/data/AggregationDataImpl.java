package com.shaiu.intuitsupport.aggregation.data;

import com.shaiu.intuitsupport.aggregation.entity.Aggregation;
import com.shaiu.intuitsupport.aggregation.repository.AggregationRepository;
import com.shaiu.intuitsupport.aggregation.repository.CrmCaseRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AggregationDataImpl implements AggregationDataInterface {

    private final AggregationRepository aggregationRepository;
    private final CrmCaseRepository crmCaseRepository;

    public AggregationDataImpl(AggregationRepository aggregationRepository, CrmCaseRepository crmCaseRepository) {
        this.aggregationRepository = aggregationRepository;
        this.crmCaseRepository = crmCaseRepository;
    }

    @Override
    public void saveData(List<Aggregation> aggregationList) {
        aggregationRepository.saveAll(aggregationList);
        aggregationList.forEach(aggregation -> crmCaseRepository.saveAll(aggregation.getCasesList()));
    }

    @Override
    public void deleteAllData() {
        aggregationRepository.deleteAll();
    }
}
