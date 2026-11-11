package ifgram.dto;

import ifgram.Model.Comment;
import java.time.LocalDateTime;

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