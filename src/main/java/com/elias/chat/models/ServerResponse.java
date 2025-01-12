package com.elias.chat.models;

public class ServerResponse {

    private String message;

    public ServerResponse() {
    }

    public ServerResponse(String name) {
        this.message = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String name) {
        this.message = name;
    }

    public String ToString(String name) {
        return "New Message: " + this.message;
    }
}
