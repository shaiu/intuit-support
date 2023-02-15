package com.shaiu.intuitsupport.aggregation.crm.banana;

import com.shaiu.intuitsupport.aggregation.crm.CrmInterface;
import com.shaiu.intuitsupport.aggregation.model.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Banana implements CrmInterface {

    private final RestTemplate restTemplate;

    public Banana(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Data getCasesData() {
        return restTemplate.getForObject("http://127.0.0.1:3000/banana", Data.class);
    }

    @Override
    public String getName() {
        return "Banana";
    }
}
