package screens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Main;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AnalyticScreenController implements Initializable, ControlledScreen {


    @FXML
    private ImageView imageView;

    private ScreensController myController;
    @FXML
    private PieChart qualificationDistributionPie = new PieChart();
    @FXML
    private BarChart hoursChargingBar;
    @FXML
    private LineChart higherEducationDynamicLine;
    @FXML
    private BarChart higherQualificationDistributionBar;
    @FXML
    NumberAxis xAxis = new NumberAxis();
    @FXML
    CategoryAxis yAxis = new CategoryAxis();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("F:\\Code\\SchoolStaffManager\\src\\screens\\res\\logo.jpg");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        xAxis.setLabel("Учитель");
        yAxis.setLabel("Часы");
        hoursChargingBar = new BarChart<>(xAxis, yAxis);
        XYChart.Series series1 = new XYChart.Series();
        series1.getData().add(new XYChart.Data("Ефим Сироткин", 90));
        hoursChargingBar.getData().add(series1);
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    @FXML
    public void goToMainScreen(ActionEvent action)
    {
        myController.setScreen(Main.mainScreenID);
    }

    @FXML
    public void generateAnalyticsGraphics()
    {

    }
    public CategoryAxis getCategoryAxis(String category) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(category);
        return xAxis;
    }
    public NumberAxis getNumberAxis(String category) {
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(category);
        return yAxis;
    }
}
