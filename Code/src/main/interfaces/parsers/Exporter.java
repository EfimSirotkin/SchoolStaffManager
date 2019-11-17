package main.interfaces.parsers;

import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.IOException;

public interface Exporter {
    void exportData();
    void createTemplateForPerson() throws IOException, WriteException;
    void createTemplateForAdministry() throws IOException, WriteException;
    void createTemplateForTeacher() throws IOException, WriteException;
    void createTemplateForServiceStaff() throws IOException, WriteException;
    void createDefaultTemplate(WritableWorkbook writableWorkbook,WritableSheet excelSheet, WritableCellFormat writableCellFormat);
}
