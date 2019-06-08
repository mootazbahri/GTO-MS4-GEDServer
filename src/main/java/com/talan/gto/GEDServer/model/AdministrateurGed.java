package com.talan.gto.GEDServer.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="ADMINISTRATEURGED")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministrateurGed {
	
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "administrateur_Sequence")
    @SequenceGenerator(name = "administrateur_Sequence", sequenceName = "SEQADMIN")
    private Long id;
	
	@Column(name = "LOGIN")
	private String login;
	
	@Column(name = "PASSWORD")
	private String password;
}
