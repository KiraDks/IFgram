
package ifgram.Service;

import ifgram.dto.PostRequest;
import ifgram.dto.PostResponse;
import ifgram.Model.Post;
import ifgram.Model.User;
import ifgram.Repository.PostRepository;
import ifgram.Repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(
            PostRepository postRepository,
            UserRepository userRepository) {

        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

  
    @Transactional
    public PostResponse criar(PostRequest request) {

        User autor = userRepository.findById(request.autorId())
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Não foi possível criar a publicação: usuário não encontrado."
                ));

        Post post = new Post(
            request.imagemUrl(),
            request.legenda(),
            autor
        );

        Post salvo = postRepository.save(post);

        return PostResponse.from(salvo);
    }


    public List<PostResponse> listarFeed() {

        return postRepository.buscarFeed()
                .stream()
                .map(PostResponse::from)
                .toList();
    }


    public PostResponse buscarPorId(Long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Publicação não encontrada."
                ));

        return PostResponse.from(post);
    }

   
    public List<PostResponse> listarPorAutor(Long autorId) {

        if (!userRepository.existsById(autorId)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Usuário não encontrado."
            );
        }

        return postRepository.findByAutorId(autorId)
                .stream()
                .map(PostResponse::from)
                .toList();
    }


    @Transactional
    public void deletar(Long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Publicação não encontrada."
                ));

        postRepository.delete(post);
    }
}
