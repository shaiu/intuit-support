package com.shaiu.intuitsupport.aggregation.crm.strawberry;

import com.shaiu.intuitsupport.aggregation.crm.CrmInterface;
import com.shaiu.intuitsupport.aggregation.model.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Strawberry implements CrmInterface {
    private final RestTemplate restTemplate;

    public Strawberry(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Data getCasesData() {
        return restTemplate.getForObject("http://127.0.0.1:3000/strawberry", Data.class);
    }

    @Override
    public String getName() {
        return "Strawberry";
    }
}
