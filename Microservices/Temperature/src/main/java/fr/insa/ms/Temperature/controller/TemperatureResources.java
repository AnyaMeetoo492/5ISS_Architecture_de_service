package fr.insa.ms.Temperature.controller;

import java.time.LocalDateTime;
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

import fr.insa.ms.Temperature.models.TemperatureEntity;
import fr.insa.ms.Temperature.repository.TemperatureRepository;

@RestController
@RequestMapping("/temperature")
public class TemperatureResources {
	
	@Autowired
	private TemperatureRepository temperatureRepository;
	
	@GetMapping("/last/{citerneID}")
	public int getLastTemperatureFromDB(@PathVariable int citerneID) {
		Optional<TemperatureEntity> opt = temperatureRepository.findTopByCiterneIDOrderByDateDesc(citerneID);
		if (opt.isPresent()) {
		    return opt.get().getValeur();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No temperature in DB");
		}
	}

	@PostMapping("/add")
	public void setTemperature(@RequestParam int valeur, @RequestParam int citerneID) {
	    TemperatureEntity record = new TemperatureEntity(valeur, citerneID, LocalDateTime.now());
	    temperatureRepository.save(record);
	}
	
}