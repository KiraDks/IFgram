
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

import ifgram.dto.CommentRequest;
import ifgram.dto.CommentResponse;
import ifgram.service.CommentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse criar(@Valid @RequestBody CommentRequest request) {
        return commentService.criar(request);
    }

   
    @GetMapping("/post/{postId}")
    public List<CommentResponse> listarPorPost(@PathVariable Long postId) {
        return commentService.listarPorPost(postId);
    }

    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        commentService.deletar(id);
    }
}
