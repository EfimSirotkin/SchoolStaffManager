package screens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import main.Main;
import main.databases.AdministryDB;
import main.databases.PedagogicalDB;
import main.databases.PersonDB;
import main.databases.ServiceStaffDB;
import main.parsers.AlertWarner;
import main.parsers.ExcelParser;
import main.parsers.LoginStorage;
import main.staff.Administrator;
import main.staff.Person;

import javax.swing.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable, ControlledScreen {

    private ScreensController myController;

    private PersonDB personDB;

    @FXML
    private Button applyButton;
    @FXML
    private TextField loginTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("F:\\Code\\SchoolStaffManager\\src\\screens\\res\\logo.jpg");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        ExcelParser excelParser = new ExcelParser();
        personDB = new PersonDB();
        personDB.appendPedagogicalStaff(excelParser.importPedagogicalTemplate("F:\\Code\\SchoolStaffManager\\res\\Шаблон(Преподавательский).xls"));
        personDB.appendAdministryStaff(excelParser.importAdministryTemplate("F:\\Code\\SchoolStaffManager\\res\\Шаблон(Административный).xls"));
        personDB.appendServiceStaff(excelParser.importServiceStaffTemplate("F:\\Code\\SchoolStaffManager\\res\\Шаблон(Обслуживающий).xls"));
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    @FXML
    private void onApplyButtonClicked() {

        boolean success = false;

        String loginInput = loginTextField.getText();
        String passWordInput = passwordTextField.getText();
        LoginStorage inputLoginStorage = new LoginStorage(loginInput, passWordInput);
        ArrayList<Person> personArrayList = personDB.getPersonList();
        for(Person person : personArrayList)
            if(person.checkLoginDataValidity(inputLoginStorage)) {
                success = true;
                AlertWarner.showAlert("Подтверждение", "Авторизация успешна", "Добро пожаловать, " +
                        person.getName() +" " + person.getSuperName() + "!", Alert.AlertType.INFORMATION);
                myController.setScreen(Main.mainScreenID);
                Stage stage = (Stage) loginTextField.getScene().getWindow();
                stage.setMaximized(true);
            }
        if(!success) {
            AlertWarner.showAlert("Ошибка авторизации", "Авторизация неуспешна", "Логин или пароль введены неверно", Alert.AlertType.ERROR);
        }
    }
    @FXML
    private void onCancelButtonClicked(ActionEvent event) {
        Stage stage = (Stage) loginTextField.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void onRegistrationButtonClicked(ActionEvent event) {
        Stage stage = (Stage) loginTextField.getScene().getWindow();
        stage.setMinHeight(800);
        stage.setMinWidth(600);
        stage.setX(stage.getX() - 100);
        stage.setY(stage.getY() - 50);
        myController.setScreen(Main.registrationTeacherScreenID);
    }

}
