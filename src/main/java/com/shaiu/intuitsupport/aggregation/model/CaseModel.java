package com.shaiu.intuitsupport.aggregation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CaseModel {

    long caseId;
    String crmName;
    int customerId;
    int provider;
    int createdErrorCode;
    String status;
    String ticketCreationData;
    String lastModifiedDate;
    String productName;
}
