package com.redsegura.models;

public class MessageModel {
    private String sender;
    private String receiver;
    private String content;
    private String timestamp;
    private boolean fromAI;

    public MessageModel() {}

    public MessageModel(String sender, String receiver, String content, String timestamp, boolean fromAI) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = timestamp;
        this.fromAI = fromAI;
    }

    public String getSender() { return sender; }

    public void setSender(String sender) { this.sender = sender; }

    public String getReceiver() { return receiver; }

    public void setReceiver(String receiver) { this.receiver = receiver; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getTimestamp() { return timestamp; }

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public boolean isFromAI() { return fromAI; }

    public void setFromAI(boolean fromAI) { this.fromAI = fromAI; }
}
