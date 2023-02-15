package com.shaiu.intuitsupport.aggregation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Data {
    List<CaseModel> data;
}
