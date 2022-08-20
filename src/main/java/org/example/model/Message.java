package org.example.model;

public class Message{
    private String definition;
    private int statusCode;
    private Object data;

    public Message(String definition, int statusCode) {
        this.definition = definition;
        this.statusCode = statusCode;
    }

    public Message(String definition, int statusCode, Object data) {
        this.definition = definition;
        this.statusCode = statusCode;
        this.data = data;
    }

    public Message(Message message, Object data) {
        this.statusCode =  message.getStatusCode();
        this.definition = message.getDefinition();
        this.data = data;
    }



    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
