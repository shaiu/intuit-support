package com.shaiu.intuitsupport.gateway;

import com.shaiu.intuitsupport.aggregation.entity.Aggregation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface SupportRepository extends CrudRepository<Aggregation, Long> {
    @Query(value = "SELECT timestamp FROM aggregation LIMIT 1", nativeQuery = true)
    Timestamp findTimestamp();
}
