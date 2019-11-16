package main.staff;

import main.interfaces.staff.StaffActivity;
import main.staff.Person;

import java.util.ArrayList;
import java.util.Map;

public class Teacher extends Person implements StaffActivity {
    private String teacherDegree;
    private ArrayList<String> extraLessonsName;
    private Map<String, ArrayList<Integer>> teachSubjectsAtClasses;

    Teacher(String name, String surName, String superName, String dateOfBirth, ArrayList<String> education,
            String[] teachingSubjects, ArrayList<ArrayList<Integer>> teachClasses, String teacherDegree, ArrayList<String> extraLessonsName)
    {
        super(name,surName, superName, dateOfBirth, education);
        for(int i =0; i < teachingSubjects.length; i++)
        {
            this.teachSubjectsAtClasses.put(teachingSubjects[i], teachClasses.get(i));
        }
    }

    public String getTeacherDegree() {
        return teacherDegree;
    }

    public ArrayList<String> getExtraLessonsName() {
        return extraLessonsName;
    }

    public Map<String, ArrayList<Integer>> getTeachSubjectsAtClasses() {
        return teachSubjectsAtClasses;
    }

    @Override
    public void editPersonalData() {

    }

    @Override
    public void requestForModification() {

    }

    @Override
    public void requestForMaterials() {

    }
}
