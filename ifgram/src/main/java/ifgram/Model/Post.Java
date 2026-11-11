package br.edu.ifpb.ifgram.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name ="tb_post" )
public class Post {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   @Column(nullable = false)
   private String imagemUrl;

   @Column(length = 300)
   private String legenda;

   private LocalDateTime criadoEm;

   public Post(String imagemUrl, String legenda) {
      this.criadoEm = LocalDateTime.now();
   }

   @ManyToOne(optional = false)
   @JoinColumn(name = "user_id")
   private String autor;

   public Post() {
   }
   public Post(String imagemUrl,String legenda , String autor ) {
   this.imagemUrl = imagemUrl;
   this.legenda = legenda;
   this.autor = autor;
   }
   public String getImagemUrl() {
      return imagemUrl;
   }
   public String getlegenda() {
      return legenda;
   }
   public String getautor() {
      return autor;
   }

}