package com.talan.gto.GEDServer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.talan.gto.GEDServer.model.Document;
import com.talan.gto.GEDServer.model.SaisieDocument;
import com.talan.gto.GEDServer.service.SaisieDocumentService;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties({
    Document.class
})
public class GtoGedServerApplication{

	public static void main(String[] args) {
		SpringApplication.run(GtoGedServerApplication.class, args);
	}

	/*@Autowired
	private SaisieDocumentService saisieDocumentService;
	 implements CommandLineRunner
	@Override
	public void run(String... args) throws Exception {
		saisieDocumentService.save(new SaisieDocument(1L, "dd", "dddddd", new Date(), true, new Date(), new Date(), 2145, 1452, false, false, 12, "dd", 125487, null));
		
	}*/

}
