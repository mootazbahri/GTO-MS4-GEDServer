package com.talan.gto.GEDServer.service;

import java.util.List;
import java.util.Optional;

import com.talan.gto.GEDServer.model.AdministrateurGed;
import com.talan.gto.GEDServer.repository.DocumentMonths;
import com.talan.gto.GEDServer.repository.DocumentParYear;
import com.talan.gto.GEDServer.repository.DocumentYear;

public interface AdministrateurGEDService {
	List<AdministrateurGed> findAll();
	
	Optional<AdministrateurGed> findById(Long id);
	
	AdministrateurGed save(AdministrateurGed liste);

	void deleteById(Long id);
	
	Optional<AdministrateurGed> findByLogin(String login);
	
	Boolean existsByLogin(String username);
	
	List<DocumentParYear> findIdDocumentGroupByYear(boolean sorted);
	
	List<DocumentYear> findByYear(boolean sorted);
	
	List<DocumentMonths> findByMonth(int year,boolean sorted);
}
