package com.shaiu.intuitsupport.aggregation.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties({"aggregation"})
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrmCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long caseId;

    private String crmName;

    int customerId;

    int provider;

    int createdErrorCode;

    String status;
    String ticketCreationData;

    String lastModifiedDate;

    String productName;

    @ManyToOne
    @JoinColumn(name = "aggregation_id")
    private Aggregation aggregation;
}
