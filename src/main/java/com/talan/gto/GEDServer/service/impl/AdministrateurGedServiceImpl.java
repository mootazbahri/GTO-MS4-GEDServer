package com.talan.gto.GEDServer.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talan.gto.GEDServer.model.AdministrateurGed;
import com.talan.gto.GEDServer.repository.AdministrateurGEDRepository;
import com.talan.gto.GEDServer.repository.DocumentMonths;
import com.talan.gto.GEDServer.repository.DocumentParYear;
import com.talan.gto.GEDServer.repository.DocumentYear;
import com.talan.gto.GEDServer.service.AdministrateurGEDService;

@Service
public class AdministrateurGedServiceImpl implements AdministrateurGEDService{

	@Autowired
	AdministrateurGEDRepository administrateurGedRepository;
	
	
	@Override
	public List<AdministrateurGed> findAll() {
		return administrateurGedRepository.findAll();
	}

	@Override
	public Optional<AdministrateurGed> findById(Long id) {
		return administrateurGedRepository.findById(id);
	}

	@Override
	public AdministrateurGed save(AdministrateurGed liste) {
		return administrateurGedRepository.save(liste);
	}

	@Override
	public void deleteById(Long id) {
		administrateurGedRepository.deleteById(id);
	}

	@Override
	public Optional<AdministrateurGed> findByLogin(String login) {
		return administrateurGedRepository.findByLogin(login);
	}

	@Override
	public Boolean existsByLogin(String username) {
		return administrateurGedRepository.existsByLogin(username);
	}

	@Override
	public List<DocumentParYear> findIdDocumentGroupByYear(boolean sorted) {
		return administrateurGedRepository.findIdDocumentGroupByYear(sorted);
	}

	@Override
	public List<DocumentYear> findByYear(boolean sorted) {
		return administrateurGedRepository.findByYear(sorted);
	}

	@Override
	public List<DocumentMonths> findByMonth(int year, boolean sorted) {
		return administrateurGedRepository.findByMonth(year, sorted);
	}

}
