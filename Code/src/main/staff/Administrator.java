package main.staff;

import main.interfaces.staff.AdministryActivity;

import java.util.ArrayList;

public class Administrator extends Person implements AdministryActivity {
    private String jobTitle;
    boolean editingAvailable = true;

    public Administrator(String name, String surname, String superName, String dateOfBirth, ArrayList<String> education,
                         String jobTitle, boolean allRights)
    {
        super(name,surname,superName,dateOfBirth, education);
        this.editingAvailable = allRights;
        this.jobTitle = jobTitle;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public boolean isEditingAvailable() {
        return editingAvailable;
    }

    @Override
    public void addEmployee() {

    }

    @Override
    public void editEmployee() {

    }

    @Override
    public void sendNotification() {

    }

    @Override
    public void deleteEmployee() {

    }
}
