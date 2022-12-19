package com.example.datn.controller;

import org.springframework.stereotype.Controller;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
    

    @PostMapping("/upload")
	@ResponseBody
	public String upload(MultipartFile file) {
		// processing file
		Path uploadPath = Paths.get("upload");
         
        try (InputStream inputStream = file.getInputStream()) {
        	//check exist
        	if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
        	String fileName = file.getOriginalFilename();
			assert fileName != null;
			fileName = fileName.substring(fileName.lastIndexOf('.'));
        	fileName = new Date().getTime() + fileName;
            Path filePath = uploadPath.resolve(fileName);
            
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return "{\"uploaded\": \"true\", \"url\" : \"/upload/" +fileName+ "\"}";
        } catch (Exception ioe) {
        	return "{\"uploaded\": \"false\", \"error\" : \"upload error" + ioe.getMessage() + "\"}";
        }
	}
	
	@GetMapping("/upload/{fileName}")
	public ResponseEntity<Resource> getUploadFile(@PathVariable String fileName) {
		Path uploadPath = Paths.get("upload");
		try {
			Path file = uploadPath.resolve(fileName);
			Resource resource = new UrlResource(file.toUri());
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"").body(resource);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
