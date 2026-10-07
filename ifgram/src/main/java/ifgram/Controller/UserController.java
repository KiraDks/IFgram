package ifgram.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifgram.Service.UserService;
import ifgram.dto.UserRequest;
import ifgram.dto.UserResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.listarUsuarios();
    }

    @PostMapping
    public ResponseEntity<UserResponse> postUser(
            @Valid @RequestBody UserRequest request) {

        UserResponse response = userService.criarUsuario(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/email/{email}")
    public UserResponse getUserByEmail(
            @PathVariable String email) {

        return userService.buscarPorEmail(email);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id) {

        userService.deletarUsuario(id);

        return ResponseEntity.noContent().build();
    }
}