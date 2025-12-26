package com.elias.chat.repositories;

import com.elias.chat.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    List<User> findByOnline(boolean online);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    // NOTE: Save a new user (inherited from JpaRepository)
    // No need to explicitly define it, as JpaRepository provides save() method
}
