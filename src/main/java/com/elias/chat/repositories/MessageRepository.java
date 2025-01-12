package com.elias.chat.repositories;

import com.elias.chat.models.*;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.*;

@Repository
public interface MessageRepository extends JpaRepository<ChatMessage, Long> {
}
