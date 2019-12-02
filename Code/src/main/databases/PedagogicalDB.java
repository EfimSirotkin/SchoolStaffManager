package main.databases;

import main.staff.Teacher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class PedagogicalDB {
    private ArrayList<Teacher> pedagogicalStaff;


    public PedagogicalDB(ArrayList<Teacher> pedagogicalStaff) {
        this.pedagogicalStaff = pedagogicalStaff;
    }

    public ArrayList<Teacher> getPedagogicalStaff() {
        return pedagogicalStaff;
    }

    public Teacher findTeacher(String surName, String name, String superName, String dateOfBirth) {
        for (Teacher teacher : getPedagogicalStaff()) {
            if (teacher.getName().equals(name) && teacher.getSurname().equals(surName) && teacher.getSuperName().equals(superName) && teacher.getDateOfBirth().equals(dateOfBirth))
                return teacher;
        }
        return null;
    }

    public void addTeacher(Teacher teacher) {
        pedagogicalStaff.add(teacher);

    }

    public ArrayList<String> getPresentQualifications() {
        ArrayList<String> allQualifications = getAllQualifications();
        return (ArrayList<String>) allQualifications.stream().distinct().collect(Collectors.toList());
    }

    public ArrayList<Integer> getCertainQualificationsCount() {
        ArrayList<Integer> countQualifications = new ArrayList<Integer>();
        ArrayList<String> allQualifications = getAllQualifications();
        ArrayList<String> presentQualifications = getPresentQualifications();
        for (String qualification : presentQualifications)
            countQualifications.add(Collections.frequency(allQualifications, qualification));

        return countQualifications;

    }

    public ArrayList<String> getAllQualifications() {
        ArrayList<String> allQualifications = new ArrayList<>();
        for (Teacher teacher : pedagogicalStaff)
            allQualifications.add(teacher.getTeacherDegree());

        return allQualifications;
    }

}
