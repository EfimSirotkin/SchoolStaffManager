package main.screens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
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
import main.staff.ServiceWorker;
import main.staff.Teacher;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable, ControlledScreen {

    private ScreensController myController;
    private static LoginStorage inputLoginStorage;
    private static boolean isPersonFound = false;
    public static boolean isAdministrator = false;


    @FXML
    private Button applyButton;
    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("res/logo.jpg");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        ExcelParser excelParser = new ExcelParser();
        Main.personDB = new PersonDB();
        Main.personDB.setPedagogicalDB(new PedagogicalDB(excelParser.importPedagogicalTemplate("res/Шаблон(Преподавательский).xls")));
        Main.personDB.setAdministryDB(new AdministryDB(excelParser.importAdministryTemplate("res/Шаблон(Административный).xls")));
        Main.personDB.setServiceStaffDB(new ServiceStaffDB(excelParser.importServiceStaffTemplate("res/Шаблон(Обслуживающий).xls")));

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void onApplyButtonClicked() {

        String errorStyle = "-fx-background-color: #eb9b9b";
        String defaultStyle = "-fx-background-color: #ffffff";
        loginTextField.setStyle(defaultStyle);
        passwordTextField.setStyle(defaultStyle);

        String loginInput = loginTextField.getText();
        String passWordInput = passwordTextField.getText();


        boolean isLoginEmpty = loginInput.equals("");
        boolean isPasswordEmpty = passWordInput.equals("");

        if (isLoginEmpty || isPasswordEmpty) {
            AlertWarner.showAlert("Ошибка авторизации", "Авторизация неуспешна", "Поля не заполнены", Alert.AlertType.ERROR);

            if (isLoginEmpty)
                loginTextField.setStyle(errorStyle);
            if (isPasswordEmpty)
                passwordTextField.setStyle(errorStyle);
            return;
        }

        inputLoginStorage = new LoginStorage(loginInput, passWordInput);
        TeacherFinder teacherFinder = new TeacherFinder();
        ServiceStaffFinder serviceStaffFinder = new ServiceStaffFinder();
        AdministratorFinder administratorFinder = new AdministratorFinder();
        teacherFinder.run();
        serviceStaffFinder.run();
        administratorFinder.run();

        if (!isPersonFound) {
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

    public class TeacherFinder implements Runnable {
        public void run() {
            PedagogicalDB pedagogicalDB = Main.personDB.getPedagogicalDB();
            for (Teacher person : pedagogicalDB.getPedagogicalStaff())
                if (person.checkLoginDataValidity(inputLoginStorage)) {
                    isPersonFound = true;
                    AlertWarner.showAlert("Подтверждение", "Авторизация успешна", "Добро пожаловать, " +
                            person.getName() + " " + person.getSuperName() + "!", Alert.AlertType.INFORMATION);
                    myController.setScreen(Main.mainScreenID);
                    Stage stage = (Stage) loginTextField.getScene().getWindow();
                    stage.setMaximized(true);
                    break;
                }

        }
    }

    public class ServiceStaffFinder implements Runnable {
        public void run() {
            ServiceStaffDB serviceStaffDB = Main.personDB.getServiceStaffDB();
            for (ServiceWorker serviceWorker : serviceStaffDB.getServiceStaff())
                if (serviceWorker.checkLoginDataValidity(inputLoginStorage)) {
                    isPersonFound = true;

                    AlertWarner.showAlert("Подтверждение", "Авторизация успешна", "Добро пожаловать, " +
                            serviceWorker.getName() + " " + serviceWorker.getSuperName() + "!", Alert.AlertType.INFORMATION);
                    myController.setScreen(Main.mainScreenID);
                    Stage stage = (Stage) loginTextField.getScene().getWindow();
                    stage.setMaximized(true);
                    break;
                }
        }
    }

    public class AdministratorFinder implements Runnable {
        public void run() {
            AdministryDB administryDB = Main.personDB.getAdministryDB();
            for (Administrator administrator : administryDB.getAdministryStaff())
                if (administrator.checkLoginDataValidity(inputLoginStorage)) {
                    isPersonFound = true;
                    isAdministrator = true;
                    AlertWarner.showAlert("Подтверждение", "Авторизация успешна", "Добро пожаловать, " +
                            administrator.getName() + " " + administrator.getSuperName() + "!", Alert.AlertType.INFORMATION);
                    myController.setScreen(Main.mainScreenID);
                    Stage stage = (Stage) loginTextField.getScene().getWindow();
                    stage.setMaximized(true);
                    break;
                }
        }
    }

}
