package main.interfaces.parsers;

import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import main.databases.AdministryDB;
import main.databases.PedagogicalDB;
import main.databases.ServiceStaffDB;
import main.staff.Administrator;

import java.io.IOException;

public interface Exporter {
    void exportData();
    void createTemplateForPerson() throws IOException, WriteException;
    void createTemplateForAdministry() throws IOException, WriteException;
    WritableWorkbook createTemplateForTeacher(WritableWorkbook writableWorkbook, WritableSheet excelSheet, String filePath) throws IOException, WriteException;
    void createTemplateForServiceStaff() throws IOException, WriteException;
    void createDefaultTemplate(WritableWorkbook writableWorkbook, WritableSheet excelSheet, WritableCellFormat writableCellFormat);

    void exportTeachers(String filePath) throws IOException, WriteException;
    void exportServiceWorkers(String filePath) throws IOException, WriteException;
    void exportAdministration(String filePath) throws IOException, WriteException;
}
