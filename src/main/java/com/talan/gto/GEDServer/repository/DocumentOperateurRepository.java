package com.talan.gto.GEDServer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talan.gto.GEDServer.model.DocumentOperateur;;


@Repository
public interface DocumentOperateurRepository  extends JpaRepository<DocumentOperateur, Long>{

}