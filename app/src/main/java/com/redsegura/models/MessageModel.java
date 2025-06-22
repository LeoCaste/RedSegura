package com.redsegura.models;

public class MessageModel {
    private String sender;
    private String to;
    private String message;
    private long timestamp;
    private boolean fromAI;

    public MessageModel() {}

    public MessageModel(String sender, String to, String message, long timestamp, boolean fromAI) {
        this.sender = sender;
        this.to = to;
        this.message = message;
        this.timestamp = timestamp;
        this.fromAI = fromAI;
    }

    // Constructor simplificado para UI
    public MessageModel(String sender, String message, long timestamp) {
        this.sender = sender;
        this.to = "";
        this.message = message;
        this.timestamp = timestamp;
        this.fromAI = false;
    }

    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }

    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

    public boolean isFromAI() { return fromAI; }
    public void setFromAI(boolean fromAI) { this.fromAI = fromAI; }
}
