package main.mail;

public class CustomMessage {
    private String messageSubject;
    private String messageText;

    public CustomMessage() {

    }
    public CustomMessage(String subject, String text) {
        this.messageSubject = subject;
        this.messageText = text;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
