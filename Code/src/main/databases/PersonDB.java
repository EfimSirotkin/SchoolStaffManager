package main.databases;

import main.staff.Person;

import java.util.ArrayList;

public class PersonDB {
    private ArrayList<Person> personList;
    private AdministryDB administryDB;
    private PedagogicalDB pedagogicalDB;
    private ServiceStaffDB serviceStaffDB;

    public void setAdministryDB(AdministryDB administryDB) {
        this.administryDB = administryDB;
    }

    public AdministryDB getAdministryDB() {
        return administryDB;
    }

    public PedagogicalDB getPedagogicalDB() {
        return pedagogicalDB;
    }

    public ServiceStaffDB getServiceStaffDB() {
        return serviceStaffDB;
    }

    public void setPedagogicalDB(PedagogicalDB pedagogicalDB) {
        this.pedagogicalDB = pedagogicalDB;
    }

    public void setServiceStaffDB(ServiceStaffDB serviceStaffDB) {
        this.serviceStaffDB = serviceStaffDB;
    }

    public PersonDB() {
        personList = new ArrayList<Person>();
    }

}
