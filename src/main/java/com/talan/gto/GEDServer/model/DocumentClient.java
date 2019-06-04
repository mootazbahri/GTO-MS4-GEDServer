package com.talan.gto.GEDServer.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="DOCUMENTCLIENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentClient implements Serializable{
	
	@EmbeddedId
	private PrimaryKeyDC id;

//    @Column(name = "client_id")
//    private long client_id;
    
//	@OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "document_id",insertable =  false, updatable = false)
//	private Document document;
}
