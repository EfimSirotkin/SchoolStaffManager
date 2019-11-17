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
        primaryStage.show();

        Text SSMText = new Text("School Staff Manager");
        SSMText.setFont(Font.font("Verdana", 40));
        SSMText.setStyle("-fx-font-weight: bold");
        VBox verticalBox = new VBox();

        Image image = new Image(new FileInputStream("res/logo.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(380);
        imageView.setFitWidth(420);

        Text typeOfInstitution = new Text("Государственное учреждение образования");
        typeOfInstitution.setFont(Font.font("Verdana", 20));
        typeOfInstitution.setStyle("-fx-font-weight: bold");

        Text institutionName = new Text("Средняя школа №181 г. Минска");
        institutionName.setFont(Font.font("Verdana", 28));
        institutionName.setStyle("-fx-font-weight: bold");

        editButton.setStyle(backGroundUIColor+defaultUIStyle);
        viewButton.setStyle(backGroundUIColor+defaultUIStyle);
        analyticsButton.setStyle(backGroundUIColor+defaultUIStyle);

        editButton.setMinHeight(100);
        editButton.setMinWidth(300);
        viewButton.setMinHeight(100);
        viewButton.setMinWidth(300);
        analyticsButton.setMinWidth(300);
        analyticsButton.setMinHeight(100);

        HBox buttonsHorizontalBox = new HBox(250);
        buttonsHorizontalBox.setPadding(new Insets(50,50,50,50));
        buttonsHorizontalBox.getChildren().addAll(editButton,viewButton,analyticsButton);
        buttonsHorizontalBox.setAlignment(Pos.CENTER);

        VBox logoButtonsLayout = new VBox();
        logoButtonsLayout.setAlignment(Pos.CENTER);
        logoButtonsLayout.setSpacing(15);
        logoButtonsLayout.getChildren().addAll(imageView, typeOfInstitution, institutionName, buttonsHorizontalBox);

        HBox staffDataBaseLayout = new HBox();
        staffDataBaseLayout.setAlignment(Pos.CENTER_RIGHT);
        staffDataBaseLayout.setPadding(new Insets(200,50,0,0));
        Text staffDataBaseText = new Text("База данных работников");
        staffDataBaseText.setFont(Font.font("Verdana", 14));
        staffDataBaseText.setStyle("-fx-font-weight: bold");
        staffDataBaseLayout.getChildren().addAll(staffDataBaseText);

        //Rectangle rectangle = new Rectangle(, , width, height);

        verticalBox.getChildren().addAll(SSMText, logoButtonsLayout, staffDataBaseLayout);

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setTitle("SSM");
        primaryStage.setScene(new Scene(verticalBox, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight()));
        primaryStage.setMaximized(true);
        primaryStage.show();

        ExcelParser excelParser = new ExcelParser();
        excelParser.createTemplateForPerson();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
