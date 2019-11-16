package main.databases;


import main.staff.Person;

import java.util.ArrayList;

public class PersonDB {
    private ArrayList<Person> personList;

    public PersonDB() {}

    public PersonDB(ArrayList<Person> personList) {
        this.personList = personList;
    }

    public ArrayList<Person> getPersonList() {
        return personList;
    }

    public void findPerson(String name) {

    }

    public void addPerson(Person person) {

    }

    public void deletePerson(Person person) {

    }
}
