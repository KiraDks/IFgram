package ifgram.Repository;

import br.edu.ifpb.ifgram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    List<User> findByNomeContainingIgnoreCase(String trecho);

    @Query("SELECT u FROM User u WHERE u.email LIKE CONCAT('%', :dominio)")
    List<User> doDominio(String dominio);

}