package ifgram.dto;

import java.time.LocalDateTime;

import ifgram.model.Comment;

public record CommentResponse(

    Long id,
    String texto,
    LocalDateTime criadoEm,
    String autorNome,
    Long postId

) {

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
            comment.getId(),
            comment.getTexto(),
            comment.getCriadoEm(),
            comment.getAutor().getNome(),
            comment.getPost().getId()
        );
    }

}