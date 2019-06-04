package com.talan.gto.GEDServer.service;

import java.util.List;
import java.util.Optional;
import com.talan.gto.GEDServer.model.DocumentClient;


public interface DocumentClientService {
	
	List<DocumentClient> findAll();
	
	Optional<DocumentClient> findById(Long id);
	
	DocumentClient save(DocumentClient liste);

	void deleteById(Long id);
}
