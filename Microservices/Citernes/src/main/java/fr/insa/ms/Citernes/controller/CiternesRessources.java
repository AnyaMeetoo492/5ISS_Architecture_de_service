package fr.insa.ms.Citernes.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.ms.Citernes.models.CiterneEntity;
import fr.insa.ms.Citernes.repository.CiternesRepository;

@RestController
@RequestMapping("/citernes")
public class CiternesRessources {
	
	@Autowired
	private CiternesRepository citernesRepository;
	
	@GetMapping("/list")
	public Iterable<CiterneEntity> listCiterne() {
	    return citernesRepository.findAll();
	}
	
	@GetMapping("/list/{id}")
	public CiterneEntity infoCiterne(@PathVariable int id) {
		return citernesRepository.getReferenceById(id);
	}
	
	@PostMapping("/add/{idCiterne}")
	public void addCiterne(@PathVariable int idCiterne, @RequestParam String citerneName) {
		CiterneEntity record = new CiterneEntity(idCiterne, citerneName, LocalDate.now(), LocalDate.now().plusYears(2), false);
		citernesRepository.save(record);
	}
	
	@DeleteMapping("/delete/{idCiterne}")
	public void deleteCiterne(@PathVariable int idCiterne) {
		citernesRepository.deleteById(idCiterne);
	}
	
	@PutMapping("/updateliquide/{idCiterne}")
	public CiterneEntity updateLiquide(@PathVariable int idCiterne, @RequestParam Boolean contientLiquide) {
		CiterneEntity citerne = citernesRepository.findById(idCiterne)
		        .orElseThrow(() -> new RuntimeException("Citerne non trouvée"));
		citerne.setContientLiquide(contientLiquide);
		return citernesRepository.save(citerne);
	}
}
