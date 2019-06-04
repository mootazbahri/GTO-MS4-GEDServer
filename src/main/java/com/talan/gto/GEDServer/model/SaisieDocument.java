package com.talan.gto.GEDServer.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="SAISIEDOCUMENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaisieDocument implements Serializable{

	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "saisie_document_Sequence")
    @SequenceGenerator(name = "saisie_document_Sequence", sequenceName = "SEQ_SAISIE_DOCUMENT")
    private Long id;
	
	@Column(name = "typeSaisie")
	private String typeSaisie;
	
	@Column(name = "quiEstMalade")
	private String quiEstMalade;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "dateNaissance")
	private Date dateNaissance;
	
	@Column(name = "ordenanceOuNon")
	private boolean ordenanceOuNon;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "dateEntree")
	private Date dateEntree;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "dateSortie")
	private Date dateSortie;
	
	@Column(name = "montant" , columnDefinition = "FLOAT(8,3)")
	private double montant;
	
	/*+---------------------------------+
	 *| if  ordenanceOuNon is true      |
	 *+---------------------------------+
	 */
	@Column(name = "montantordenance" , columnDefinition = "FLOAT(8,3)")
	private double montantordenance;
	
	//dentaire
	@Column(name = "soinOuProthese")
	private boolean soinOuProthese;
	
	@Column(name = "enfantOuAdult")
	private boolean enfantOuAdult;
	
	@Column(name = "dent")
	private int dent;
	
	@Column(name = "dents")
	private String dents;
	
	@Column(name = "montantTotal" , columnDefinition = "FLOAT(8,3)")
	private double montantTotal;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "document_id", nullable = false)
	private Document document;
}
