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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
        ArrayList<String> phoneNumberList;
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
            columnCounter++;

            rowCounter = 2;
            phoneNumberList = readColumns(columnCounter, rowCounter, sheet);


            for(int i = 0; i < surNameList.size(); i ++) {
                ArrayList<String> parsedEducationList = parseEducationString(educationList.get(i));
                importedPersons.add(new Person(nameList.get(i),
                                    surNameList.get(i),
                                    superNameList.get(i),
                                    dateOfBirthList.get(i),
                                    parsedEducationList,
                                    phoneNumberList.get(i)
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
    public ArrayList<Administrator> importAdministryTemplate(String filePath) {

        ArrayList<Administrator> importedAdministrators;
        StaffConverter staffConverter = new StaffConverter();
        importedAdministrators = staffConverter.fromPersonToAdministrator(importPersonTemplate(filePath));
        try {
            Workbook workbook = Workbook.getWorkbook(new File(filePath));

            ArrayList<String> jobTitleList;
            ArrayList<String> accessRightsList;

            Sheet sheet = workbook.getSheet(0);

            int columnCounter = 7;
            int rowCounter = 2;
            jobTitleList = readColumns(columnCounter, rowCounter, sheet);
            columnCounter++;

            accessRightsList = readColumns(columnCounter, rowCounter, sheet);

            for(int i = 0; i < importedAdministrators.size(); i++) {
                importedAdministrators.get(i).setJobTitle(jobTitleList.get(i));
                importedAdministrators.get(i).setEditingAvailable(parseBooleanString(accessRightsList.get(i)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return importedAdministrators;
    }

    @Override
    public ArrayList<Teacher> importPedagogicalTemplate(String filePath) {
        ArrayList<Teacher> importedTeachers;
        StaffConverter staffConverter = new StaffConverter();
        importedTeachers = staffConverter.fromPersonToTeacher(importPersonTemplate(filePath));

        try {
            Workbook workbook = Workbook.getWorkbook(new File(filePath));
            ArrayList<String> qualificationCategory;
            ArrayList<String> qualificationCourses;
            ArrayList<String> teachingSubjects;
            ArrayList<String> teachingClasses;
            ArrayList<String> workingExperience;
            ArrayList<String> teachingHoursList;

            Sheet sheet = workbook.getSheet(0);

            int columnCounter = 7;
            int rowCounter = 2;
            qualificationCategory = readColumns(columnCounter, rowCounter, sheet);
            columnCounter++;

            rowCounter = 2;
            qualificationCourses = readColumns(columnCounter,rowCounter,sheet);
            columnCounter++;

            rowCounter = 2;
            teachingSubjects = readColumns(columnCounter, rowCounter, sheet);
            columnCounter++;

            rowCounter = 2;
            teachingClasses = readColumns(columnCounter, rowCounter, sheet);
            columnCounter++;

            rowCounter = 2;
            teachingHoursList = readColumns(columnCounter, rowCounter, sheet);
            columnCounter++;

            rowCounter = 2;
            workingExperience = readColumns(columnCounter,rowCounter, sheet);



            for(int i = 0; i < importedTeachers.size(); i++) {
                importedTeachers.get(i).setTeacherDegree(qualificationCategory.get(i));
                importedTeachers.get(i).setTeachSubjectsAtClasses(mapSubjectsWithClasses(teachingSubjects, teachingClasses));
                importedTeachers.get(i).setWorkingExperience(workingExperience.get(i));
                importedTeachers.get(i).setQualificationCourses(parseEducationString(qualificationCourses.get(i)));
                importedTeachers.get(i).setWeeklyTeachingHours(Integer.valueOf(teachingHoursList.get(i)));
                System.out.println(importedTeachers.get(i).getWeeklyTeachingHours());
            }


        }catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return importedTeachers;
    }


    @Override
    public ArrayList<ServiceWorker> importServiceStaffTemplate(String filePath) {
        ArrayList<ServiceWorker> importedServiceWorkers = new ArrayList<ServiceWorker>();
        StaffConverter staffConverter = new StaffConverter();
        importedServiceWorkers = staffConverter.fromPersonToServiceWorker(importPersonTemplate(filePath));

        try{
            Workbook workbook = Workbook.getWorkbook(new File(filePath));

            ArrayList<String> specializationList;
            ArrayList<String> workRankList;
            ArrayList<String> responsibilityList;
            ArrayList<String> inventoryNeeded;

            Sheet sheet = workbook.getSheet(0);

            int columnCounter = 7;
            int rowCounter = 2;
            specializationList = readColumns(columnCounter, rowCounter, sheet);
            columnCounter++;

            rowCounter = 2;
            workRankList = readColumns(columnCounter,rowCounter, sheet);
            columnCounter++;

            rowCounter = 2;
            responsibilityList = readColumns(columnCounter,rowCounter, sheet);
            columnCounter++;

            rowCounter = 2;
            inventoryNeeded = readColumns(columnCounter, rowCounter, sheet);

            for(int i = 0; i < importedServiceWorkers.size(); i++) {
                importedServiceWorkers.get(i).setTypeOfWork(specializationList.get(i));
                importedServiceWorkers.get(i).setWorkRank(workRankList.get(i));
                importedServiceWorkers.get(i).setResponsibilityZone(responsibilityList.get(i));
                importedServiceWorkers.get(i).setInstrumentsNeeded(parseBooleanString(inventoryNeeded.get(i)));

            }


        }catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return importedServiceWorkers;
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

    public HashMap<String, ArrayList<String>> mapSubjectsWithClasses(ArrayList<String> subjects, ArrayList<String> classes) {
        HashMap<String, ArrayList<String>> tempMap = new HashMap<>();
        for(int i = 0; i < subjects.size(); i++) {
            tempMap.put(subjects.get(i), parseClassesString(classes.get(i)));
        }
        return tempMap;
    }

    public ArrayList<String> parseClassesString(String classesToParse) {
        ArrayList<String> tempClassList = new ArrayList<>();
        int beginning =0;
        int end = 0;
        boolean hasMultipleClasses = false;
        for(int i =0; i < classesToParse.length(); i++) {
            String tempClass;
            if( i == classesToParse.length()-1) {
                tempClass = classesToParse.substring(beginning);
                tempClass =  tempClass.replace(" ", "");
                tempClassList.add(tempClass);
            }
            if(classesToParse.charAt(i)== ','){
                hasMultipleClasses = true;
                end = i;
                tempClass = classesToParse.substring(beginning, end);
                tempClass =  tempClass.replace(" ", "");
                tempClassList.add(tempClass);
                beginning = i + 1;
            }

        }
        if(!hasMultipleClasses)
            tempClassList.add(classesToParse);
        return tempClassList;
    }



    public boolean parseBooleanString(String stringToParse) {
        if(stringToParse.equals("Да") || stringToParse.equals("да"))
            return true;
        else
            return false;
    }
}