package com.talan.gto.GEDServer.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talan.gto.GEDServer.model.DocumentOperateur;
import com.talan.gto.GEDServer.repository.DocumentOperateurRepository;
import com.talan.gto.GEDServer.service.DocumentOperateurService;

@Service
public class DocumentOperateurServiceImpl implements DocumentOperateurService{
	
	@Autowired
	DocumentOperateurRepository documentOperateurRepository;
	
	@Override
	public List<DocumentOperateur> findAll() {
		return documentOperateurRepository.findAll();
	}

	@Override
	public Optional<DocumentOperateur> findById(Long id) {
		return documentOperateurRepository.findById(id);
	}

	@Override
	public DocumentOperateur save(DocumentOperateur liste) {
		DocumentOperateur d = documentOperateurRepository.save(liste);
		return d;
	}

	@Override
	public void deleteById(Long id) {
		documentOperateurRepository.deleteById(id);
	}
}
