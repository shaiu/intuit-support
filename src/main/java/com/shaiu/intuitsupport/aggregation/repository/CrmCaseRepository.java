package com.shaiu.intuitsupport.aggregation.repository;

import com.shaiu.intuitsupport.aggregation.entity.CrmCase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrmCaseRepository extends CrudRepository<CrmCase, Long> {
}
