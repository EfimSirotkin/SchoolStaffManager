package main.databases;


import main.parsers.LoginStorage;
import main.staff.Administrator;
import main.staff.Person;
import main.staff.ServiceWorker;
import main.staff.Teacher;

import java.util.ArrayList;

public class PersonDB {
    private ArrayList<Person> personList;

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
