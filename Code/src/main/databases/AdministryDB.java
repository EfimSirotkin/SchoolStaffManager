package main.databases;

import main.staff.Administrator;

import java.util.ArrayList;

public class AdministryDB {
    private ArrayList<Administrator> administryStaff;

    public AdministryDB(ArrayList<Administrator> administryStaff) {
        this.administryStaff = administryStaff;
    }

    public ArrayList<Administrator> getAdministryStaff() {
        return administryStaff;
    }

    public Administrator findAdministrator(String surName, String name, String superName, String dateOfBirth) {
        for (Administrator administrator : administryStaff) {
            if (administrator.getName().equals(name) && administrator.getSurname().equals(surName) && administrator.getSuperName().equals(superName) && administrator.getDateOfBirth().equals(dateOfBirth))
                return administrator;
        }
        return null;
    }

}
