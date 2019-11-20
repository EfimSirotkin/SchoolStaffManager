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
