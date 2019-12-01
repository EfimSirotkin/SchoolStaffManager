package main.screens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import jxl.write.WriteException;
import main.Main;
import main.databases.PersonDB;
import main.parsers.AlertWarner;
import main.parsers.ExcelParser;
import main.parsers.LoginStorage;
import main.parsers.ParserUtils;
import main.staff.Teacher;


import java.io.File;
import java.io.IOException;
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
        File file = new File("res\\logo.jpg");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);

    }
    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    @FXML
    private void onRegisterButtonClicked(ActionEvent event) throws IOException, WriteException {
        try {

            ExcelParser excelParser = new ExcelParser();

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

            int loginLength = 8;
            int passwordNumbersLength = 3;
            int passwordSymbolsLength = 5;

            LoginStorage newLoginData = new LoginStorage(ParserUtils.generateLogin(loginLength), ParserUtils.generatePassword(passwordSymbolsLength, passwordNumbersLength));

            Teacher newTeacher = new Teacher(name, surName, superName, dateOfBirth, ParserUtils.parseEducationString(education), phoneNumber);
            newTeacher.setWeeklyTeachingHours(weeklyTeachingHours);
            newTeacher.setTeacherDegree(qualification);
            newTeacher.setQualificationCourses(ParserUtils.parseEducationString(qualificationCourses));
            newTeacher.setTeachSubjectsAtClasses(ParserUtils.mapSubjectsWithClasses(teachingSubjects, teachSubjectAtClasses));
            newTeacher.setLoginStorage(newLoginData);
            newTeacher.setHaveHigherEducation(true);
            Main.personDB.getPedagogicalDB().addTeacher(newTeacher);

            excelParser.exportTeachers("res/Шаблон(Преподавательский12).xls", Main.personDB.getPedagogicalDB());

        }
        catch (IOException | WriteException e) {
            AlertWarner.showAlert("Ошибка", "Экспорт шаблона: " +
                            "Шаблон(Преподавательский).xls","Произошел сбой",
                    Alert.AlertType.ERROR );

            e.printStackTrace();
        }
        finally {

            Stage stage = (Stage) nameField.getScene().getWindow();
            myController.setScreen(Main.mainScreenID);
            stage.setMaximized(true);
        }


    }
    @FXML
    private void onBackToLoginButtonClicked(ActionEvent event) {
        myController.setScreen(Main.loginScreenID);
    }

}
