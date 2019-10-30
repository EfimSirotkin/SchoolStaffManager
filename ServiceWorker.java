package sample;

import java.util.ArrayList;

public class ServiceWorker extends Person {
    private String typeOfWork;
    private String workRank;
    private String responsibilityZone;
    private boolean areInstrumentsNeeded = false;

    public ServiceWorker(String name, String surname, String superName, String dateOfBirth, ArrayList<String> education, String typeOfWork, String workRank, String responsibilityZone, boolean areInstrumentsNeeded)
    {
        super(name, surname, superName, dateOfBirth, education);
        this.typeOfWork = typeOfWork;
        this.workRank = workRank;
        this.responsibilityZone = responsibilityZone;
        this.areInstrumentsNeeded = areInstrumentsNeeded;
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

    public boolean isAreInstrumentsNeeded() {
        return areInstrumentsNeeded;
    }
}
