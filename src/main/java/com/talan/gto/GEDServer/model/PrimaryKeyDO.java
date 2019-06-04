package com.talan.gto.GEDServer.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Embeddable
public class PrimaryKeyDO implements Serializable {
//	@Column(name = "document_id")
//    private long document_id;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id",insertable =  false, updatable = false)
	private Document document;
	
	@Column(name = "operateur_id")
    private long operateur_id;
}
