package main.staff;

import main.interfaces.staff.PersonActivity;
import main.parsers.LoginStorage;

import java.util.ArrayList;

public class Person implements PersonActivity {
    private String name;
    private String surname;
    private String superName;
    private String dateOfBirth;
    private ArrayList<String> education;
    private String phoneNumber;
    LoginStorage loginStorage;

    public Person() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Person(String name, String surname, String superName, String dateOfBirth, ArrayList<String> education, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.superName = superName;
        this.dateOfBirth = dateOfBirth;
        this.education = new ArrayList<>();
        for (String diploma : education)
            this.education.add(diploma);
        this.phoneNumber = phoneNumber;

    }


    public String getName() {
        return name;
    }

    public String getSuperName() {
        return superName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSurname() {
        return surname;
    }

    public ArrayList<String> getEducation() {
        return education;
    }

    public LoginStorage getLoginStorage() {
        return loginStorage;
    }

    public void setLoginStorage(LoginStorage loginStorage) {
        this.loginStorage = loginStorage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEducation(ArrayList<String> education) {
        this.education = education;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPerson(Teacher sourceTeacher) {
        this.name = sourceTeacher.getName();
        this.surname = sourceTeacher.getSurname();
        this.superName = sourceTeacher.getSuperName();
        this.education = sourceTeacher.getEducation();
        this.phoneNumber = sourceTeacher.getPhoneNumber();
        this.dateOfBirth = sourceTeacher.getDateOfBirth();
    }

    public boolean checkLoginDataValidity(LoginStorage loginStorage) {
        if(this.loginStorage.getLogin().equals(loginStorage.getLogin()) && this.loginStorage.getPassword().equals(loginStorage.getPassword()))
            return true;
        else
            return false;
    }


    @Override
    public void viewStaffList() {

    }

    @Override
    public void viewAnalytics() {

    }

    @Override
    public void viewEmployeeInformation() {

    }

    @Override
    public void register() {

    }

    @Override
    public void login() {

    }
}
