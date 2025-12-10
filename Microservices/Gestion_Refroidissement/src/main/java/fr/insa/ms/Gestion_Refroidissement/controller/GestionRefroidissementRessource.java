package fr.insa.ms.Gestion_Refroidissement.controller;
import org.springframework.web.bind.annotation.*;

import fr.insa.ms.Gestion_Refroidissement.models.*;

@RestController
public class GestionRefroidissementRessource {
	@PutMapping("/actionReffroidissement/{value}")
	public GestionRefroidissement updateAction(@PathVariable boolean value) {
		GestionRefroidissement action = new GestionRefroidissement(value);
		return action;
	}
}
