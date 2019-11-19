package main.parsers;

import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import jxl.Cell;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.*;
import main.interfaces.parsers.Exporter;
import main.interfaces.parsers.Importer;
import main.staff.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ExcelParser implements Exporter, Importer {

    public final int dimensionMultiplier = 256;
    @Override
    public void exportData() {
    }
    @Override
    public void createTemplateForPerson() throws IOException, WriteException {
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(null);

            if(selectedDirectory != null) {
                WritableWorkbook personWBook = Workbook.createWorkbook(new File(selectedDirectory + "\\Шаблон(Пользовательский).xls"));    // nice file path, i know :)
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
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Предупреждение");
                alert.setHeaderText("Экспорт шаблона: " + "Шаблон(Пользовательский).xls");
                alert.setContentText("Экспорт отменён");
                alert.showAndWait();
            }

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
    public ArrayList<Person> importPersonTemplate(String filePath) {
        Workbook workbook;
        ArrayList<Person> importedPersons = new ArrayList<>();
        ArrayList<String> nameList;
        ArrayList<String> surNameList;
        ArrayList<String> superNameList;
        ArrayList<String> dateOfBirthList;
        ArrayList<String> educationList;
        try {
            workbook = Workbook.getWorkbook(new File(filePath));

            Sheet sheet = workbook.getSheet(0);

            int columnCounter = 1;
            int rowCounter = 2;

            surNameList = readColumns(columnCounter, rowCounter, sheet);   // Читаем строки по столбцам, начиная со второго(1)
            columnCounter++;

            rowCounter = 2;
            nameList = readColumns(columnCounter, rowCounter, sheet);
            columnCounter++;

            rowCounter = 2;
            superNameList = readColumns(columnCounter,rowCounter,sheet);
            columnCounter++;

            rowCounter = 2;
            dateOfBirthList = readColumns(columnCounter,rowCounter,sheet);
            dateOfBirthList = removeSlashes(dateOfBirthList);
            columnCounter++;

            rowCounter = 2;
            educationList = readColumns(columnCounter,rowCounter, sheet);

            for(int i = 0; i < surNameList.size(); i ++) {
                ArrayList<String> parsedEducationList = parseEducationString(educationList.get(i));
                importedPersons.add(new Person(nameList.get(i),
                                    surNameList.get(i),
                                    superNameList.get(i),
                                    dateOfBirthList.get(i),
                                    parsedEducationList
                        ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return importedPersons;
    }

    @Override
    public void importAdministryTemplate(String filePath) {

        ArrayList<Administrator> importedAdministrators;
        StaffConverter staffConverter = new StaffConverter();
        importedAdministrators = staffConverter.fromPersonToAdministrator(importPersonTemplate(filePath));
        try {
            Workbook workbook = Workbook.getWorkbook(new File(filePath));

            ArrayList<String> jobTitleList = new ArrayList<>();
            ArrayList<String> accessRightsList = new ArrayList<>();

            Sheet sheet = workbook.getSheet(0);

            int columnCounter = 6;
            int rowCounter = 2;
            jobTitleList = readColumns(columnCounter, rowCounter, sheet);
            columnCounter++;

            accessRightsList = readColumns(columnCounter, rowCounter, sheet);

            for(int i = 0; i < importedAdministrators.size(); i++) {
                importedAdministrators.get(i).setJobTitle(jobTitleList.get(i));
                boolean currentAccessRights;
                String currentARString = accessRightsList.get(i);
                if(currentARString.equals("Да") || currentARString.equals("да"))
                    currentAccessRights = true;
                else
                    currentAccessRights = false;
                importedAdministrators.get(i).setEditingAvailable(currentAccessRights);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void importPedagogicalTemplate(String filePath) {
        ArrayList<Teacher> importedTeachers;
        StaffConverter staffConverter = new StaffConverter();
        importedTeachers = staffConverter.fromPersonToTeacher(importPersonTemplate(filePath));

        try {
            Workbook workbook = Workbook.getWorkbook(new File(filePath));

        }catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ServiceWorker> fromPersonToServiceWorker(ArrayList<Person> personsToConvert) {
        ArrayList<ServiceWorker> tempServiceWorkerList = new ArrayList<>();
        for(Person person : personsToConvert)
            tempServiceWorkerList.add(new ServiceWorker(person.getName(), person.getSurname(), person.getSuperName(), person.getDateOfBirth(), person.getEducation()));

        return tempServiceWorkerList;
    }

    @Override
    public void importServiceStaffTemplate() {

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

    public ArrayList<String> readColumns(int columnNumber, int rowNumber,Sheet writableSheet) {
        ArrayList<String> tempDataList = new ArrayList<>();
        Cell currentCell;
        try {
            while ((currentCell = writableSheet.getCell(columnNumber, rowNumber)) != null) {
                tempDataList.add(currentCell.getContents());
                rowNumber++;
            }
        }catch (ArrayIndexOutOfBoundsException e) {

        }
        return tempDataList;
    }


    public ArrayList<String> parseEducationString(String education) {
        ArrayList<String> educationList = new ArrayList<>();
        int beginning = 0;
        int end = 0;
        String parsedEducation;
        boolean multipleEducation = false;
        for(int i = 0; i < education.length(); i++)
            if(education.charAt(i) == ',')
            {
                multipleEducation = true;
                if(i>0)
                    end = i - 1;
                else
                    end = 0;
                parsedEducation = education.substring(beginning, end);
                educationList.add(parsedEducation);
                beginning = i + 1;
            }
        if(!multipleEducation)
            educationList.add(education);
        for(int i =0; i< educationList.size();i++) {
            if(educationList.get(i).charAt(0) == ' ')
                educationList.set(i, educationList.get(i).substring(1));
        }
        return educationList;
    }

    public ArrayList<String> removeSlashes(ArrayList<String> dateOfBirthList) {
        ArrayList<String> tempList = new ArrayList<>();
        for(String dateOfBirth : dateOfBirthList) {
            dateOfBirth = dateOfBirth.replace("/", ".");
            tempList.add(dateOfBirth);
        }
        return  tempList;
    }

}