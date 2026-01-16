package fr.insa.ms.Orchestrateur.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.ms.Refroidissement.models.*;

@RestController
@RequestMapping("/orchestrateur")
public class OrchestrateurResources {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/decision/{citerneID}")
    public String orchestrer(@PathVariable int citerneID) {
        int temp = restTemplate.getForObject(
            "http://TEMPERATURE/temperature/last/" + citerneID,
            Integer.class
        );

        boolean actif = temp > 30;

        restTemplate.postForObject(
            "http://REFROIDISSEMENT/refroidissement/apply",
            new Refroidissement(citerneID, actif),
            Void.class
        );
        return "Citerne = " + citerneID + " | Température = " + temp + " | Refroidissement actif = " + actif;
    }
  
    
    
}