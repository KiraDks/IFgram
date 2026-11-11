
package ifgram.Service;

import ifgram.dto.CommentRequest;
import ifgram.dto.CommentResponse;
import ifgram.model.Comment;
import ifgram.Model.Post;
import ifgram.Model.User;
import ifgram.Repository.CommentRepository;
import ifgram.Repository.PostRepository;
import ifgram.Repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(
            CommentRepository commentRepository,
            PostRepository postRepository,
            UserRepository userRepository) {

        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

  
    @Transactional
    public CommentResponse criar(CommentRequest request) {

        if (request.texto() == null || request.texto().isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "O texto do comentário não pode ficar em branco."
            );
        }

        Post post = postRepository.findById(request.postId())
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Não foi possível comentar: publicação não encontrada."
                ));

        User autor = userRepository.findById(request.autorId())
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Não foi possível comentar: usuário não encontrado."
                ));

        Comment comentario = new Comment(
            request.texto(),
            post,
            autor
        );

        Comment salvo = commentRepository.save(comentario);

        return CommentResponse.from(salvo);
    }

   
    public List<CommentResponse> listarPorPost(Long postId) {

        if (!postRepository.existsById(postId)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Publicação não encontrada."
            );
        }

        return commentRepository.findByPostIdOrderByCriadoEmAsc(postId)
                .stream()
                .map(CommentResponse::from)
                .toList();
    }

   
    @Transactional
    public void deletar(Long id) {

        Comment comentario = commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Comentário não encontrado."
                ));

        commentRepository.delete(comentario);
    }
}
