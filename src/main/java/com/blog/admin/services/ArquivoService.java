package com.blog.admin.services;

import com.blog.admin.entities.Arquivos;
import com.blog.admin.repositories.ArquivoRepository;
import com.blog.admin.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class ArquivoService {

    @Autowired
    private ArquivoRepository repo;

    @Value("${FOLDER_PATH}")
    private String FOLDER_PATH;

    public Arquivos uploadImagemToFileSystem(MultipartFile file) throws IOException {

        String pathFile=FOLDER_PATH+file.getOriginalFilename();

        Arquivos imagemData = repo.save(new Arquivos(null, file.getOriginalFilename(), file.getContentType(), pathFile));

        file.transferTo(new File(pathFile));

        if(imagemData != null){
            return imagemData;
        }

        return null;

    }
    public Arquivos find(String name) {

        Arquivos imagem = repo.findByName(name)
                .orElseThrow(() -> new ObjectNotFoundException("Imagem não encontrada!"));

        return imagem;
    }

    public byte[] findFile(String name) throws IOException {

        Optional<Arquivos> arquivos = Optional
                .ofNullable(repo.findByName(name)
                        .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!")));

        String pathName = arquivos.get().getFilePath();

        byte[] images = Files.readAllBytes(new File(pathName).toPath());

        return images;

    }


}
