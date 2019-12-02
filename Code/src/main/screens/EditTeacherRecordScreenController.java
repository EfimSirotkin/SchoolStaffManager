package main.screens;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import jxl.write.WriteException;
import main.parsers.AlertWarner;
import main.parsers.ExcelParser;
import main.parsers.ParserUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static main.screens.EditScreenController.foundTeacher;

public class EditTeacherRecordScreenController implements Initializable {

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

        nameField.setText(foundTeacher.getName());
        surNameField.setText(foundTeacher.getSurname());
        superNameField.setText(foundTeacher.getSuperName());
        dateOfBirthField.setText(foundTeacher.getDateOfBirth());
        educationField.setText(ParserUtils.generateStringFromList(foundTeacher.getEducation()));
        teachingSubjectsField.setText(ParserUtils.generateStringFromKeys(foundTeacher.getTeachSubjectsAtClasses()));
        teachAtClassesField.setText(ParserUtils.generateStringFromValues(foundTeacher.getTeachSubjectsAtClasses()));
        weeklyTeachingHoursField.setText(foundTeacher.getWeeklyTeachingHours().toString());
        qualificationField.setText(foundTeacher.getTeacherDegree());
        qualificationCoursesField.setText(ParserUtils.generateStringFromList(foundTeacher.getQualificationCourses()));
        phoneNumberField.setText(foundTeacher.getPhoneNumber());
        workingExperienceField.setText(foundTeacher.getWorkingExperience());


    }

    @FXML
    public void onApplyButtonClicked() throws IOException, WriteException {

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
        String workingExperinece = workingExperienceField.getText();

        foundTeacher.setName(name);
        foundTeacher.setSurname(surName);
        foundTeacher.setSuperName(superName);
        foundTeacher.setDateOfBirth(dateOfBirth);
        foundTeacher.setPhoneNumber(phoneNumber);
        foundTeacher.setEducation(ParserUtils.parseEducationString(education));
        foundTeacher.setTeacherDegree(qualification);
        foundTeacher.setWeeklyTeachingHours(weeklyTeachingHours);
        foundTeacher.setTeachSubjectsAtClasses(ParserUtils.mapSubjectsWithClasses(teachingSubjects, teachSubjectAtClasses));
        foundTeacher.setQualificationCourses(ParserUtils.parseEducationString(qualificationCourses));
        foundTeacher.setHaveHigherEducation(true);
        foundTeacher.setWorkingExperience(workingExperinece);

        ExcelParser excelParser = new ExcelParser();
        Stage stage = (Stage) imageView.getScene().getWindow();
        stage.setAlwaysOnTop(false);
        excelParser.exportTeachers("res/Шаблон(Преподавательский123).xls");

        AlertWarner.showAlert("Редактирование", "Запись успешно отредактирована", "Успешно.", Alert.AlertType.INFORMATION);


        stage.close();

    }
}
