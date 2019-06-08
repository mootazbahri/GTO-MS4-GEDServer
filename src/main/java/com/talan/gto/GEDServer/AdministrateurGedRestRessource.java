package com.talan.gto.GEDServer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.talan.gto.GEDServer.model.AdministrateurGed;
import com.talan.gto.GEDServer.model.Document;
import com.talan.gto.GEDServer.model.SaisieDocument;
import com.talan.gto.GEDServer.repository.DocumentMonths;
import com.talan.gto.GEDServer.repository.DocumentParYear;
import com.talan.gto.GEDServer.repository.DocumentYear;
import com.talan.gto.GEDServer.service.AdministrateurGEDService;
import com.talan.gto.GEDServer.service.DocumentService;

@RestController
@EnableAutoConfiguration
@RequestMapping("/AdministrateurGed")
public class AdministrateurGedRestRessource {

	@Autowired
	AdministrateurGEDService administrateurGedService;
	
	@Autowired
	DocumentService documentService;
	
	@PostMapping(value="/login")
    public AdministrateurGed login(@RequestParam("login") String login,@RequestParam("password") String password) {	
		if(administrateurGedService.existsByLogin(login)) {
			AdministrateurGed admin = administrateurGedService.findByLogin(login).get();
			if(this.verifyHash(password, admin.getPassword()))
				return admin;
			else 
				return null;
		}
		else {
			return null;
		}
    }
	
	public String hash(String password) {
	    return BCrypt.hashpw(password, BCrypt.gensalt(10));
	}

	public boolean verifyHash(String password, String hash) {
	    return BCrypt.checkpw(password, hash);
	}
	
	@GetMapping(value="/getDocumentByYear/{sorted}")
    public List<DocumentParYear> getDocumentByYear(@PathVariable boolean sorted) {	
		List<DocumentParYear> liste = administrateurGedService.findIdDocumentGroupByYear(sorted);
		return liste;
    }
	
	@GetMapping(value="/getDocumentYears/{sorted}")
    public List<DocumentYear> getDocumentYears(@PathVariable boolean sorted) {	
		List<DocumentYear> liste = administrateurGedService.findByYear(sorted);
		return liste;
    }
	
	@GetMapping(value="/getDocumentMonths/{sorted}/{year}")
    public List<DocumentMonths> getDocumentMonths(@PathVariable boolean sorted,@PathVariable int year) {	
		List<DocumentMonths> liste = administrateurGedService.findByMonth(year,sorted);
		return liste;
    }
}
