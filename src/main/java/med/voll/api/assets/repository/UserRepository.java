package med.voll.api.assets.repository;

import med.voll.api.assets.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByUsername(String username);
}
