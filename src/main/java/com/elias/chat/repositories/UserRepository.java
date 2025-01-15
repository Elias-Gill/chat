package com.elias.chat.repositories;

import com.elias.chat.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user by email
    Optional<User> findByEmail(String email);

    // Find all users by online status
    List<User> findByOnline(boolean online);

    // Find users by name (case-insensitive search)
    List<User> findByNameContainingIgnoreCase(String name);
}
