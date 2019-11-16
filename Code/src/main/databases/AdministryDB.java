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
    public void addAdministrator(Administrator administrator) {

    }
    public void findAdministrator(String name) {

    }
    public void deleteAdministrator(String name) {

    }

}
