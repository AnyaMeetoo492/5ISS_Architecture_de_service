package fr.insa.ms.Gestion_Temperature.controller;

import org.springframework.web.bind.annotation.*;

import fr.insa.ms.Gestion_Temperature.models.*;
import java.util.*;


@RestController
public class GestionTemperatureRessources {
	@GetMapping("/temperature")
	public ArrayList<Temperature> getTemperature() {
		ArrayList<Temperature> listtemp = new ArrayList<> ();
		Temperature temp0 = new Temperature(new Date(), 20);
		Temperature temp1 = new Temperature(new Date(), 30);
		listtemp.add(temp0);
		listtemp.add(temp1);
		return listtemp;
	}
}
