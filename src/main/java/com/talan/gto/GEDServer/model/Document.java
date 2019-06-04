package com.talan.gto.GEDServer.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="DOCUMENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "file")
public class Document implements Serializable{
	
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "document_Sequence")
    @SequenceGenerator(name = "document_Sequence", sequenceName = "SEQ_DOCUMENT")
    private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "uploadDir")
	private String uploadDir;
	
	@Column(name = "src_File")
	private String srcFile;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "uploadDate")
	private Date uploadDate;
	
	@Column(name = "uploadDirArchive")
	private String uploadDirArchive;
	
	@Column(name = "archived")
	private boolean archived;
	
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "document")
	private SaisieDocument saisieDocument;
}
