package fr.insa.ms.Refroidissement.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.ms.Refroidissement.models.Refroidissement;

@RestController
@RequestMapping("/refroidissement")
public class RefroidissementResources {
	
	private final Map<Integer, Boolean> etatParCiterne = new HashMap<>();

    @PostMapping("/apply")
    public void apply(@RequestBody Refroidissement refroidissement) {
        etatParCiterne.put(refroidissement.getCiterneID(), refroidissement.isActive());
        System.out.println("Refroidissement citerne " + refroidissement.getCiterneID() + " = " + (refroidissement.isActive() ? "ON" : "OFF"));
    }

    @GetMapping("/state/{citerneID}")
    public boolean getState(@PathVariable int citerneID) {
        return etatParCiterne.getOrDefault(citerneID, false);
    }

}