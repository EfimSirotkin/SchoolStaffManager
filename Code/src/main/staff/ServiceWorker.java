package main.staff;

import main.interfaces.staff.StaffActivity;
import main.staff.Person;

import java.util.ArrayList;

public class ServiceWorker extends Person implements StaffActivity {
    private String typeOfWork;
    private String workRank;
    private String responsibilityZone;
    private boolean InstrumentsNeeded = false;

    public ServiceWorker(String name, String surname, String superName, String dateOfBirth, ArrayList<String> education, String typeOfWork, String workRank, String responsibilityZone, boolean areInstrumentsNeeded)
    {
        super(name, surname, superName, dateOfBirth, education);
        this.typeOfWork = typeOfWork;
        this.workRank = workRank;
        this.responsibilityZone = responsibilityZone;
        this.InstrumentsNeeded = areInstrumentsNeeded;
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
