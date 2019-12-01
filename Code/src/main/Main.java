package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.databases.PersonDB;
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
    public static String loginScreenID = "Login";
    public static String loginScreenFile = "LoginScreen.fxml";
    public static String registrationTeacherScreenID = "teacherRegistration";
    public static String registrationTeacherScreenFile = "TeacherRegistrationScreen.fxml";
    public static String registrationServiceStaffID = "ServiceStaffRegistration";
    public static String registrationServiceStaffFile = "ServiceStaffRegistration.fxml";

    public static PersonDB personDB;

    @Override
    public void start(Stage primaryStage) throws Exception {

        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(mainScreenID, mainScreenFile);
        mainContainer.loadScreen(loginScreenID, loginScreenFile);
        mainContainer.loadScreen(registrationServiceStaffID, registrationServiceStaffFile);
        mainContainer.loadScreen(registrationTeacherScreenID, registrationTeacherScreenFile);
        mainContainer.loadScreen(viewScreenID, viewScreenFile);
        mainContainer.loadScreen(editScreenID, editScreenFile);
        mainContainer.loadScreen(analyticScreenID, analyticScreenFile);

        mainContainer.setScreen(loginScreenID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("School Staff Manager");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
