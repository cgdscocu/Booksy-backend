package com.booksy.repository;

import com.booksy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, UUID> {
    // Buraya özel sorgular ekleyebilirsin, şimdilik boş bırakabilirsin
    Optional<User> findByEmail(String email);
}
