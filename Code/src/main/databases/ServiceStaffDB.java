package main.databases;

import main.staff.ServiceWorker;

import java.util.ArrayList;

public class ServiceStaffDB {
    private ArrayList<ServiceWorker> serviceStaff;

    public ServiceStaffDB() {}

    public ServiceStaffDB(ArrayList<ServiceWorker> serviceStaff) {
        this.serviceStaff = serviceStaff;
    }

    public ArrayList<ServiceWorker> getServiceStaff() {
        return serviceStaff;
    }

    public void findServiceWorker(String name) {

    }

    public void addServiceWorker(ServiceWorker serviceWorker)
    {

    }

    public void deleteServiceWorker(String name) {

    }
}
