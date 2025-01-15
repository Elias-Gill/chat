package com.elias.chat.repositories;

import com.elias.chat.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    // Find a group by name
    Optional<Group> findByName(String name);

    // Find all groups that a specific user is a member of
    @Query("SELECT g FROM ChatGroup g JOIN g.members m WHERE m.id = :userId")
    List<Group> findChatGroupsByUserId(Long userId);

    // Find all groups with a specific name (case-insensitive search)
    List<Group> findByNameContainingIgnoreCase(String name);
}
