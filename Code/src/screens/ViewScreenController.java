package screens;

import javafx.fxml.Initializable;

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
}