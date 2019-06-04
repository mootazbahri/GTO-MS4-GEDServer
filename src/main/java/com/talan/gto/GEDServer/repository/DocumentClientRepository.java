package com.talan.gto.GEDServer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.talan.gto.GEDServer.model.DocumentClient;


@Repository
public interface DocumentClientRepository  extends JpaRepository<DocumentClient, Long>{

}