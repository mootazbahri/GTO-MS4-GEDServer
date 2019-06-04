package com.talan.gto.GEDServer.service;

import java.util.List;
import java.util.Optional;
import com.talan.gto.GEDServer.model.SaisieDocument;


public interface SaisieDocumentService {
	
	List<SaisieDocument> findAll();
	
	Optional<SaisieDocument> findById(Long id);
	
	SaisieDocument save(SaisieDocument liste);

	void deleteById(Long id);
}
