package com.talan.gto.GEDServer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.talan.gto.GEDServer.model.AdministrateurGed;


@Repository
public interface AdministrateurGEDRepository   extends JpaRepository<AdministrateurGed, Long>{
	Optional<AdministrateurGed> findByLogin(String login);
	Boolean existsByLogin(String username);
	@Query("SELECT " +
	           "    new com.talan.gto.GEDServer.repository.DocumentParYear(d.id, EXTRACT(year FROM uploadDate) as year, EXTRACT(month FROM uploadDate) as month, d.srcFile, d.uploadDir) " +
	           "FROM " +
	           "    Document d "+
	           "WHERE " +
	           "    d.sorted = ?1")
	 List<DocumentParYear> findIdDocumentGroupByYear(boolean sorted);
	
	@Query("SELECT " +
	           "   DISTINCT new com.talan.gto.GEDServer.repository.DocumentYear(EXTRACT(year FROM uploadDate) as year) " +
	           "FROM " +
	           "    Document d "+
	           "WHERE " +
	           "    d.sorted = ?1")
	 List<DocumentYear> findByYear(boolean sorted);
	
	@Query("SELECT " +
	           "   DISTINCT new com.talan.gto.GEDServer.repository.DocumentMonths(EXTRACT(month FROM uploadDate) as month) " +
	           "FROM " +
	           "    Document d " +
	           "WHERE " +
	           "    EXTRACT(year FROM uploadDate) = ?1"+
	           "    AND d.sorted = ?2")
	 List<DocumentMonths> findByMonth(int year,boolean sorted);
}
