package com.shaiu.intuitsupport.aggregation.data;

import com.shaiu.intuitsupport.aggregation.entity.Aggregation;

import java.util.List;

public interface AggregationDataInterface {
    void saveData(List<Aggregation> aggregation);

    void deleteAllData();
}
