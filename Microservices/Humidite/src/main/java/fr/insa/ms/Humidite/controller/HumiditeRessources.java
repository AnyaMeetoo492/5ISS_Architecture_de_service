package fr.insa.ms.Humidite.controller;

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

import fr.insa.ms.Humidite.models.HumiditeEntity;
import fr.insa.ms.Humidite.repository.HumiditeRepository;

@RestController
@RequestMapping("/humidite")
public class HumiditeRessources {
	
	@Autowired
	private HumiditeRepository humuditeRepository;
	
	@PostMapping("/add")
	public void setHumidite(@RequestParam float valeur, @RequestParam int citerneID) {
	    HumiditeEntity record = new HumiditeEntity(LocalDateTime.now(), valeur, citerneID);
	    humuditeRepository.save(record);
	}
	
	@GetMapping("/last/{citerneID}")
	public float getLastHumiditeFromDB(@PathVariable int citerneID) {
		Optional<HumiditeEntity> opt = humuditeRepository.findTopByCiterneIDOrderByDateDesc(citerneID);
		if (opt.isPresent()) {
		    return opt.get().getValeur();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No humidity in DB");
		}
	}
}
