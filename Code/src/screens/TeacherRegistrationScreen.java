package screens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.databases.PedagogicalDB;
import main.databases.PersonDB;
import main.parsers.ExcelParser;
import main.parsers.ParserUtils;
import main.staff.Teacher;


import javax.xml.soap.Text;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherRegistrationScreen implements Initializable, ControlledScreen {

    private ScreensController myController;
    private PersonDB personDB;

    @FXML
    private TextField nameField;
    @FXML
    private TextField surNameField;
    @FXML
    private TextField superNameField;
    @FXML
    private TextField dateOfBirthField;
    @FXML
    private TextField educationField;
    @FXML
    private TextField teachingSubjectsField;
    @FXML
    private TextField teachAtClassesField;
    @FXML
    private TextField weeklyTeachingHoursField;
    @FXML
    private TextField qualificationField;
    @FXML
    private TextField workingExperienceField;
    @FXML
    private TextField qualificationCoursesField;
    @FXML
    private TextField phoneNumberField;

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
    private void onRegisterButtonClicked(ActionEvent event) {

        ExcelParser excelParser = new ExcelParser();
        PedagogicalDB pedagogicalDB = new PedagogicalDB(excelParser.importPedagogicalTemplate("F:\\Code\\SchoolStaffManager\\res\\Шаблон(Преподавательский).xls"));

        String name = nameField.getText();
        String surName = surNameField.getText();
        String superName = superNameField.getText();
        String dateOfBirth = dateOfBirthField.getText();
        String education = educationField.getText();
        String qualification = qualificationField.getText();
        String qualificationCourses = qualificationCoursesField.getText();
        String teachingSubjects = teachingSubjectsField.getText();
        String teachSubjectAtClasses = teachAtClassesField.getText();
        String phoneNumber = phoneNumberField.getText();
        Integer weeklyTeachingHours = Integer.valueOf(weeklyTeachingHoursField.getText());
        Teacher newTeacher = new Teacher(name, surName, superName, dateOfBirth, ParserUtils.parseEducationString(education), phoneNumber);
        newTeacher.setWeeklyTeachingHours(weeklyTeachingHours);
        newTeacher.setTeacherDegree(qualification);
        newTeacher.setQualificationCourses(ParserUtils.parseEducationString(qualificationCourses));
        newTeacher.setTeachSubjectsAtClasses(ParserUtils.mapSubjectsWithClasses(ParserUtils.parseEducationString(teachingSubjects), ParserUtils.parseClassesString(teachSubjectAtClasses)));
        pedagogicalDB.addTeacher(newTeacher);

    }

}
