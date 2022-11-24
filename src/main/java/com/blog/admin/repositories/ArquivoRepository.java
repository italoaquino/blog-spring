package com.blog.admin.repositories;

import com.blog.admin.entities.Arquivos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArquivoRepository extends JpaRepository<Arquivos, Long> {
    Optional<Arquivos> findByName(String name);
}
