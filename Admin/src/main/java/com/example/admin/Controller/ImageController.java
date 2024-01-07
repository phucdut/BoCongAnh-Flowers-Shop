package com.example.admin.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImageController {

    @Value("${imagePath}")
    private String imagePath;

    @Value("${imagePathCustomer}")
    private String imagePathCustomer;


    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
        try {
//            System.out.println(filename);
            Path path = Paths.get(imagePath, filename);
//            System.out.println(path);
            Resource file = new UrlResource(path.toUri());
//            System.out.println(file);

            if (file.exists() || file.isReadable()) {
                return ResponseEntity.ok().body(file);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/customer/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            System.out.println("image: " + imagePathCustomer);
            Path path = Paths.get(imagePathCustomer, filename);
            Resource file = new UrlResource(path.toUri());

            if (file.exists() || file.isReadable()) {
                return ResponseEntity.ok().body(file);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

