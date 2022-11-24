package com.blog.admin.services;

import com.blog.admin.entities.Arquivos;
import com.blog.admin.entities.Post;
import com.blog.admin.repositories.PostRepository;
import com.blog.admin.services.exceptions.DataIntegrityException;
import com.blog.admin.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ArquivoService arquivoService;

    public List<Post> listProducts(){
        return postRepository.findAll();
    }

    public Post findProduct(Long id){
        return postRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Não encontrado id : " + id));
    }

    public Post savePost(Post post){

        Post obj = new Post();
        obj.setId(null);
        obj.setTittle(post.getTittle());
        obj.setDatePost(post.getDatePost());
        obj.setText(post.getText());

        Arquivos arquivo =  arquivoService.find(post.getImagemNoticia().getName());
        obj.setImagemNoticia(arquivo);

        return postRepository.save(obj);

    }


    public Post update(Post post, Long id){

        Post obj = findProduct(id);

        obj.setTittle(post.getTittle());
        obj.setDatePost(post.getDatePost());
        obj.setText(post.getText());
        obj.setImagemNoticia(post.getImagemNoticia());

        return postRepository.save(obj);

    }

    public Post delete(Long id){

        Post obj = findProduct(id);

        try{
            postRepository.delete(obj);
        }catch(DataIntegrityViolationException dataIntegrityViolationException){
            throw new DataIntegrityException("Erro ao deletar");
        }
        return obj;

    }


}
