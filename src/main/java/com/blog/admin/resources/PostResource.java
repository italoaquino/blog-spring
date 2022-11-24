package com.blog.admin.resources;

import com.blog.admin.entities.Post;
import com.blog.admin.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findAll(){
        return new ResponseEntity<List<Post>>(postService.listProducts(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id){
        return new ResponseEntity<Post>(postService.findProduct(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Post> findById(@Valid @RequestBody Post post){
        return new ResponseEntity<Post>(postService.savePost(post), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public ResponseEntity<Post> update(@Valid @RequestBody Post post, @PathVariable Long id){
        return new ResponseEntity<Post>(postService.update(post, id), HttpStatus.ACCEPTED);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public ResponseEntity<Post> delete(@PathVariable Long id){
        return new ResponseEntity<Post>(postService.delete(id), HttpStatus.NO_CONTENT);
    }

}
