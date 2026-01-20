package fr.insa.ms.NiveauLiquide.controller;

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

import fr.insa.ms.NiveauLiquide.models.NiveauLiquideEntity;
import fr.insa.ms.NiveauLiquide.repository.NiveauLiquideRepository;

@RestController
@RequestMapping("/niveauliquide")
public class NiveauLiquideRessources {
	
	@Autowired
	private NiveauLiquideRepository niveauliquideRepository;
	
	@GetMapping("/list")
	public Iterable<NiveauLiquideEntity> getHumidite() {
		return niveauliquideRepository.findAll();
	}
	
	@PostMapping("/add")
	public void setNiveauLiquide(@RequestParam float valeur, @RequestParam int citerneID) {
		NiveauLiquideEntity record = new NiveauLiquideEntity(LocalDateTime.now(), valeur, citerneID);
		niveauliquideRepository.save(record);
	}
	
	@GetMapping("/last/{citerneID}")
	public float getLastNiveauLiquideFromDB(@PathVariable int citerneID) {
		Optional<NiveauLiquideEntity> opt = niveauliquideRepository.findTopByCiterneIDOrderByDateDesc(citerneID);
		if (opt.isPresent()) {
		    return opt.get().getValeur();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No temperature in DB");
		}
	}
	
}
