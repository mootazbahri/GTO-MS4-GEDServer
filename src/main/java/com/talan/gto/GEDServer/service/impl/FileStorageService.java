package com.talan.gto.GEDServer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.talan.gto.GEDServer.exception.FileStorageException;
import com.talan.gto.GEDServer.exception.MyFileNotFoundException;
import com.talan.gto.GEDServer.model.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;

	@Autowired
	public FileStorageService(Document fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
		// System.out.println("-------------------------------------------------------------");
		// System.out.println("| fileStorageLocation : "+this.fileStorageLocation);
		// System.out.println("-------------------------------------------------------------");

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	public String storeFile(MultipartFile file, String user, Long idDocument) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		// System.out.println("-------------------------------------------------------------");
		// System.out.println("| fileStorageLocation:
		// "+this.fileStorageLocation.toString());
		Path path = Paths.get(this.fileStorageLocation.toString(), user, idDocument + "");
		// System.out.println("| fileStorageLocation with Doc ID: "+path.toString());
		// System.out.println("| fileStorageLocation qq: "+fileName);
		// System.out.println("-------------------------------------------------------------");
		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			try {
				Files.createDirectories(path);

			} catch (Exception ex) {
				throw new FileStorageException(
						"Could not create the directory where the uploaded files will be stored.", ex);
			}
			Path targetLocation = path.resolve(fileName);
			// System.out.println("-------------------------------------------------------------");
			// System.out.println("| targetLocation : "+targetLocation.toString());
			// System.out.println("-------------------------------------------------------------");
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public Resource loadFileAsResource(String fileName, String user,String type, Long idDocument,boolean tri) {
		try {
			Path filePath;
			if(tri) {
				filePath = this.fileStorageLocation.resolve(user + "/" + type+ "/" + idDocument + "/" + fileName).normalize();
			} else {
				filePath = this.fileStorageLocation.resolve(user + "/" + idDocument + "/" + fileName).normalize();
			}
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}

	public boolean triFile(String file, String user, String type, Long idDocument, String fileName) {
		try {
			Path fileToMovePath = Paths.get(this.fileStorageLocation.toString(),user,idDocument + "" , fileName);
			Path targetPath = Paths.get(this.fileStorageLocation.toString(),user,type ,idDocument+"");

//			System.out.println("+---------------------------------------------------------+");
//			System.out.println("| fileToMovePlace : " + fileToMovePath.toString());
//			System.out.println("| targetPath : " + targetPath.toString());
//			System.out.println("| filetarget : " + this.fileStorageLocation.toString());
//			System.out.println("| fileToMovePlace NAme : " + fileToMovePath.getFileName());
//			System.out.println("+---------------------------------------------------------+");
			try {
				Files.createDirectories(targetPath);
			} catch (Exception ex) {
				throw new FileStorageException(
						"Could not create the directory where the uploaded files will be stored after the tri.", ex);
			}
			Files.move(fileToMovePath, targetPath.resolve(fileToMovePath.getFileName()));
			try {
				Files.delete(Paths.get(this.fileStorageLocation.toString(),user,idDocument+""));
			} catch (Exception ex) {
				throw new FileStorageException(
						"Could not delete the directory where the file was stored", ex);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}
}
