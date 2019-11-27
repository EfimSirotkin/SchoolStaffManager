package main.databases;


import main.parsers.LoginStorage;
import main.staff.Administrator;
import main.staff.Person;
import main.staff.ServiceWorker;
import main.staff.Teacher;

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

    public PersonDB() { personList = new ArrayList<Person>(); }

    public PersonDB(ArrayList<Person> personList) {
        this.personList = personList;
    }

    public ArrayList<Person> getPersonList() {
        return personList;
    }

    public void appendPedagogicalStaff(ArrayList<Teacher> sourceList) {
        for(Teacher teacher : sourceList)
            personList.add(teacher);

    }
    public void appendAdministryStaff(ArrayList<Administrator> sourceList) {
        for(Administrator administrator : sourceList)
            personList.add(administrator);
    }

    public void appendServiceStaff(ArrayList<ServiceWorker> sourceList) {
        for(ServiceWorker serviceWorker : sourceList)
            personList.add(serviceWorker);
    }

    public void appendTeacher(Teacher sourceTeacher) {
        personList.add(sourceTeacher);
    }

    public void findPerson(String name) {

    }

    public void addPerson(Person person) {

    }

    public void deletePerson(Person person) {

    }
}
