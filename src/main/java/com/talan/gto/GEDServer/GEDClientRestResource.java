package com.talan.gto.GEDServer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import com.talan.gto.GEDServer.model.Document;
import com.talan.gto.GEDServer.model.DocumentClient;
import com.talan.gto.GEDServer.model.PrimaryKeyDC;
import com.talan.gto.GEDServer.payload.UploadFileResponse;
import com.talan.gto.GEDServer.repository.DocumentRepository;
import com.talan.gto.GEDServer.service.DocumentClientService;
import com.talan.gto.GEDServer.service.DocumentService;
import com.talan.gto.GEDServer.service.impl.FileStorageService;


@RestController
@EnableAutoConfiguration
@RequestMapping("/GED/Client")
public class GEDClientRestResource {
	private static final String userType = "Client";
	@Autowired
	private DocumentService documentService;
	@Autowired
	private DocumentClientService documentClientService;
	
	private static final Logger logger = LoggerFactory.getLogger(GEDClientRestResource.class);

    @Autowired
    private FileStorageService fileStorageService;
    

    
    /*
     * +---------------------------------------------------------------------------+
     * | input : File, Id client                                                   |
     * | Processing : Added the file to the DB to get an ID                        |
     * | then use that ID to store the file into the DB once ur done               |
     * | you can update the specific information to the file stored into           |
     * | the DB, the file will be stored under the uploads/Operateur folder        |
     * | and as and organisation point we need the create a folder with document ID|
     * | output : Document Object                                                  |
     * +---------------------------------------------------------------------------+
     */
    @PostMapping("/uploadFile")
    public Document uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("idClient") Long idClient) {
    	 /*
         * +---------------------------------------------------------------------------+
         * | create the Document Object in the DB to get the ID                        |
         * +---------------------------------------------------------------------------+
         */
    	Document d = documentService.save(new Document());
    	
    	try {
    		 /*
             * +---------------------------------------------------------------------------+
             * | upload the file into the server directory and return the accecible path   |
             * +---------------------------------------------------------------------------+
             */
	        String fileName = fileStorageService.storeFile(file,userType,d.getId());
	        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/GED/Client/downloadFile/"+d.getId()+"/")
	                .path(fileName)
	                .toUriString();
	        /*
             * +---------------------------------------------------------------------------+
             * | get the specific information of the file form the UploadFileResponse      |
             * | object then update the Document object with the specific information      |
             * | once that done update the Document object into the DB                     |
             * +---------------------------------------------------------------------------+
             */
	        UploadFileResponse response = new UploadFileResponse(fileName, fileDownloadUri,file.getContentType(), file.getSize());
	        d.setName(response.getFileName());
	        d.setUploadDir(response.getFileDownloadUri());
	        d.setSrcFile("uploads/Client/"+d.getId()+"/"+d.getName());
	        d.setUploadDate(new Date());
	        d.setSorted(false);
	        d = documentService.save(d);
	        /*
             * +---------------------------------------------------------------------------+
             * | now we need to add the link between the client and this document          |
             * +---------------------------------------------------------------------------+
             */
	        PrimaryKeyDC dcPrimary = new PrimaryKeyDC();
	        dcPrimary.setClient_id(idClient);
	        dcPrimary.setDocument(d);
	        DocumentClient dc = documentClientService.save(new DocumentClient(dcPrimary));
	        System.out.println("-------------------------------------------------------------");
	        System.out.println("| DocumentClient : "+dc);
	        System.out.println("-------------------------------------------------------------");
	        return d;
    	}
    	catch(Exception e) {
    		 /*
             * +------------------------------------------------------------------------------+
             * | in case there is a problem we need to delete the Document object from the DB |
             * +------------------------------------------------------------------------------+
             */
    		documentService.deleteById(d.getId());
    		return null;
    	}
    }

    @PostMapping("/uploadMultipleFiles")
    public List<Document> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,@RequestParam("idClient") Long idClient) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file,idClient))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{idDocument}/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, @PathVariable Long idDocument, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName,userType,"dd",idDocument,false);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
   

    @GetMapping("/downloadFile/{natureTache}/{idDocument}/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName,@PathVariable String natureTache, @PathVariable Long idDocument, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName,userType,natureTache,idDocument,true);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
   
    
    @GetMapping("/getDocument/{documentID}")
	Document getDocument(@PathVariable Long documentID) {
		return documentService.findById(documentID).get();
	}
    
    @GetMapping("/TriDocument/{documentID}/{natureDeTache}")
	Document TriDocument(@PathVariable Long documentID, @PathVariable String natureDeTache) {
    	Document document = documentService.findById(documentID).get();

    	boolean isMoved = this.fileStorageService.triFile(document.getSrcFile(), "Client", natureDeTache, document.getId(),document.getName());
    	
    	if(isMoved) {
    		document.setSrcFile("uploads/Client/"+ natureDeTache + "/" + document.getId()+"/"+document.getName());
    		 String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
 	                .path("/GED/Client/downloadFile/"+ natureDeTache + "/" + document.getId()+"/")
 	                .path(document.getName())
 	                .toUriString();
    		document.setUploadDir(fileDownloadUri);
    		document.setSorted(true);
    		documentService.save(document);
    	} 
    	
		return documentService.findById(documentID).get();
	}
}
