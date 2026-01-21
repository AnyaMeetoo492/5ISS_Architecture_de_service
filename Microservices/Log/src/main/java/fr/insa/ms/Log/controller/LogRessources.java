package fr.insa.ms.Log.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.ms.Log.models.LogEntity;
import fr.insa.ms.Log.repository.LogRepository;
@RestController
@RequestMapping("/log")
public class LogRessources {
	
	@Autowired
	private LogRepository logRepository;
	
	@GetMapping("/list")
	public Iterable<LogEntity> getLog() {
		return logRepository.findAll();
	}
	
	@GetMapping("/list/{idCiterne}")
	public Iterable<LogEntity> getLogIdCiterne(@PathVariable int idCiterne) {
		return logRepository.findAllByCiterneIDOrderByDateDesc(idCiterne);
	}
	
	@PostMapping("/add")
	public void setLog(@RequestBody LogEntity log) {
		LogEntity record = new LogEntity(log.getCiterneID(), log.getActionType(), LocalDateTime.now(), log.getObservation());
		logRepository.save(record);
	}

}
