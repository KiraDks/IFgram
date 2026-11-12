
package ifgram.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ifgram.dto.PostRequest;
import ifgram.dto.PostResponse;
import ifgram.service.PostService;
import jakarta.validation.Valid;

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
