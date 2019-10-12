package energy.viridis.exercise.repository;

import energy.viridis.exercise.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Cacheable("users")
    Optional<User> findByUsername(String username);
}
