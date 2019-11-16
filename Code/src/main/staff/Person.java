package main.staff;

import main.interfaces.staff.PersonActivity;

import java.util.ArrayList;

public class Person implements PersonActivity {
    private String name;
    private String surname;
    private String superName;
    private String dateOfBirth;
    private ArrayList<String> education;

    public Person() {}

    public Person(String name, String surname, String superName, String dateOfBirth, ArrayList<String> education)
    {
        this.name = name;
        this.surname = surname;
        this.superName = superName;
        this.dateOfBirth = dateOfBirth;
        for(String diploma : education)
            this.education.add(diploma);

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
