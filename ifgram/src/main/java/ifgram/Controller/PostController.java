
package ifgram.Controller;

import ifgram.dto.PostRequest;
import ifgram.dto.PostResponse;
import ifgram.Service.PostService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

   
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse criar(@Valid @RequestBody PostRequest request) {
        return postService.criar(request);
    }

 
    @GetMapping
    public List<PostResponse> listarFeed() {
        return postService.listarFeed();
    }

   
    @GetMapping("/{id}")
    public PostResponse buscarPorId(@PathVariable Long id) {
        return postService.buscarPorId(id);
    }

    
    @GetMapping("/autor/{autorId}")
    public List<PostResponse> listarPorAutor(@PathVariable Long autorId) {
        return postService.listarPorAutor(autorId);
    }

    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        postService.deletar(id);
    }
}
