package com.example.msdemandev3.proxy;



import com.example.msdemandev3.model.Citizen;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name="auth")
@LoadBalancerClient(name="auth")
public interface CitizenProx {
    @GetMapping("/citizens/search/findCitizensByNin")
    Citizen getUserDetails(@RequestParam("nin") String nin,
                           @RequestHeader("Authorization") String authorizationHeader);


}