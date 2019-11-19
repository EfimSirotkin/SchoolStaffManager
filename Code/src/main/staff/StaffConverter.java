package main.staff;

import java.util.ArrayList;

public class StaffConverter {

    public ArrayList<Teacher> fromPersonToTeacher(ArrayList<Person> personsToConvert) {
        ArrayList<Teacher> tempTeacherList = new ArrayList<>();
        for(Person person : personsToConvert)
            tempTeacherList.add(new Teacher(person.getName(), person.getSurname(), person.getSuperName(), person.getDateOfBirth(), person.getEducation()));
        return tempTeacherList;
    }

    public ArrayList<Administrator> fromPersonToAdministrator(ArrayList<Person> personsToConvert) {
        ArrayList<Administrator> tempAdministratorsList = new ArrayList<>();
        for(Person person : personsToConvert) {
            tempAdministratorsList.add(new Administrator(person.getName(), person.getSurname(), person.getSuperName(), person.getDateOfBirth(), person.getEducation()));
        }
        return tempAdministratorsList;
    }

    public ArrayList<ServiceWorker> fromPersonToServiceWorker(ArrayList<Person> personsToConvert) {
        ArrayList<ServiceWorker> tempServiceWorkerList = new ArrayList<>();
        for(Person person : personsToConvert)
            tempServiceWorkerList.add(new ServiceWorker(person.getName(), person.getSurname(), person.getSuperName(), person.getDateOfBirth(), person.getEducation()));

        return tempServiceWorkerList;
    }
}
