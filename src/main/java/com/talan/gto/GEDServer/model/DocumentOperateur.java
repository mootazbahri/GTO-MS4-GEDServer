package com.talan.gto.GEDServer.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="DOCUMENTOPERATEUR")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentOperateur implements Serializable{

	@EmbeddedId
	private PrimaryKeyDO id;
	
//    @Column(name = "operateur_id")
//    private long operateur_id;
	
//	@OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "document_id",insertable =  false, updatable = false)
//	private Document document;
}
