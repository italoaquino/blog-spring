package com.blog.admin.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_post")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "${validacao.notblank}")
    private String tittle;

    @NotBlank(message = "${validacao.notblank}")
    private String text;

    @ManyToOne
    @JoinColumn(name="imagemNoticia_id")
    private Arquivos imagemNoticia;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    @CreationTimestamp
    private LocalDateTime datePost;

    public Post() {
    }

    public Post(Long id, String tittle, Arquivos imagemNoticia, String text, LocalDateTime datePost) {
        this.id = id;
        this.tittle = tittle;
        this.text = text;
        this.imagemNoticia = imagemNoticia;
        this.datePost = datePost;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Arquivos getImagemNoticia() {
        return imagemNoticia;
    }

    public void setImagemNoticia(Arquivos imagemNoticia) {
        this.imagemNoticia = imagemNoticia;
    }

    public LocalDateTime getDatePost() {
        return datePost;
    }

    public void setDatePost(LocalDateTime datePost) {
        this.datePost = datePost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", tittle='" + tittle + '\'' +
                ", text='" + text + '\'' +
                ", datePost=" + datePost +
                '}';
    }
}


