package com.talan.gto.GEDServer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.talan.gto.GEDServer.model.Document;
import com.talan.gto.GEDServer.repository.DocumentRepository;


public interface DocumentService {
	
	List<Document> findAll();
	
	Optional<Document> findById(Long id);
	
	Document save(Document liste);

	void deleteById(Long id);
}
