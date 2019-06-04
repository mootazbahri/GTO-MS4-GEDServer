package com.talan.gto.GEDServer.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talan.gto.GEDServer.model.SaisieDocument;
import com.talan.gto.GEDServer.repository.SaisieDocumentRepository;
import com.talan.gto.GEDServer.service.SaisieDocumentService;

@Service
public class SaisieDocumentServiceImpl implements SaisieDocumentService{
	
	@Autowired
	SaisieDocumentRepository saisieDocumentRepository;
	
	@Override
	public List<SaisieDocument> findAll() {
		return saisieDocumentRepository.findAll();
	}

	@Override
	public Optional<SaisieDocument> findById(Long id) {
		return saisieDocumentRepository.findById(id);
	}

	@Override
	public SaisieDocument save(SaisieDocument liste) {
		SaisieDocument d = saisieDocumentRepository.save(liste);
		return d;
	}

	@Override
	public void deleteById(Long id) {
		saisieDocumentRepository.deleteById(id);
	}
}
