package screens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import main.Main;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;



public class MainScreenController implements Initializable,  ControlledScreen{
    @FXML
    private ImageView imageView;


    private ScreensController myController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("F:\\Code\\SchoolStaffManager\\src\\screens\\res\\logo.jpg");
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
    private void goToEditScreen(ActionEvent event) {
        myController.setScreen(Main.editScreenID);
    }

}
