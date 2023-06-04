package com.example.msparticipationcitoyen.proxy;

import com.example.msparticipationcitoyen.model.CitizenAuth;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="ms-auth-admin")
@LoadBalancerClient(name="ms-auth-admin")
public interface CitizenProxy {
    @GetMapping("/citizens/{id}")
    CitizenAuth getCitizenAuth(@PathVariable("id") Long id,
                                           @RequestParam("projection") String projection);
}
