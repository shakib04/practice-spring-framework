package learning.practice.repository;

import learning.practice.model.User;
import learning.practice.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

