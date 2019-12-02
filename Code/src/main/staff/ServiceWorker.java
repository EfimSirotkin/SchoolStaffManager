package main.staff;


import java.util.ArrayList;

public class ServiceWorker extends Person {
    private String typeOfWork;
    private String workRank;
    private String responsibilityZone;
    private boolean InstrumentsNeeded = false;


    public ServiceWorker(String name, String surName, String superName, String dateOfBirth, ArrayList<String> education, String phoneNumber) {
        super(name, surName, superName, dateOfBirth, education, phoneNumber);
    }

    public String getTypeOfWork() {
        return typeOfWork;
    }

    public String getWorkRank() {
        return workRank;
    }

    public String getResponsibilityZone() {
        return responsibilityZone;
    }

    public boolean isInstrumentsNeeded() {
        return InstrumentsNeeded;
    }


    public void setTypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public void setWorkRank(String workRank) {
        this.workRank = workRank;
    }

    public void setResponsibilityZone(String responsibilityZone) {
        this.responsibilityZone = responsibilityZone;
    }

    public void setInstrumentsNeeded(boolean instrumentsNeeded) {
        InstrumentsNeeded = instrumentsNeeded;
    }
}
