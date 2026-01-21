package fr.insa.ms.Extraction.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.ms.Extraction.models.Extraction;

@RestController
@RequestMapping("/extraction")
public class ExtractionRessources {

	private final Map<Integer, Boolean> etatParCiterne = new HashMap<>();
	
	@PostMapping("/apply")
    public void apply(@RequestBody Extraction extraction) {
        etatParCiterne.put(extraction.getCiterneID(), extraction.isActive());
        System.out.println("Refroidissement citerne " + extraction.getCiterneID() + " = " + (extraction.isActive() ? "ON" : "OFF"));
    }

    @GetMapping("/state/{citerneID}")
    public boolean getState(@PathVariable int citerneID) {
        return etatParCiterne.getOrDefault(citerneID, false);
    }
	
}
