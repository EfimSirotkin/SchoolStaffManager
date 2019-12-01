package main.screens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;
import main.Main;
import main.parsers.AlertWarner;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class MainScreenController implements Initializable, ControlledScreen {

    @FXML
    private Button editScreenButton = new Button();
    @FXML
    private Button viewScreenButton;
    @FXML
    private Button analyticsScreenButton;
    @FXML
    private ImageView imageView;
    private ScreensController myController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("res/logo.jpg");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void goToViewScreen(ActionEvent event) {
        myController.setScreen(Main.viewScreenID);
    }

    @FXML
    private void goToAnalyticScreen(ActionEvent event) {
        myController.setScreen(Main.analyticScreenID);
    }

    @FXML
    private void goToEditScreen(ActionEvent event)
    {
        if(LoginScreenController.isAdministrator)
        myController.setScreen(Main.editScreenID);
        else {
            editScreenButton.setDisable(true);
            AlertWarner.showAlert("Права доступа", "Редактировать записи могут только работники администрации", "Приносим свои извинения", Alert.AlertType.INFORMATION);
        }
    }

}
