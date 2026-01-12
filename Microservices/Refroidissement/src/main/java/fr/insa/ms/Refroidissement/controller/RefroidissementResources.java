package fr.insa.ms.Refroidissement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.ms.Refroidissement.models.Refroidissement;

@RestController
@RequestMapping("/refroidissement")
public class RefroidissementResources {
	
	@Autowired
    private RestTemplate restTemplate;
	
	@GetMapping("/{id}")
	public boolean getActivation(@PathVariable("id") int id) {
		int temperature = restTemplate.getForObject("http://TEMPERATURE/temperature/" + id, Integer.class);
		
		Refroidissement refroid = new Refroidissement();
		refroid.verifActivation(temperature);
		
		return refroid.getActive();
	}

}