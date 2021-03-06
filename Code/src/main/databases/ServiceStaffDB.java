package main.databases;

import main.staff.ServiceWorker;

import java.util.ArrayList;

public class ServiceStaffDB {
    private ArrayList<ServiceWorker> serviceStaff;

    public ServiceStaffDB(ArrayList<ServiceWorker> serviceStaff) {
        this.serviceStaff = serviceStaff;
    }

    public ArrayList<ServiceWorker> getServiceStaff() {
        return serviceStaff;
    }

    public ServiceWorker findServiceWorker(String surName, String name, String superName, String dateOfBirth) {
        for (ServiceWorker serviceWorker : serviceStaff)
            if (serviceWorker.getName().equals(name) && serviceWorker.getSurname().equals(surName) && serviceWorker.getSuperName().equals(superName) && serviceWorker.getDateOfBirth().equals(dateOfBirth))
                return serviceWorker;
        return null;
    }


}
