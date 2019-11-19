package main.interfaces.parsers;

import jxl.read.biff.BiffException;
import main.staff.Administrator;
import main.staff.Person;
import main.staff.ServiceWorker;
import main.staff.Teacher;

import java.io.IOException;
import java.util.ArrayList;

public interface Importer {
    void importData();
    ArrayList<Person> importPersonTemplate(String filePath);
    ArrayList<Administrator> importAdministryTemplate(String filePath) throws IOException, BiffException;
    ArrayList<Teacher> importPedagogicalTemplate(String filePath);
    ArrayList<ServiceWorker> importServiceStaffTemplate(String filePath);
}
