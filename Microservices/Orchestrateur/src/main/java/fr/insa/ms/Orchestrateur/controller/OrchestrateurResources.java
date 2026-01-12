package fr.insa.ms.Orchestrateur.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orchestrateur")
public class OrchestrateurResources {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    public String orchestrer(@PathVariable int id) {
        int temp = restTemplate.getForObject(
            "http://TEMPERATURE/temperature/" + id,
            Integer.class
        );

        boolean actif = restTemplate.getForObject(
            "http://REFROIDISSEMENT/refroidissement/" + id,
            Boolean.class
        );

        return "Température = " + temp + " | Refroidissement actif = " + actif;
    }
}