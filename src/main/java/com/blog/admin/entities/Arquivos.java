package com.blog.admin.entities;

import javax.persistence.*;

@Entity
@Table(name = "tb_arquivos")
public class Arquivos {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String name;

        private String type;

        private String filePath;

        public Arquivos() {
        }

        public Arquivos(Long id, String name, String type, String filePath) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.filePath = filePath;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }
}


