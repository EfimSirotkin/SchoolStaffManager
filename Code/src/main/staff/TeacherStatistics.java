package main.staff;

import java.util.ArrayList;

public class TeacherStatistics {
    private ArrayList<Integer> haveHigherEducation;
    private ArrayList<Integer> higherQualificationList;
    private ArrayList<String> yearsList;

    public TeacherStatistics() {
    }
    public TeacherStatistics(ArrayList<Integer> haveHigherEducation, ArrayList<Integer> higherQualificationList, ArrayList<String> yearsList) {

        this.haveHigherEducation = new ArrayList<>();
        this.higherQualificationList = new ArrayList<>();
        this.yearsList = new ArrayList<>();

        this.haveHigherEducation = haveHigherEducation;
        this.higherQualificationList = higherQualificationList;
        this.yearsList = yearsList;
    }
    public ArrayList<Integer> getHaveHigherEducation() {
        return haveHigherEducation;
    }

    public void setHaveHigherEducation(ArrayList<Integer> haveHigherEducation) {
        this.haveHigherEducation = haveHigherEducation;
    }
    public ArrayList<Integer> getHigherQualificationList() {
        return higherQualificationList;
    }

    public void setHigherQualificationList(ArrayList<Integer> higherQualificationList) {
        this.higherQualificationList = higherQualificationList;
    }
    public ArrayList<String> getYearsList() {
        return yearsList;
    }

    public void setYearsList(ArrayList<String> yearsList) {
        this.yearsList = yearsList;
    }
}
