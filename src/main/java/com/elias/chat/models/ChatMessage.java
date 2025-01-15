package com.elias.chat.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El emisor no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender; // Usuario que envía el mensaje

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver; // Usuario que recibe el mensaje (puede ser nulo si es un grupo)

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group; // Grupo al que va dirigido el mensaje (puede ser nulo si es directo)

    @NotBlank(message = "El contenido del mensaje no puede estar vacío")
    @Size(max = 1000, message = "El contenido del mensaje no puede exceder los 1000 caracteres")
    private String content; // Contenido del mensaje

    @NotNull(message = "La fecha y hora de envío no pueden ser nulas")
    private LocalDateTime timestamp; // Fecha y hora de envío

    // -- Constructores --

    public ChatMessage() {
        this.timestamp = LocalDateTime.now(); // Asigna la fecha y hora actual al crear un mensaje
    }

    public ChatMessage(User sender, User receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public ChatMessage(User sender, Group group, String content) {
        this.sender = sender;
        this.group = group;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    // -- Getters and Setters --

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // -- Métodos útiles --

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender=" + sender.getName() +
                ", receiver=" + (receiver != null ? receiver.getName() : "null") +
                ", group=" + (group != null ? group.getName() : "null") +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessage message = (ChatMessage) o;
        return Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
