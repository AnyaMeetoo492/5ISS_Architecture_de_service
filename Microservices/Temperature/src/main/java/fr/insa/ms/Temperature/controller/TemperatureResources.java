package fr.insa.ms.Temperature.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.ms.Temperature.models.Temperature;

@RestController
@RequestMapping("/temperature")
public class TemperatureResources {
	
	public List<Temperature> initListeTemperatures() {
		Temperature temp1 = new Temperature(35);
		Temperature temp2 = new Temperature(30);
		Temperature temp3 = new Temperature(20);
		Temperature temp4 = new Temperature(15);
		
		List<Temperature> liste = new ArrayList<Temperature>();
		
		liste.add(temp1);
		liste.add(temp2);
		liste.add(temp3);
		liste.add(temp4);
		
		return liste;
		
	}
	
	@GetMapping("/")
	public List<Temperature> getListeTemperatures() {
		return initListeTemperatures();
	}
	
	@GetMapping("/{id}")
	public int getTemperature(@PathVariable("id") int ID) {
		List<Temperature> liste = initListeTemperatures();
		return liste.get(ID).getValeur();
	}
	
	@GetMapping("/{id}/{valeur}")
	public void setTemperature(@PathVariable("id") int ID, @PathVariable("valeur") int valeur) {
		List<Temperature> liste = initListeTemperatures();
		liste.get(ID).setValeur(valeur);
	}
	
	
}