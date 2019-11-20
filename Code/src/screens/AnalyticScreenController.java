package screens;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Main;
import main.databases.PedagogicalDB;
import main.parsers.ExcelParser;
import main.staff.Teacher;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

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

    private PedagogicalDB pedagogicalDB;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("F:\\Code\\SchoolStaffManager\\src\\screens\\res\\logo.jpg");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        hoursChargingBar.getData().add(getSeriesDistribution());
        higherEducationDynamicLine.getData().add(getHigherEducationDistribution());
        higherQualificationDistributionBar.getData().add(getHigherQualificationDistribution());
        qualificationDistributionPie.setData(getPieChartData());
        hoursChargingBar.setBarGap(3);;

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
        hoursChargingBar.getData().clear();
        hoursChargingBar.getData().add(getSeriesDistribution());
        qualificationDistributionPie.getData().clear();
        qualificationDistributionPie.setData(getPieChartData());
    }
    public XYChart.Series<String, Number> getSeriesDistribution() {
        ExcelParser excelParser = new ExcelParser();
        XYChart.Series<String, Number> tempSeries = new XYChart.Series<>();
        pedagogicalDB = new PedagogicalDB(excelParser.importPedagogicalTemplate("F:\\Code\\SchoolStaffManager\\res\\Шаблон(Преподавательский).xls"));
        tempSeries.setName("2019");
        for(Teacher teacher : pedagogicalDB.getPedagogicalStaff()) {
            String fullName = teacher.getName() + " " + teacher.getSurname();

            tempSeries.getData().add(new XYChart.Data<>(fullName, teacher.getWeeklyTeachingHours()));
        }
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
        ArrayList<Teacher> teacherList = pedagogicalDB.getPedagogicalStaff();
        ArrayList<String> qualificationPresents = pedagogicalDB.getPresentQualifications();
        ArrayList<Integer> qualificationCount = pedagogicalDB.getCertainQualificationsCount();

        for(int i =0; i < qualificationPresents.size(); i++)
            arrayList.add(new PieChart.Data(qualificationPresents.get(i), qualificationCount.get(i)));

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(arrayList);

        return pieChartData;

    }



}
