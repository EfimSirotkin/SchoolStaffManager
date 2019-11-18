package screens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import main.Main;
import main.staff.Person;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class EditScreenController implements Initializable, ControlledScreen {

    @FXML
    private Label fullName;
    @FXML
    private Label jobTitle;
    @FXML
    private Label dateOfBirth;
    @FXML
    private Label workingExperience;
    @FXML
    private Label phoneNumber;
    @FXML
    private Label qualification;
    @FXML
    private Label education;
    @FXML
    private Label qualificationCourses;

    @FXML
    private TableView<Person> staffTable;

    private ScreensController myController;

    @FXML
    private ImageView imageView;

    @FXML
    private ImageView personImage;

    public EditScreenController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("F:\\Code\\SchoolStaffManager\\src\\screens\\res\\logo.jpg");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        File photoFile = new File("F:\\Code\\SchoolStaffManager\\res\\photo.jpg");
        Image photoImage = new Image(photoFile.toURI().toString());
        personImage.setImage(photoImage);

        fullName.setText("Житейко Татьяна Васильевна");
        workingExperience.setText("Стаж работы: " + "20 лет");
        jobTitle.setText("директор");
        dateOfBirth.setText("Дата рождения: " + "18.09.1973");
        phoneNumber.setText("Телефон: "  + "+375295548776");
        qualification.setText("Квалификационная категория: " +  "высшая");
        education.setText("Белорусский государственный педагогический университет\n"
                            + "Академия управления при президенте Республики Беларусь");
        qualificationCourses.setText("Институт повышения квалификации работников учреждений образования");
        getTableViewStaff();
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {

        myController = screenParent;
    }

    @FXML
    public void goToMainScreen(ActionEvent action) {
        myController.setScreen(Main.mainScreenID);
    }
    public void getTableViewStaff(){

        TableColumn<Person, String> nameColumn = new TableColumn<Person, String>("Имя");
        TableColumn<Person, String> surNameColumn = new TableColumn<Person, String>("Фамилия");
        TableColumn<Person, String> superNameColumn = new TableColumn<Person, String>("Отчество");
        TableColumn<Person, String> dateOfBirth = new TableColumn<Person, String>("Дата рождения");
        TableColumn<Person, String> jobTitle = new TableColumn<Person, String>("Должность");
        nameColumn.setStyle("-fx-font-size:14px; -fx-font-weight: bold");
        surNameColumn.setStyle("-fx-font-size:14px; -fx-font-weight: bold");
        superNameColumn.setStyle("-fx-font-size:14px; -fx-font-weight: bold");
        dateOfBirth.setStyle("-fx-font-size:14px; -fx-font-weight: bold");
        jobTitle.setStyle("-fx-font-size:14px; -fx-font-weight: bold");
        nameColumn.setMinWidth(170);
        surNameColumn.setMinWidth(170);
        superNameColumn.setMinWidth(170);
        dateOfBirth.setMinWidth(170);
        jobTitle.setMinWidth(170);

        nameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("имя"));
        surNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("фамилия"));
        superNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("отчество"));
        dateOfBirth.setCellValueFactory(new PropertyValueFactory<Person, String>("дата рождения"));
        jobTitle.setCellValueFactory(new PropertyValueFactory<Person, String>("должность"));

        staffTable.getColumns().addAll(nameColumn,surNameColumn, superNameColumn, dateOfBirth, jobTitle);

    }
}
