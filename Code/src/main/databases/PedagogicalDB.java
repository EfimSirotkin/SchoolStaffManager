package main.databases;

import main.staff.Teacher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

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
        pedagogicalStaff.add(teacher);

    }
    public void deleteTeacher(String name) {

    }
    public ArrayList<String> getPresentQualifications(){
        ArrayList<String> allQualifications = getAllQualifications();
        return (ArrayList<String>) allQualifications.stream().distinct().collect(Collectors.toList());
    }
    public ArrayList<Integer> getCertainQualificationsCount() {
        ArrayList<Integer> countQualifications = new ArrayList<Integer>();
        ArrayList<String> allQualifications = getAllQualifications();
        ArrayList<String> presentQualifications = getPresentQualifications();
        for(String qualification: presentQualifications)
            countQualifications.add(Collections.frequency(allQualifications, qualification));

        return countQualifications;

    }
    public ArrayList<String> getAllQualifications() {
        ArrayList<String> allQualifications = new ArrayList<>();
        for(Teacher teacher: pedagogicalStaff)
            allQualifications.add(teacher.getTeacherDegree());

        return allQualifications;
    }

    public HashMap<String, Integer> getQualificationCountMap(ArrayList<String> qualifications, ArrayList<Integer> qualificationCount) {
        HashMap<String, Integer> tempHashMap = new HashMap<>();
        for(int i = 0; i < qualifications.size(); i++)
            tempHashMap.put(qualifications.get(i), qualificationCount.get(i));

        return tempHashMap;
    }
}
