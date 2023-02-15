package com.shaiu.intuitsupport.aggregation.repository;

import com.shaiu.intuitsupport.aggregation.entity.Aggregation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AggregationRepository extends CrudRepository<Aggregation, Long> {}
