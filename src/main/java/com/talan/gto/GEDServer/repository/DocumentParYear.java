package com.talan.gto.GEDServer.repository;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DocumentParYear {
	private Long id;
	
	private int year;
	
	private int month;
	
	private String srcFile;
	
	private String uploadDir;
}
