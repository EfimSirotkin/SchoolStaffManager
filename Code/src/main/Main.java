package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import main.parsers.ExcelParser;
import screens.ScreensController;

public class Main extends Application {

    public static String mainScreenID = "Main";
    public static String mainScreenFile = "MainScreen.fxml";
    public static String analyticScreenID = "Analytic";
    public static String analyticScreenFile = "AnalyticScreen.fxml";
    public static String viewScreenID = "View";
    public static String viewScreenFile = "ViewScreen.fxml";
    public static String editScreenID = "Edit";
    public static String editScreenFile= "EditScreen.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {

        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(mainScreenID, mainScreenFile);
        mainContainer.loadScreen(viewScreenID, viewScreenFile);
        mainContainer.loadScreen(editScreenID, editScreenFile);
        mainContainer.loadScreen(analyticScreenID, analyticScreenFile);

        mainContainer.setScreen(mainScreenID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("School Staff Manager");
        primaryStage.show();

        ExcelParser excelParser = new ExcelParser();
        excelParser.importPersonTemplate("F:\\Code\\SchoolStaffManager\\res\\Шаблон(Пользовательский).xls");
        excelParser.importAdministryTemplate("F:\\Code\\SchoolStaffManager\\res\\Шаблон(Административный).xls");
        //excelParser.createTemplateForPerson();
        //excelParser.createTemplateForAdministry();
        //excelParser.createTemplateForServiceStaff();
        //excelParser.createTemplateForTeacher();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
