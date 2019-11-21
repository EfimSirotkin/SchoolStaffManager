package main.staff;


import main.interfaces.staff.StaffActivity;
import main.staff.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Teacher extends Person implements StaffActivity {
    private String teacherDegree;
    private ArrayList<String> extraLessonsName;
    private HashMap<String, ArrayList<String>> teachSubjectsAtClasses;
    private String workingExperience;
    private ArrayList<String> qualificationCourses;
    private Integer weeklyTeachingHours;

    public void setWeeklyTeachingHours(Integer weeklyTeachingHours) {
        this.weeklyTeachingHours = weeklyTeachingHours;
    }

    public Integer getWeeklyTeachingHours() {
        return weeklyTeachingHours;
    }

    public void setQualificationCourses(ArrayList<String> qualificationCourses) {
        this.qualificationCourses = qualificationCourses;
    }

    public ArrayList<String> getQualificationCourses() {
        return qualificationCourses;
    }

    public Teacher(String name, String surName, String superName, String dateOfBirth, ArrayList<String> education, String phoneNumber) {
        super(name, surName, superName, dateOfBirth, education, phoneNumber);
    }

    public void setWorkingExperience(String workingExperience) {
        this.workingExperience = workingExperience;
    }

    public String getWorkingExperience() {
        return workingExperience;
    }

    public String getTeacherDegree() {
        return teacherDegree;
    }

    public ArrayList<String> getExtraLessonsName() {
        return extraLessonsName;
    }

    public HashMap<String, ArrayList<String>> getTeachSubjectsAtClasses() {
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

    public void setTeacherDegree(String teacherDegree) {
        this.teacherDegree = teacherDegree;
    }

    public void setExtraLessonsName(ArrayList<String> extraLessonsName) {
        this.extraLessonsName = extraLessonsName;
    }

    public void setTeachSubjectsAtClasses(HashMap<String, ArrayList<String>> teachSubjectsAtClasses) {
        this.teachSubjectsAtClasses = teachSubjectsAtClasses;
    }
}
