package main.interfaces.parsers;

import jxl.read.biff.BiffException;
import main.staff.Person;

import java.io.IOException;
import java.util.ArrayList;

public interface Importer {
    void importData();
    ArrayList<Person> importPersonTemplate(String filePath);
    void importAdministryTemplate(String filePath) throws IOException, BiffException;
    void importPedagogicalTemplate(String filePath);
    void importServiceStaffTemplate();
}
