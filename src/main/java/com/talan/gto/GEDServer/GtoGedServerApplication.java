package com.talan.gto.GEDServer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.talan.gto.GEDServer.model.AdministrateurGed;
import com.talan.gto.GEDServer.model.Document;
import com.talan.gto.GEDServer.model.SaisieDocument;
import com.talan.gto.GEDServer.service.AdministrateurGEDService;
import com.talan.gto.GEDServer.service.SaisieDocumentService;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties({
    Document.class
})
public class GtoGedServerApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(GtoGedServerApplication.class, args);
	}

	@Autowired
	private AdministrateurGEDService administrateurGEDService;
	
	@Override
	public void run(String... args) throws Exception {
		administrateurGEDService.save(new AdministrateurGed(1L,"AdminGED",this.hash("123456789")));
	}
	
	
	public String hash(String password) {
	    return BCrypt.hashpw(password, BCrypt.gensalt(10));
	}

	public boolean verifyHash(String password, String hash) {
	    return BCrypt.checkpw(password, hash);
	}
}
