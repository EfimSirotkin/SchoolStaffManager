package main.mail;

import javafx.scene.control.Alert;
import main.parsers.AlertWarner;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {

    private String sourceAddress;
    private String destinationAddress;
    private String localHost;
    private CustomMessage message;


    public MailSender(String sourceAddress, String destinationAddress, String localHost) {
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.localHost = localHost;
    }

    public CustomMessage getMessage() {
        return message;
    }

    public void setMessage(CustomMessage message) {
        this.message = message;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }


    public void sendMailMessage() throws MessagingException {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myPassword = "";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(getSourceAddress(), myPassword);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(getSourceAddress()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(getDestinationAddress()));
            message.setSubject(getMessage().getMessageSubject());
            message.setText(getMessage().getMessageText());
            Transport.send(message);

            AlertWarner.showAlert("Отправка сообщения", "Собщение от " + getSourceAddress() + "\nк " + getDestinationAddress(), "Успешно отправлено.", Alert.AlertType.INFORMATION);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            AlertWarner.showAlert("Отправка сообщения", "Собщение от " + getSourceAddress() + "\nк " + getDestinationAddress(), "Невозможно отправить(проверьте подключение к Интернету).", Alert.AlertType.ERROR);
        }


    }
}

