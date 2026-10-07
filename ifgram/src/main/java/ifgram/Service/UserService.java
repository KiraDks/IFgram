package ifgram.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import ifgram.Model.User;
import ifgram.Repository.UserRepository;
import ifgram.dto.UserRequest;
import ifgram.dto.UserResponse;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> listarUsuarios() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::from)
                .toList();
    }

    public UserResponse criarUsuario(UserRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Já existe um usuário com esse email.");
        }

        User user = new User(
                request.nome(),
                request.email()
        );

        User usuarioSalvo = userRepository.save(user);

        return UserResponse.from(usuarioSalvo);
    }

    public UserResponse buscarPorEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado.")
                );

        return UserResponse.from(user);
    }

    public void deletarUsuario(Long id) {

        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado.");
        }

        userRepository.deleteById(id);
    }
}