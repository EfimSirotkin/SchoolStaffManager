package main.staff;

import java.util.ArrayList;
import java.util.HashMap;


public class Teacher extends Person {
    private String teacherDegree;
    private ArrayList<String> extraLessonsName;
    private HashMap<String, ArrayList<String>> teachSubjectsAtClasses;
    private String workingExperience;
    private ArrayList<String> qualificationCourses;
    private Integer weeklyTeachingHours;
    private boolean haveHigherEducation;

    public Teacher(Teacher sourceTeacher) {
        this(sourceTeacher.getName(), sourceTeacher.getSurname(), sourceTeacher.getSuperName(),
                sourceTeacher.getDateOfBirth(), sourceTeacher.getEducation(), sourceTeacher.getPhoneNumber());
        this.teacherDegree = sourceTeacher.getTeacherDegree();
        this.teachSubjectsAtClasses = sourceTeacher.getTeachSubjectsAtClasses();
        this.workingExperience = sourceTeacher.getWorkingExperience();
        this.qualificationCourses = sourceTeacher.getQualificationCourses();
        this.weeklyTeachingHours = sourceTeacher.getWeeklyTeachingHours();
        this.haveHigherEducation = sourceTeacher.isHaveHigherEducation();
    }

    public void setHaveHigherEducation(boolean haveHigherEducation) {
        this.haveHigherEducation = haveHigherEducation;
    }

    public boolean isHaveHigherEducation() {
        return haveHigherEducation;
    }

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


    public HashMap<String, ArrayList<String>> getTeachSubjectsAtClasses() {
        return teachSubjectsAtClasses;
    }

    public void setTeacher(Teacher sourceTeacher) {
        this.setPerson(sourceTeacher);
        this.teacherDegree = sourceTeacher.getTeacherDegree();
        this.teachSubjectsAtClasses = sourceTeacher.getTeachSubjectsAtClasses();
        this.workingExperience = sourceTeacher.getWorkingExperience();
        this.qualificationCourses = sourceTeacher.getQualificationCourses();
        this.weeklyTeachingHours = sourceTeacher.getWeeklyTeachingHours();
        this.haveHigherEducation = sourceTeacher.isHaveHigherEducation();
    }


    public void setTeacherDegree(String teacherDegree) {
        this.teacherDegree = teacherDegree;
    }

    public void setTeachSubjectsAtClasses(HashMap<String, ArrayList<String>> teachSubjectsAtClasses) {
        this.teachSubjectsAtClasses = teachSubjectsAtClasses;
    }
}
