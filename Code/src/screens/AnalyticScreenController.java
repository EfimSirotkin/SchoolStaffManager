package screens;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Main;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AnalyticScreenController implements Initializable, ControlledScreen {


    @FXML
    private ImageView imageView;

    private ScreensController myController;

    @FXML
    private PieChart qualificationDistributionPie;
    @FXML
    private BarChart<String, Number> hoursChargingBar;
    @FXML
    private LineChart higherEducationDynamicLine;
    @FXML
    private BarChart higherQualificationDistributionBar;

    @FXML
    private CategoryAxis xAxisHoursCharging;
    @FXML
    private NumberAxis yAxisHoursCharging;

    @FXML
    private CategoryAxis xAxisHigherEducation;
    @FXML
    private NumberAxis yAxisHigherEducation;
    @FXML
    private CategoryAxis xAxisHigherQualification;
    @FXML
    private NumberAxis yAxisHigherQualification;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("F:\\Code\\SchoolStaffManager\\src\\screens\\res\\logo.jpg");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);

        hoursChargingBar.getData().add(getSeriesDistribution());
        higherEducationDynamicLine.getData().add(getHigherEducationDistribution());
        higherQualificationDistributionBar.getData().add(getHigherQualificationDistribution());
        qualificationDistributionPie.setData(getPieChartData());

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
    public XYChart.Series<String, Number> getSeriesDistribution() {

        XYChart.Series<String, Number> tempSeries = new XYChart.Series<>();
        tempSeries.setName("2019");
        tempSeries.getData().add(new XYChart.Data<>("Андрей Демко", 100.0));
        tempSeries.getData().add(new XYChart.Data<>("Анастасия Шухова", 95.0));
        tempSeries.getData().add(new XYChart.Data<>("Иван Литвин", 75.0));
        tempSeries.getData().add(new XYChart.Data<>("Галина Шикова", 87.0));
        tempSeries.getData().add(new XYChart.Data<>("Мария Васильева", 58.0));
        tempSeries.getData().add(new XYChart.Data<>("Соня Назарова", 22));
        tempSeries.getData().add(new XYChart.Data<>("Инна Светлугина", 54.0));
        tempSeries.getData().add(new XYChart.Data<>("Елена Курило", 120.0));
        tempSeries.getData().add(new XYChart.Data<>("Юлия Шунейко", 27.0));
        tempSeries.getData().add(new XYChart.Data<>("Ефим Сироткин", 90.0));
        tempSeries.getData().add(new XYChart.Data<>("Анастасия Сущеня", 40.0));
        tempSeries.getData().add(new XYChart.Data<>("Елена Лукашевич", 80.0));

        return tempSeries;
    }
    public XYChart.Series<String, Number> getHigherEducationDistribution() {

        XYChart.Series<String, Number> tempSeries = new XYChart.Series<>();
        tempSeries.setName("Сотрудники");
        tempSeries.getData().add(new XYChart.Data<>("2011", 87.0));
        tempSeries.getData().add(new XYChart.Data<>("2012", 41.0));
        tempSeries.getData().add(new XYChart.Data<>("2013", 57.0));
        tempSeries.getData().add(new XYChart.Data<>("2014", 68.0));
        tempSeries.getData().add(new XYChart.Data<>("2015", 41.0));
        tempSeries.getData().add(new XYChart.Data<>("2016", 80.0));
        tempSeries.getData().add(new XYChart.Data<>("2017", 90.0));
        tempSeries.getData().add(new XYChart.Data<>("2018", 40.0));
        tempSeries.getData().add(new XYChart.Data<>("2019", 80.0));

        return tempSeries;
    }

    public XYChart.Series<String, Number> getHigherQualificationDistribution() {

        XYChart.Series<String, Number> tempSeries = new XYChart.Series<>();
        tempSeries.setName("Сотрудники");
        tempSeries.getData().add(new XYChart.Data<>("2011", 5.0));
        tempSeries.getData().add(new XYChart.Data<>("2012", 7.0));
        tempSeries.getData().add(new XYChart.Data<>("2013", 6.0));
        tempSeries.getData().add(new XYChart.Data<>("2014", 8.0));
        tempSeries.getData().add(new XYChart.Data<>("2015", 3.0));
        tempSeries.getData().add(new XYChart.Data<>("2016", 11.0));
        tempSeries.getData().add(new XYChart.Data<>("2017", 12.0));
        tempSeries.getData().add(new XYChart.Data<>("2018", 13.0));
        tempSeries.getData().add(new XYChart.Data<>("2019", 9.0));

        return tempSeries;
    }

    public ObservableList<PieChart.Data> getPieChartData() {

        ArrayList<PieChart.Data> arrayList = new ArrayList<>();

        arrayList.add(new PieChart.Data("Высшая", 10));
        arrayList.add(new PieChart.Data("Первая", 17));
        arrayList.add(new PieChart.Data("Вторая", 12));
        arrayList.add(new PieChart.Data("Без категории", 4));

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(arrayList);

        return pieChartData;

    }

}
