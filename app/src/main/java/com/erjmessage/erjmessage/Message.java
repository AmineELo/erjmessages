package com.erjmessage.erjmessage;

public class Message {

    private String messageId, sender, receiver, messageSubject, messageBody, messageTimeStamp;

    public Message(String messageId, String sender, String receiver, String messageSubject, String messageBody, String messageTimeStamp) {
        this.messageId = messageId;
        this.sender = sender;
        this.receiver = receiver;
        this.messageSubject = messageSubject;
        this.messageBody = messageBody;
        this.messageTimeStamp = messageTimeStamp;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public String getMessageTimeStamp() {
        return messageTimeStamp;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public void setMessageTimeStamp(String messageTimeStamp) {
        this.messageTimeStamp = messageTimeStamp;
    }
}
