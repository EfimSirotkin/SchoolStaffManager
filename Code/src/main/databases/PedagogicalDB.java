package main.databases;

import main.staff.Teacher;

import java.util.ArrayList;

public class PedagogicalDB {
    private ArrayList<Teacher> pedagogicalStaff;

    public PedagogicalDB() {}

    public PedagogicalDB(ArrayList<Teacher> pedagogicalStaff) {
        this.pedagogicalStaff = pedagogicalStaff;
    }

    public ArrayList<Teacher> getPedagogicalStaff() {
        return pedagogicalStaff;
    }
    public Teacher findTeacher(String name) {
        for(Teacher teacher : getPedagogicalStaff()) {
            if(teacher.getName().equals(name))
                return teacher;
        }
    return null;
    }
    public void addTeacher(Teacher teacher) {

    }
    public void deleteTeacher(String name) {

    }
}
