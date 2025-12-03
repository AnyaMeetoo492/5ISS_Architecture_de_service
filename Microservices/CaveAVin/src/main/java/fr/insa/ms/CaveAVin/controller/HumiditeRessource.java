package fr.insa.ms.CaveAVin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HumiditeRessource {

	@GetMapping("/humidite")
	public float humiditeValue() {
		return (float) 12.0;
	}
}
