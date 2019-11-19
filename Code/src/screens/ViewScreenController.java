package screens;

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
import main.databases.PersonDB;
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
    private AdministryDB administryDB;
    private PedagogicalDB pedagogicalDB;
    private ServiceStaffDB serviceStaffDB;
    private PersonDB personDB;
    private SourceStaff sourceStaff;

    enum SourceStaff { PEDAGOGICAL, ADMINISTRATION, SERVICESTAFF}

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
        SourceStaff sourceStaff;
        getTableViewStaff();
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void goToMainScreen(javafx.event.ActionEvent actionEvent) {
        myController.setScreen(Main.mainScreenID);
    }
    public void getTableViewStaff(){
    }
    @FXML
    public void onStaffTypeClicked(ActionEvent actionEvent) {
        exportToExcelButton.setDisable(false);
        importFromExcel.setDisable(false);
        ExcelParser excelParser = new ExcelParser();

        if(actionEvent.getSource().equals(pedagogicalButton)) {
                sourceStaff = SourceStaff.PEDAGOGICAL;
                pedagogicalDB = new PedagogicalDB(excelParser.importPedagogicalTemplate("F:\\Code\\SchoolStaffManager\\res\\Шаблон(Преподавательский).xls"));
                ObservableList<Person> observableList = FXCollections.observableArrayList(pedagogicalDB.getPedagogicalStaff());
                staffTable.setItems(FXCollections.observableArrayList(observableList));
                putDataIntoTableView();

        }
        else if(actionEvent.getSource().equals(administryButton))
        {
            sourceStaff = SourceStaff.ADMINISTRATION;
            administryDB = new AdministryDB(excelParser.importAdministryTemplate("F:\\Code\\SchoolStaffManager\\res\\Шаблон(Административный).xls"));
            ObservableList<Person> observableList = FXCollections.observableArrayList(administryDB.getAdministryStaff());
            staffTable.setItems(FXCollections.observableArrayList(observableList));
            putDataIntoTableView();

        }
        else if(actionEvent.getSource().equals(serviceStaffButton))
        {
            sourceStaff = SourceStaff.SERVICESTAFF;
            serviceStaffDB = new ServiceStaffDB(excelParser.importServiceStaffTemplate("F:\\Code\\SchoolStaffManager\\res\\Шаблон(Обслуживающий).xls"));
            ObservableList<Person> observableList = FXCollections.observableArrayList(serviceStaffDB.getServiceStaff());
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
        if(event.getClickCount() == 2) {
            if(sourceStaff == SourceStaff.PEDAGOGICAL)
            {
                Teacher selectedTeacher = pedagogicalDB.findTeacher(staffTable.getSelectionModel().getSelectedItem().getName());
                if(selectedTeacher != null) {
                    fullName.setText(getFullName());
                    education.setText(generateEducationString());
                    dateOfBirth.setText("Дата рождения: " + staffTable.getSelectionModel().getSelectedItem().getDateOfBirth());
                    jobTitle.setText("учитель");
                    qualification.setText("Квалификационная категория: " + selectedTeacher.getTeacherDegree());
                }
            }
            else if(sourceStaff == SourceStaff.ADMINISTRATION)
            {
                Administrator selectedAdministrator = administryDB.findAdministrator(staffTable.getSelectionModel().getSelectedItem().getName());
                if(selectedAdministrator != null) {
                    fullName.setText(getFullName());
                    education.setText(generateEducationString());
                    dateOfBirth.setText("Дата рождения: " + staffTable.getSelectionModel().getSelectedItem().getDateOfBirth());
                    jobTitle.setText(selectedAdministrator.getJobTitle());
                    qualification.setText("-");
                }
            }
            else if(sourceStaff == SourceStaff.SERVICESTAFF)
            {
                ServiceWorker selectedServiceWorker = serviceStaffDB.findServiceWorker(staffTable.getSelectionModel().getSelectedItem().getName());
                if(selectedServiceWorker != null) {
                    fullName.setText(getFullName());
                    education.setText(generateEducationString());
                    dateOfBirth.setText("Дата рождения: " + staffTable.getSelectionModel().getSelectedItem().getDateOfBirth());
                    jobTitle.setText(selectedServiceWorker.getTypeOfWork());
                    qualification.setText("Разряд: " + selectedServiceWorker.getWorkRank());
                }
            }
        }
    }

    public String generateEducationString() {
        ArrayList<String> tempList = new ArrayList<String>();
        tempList = staffTable.getSelectionModel().getSelectedItem().getEducation();
        String generatedString ="";
        for(int i = 0; i < tempList.size(); i++) {
            generatedString += tempList.get(i);
            generatedString +="\n";
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
            educationColumn.setMinWidth(170);

            staffTable.getColumns().add(educationColumn);
        }

    }

