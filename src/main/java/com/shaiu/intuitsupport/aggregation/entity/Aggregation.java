package com.shaiu.intuitsupport.aggregation.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * This entity represents the aggregation data from several CRM services
 * The aggregation is aggregated from the error code of the cases and holds a list of the cases that came from those CRMs
 * the cases are held in another table and pointed here
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Data
@NoArgsConstructor
public class Aggregation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    int errorCode;
    int numberOfCases;
    Timestamp timestamp;

    @OneToMany(mappedBy = "aggregation")
    List<CrmCase> casesList;

    public Aggregation(int errorCode, int numberOfCases, List<CrmCase> crmCases, Timestamp timestamp) {
        this.errorCode = errorCode;
        this.numberOfCases = numberOfCases;
        this.casesList = crmCases;
        this.timestamp = timestamp;
    }
}
