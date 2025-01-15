package com.elias.chat.repositories;

import com.elias.chat.models.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<ChatMessage, Long> {

    // Find all messages sent by a specific user
    List<ChatMessage> findBySenderId(Long senderId);

    // Find all messages received by a specific user
    List<ChatMessage> findByReceiverId(Long receiverId);

    // Find all messages sent to a specific group
    List<ChatMessage> findByGroupId(Long groupId);

    // Find all messages between two users (direct messages)
    @Query("SELECT m FROM ChatMessage m WHERE (m.sender.id = :user1Id AND m.receiver.id = :user2Id) OR (m.sender.id = :user2Id AND m.receiver.id = :user1Id)")
    List<ChatMessage> findDirectChatMessagesBetweenUsers(Long user1Id, Long user2Id);
}
