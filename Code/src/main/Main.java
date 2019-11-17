package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.*;
import jxl.write.Number;
import main.parsers.ExcelParser;
import screens.ScreensController;


import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.IOException;


public class Main extends Application {

    static Button editButton = new Button("Редактировать");
    static Button viewButton = new Button("Просмотреть");
    static Button analyticsButton = new Button("Аналитика");
    static String defaultUIStyle = ";-fx-text-fill: black;-fx-font-weight: bold;-fx-font-size: 24px";
    static String backGroundUIColor = "-fx-background-color: #7ebf7f ";

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

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

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
        excelParser.createTemplateForPerson();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
