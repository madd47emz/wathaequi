package com.example.msdemandev3.proxy;

import com.example.msdemandev3.model.Utilisateur;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
@LoadBalancerClient(name="user-service")
public interface UtilisateurProxy {
    @GetMapping("Utilisateur/search/findUserByDemande_IdDemande")
    public CollectionModel<Utilisateur> getDemande(@RequestParam("idd") Long idd,
                                                   @RequestParam("projection") String projection);
}