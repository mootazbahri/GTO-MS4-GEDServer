package com.talan.gto.GEDServer.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.talan.gto.GEDServer.model.DocumentClient;
import com.talan.gto.GEDServer.repository.DocumentClientRepository;
import com.talan.gto.GEDServer.service.DocumentClientService;;

@Service
public class DocumentClientServiceImpl implements DocumentClientService{
	
	@Autowired
	DocumentClientRepository documentClientRepository;
	
	@Override
	public List<DocumentClient> findAll() {
		return documentClientRepository.findAll();
	}

	@Override
	public Optional<DocumentClient> findById(Long id) {
		return documentClientRepository.findById(id);
	}

	@Override
	public DocumentClient save(DocumentClient liste) {
		DocumentClient d = documentClientRepository.save(liste);
		return d;
	}

	@Override
	public void deleteById(Long id) {
		documentClientRepository.deleteById(id);
	}
}
