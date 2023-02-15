package com.shaiu.intuitsupport.aggregation

import com.shaiu.intuitsupport.aggregation.crm.CrmInterface
import com.shaiu.intuitsupport.aggregation.crm.banana.Banana
import com.shaiu.intuitsupport.aggregation.crm.strawberry.Strawberry
import com.shaiu.intuitsupport.aggregation.model.CaseModel
import com.shaiu.intuitsupport.aggregation.model.Data
import com.shaiu.intuitsupport.aggregation.data.AggregationDataInterface
import org.spockframework.spring.SpringBean
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

@SpringBootTest
class AggregationServiceSpecIT extends Specification {

    AggregationService aggregationService

    List<CrmInterface> crmList

    @SpringBean
    AggregationDataInterface aggregationData = Mock()

    @SpringBean
    RestTemplate restTemplate = Mock()

    void setup() {
        crmList = [new Banana(restTemplate), new Strawberry(restTemplate)]
        aggregationService = new AggregationService(crmList, aggregationData)
    }

    def "Aggregation"() {
        when:
        aggregationService.aggregation()
        then:
        2 * restTemplate.getForObject(_, _) >> new Data([getCaseModel()])
        1 * aggregationData.deleteAllData()
        1 * aggregationData.saveData(_)
    }


    def getCaseModel() {
        def caseModel = new CaseModel()
        caseModel.caseId = 2
        caseModel.customerId = 818591
        caseModel.provider = 6111
        caseModel.createdErrorCode = 324
        caseModel.status = "Open"
        caseModel.ticketCreationData = "3/14/2019 16:30"
        caseModel.lastModifiedDate = "3/17/2019 3:41"
        caseModel.productName = "BLUE"
        caseModel
    }
}
