package main.screens;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import main.mail.CustomMessage;
import main.mail.MailSender;
import main.parsers.AlertWarner;

import javax.mail.MessagingException;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SendMessageScreenController implements Initializable {

    @FXML
    public Label senderAddress;
    @FXML
    public Label receiverAddress;
    @FXML
    public TextField messageThemeField;
    @FXML
    public TextArea messageMainTextField;
    @FXML
    public Button sendButton;
    @FXML
    public Button cancelButton;
    @FXML
    public ImageView imageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("res\\logo.jpg");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        senderAddress.setText("efim.sirotkin@gmail.com");
        receiverAddress.setText("ozi.bsuir.recovery@gmail.com");
    }

    @FXML
    private void onCancelButtonClicked() {
        Stage stage = (Stage) imageView.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onSendMessageButtonClicked() throws MessagingException {
        Stage stage = (Stage) imageView.getScene().getWindow();
        stage.setAlwaysOnTop(false);
        String messageTheme = messageThemeField.getText();
        String messageMainText = messageMainTextField.getText();
        String host = "localhost";

        if (!messageTheme.equals("") || !messageMainText.equals("")) {
            MailSender mailSender = new MailSender(senderAddress.getText(), receiverAddress.getText(), host);
            mailSender.setMessage(new CustomMessage(messageTheme, messageMainText));
            mailSender.sendMailMessage();
        } else {
            AlertWarner.showAlert("Отправка сообщения", "Тема письма или основной текст не указаны", "Проверьте введённые данные", Alert.AlertType.WARNING);
        }
    }

}
