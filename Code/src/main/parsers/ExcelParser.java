package main.parsers;

import javafx.scene.control.Alert;
import jxl.Cell;
import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import main.interfaces.parsers.Exporter;
import main.interfaces.parsers.Importer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelParser implements Exporter, Importer {

    public final int dimensionMultiplier = 256;
    @Override
    public void exportData() {

    }

    @Override
    public void createTemplateForPerson() throws IOException, WriteException {
        try {
            WritableWorkbook personWBook = Workbook.createWorkbook(new File("F:\\Code\\SchoolStaffManager\\res\\Шаблон(Пользовательский).xls"));    // nice file path, i know :)
            WritableSheet excelSheet = personWBook.createSheet("Лист", 0);
            WritableCellFormat cFormat = new WritableCellFormat();
            cFormat.setAlignment(Alignment.CENTRE);
            cFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            WritableFont font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            cFormat.setFont(font);
            createDefaultTemplate(personWBook, excelSheet, cFormat);

            personWBook.write();
            personWBook.close();
        }
        catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение");
            alert.setHeaderText("Экспорт шаблона: " + "Шаблон(Пользовательский).xls");
            alert.setContentText("Вероятно, файл шаблона уже открыт в Microsoft Excel");

            alert.showAndWait();
            e.printStackTrace();
        }

    }

    @Override
    public void createTemplateForAdministry() throws IOException, WriteException {
        try {
            WritableWorkbook administryWBook = Workbook.createWorkbook(new File("F:\\Code\\SchoolStaffManager\\res\\Шаблон(Административный).xls"));
            WritableSheet excelSheet = administryWBook.createSheet("Лист", 0);
            WritableCellFormat cFormat = new WritableCellFormat();
            cFormat.setAlignment(Alignment.CENTRE);
            cFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            WritableFont font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            cFormat.setFont(font);

            createDefaultTemplate(administryWBook, excelSheet, cFormat);

            CellView cellView = new CellView();
            cellView.setSize(20 * dimensionMultiplier);


            Label label = new Label(6, 1, "Должность", cFormat);
            excelSheet.setColumnView(6, cellView);
            excelSheet.addCell(label);

            label = new Label(7, 1, "Права доступа", cFormat);
            excelSheet.setColumnView(7, cellView);
            excelSheet.addCell(label);

            administryWBook.write();
            administryWBook.close();
        }catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение");
            alert.setHeaderText("Экспорт шаблона: " + "Шаблон(Административный).xls");
            alert.setContentText("Вероятно, файл шаблона уже открыт в Microsoft Excel");

            alert.showAndWait();
            e.printStackTrace();
        }

    }

    @Override
    public void createTemplateForTeacher() throws IOException, WriteException, FileNotFoundException {
        try {
            WritableWorkbook teachersWBook = Workbook.createWorkbook(new File("F:\\Code\\SchoolStaffManager\\res\\Шаблон(Преподавательский).xls"));
            WritableSheet excelSheet = teachersWBook.createSheet("Лист", 0);

            WritableCellFormat cFormat = new WritableCellFormat();
            cFormat.setAlignment(Alignment.CENTRE);
            cFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            WritableFont font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            cFormat.setFont(font);

            createDefaultTemplate(teachersWBook, excelSheet, cFormat);

            CellView cellView = new CellView();
            cellView.setSize(20 * dimensionMultiplier);

            Label label = new Label(6, 1, "Квалификационная категория", cFormat);
            cellView.setSize(30 * dimensionMultiplier);
            excelSheet.setColumnView(6, cellView);
            excelSheet.addCell(label);

            label = new Label(7, 1, "Преподает предмет", cFormat);
            excelSheet.setColumnView(7, cellView);
            excelSheet.addCell(label);

            label = new Label(8, 1, "В следующих классах", cFormat);
            excelSheet.setColumnView(8, cellView);
            excelSheet.addCell(label);

            teachersWBook.write();
            teachersWBook.close();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение");
            alert.setHeaderText("Экспорт шаблона: " + "Шаблон(Преподавательский).xls");
            alert.setContentText("Вероятно, файл шаблона уже открыт в Microsoft Excel");

            alert.showAndWait();
            e.printStackTrace();
        }

    }

    @Override
    public void createTemplateForServiceStaff() throws IOException, WriteException {
        try {
            WritableWorkbook serviceStaffWBook = Workbook.createWorkbook(new File("F:\\Code\\SchoolStaffManager\\res\\Шаблон(Обслуживающий).xls"));
            WritableSheet excelSheet = serviceStaffWBook.createSheet("Лист", 0);

            WritableCellFormat cFormat = new WritableCellFormat();
            cFormat.setAlignment(Alignment.CENTRE);
            cFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            WritableFont font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            cFormat.setFont(font);

            createDefaultTemplate(serviceStaffWBook, excelSheet, cFormat);

            CellView cellView = new CellView();
            cellView.setSize(20 * dimensionMultiplier);


            Label label = new Label(6, 1, "Специализация", cFormat);
            excelSheet.setColumnView(6, cellView);
            excelSheet.addCell(label);

            label = new Label(7, 1, "Разряд", cFormat);
            excelSheet.setColumnView(7, cellView);
            excelSheet.addCell(label);

            label = new Label(8, 1, "Зона ответственности", cFormat);
            cellView.setSize(25 * dimensionMultiplier);
            excelSheet.setColumnView(8, cellView);
            excelSheet.addCell(label);

            label = new Label(9, 1, "Необходим инвентарь", cFormat);
            cellView.setSize(30 * dimensionMultiplier);
            excelSheet.setColumnView(9, cellView);
            excelSheet.addCell(label);

            serviceStaffWBook.write();
            serviceStaffWBook.close();
        }
         catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение");
            alert.setHeaderText("Экспорт шаблона: " + "Шаблон(Обслуживающий).xls");
            alert.setContentText("Вероятно, файл шаблона уже открыт в Microsoft Excel");

            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @Override
    public void importData() {

    }
    @Override
    public void createDefaultTemplate(WritableWorkbook writableWorkbook,WritableSheet excelSheet, WritableCellFormat writableCellFormat)
    {

        try {
            CellView cellView = new CellView();
            cellView.setSize(20 * dimensionMultiplier);
            for(int i = 1; i <= 5; i ++)
                excelSheet.setColumnView(i,cellView);

            Label label = new Label(1, 1, "Фамилия", writableCellFormat);
            excelSheet.addCell(label);

            label = new Label(2, 1, "Имя",writableCellFormat);
            excelSheet.addCell(label);

            label = new Label(3, 1, "Отчество",writableCellFormat);
            excelSheet.addCell(label);

            label = new Label(4, 1, "Дата рождения",writableCellFormat);
            excelSheet.addCell(label);

            label = new Label(5,1, "Образование", writableCellFormat);
            excelSheet.addCell(label);

        } catch (WriteException e) {

            e.printStackTrace();
        }
    }
}