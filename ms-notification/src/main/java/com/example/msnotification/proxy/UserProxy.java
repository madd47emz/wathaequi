package com.example.msnotification.proxy;

import com.example.msnotification.model.User;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth")
@LoadBalancerClient(name = "auth")
public interface UserProxy {

    @GetMapping("/citizens/search/findCitizensByNin")
    User getUserDetails(@RequestParam("nin") String nin, @RequestHeader("Authorization") String authorizationHeader);
}
