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
import main.staff.TeacherStatistics;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("F:\\Code\\SchoolStaffManager\\src\\screens\\res\\logo.jpg");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        hoursChargingBar.getData().add(getSeriesDistribution());
        higherEducationDynamicLine.getData().add(getHigherEducationDistribution());
        higherQualificationDistributionBar.getData().add(getHigherQualificationDistribution());
        qualificationDistributionPie.setData(getPieChartData());
        hoursChargingBar.setBarGap(3);

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    public void goToMainScreen(ActionEvent action) {
        myController.setScreen(Main.mainScreenID);
    }

    @FXML
    public void generateAnalyticsGraphics() {
        PieChartCreator pieChartCreator = new PieChartCreator();
        HoursBarCreator hoursBarCreator = new HoursBarCreator();
        HigherEducationLineCreator higherEducationLineCreator = new HigherEducationLineCreator();
        HigherQualificationLineCreator higherQualificationLineCreator = new HigherQualificationLineCreator();
        pieChartCreator.run();
        hoursBarCreator.run();
        higherEducationLineCreator.run();
        higherQualificationLineCreator.run();
    }

    public XYChart.Series<String, Number> getSeriesDistribution() {
        XYChart.Series<String, Number> tempSeries = new XYChart.Series<>();
        tempSeries.setName("2019");
        for (Teacher teacher : Main.personDB.getPedagogicalDB().getPedagogicalStaff()) {
            String fullName = teacher.getName() + " " + teacher.getSurname();
            tempSeries.getData().add(new XYChart.Data<>(fullName, teacher.getWeeklyTeachingHours()));
        }
        return tempSeries;
    }

    public XYChart.Series<String, Number> getHigherEducationDistribution() {

        ExcelParser excelParser = new ExcelParser();
        TeacherStatistics teacherStatistics = excelParser.importStatisticsData("F:\\Code\\SchoolStaffManager\\res\\Динамика.xls");
        ArrayList<String> statisticsYearList = teacherStatistics.getYearsList();
        ArrayList<Integer> statisticsHigherEducationCount = teacherStatistics.getHaveHigherEducation();

        XYChart.Series<String, Number> tempSeries = new XYChart.Series<>();
        tempSeries.setName("Сотрудники");

        for(int i = 0; i < statisticsYearList.size(); i++)
            tempSeries.getData().add(new XYChart.Data<>(statisticsYearList.get(i), statisticsHigherEducationCount.get(i)));

        return tempSeries;
    }

    public XYChart.Series<String, Number> getHigherQualificationDistribution() {

        ExcelParser excelParser = new ExcelParser();
        TeacherStatistics teacherStatistics = excelParser.importStatisticsData("F:\\Code\\SchoolStaffManager\\res\\Динамика.xls");
        ArrayList<String> statisticsYearList = teacherStatistics.getYearsList();
        ArrayList<Integer> statisticsHigherQualificationCount = teacherStatistics.getHigherQualificationList();

        XYChart.Series<String, Number> tempSeries = new XYChart.Series<>();
        tempSeries.setName("Сотрудники");

        for(int i =0; i < statisticsYearList.size(); i++)
            tempSeries.getData().add(new XYChart.Data<>(statisticsYearList.get(i), statisticsHigherQualificationCount.get(i)));

        return tempSeries;
    }

    public ObservableList<PieChart.Data> getPieChartData() {

        ArrayList<PieChart.Data> arrayList = new ArrayList<>();
        ArrayList<String> qualificationPresents = Main.personDB.getPedagogicalDB().getPresentQualifications();
        ArrayList<Integer> qualificationCount = Main.personDB.getPedagogicalDB().getCertainQualificationsCount();

        for (int i = 0; i < qualificationPresents.size(); i++)
            arrayList.add(new PieChart.Data(qualificationPresents.get(i), qualificationCount.get(i)));

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(arrayList);

        return pieChartData;

    }

    public class PieChartCreator implements Runnable {

        @Override
        public void run() {
            qualificationDistributionPie.getData().clear();
            qualificationDistributionPie.setData(getPieChartData());
        }
    }
    public class HoursBarCreator implements  Runnable {

        @Override
        public void run() {
            hoursChargingBar.getData().clear();
            hoursChargingBar.getData().add(getSeriesDistribution());
        }
    }
    public class HigherEducationLineCreator implements Runnable {

        @Override
        public void run() {
            higherEducationDynamicLine.getData().clear();
            higherEducationDynamicLine.getData().add(getHigherEducationDistribution());
        }
    }
    public class HigherQualificationLineCreator implements Runnable {

        @Override
        public void run() {
            higherQualificationDistributionBar.getData().clear();
            higherQualificationDistributionBar.getData().add(getHigherQualificationDistribution());
        }
    }
}
