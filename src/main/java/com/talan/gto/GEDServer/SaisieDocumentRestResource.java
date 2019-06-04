package com.talan.gto.GEDServer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talan.gto.GEDServer.model.SaisieDocument;
import com.talan.gto.GEDServer.service.SaisieDocumentService;




@RestController
@EnableAutoConfiguration
@RequestMapping("/SaisieDocument")
public class SaisieDocumentRestResource {
	
	@Autowired
	private SaisieDocumentService saisieDocumentService;
	
	/*+--------------------------------------------------------------------+
	 *| => Simple Crud                                                     |
	 *+--------------------------------------------------------------------+*/
	
	@GetMapping("/getAll")
	List<SaisieDocument> getAll() {
		return saisieDocumentService.findAll();
	}
	@GetMapping("/getSaisieDocument/{saisieDocumentId}")
	Optional<SaisieDocument> getSaisieDocument(@PathVariable Long saisieDocumentId) {
		return saisieDocumentService.findById(saisieDocumentId);
	}
	@PostMapping(value="/addSaisieDocument")
    public List<SaisieDocument> addSaisieDocument(@RequestBody SaisieDocument liste) {
		saisieDocumentService.save(liste);
		return saisieDocumentService.findAll();
    }
	
	@PostMapping(value="/deleteSaisieDocument/{saisieDocumentId}")
	public List<SaisieDocument> deleteSaisieDocument(@PathVariable Long saisieDocumentId) {
		saisieDocumentService.deleteById(saisieDocumentId);
		return saisieDocumentService.findAll();
	}
}
