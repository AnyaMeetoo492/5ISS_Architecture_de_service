package fr.insa.ms.Temperature.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.insa.ms.Temperature.models.Temperature;
import fr.insa.ms.Temperature.models.TemperatureEntity;
import fr.insa.ms.Temperature.repository.TemperatureRepository;

@RestController
@RequestMapping("/temperature")
public class TemperatureResources {
	
	@Autowired
	private TemperatureRepository temperatureRepository;

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
	
	@GetMapping("/demo/{id}")
	public int getTemperature(@PathVariable("id") int ID) {
		List<Temperature> liste = initListeTemperatures();
		return liste.get(ID).getValeur();
	}
	
	@GetMapping("/last")
	public int getLastTemperatureFromDB() {
		Optional<TemperatureEntity> opt = temperatureRepository.findTopByOrderByDateDesc();
		if (opt.isPresent()) {
		    return opt.get().getValeur();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No temperature in DB");
		}
	}

	@PostMapping("/add")
	public void setTemperature(@RequestParam int valeur) {
	    TemperatureEntity record = new TemperatureEntity(valeur, LocalDateTime.now());
	    temperatureRepository.save(record);
	}
	
}