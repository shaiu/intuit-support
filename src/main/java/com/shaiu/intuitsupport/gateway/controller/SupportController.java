package com.shaiu.intuitsupport.gateway.controller;

import com.shaiu.intuitsupport.aggregation.entity.Aggregation;
import com.shaiu.intuitsupport.gateway.service.SupportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupportController {

    private final SupportService supportService;

    public SupportController(SupportService supportService) {
        this.supportService = supportService;
    }


    /**
     * @return aggregation of the support tickets from several CRM applications
     */
    @GetMapping("/aggregation")
    public List<Aggregation> aggregation() {
        return supportService.getData();
    }
}
