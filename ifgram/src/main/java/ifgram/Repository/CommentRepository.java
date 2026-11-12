package ifgram.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifgram.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostIdOrderByCriadoEmAsc(Long postId);

    long countByPostId(Long postId);

    List<Comment> findByAutorId(Long autorId);

}