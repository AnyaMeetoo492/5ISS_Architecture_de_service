package fr.insa.ms.Orchestrateur.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.ms.Orchestrateur.models.LogEntity;
import fr.insa.ms.Orchestrateur.models.Refroidissement;

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
        
        float valueliquide = restTemplate.getForObject(
      		"http://NIVEAULIQUIDE/niveauliquide/last/" + citerneID,
             Integer.class
        );
        
        String ActionType = "";
        String Observation = "";
        boolean actif = temp > 30;
        if (actif) {
        	ActionType = "Refroidissement";
        	Observation = "Température de la citerne élevée !";
        }
        else {
        	ActionType = "";
        	Observation = "OK !";
        }
        Boolean liquide = valueliquide > 0;
	
        restTemplate.postForObject(
            "http://REFROIDISSEMENT/refroidissement/apply",
            new Refroidissement(citerneID, actif),
            Void.class
        );
        
        restTemplate.postForObject(
        		"http://LOG/log/add", 
        		new LogEntity(citerneID, ActionType, Observation), 
        		Void.class);
        
        restTemplate.put(
        		"http://CITERNES/citernes/updateliquide/" + citerneID + "?contientLiquide=" + liquide, 
        		null);
        
        return "Citerne = " + citerneID + " | Température = " + temp + " | Refroidissement actif = " + actif + " | Niveau liquide = " + valueliquide;
    }
}