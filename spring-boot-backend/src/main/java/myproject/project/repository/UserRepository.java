package myproject.project.repository;

import myproject.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query("select u from User u where u.id = ?1")
    User findByUserId(Long id);
    boolean existsByEmail(String email);
}
