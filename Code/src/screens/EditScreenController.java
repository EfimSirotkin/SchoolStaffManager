package screens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.Main;


import java.net.URL;
import java.util.ResourceBundle;

public class EditScreenController implements Initializable, ControlledScreen {

    private ScreensController myController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    public void goToMainScreen(ActionEvent action) {
        myController.setScreen(Main.mainScreenID);
    }
}
