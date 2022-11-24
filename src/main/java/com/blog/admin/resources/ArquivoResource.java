package com.blog.admin.resources;

import com.blog.admin.entities.Arquivos;
import com.blog.admin.services.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/v1/arquivo")
public class ArquivoResource {

    @Autowired
    private ArquivoService arquivoService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<?> uploadImagem(@RequestParam("image")MultipartFile file) throws IOException {

        Arquivos uploadImage = arquivoService.uploadImagemToFileSystem(file);
        return new ResponseEntity<>(uploadImage, HttpStatus.ACCEPTED);

    }

    @RequestMapping(value = "{fileName}", method = RequestMethod.GET)
    public ResponseEntity<?> getImagem(@PathVariable String fileName) throws IOException {

        byte[] file = arquivoService.findFile(fileName);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(file);
    }
}
