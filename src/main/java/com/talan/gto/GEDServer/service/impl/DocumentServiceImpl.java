package com.talan.gto.GEDServer.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talan.gto.GEDServer.model.Document;
import com.talan.gto.GEDServer.repository.DocumentRepository;
import com.talan.gto.GEDServer.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService{
	
	@Autowired
	DocumentRepository documentRepository;
	
	@Override
	public List<Document> findAll() {
		return documentRepository.findAll();
	}

	@Override
	public Optional<Document> findById(Long id) {
		return documentRepository.findById(id);
	}

	@Override
	public Document save(Document liste) {
		Document d = documentRepository.save(liste);
		return d;
	}

	@Override
	public void deleteById(Long id) {
		documentRepository.deleteById(id);
	}
}
