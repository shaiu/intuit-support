package com.shaiu.intuitsupport.gateway.service;

import com.shaiu.intuitsupport.aggregation.AggregationService;
import com.shaiu.intuitsupport.aggregation.entity.Aggregation;
import com.shaiu.intuitsupport.gateway.SupportRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class SupportServiceImpl implements SupportService {

    private final SupportRepository supportRepository;

    private final AggregationService aggregationService;

    public SupportServiceImpl(SupportRepository supportRepository, AggregationService aggregationService) {
        this.supportRepository = supportRepository;
        this.aggregationService = aggregationService;
    }

    /**
     * @return returns the aggregation data from the repository
     * if the timestamp is older than 15minutes we fetch the data live.
     */
    @Override
    public List<Aggregation> getData() {
        Timestamp timestamp = supportRepository.findTimestamp();
        if (timestamp.before(Timestamp.valueOf(LocalDateTime.now().minusMinutes(15)))) {
            aggregationService.aggregation();
        }
        return (List<Aggregation>) supportRepository.findAll();
    }
}
