package main.screens;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import main.Main;
import main.databases.AdministryDB;
import main.databases.PedagogicalDB;
import main.databases.ServiceStaffDB;
import main.parsers.ExcelParser;
import main.staff.Administrator;
import main.staff.Person;
import main.staff.ServiceWorker;
import main.staff.Teacher;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewScreenController implements Initializable, ControlledScreen {

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
    private SourceStaff sourceStaff;

    public String whiteStyle = "-fx-background-color: #ffffff";
    public String colorStyle = "-fx-background-color: #7ebf7f";


    enum SourceStaff {PEDAGOGICAL, ADMINISTRATION, SERVICESTAFF}

    @FXML
    private ImageView imageView;
    @FXML
    private ImageView personImage;
    @FXML
    private Button pedagogicalButton;
    @FXML
    private Button administryButton;
    @FXML
    private Button serviceStaffButton;
    @FXML
    private Button importFromExcel;
    @FXML
    private Button exportToExcelButton;
    @FXML
    private Button sendMessageButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File file = new File("res/logo.jpg");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        File photoFile = new File("res/photo.jpg");
        Image photoImage = new Image(photoFile.toURI().toString());
        personImage.setImage(photoImage);

        fullName.setText("Житейко Татьяна Васильевна");
        workingExperience.setText("Стаж работы: " + "20 лет");
        jobTitle.setText("директор");
        dateOfBirth.setText("Дата рождения: " + "18.09.1973");
        phoneNumber.setText("Телефон: " + "+375295548776");
        qualification.setText("Квалификационная категория: " + "высшая");
        education.setText("Белорусский государственный педагогический университет\n"
                + "Академия управления при президенте Республики Беларусь");
        qualificationCourses.setText("Институт повышения квалификации работников учреждений образования");

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void goToMainScreen(javafx.event.ActionEvent actionEvent) {
        myController.setScreen(Main.mainScreenID);
    }



    @FXML
    public void onStaffTypeClicked(ActionEvent actionEvent) {
        exportToExcelButton.setDisable(false);
        importFromExcel.setDisable(false);
        ExcelParser excelParser = new ExcelParser();

        if (actionEvent.getSource().equals(pedagogicalButton)) {

            administryButton.setStyle(whiteStyle);
            pedagogicalButton.setStyle(colorStyle);
            serviceStaffButton.setStyle(whiteStyle);

            sourceStaff = ViewScreenController.SourceStaff.PEDAGOGICAL;
            Main.personDB.setPedagogicalDB(new PedagogicalDB(excelParser.importPedagogicalTemplate("res/Шаблон(Преподавательский).xls")));
            ObservableList<Person> observableList = FXCollections.observableArrayList(Main.personDB.getPedagogicalDB().getPedagogicalStaff());
            staffTable.getColumns().clear();
            staffTable.setItems(FXCollections.observableArrayList(observableList));
            putDataIntoTableView();

        } else if (actionEvent.getSource().equals(administryButton)) {

            administryButton.setStyle(colorStyle);
            pedagogicalButton.setStyle(whiteStyle);
            serviceStaffButton.setStyle(whiteStyle);

            sourceStaff = ViewScreenController.SourceStaff.ADMINISTRATION;
            Main.personDB.setAdministryDB(new AdministryDB(excelParser.importAdministryTemplate("res/Шаблон(Административный).xls")));
            ObservableList<Person> observableList = FXCollections.observableArrayList(Main.personDB.getAdministryDB().getAdministryStaff());
            staffTable.getColumns().clear();
            staffTable.setItems(FXCollections.observableArrayList(observableList));
            putDataIntoTableView();

        } else if (actionEvent.getSource().equals(serviceStaffButton)) {

            administryButton.setStyle(whiteStyle);
            pedagogicalButton.setStyle(whiteStyle);
            serviceStaffButton.setStyle(colorStyle);

            sourceStaff = ViewScreenController.SourceStaff.SERVICESTAFF;
            Main.personDB.setServiceStaffDB(new ServiceStaffDB(excelParser.importServiceStaffTemplate("res/Шаблон(Обслуживающий).xls")));
            ObservableList<Person> observableList = FXCollections.observableArrayList(Main.personDB.getServiceStaffDB().getServiceStaff());
            staffTable.getColumns().clear();
            staffTable.setItems(FXCollections.observableArrayList(observableList));
            putDataIntoTableView();
        }
    }

    public String getFullName() {
        return new String(staffTable.getSelectionModel().getSelectedItem().getSurname() + " " +
                staffTable.getSelectionModel().getSelectedItem().getName() + " " +
                staffTable.getSelectionModel().getSelectedItem().getSuperName());
    }

    @FXML
    public void clickTableViewItem(MouseEvent event) {
        if (event.getClickCount() == 2) {

            String surName = staffTable.getSelectionModel().getSelectedItem().getSurname();
            String name = staffTable.getSelectionModel().getSelectedItem().getName();
            String superName = staffTable.getSelectionModel().getSelectedItem().getSuperName();
            String dateOfBirth = staffTable.getSelectionModel().getSelectedItem().getDateOfBirth();

            if (sourceStaff == ViewScreenController.SourceStaff.PEDAGOGICAL) {
                Teacher selectedTeacher = Main.personDB.getPedagogicalDB().findTeacher(surName, name,superName, dateOfBirth);
                if (selectedTeacher != null) {
                    fullName.setText(getFullName());
                    education.setText(generateEducationString(staffTable.getSelectionModel().getSelectedItem().getEducation()));
                    this.dateOfBirth.setText("Дата рождения: " + staffTable.getSelectionModel().getSelectedItem().getDateOfBirth());
                    jobTitle.setText("учитель");
                    qualification.setText("Квалификационная категория: " + selectedTeacher.getTeacherDegree());
                    phoneNumber.setText("Телефон: " + selectedTeacher.getPhoneNumber());
                    qualificationCourses.setText(generateEducationString(selectedTeacher.getQualificationCourses()));
                    workingExperience.setText("Стаж работы: " + selectedTeacher.getWorkingExperience() + " лет");
                }
            } else if (sourceStaff == ViewScreenController.SourceStaff.ADMINISTRATION) {
                Administrator selectedAdministrator = Main.personDB.getAdministryDB().findAdministrator(surName, name,superName, dateOfBirth);
                if (selectedAdministrator != null) {
                    fullName.setText(getFullName());
                    education.setText(generateEducationString(staffTable.getSelectionModel().getSelectedItem().getEducation()));
                    this.dateOfBirth.setText("Дата рождения: " + staffTable.getSelectionModel().getSelectedItem().getDateOfBirth());
                    jobTitle.setText(selectedAdministrator.getJobTitle());
                    phoneNumber.setText("Телефон: " + selectedAdministrator.getPhoneNumber());
                    if (selectedAdministrator.isEditingAvailable())
                        qualification.setText("Полные права доступа");
                    else
                        qualification.setText("Права доступа пользователя");
                    qualificationCourses.setText("Квалификационная категория: " + "актуально для преподавателей");
                    workingExperience.setText("Стаж работы: " + "актуален для преподавателей");
                }
            } else if (sourceStaff == ViewScreenController.SourceStaff.SERVICESTAFF) {
                ServiceWorker selectedServiceWorker = Main.personDB.getServiceStaffDB().findServiceWorker(surName, name,superName, dateOfBirth);
                if (selectedServiceWorker != null) {
                    fullName.setText(getFullName());
                    education.setText(generateEducationString(staffTable.getSelectionModel().getSelectedItem().getEducation()));
                    this.dateOfBirth.setText("Дата рождения: " + staffTable.getSelectionModel().getSelectedItem().getDateOfBirth());
                    jobTitle.setText(selectedServiceWorker.getTypeOfWork());
                    qualification.setText("Разряд: " + selectedServiceWorker.getWorkRank());
                    phoneNumber.setText(("Телефон: " + selectedServiceWorker.getPhoneNumber()));
                }
            }
        }

        if(event.getClickCount() == 1) {
            sendMessageButton.setDisable(false);
        }
    }


    public String generateEducationString(ArrayList<String> sourceList) {
        ArrayList<String> tempList = new ArrayList<String>();
        tempList = sourceList;
        String generatedString = "";
        for (int i = 0; i < tempList.size(); i++) {
            generatedString += tempList.get(i);
            generatedString += "\n";
        }
        return generatedString;
    }

    public void putDataIntoTableView() {

        TableColumn<Person, String> surNameColumn = new TableColumn<>("Фамилия");
        surNameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        surNameColumn.setMinWidth(170);

        staffTable.getColumns().add(surNameColumn);

        TableColumn<Person, String> nameColumn = new TableColumn<>("Имя");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setMinWidth(170);
        staffTable.getColumns().add(nameColumn);

        TableColumn<Person, String> superNameColumn = new TableColumn<>("Отчество");
        superNameColumn.setCellValueFactory(new PropertyValueFactory<>("superName"));

        superNameColumn.setMinWidth(170);
        staffTable.getColumns().add(superNameColumn);

        TableColumn<Person, String> dateOfBirthColumn = new TableColumn<>("Дата рождения");
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        dateOfBirthColumn.setMinWidth(170);

        staffTable.getColumns().add(dateOfBirthColumn);

        TableColumn<Person, ArrayList<String>> educationColumn = new TableColumn<>("Образование");
        educationColumn.setCellValueFactory(new PropertyValueFactory<>("education"));
        educationColumn.setMinWidth(250);

        staffTable.getColumns().add(educationColumn);
    }
}

