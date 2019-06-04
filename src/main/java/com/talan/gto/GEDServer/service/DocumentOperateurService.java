package com.talan.gto.GEDServer.service;

import java.util.List;
import java.util.Optional;
import com.talan.gto.GEDServer.model.DocumentOperateur;


public interface DocumentOperateurService {
	
	List<DocumentOperateur> findAll();
	
	Optional<DocumentOperateur> findById(Long id);
	
	DocumentOperateur save(DocumentOperateur liste);

	void deleteById(Long id);
}
