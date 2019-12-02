package main.staff;


import java.util.ArrayList;

public class Administrator extends Person {
    private String jobTitle;
    boolean editingAvailable = true;


    public Administrator(String name, String surname, String superName, String dateOfBirth, ArrayList<String> education, String phoneNumber) {
        super(name, surname, superName, dateOfBirth, education, phoneNumber);
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public boolean isEditingAvailable() {
        return editingAvailable;
    }


    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setEditingAvailable(boolean editingAvailable) {
        this.editingAvailable = editingAvailable;
    }
}
