package ifgram.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String texto;

    private LocalDateTime criadoEm;

    @ManyToOne(optional = false)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User autor;

    public Comment() {
    }

    public Comment(String texto, Post post, User autor) {
        this.texto = texto;
        this.post = post;
        this.autor = autor;
        this.criadoEm = LocalDateTime.now();
    }

    public Long getId() {
        return this.id;
    }

    public String getTexto() {
        return this.texto;
    }

    public LocalDateTime getCriadoEm() {
        return this.criadoEm;
    }

    public Post getPost() {
        return this.post;
    }

    public User getAutor() {
        return this.autor;
    }
}