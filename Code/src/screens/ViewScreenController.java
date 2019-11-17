package screens;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.Main;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewScreenController implements Initializable, ControlledScreen {

    private ScreensController myController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void goToMainScreen(ActionEvent event) {
        myController.setScreen(Main.mainScreenID);
    }
}
