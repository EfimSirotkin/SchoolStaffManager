package main.parsers;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import jxl.Cell;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;

import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;
import main.Main;
import main.databases.PedagogicalDB;
import main.databases.ServiceStaffDB;
import main.interfaces.parsers.Exporter;
import main.interfaces.parsers.Importer;
import main.staff.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;


public class ExcelParser implements Exporter, Importer {

    public final int dimensionMultiplier = 256;

    @Override
    public void exportData() {
    }

    @Override
    public void createDefaultTemplate(WritableWorkbook writableWorkbook,WritableSheet excelSheet, WritableCellFormat writableCellFormat) {

        try {
            CellView cellView = new CellView();
            cellView.setSize(20 * dimensionMultiplier);
            for (int i = 1; i <= 5; i++)
                excelSheet.setColumnView(i, cellView);

            Label label = new Label(1, 1, "Фамилия", writableCellFormat);
            excelSheet.addCell(label);

            label = new Label(2, 1, "Имя", writableCellFormat);
            excelSheet.addCell(label);

            label = new Label(3, 1, "Отчество", writableCellFormat);
            excelSheet.addCell(label);

            label = new Label(4, 1, "Дата рождения", writableCellFormat);
            excelSheet.addCell(label);

            label = new Label(5, 1, "Образование", writableCellFormat);
            excelSheet.addCell(label);

            label = new Label(6, 1, "Телефон", writableCellFormat);
            excelSheet.addCell(label);

            writableWorkbook.write();

        } catch (WriteException | IOException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void exportServiceWorkers(String filePath) throws IOException, WriteException {
        WritableWorkbook serviceStaffWBook = Workbook.createWorkbook(new File(filePath));
        WritableSheet excelSheet = serviceStaffWBook.createSheet("Sheet 1", 0);

        WritableCellFormat cFormat = new WritableCellFormat();
        cFormat.setAlignment(Alignment.CENTRE);
        cFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        WritableFont font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        cFormat.setFont(font);

        CellView cellView = new CellView();
        cellView.setSize(20 * dimensionMultiplier);
        for (int i = 1; i <= 5; i++)
            excelSheet.setColumnView(i, cellView);

        Label label = new Label(1, 1, "Фамилия", cFormat);
        excelSheet.addCell(label);

        label = new Label(2, 1, "Имя", cFormat);
        excelSheet.addCell(label);

        label = new Label(3, 1, "Отчество", cFormat);
        excelSheet.addCell(label);

        label = new Label(4, 1, "Дата рождения", cFormat);
        excelSheet.addCell(label);

        label = new Label(5, 1, "Образование", cFormat);
        excelSheet.addCell(label);

        cellView.setSize(30 * dimensionMultiplier);
        label = new Label(6, 1, "Телефон", cFormat);
        excelSheet.addCell(label);

        label = new Label(7, 1, "Специализация", cFormat);
        excelSheet.addCell(label);

        label = new Label(8, 1, "Разряд", cFormat);
        excelSheet.addCell(label);

        label = new Label(9, 1, "Зона ответственности", cFormat);
        excelSheet.addCell(label);

        label = new Label(10, 1, "Необходим инвентарь", cFormat);
        excelSheet.addCell(label);

        label = new Label(14, 1, "Логин", cFormat);
        excelSheet.setColumnView(14, cellView);
        excelSheet.addCell(label);

        label = new Label(15, 1, "Пароль", cFormat);
        excelSheet.setColumnView(15, cellView);
        excelSheet.addCell(label);

        final int STARTING_ROW = 2;

        ArrayList<ServiceWorker> serviceWorkersToExport = Main.personDB.getServiceStaffDB().getServiceStaff();
        int serviceWorkersToExportSize = serviceWorkersToExport.size();

        Label surNameLabel;
        Label nameLabel;
        Label superNameLabel;
        Label dateOfBirthLabel;
        Label educationLabel;
        Label mobilePhoneLabel;
        Label specializationLabel;
        Label degreeLabel;
        Label responsibilityZoneLabel;
        Label isInventoryNeededLabel;
        Label loginLabel;
        Label passWordLabel;

        for(int i = 0; i < serviceWorkersToExportSize; ++i) {
            ServiceWorker currentServiceWorker = serviceWorkersToExport.get(i);
            surNameLabel = new Label(1 , STARTING_ROW + i, currentServiceWorker.getSurname());
            nameLabel = new Label(2, STARTING_ROW + i, currentServiceWorker.getName());
            superNameLabel = new Label(3, STARTING_ROW + i, currentServiceWorker.getSuperName());
            dateOfBirthLabel = new Label(4, STARTING_ROW + i, currentServiceWorker.getDateOfBirth());
            educationLabel = new Label(5, STARTING_ROW + i, ParserUtils.generateStringFromList(currentServiceWorker.getEducation()));
            mobilePhoneLabel = new Label(6, STARTING_ROW + i, currentServiceWorker.getPhoneNumber());
            specializationLabel = new Label(7, STARTING_ROW + i, currentServiceWorker.getTypeOfWork());
            degreeLabel = new Label(8, STARTING_ROW + i, currentServiceWorker.getWorkRank());
            responsibilityZoneLabel = new Label(9, STARTING_ROW + i, currentServiceWorker.getResponsibilityZone());
            if(currentServiceWorker.isInstrumentsNeeded())
                isInventoryNeededLabel = new Label(10, STARTING_ROW + i, "да");
            else
                isInventoryNeededLabel = new Label(10,STARTING_ROW + i, "нет");
            loginLabel = new Label(14, STARTING_ROW + i, currentServiceWorker.getLoginStorage().getLogin());
            passWordLabel = new Label(15, STARTING_ROW + i, currentServiceWorker.getLoginStorage().getPassword());

            excelSheet.addCell(surNameLabel);
            excelSheet.addCell(nameLabel);
            excelSheet.addCell(superNameLabel);
            excelSheet.addCell(dateOfBirthLabel);
            excelSheet.addCell(mobilePhoneLabel);
            excelSheet.addCell(educationLabel);
            excelSheet.addCell(specializationLabel);
            excelSheet.addCell(degreeLabel);
            excelSheet.addCell(responsibilityZoneLabel);
            excelSheet.addCell(isInventoryNeededLabel);
            excelSheet.addCell(loginLabel);
            excelSheet.addCell(passWordLabel);
        }
        serviceStaffWBook.write();
        serviceStaffWBook.close();

    }

    @Override
    public void exportAdministration(String filePath) throws IOException, WriteException {
        WritableWorkbook administrationWBook = Workbook.createWorkbook(new File(filePath));
        WritableSheet excelSheet =  administrationWBook.createSheet("Sheet 1", 0);

        WritableCellFormat cFormat = new WritableCellFormat();
        cFormat.setAlignment(Alignment.CENTRE);
        cFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        WritableFont font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        cFormat.setFont(font);

        CellView cellView = new CellView();
        cellView.setSize(20 * dimensionMultiplier);
        for (int i = 1; i <= 5; i++)
            excelSheet.setColumnView(i, cellView);

        Label label = new Label(1, 1, "Фамилия", cFormat);
        excelSheet.addCell(label);

        label = new Label(2, 1, "Имя", cFormat);
        excelSheet.addCell(label);

        label = new Label(3, 1, "Отчество", cFormat);
        excelSheet.addCell(label);

        label = new Label(4, 1, "Дата рождения", cFormat);
        excelSheet.addCell(label);

        label = new Label(5, 1, "Образование", cFormat);
        excelSheet.addCell(label);

        cellView.setSize(30 * dimensionMultiplier);
        label = new Label(6, 1, "Телефон", cFormat);
        excelSheet.addCell(label);

        cellView.setSize(80 * dimensionMultiplier);
        label = new Label(7, 1, "Должность", cFormat);
        excelSheet.addCell(label);

        cellView.setSize(30 * dimensionMultiplier);
        label = new Label(8, 1, "Права доступа", cFormat);
        excelSheet.addCell(label);

        label = new Label(14, 1, "Логин", cFormat);
        excelSheet.setColumnView(14, cellView);
        excelSheet.addCell(label);

        label = new Label(15, 1, "Пароль", cFormat);
        excelSheet.setColumnView(15, cellView);
        excelSheet.addCell(label);

        final int STARTING_ROW = 2;

        ArrayList<Administrator> administratorsToExport = Main.personDB.getAdministryDB().getAdministryStaff();
        int administratorsToExportSize = administratorsToExport.size();

        Label surNameLabel;
        Label nameLabel;
        Label superNameLabel;
        Label dateOfBirthLabel;
        Label educationLabel;
        Label mobilePhoneLabel;
        Label jobTitleLable;
        Label rightsLabel;
        Label loginLabel;
        Label passWordLabel;

        for(int i = 0; i < administratorsToExportSize; ++i) {
            Administrator currentAdministrator = administratorsToExport.get(i);
            superNameLabel = new Label(1, STARTING_ROW + i, currentAdministrator.getSuperName());
            nameLabel = new Label(2, STARTING_ROW + i, currentAdministrator.getName());
            surNameLabel = new Label(3, STARTING_ROW + i, currentAdministrator.getSurname());
            dateOfBirthLabel = new Label(4, STARTING_ROW + i, currentAdministrator.getDateOfBirth());
            educationLabel = new Label(5, STARTING_ROW + i, ParserUtils.generateStringFromList(currentAdministrator.getEducation()));
            mobilePhoneLabel = new Label(6, STARTING_ROW + i, currentAdministrator.getPhoneNumber());
            jobTitleLable = new Label(7, STARTING_ROW + i, currentAdministrator.getJobTitle());
            rightsLabel = new Label(8, STARTING_ROW + i, "да");
            loginLabel = new Label(14, STARTING_ROW + i, currentAdministrator.getLoginStorage().getLogin());
            passWordLabel = new Label(15, STARTING_ROW + i, currentAdministrator.getLoginStorage().getPassword());

            excelSheet.addCell(surNameLabel);
            excelSheet.addCell(nameLabel);
            excelSheet.addCell(superNameLabel);
            excelSheet.addCell(dateOfBirthLabel);
            excelSheet.addCell(mobilePhoneLabel);
            excelSheet.addCell(educationLabel);
            excelSheet.addCell(jobTitleLable);
            excelSheet.addCell(rightsLabel);
            excelSheet.addCell(loginLabel);
            excelSheet.addCell(passWordLabel);
        }
        administrationWBook.write();
        administrationWBook.close();
    }

    @Override
    public void exportTeachers(String filePath) throws IOException, WriteException {

        WritableWorkbook teachersWBook = Workbook.createWorkbook(new File(filePath));
        WritableSheet excelSheet = teachersWBook.createSheet("Sheet 1", 0);

        WritableCellFormat cFormat = new WritableCellFormat();
        cFormat.setAlignment(Alignment.CENTRE);
        cFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        WritableFont font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        cFormat.setFont(font);

        CellView cellView = new CellView();
        cellView.setSize(20 * dimensionMultiplier);
        for (int i = 1; i <= 5; i++)
            excelSheet.setColumnView(i, cellView);

        Label label = new Label(1, 1, "Фамилия", cFormat);
        excelSheet.addCell(label);

        label = new Label(2, 1, "Имя", cFormat);
        excelSheet.addCell(label);

        label = new Label(3, 1, "Отчество", cFormat);
        excelSheet.addCell(label);

        label = new Label(4, 1, "Дата рождения", cFormat);
        excelSheet.addCell(label);

        label = new Label(5, 1, "Образование", cFormat);
        excelSheet.addCell(label);

        cellView.setSize(30 * dimensionMultiplier);
        label = new Label(6, 1, "Телефон", cFormat);
        excelSheet.addCell(label);

        label = new Label(7, 1, "Квалификационная категория", cFormat);
        cellView.setSize(30 * dimensionMultiplier);
        excelSheet.setColumnView(7, cellView);
        excelSheet.addCell(label);

        label = new Label(8, 1, "Курсы повышения квалификации", cFormat);
        cellView.setSize(50 * dimensionMultiplier);
        excelSheet.setColumnView(8, cellView);
        excelSheet.addCell(label);

        label = new Label(9, 1, "Преподает предмет", cFormat);
        cellView.setSize(40 * dimensionMultiplier);
        excelSheet.setColumnView(9, cellView);
        excelSheet.addCell(label);

        label = new Label(10, 1, "В следующих классах", cFormat);

        excelSheet.setColumnView(10, cellView);
        excelSheet.addCell(label);

        label = new Label(11, 1, "Учебные часы", cFormat);
        cellView.setSize(20 * dimensionMultiplier);
        excelSheet.setColumnView(11, cellView);
        excelSheet.addCell(label);

        label = new Label(12, 1, "Стаж работы", cFormat);
        excelSheet.setColumnView(12, cellView);
        excelSheet.addCell(label);

        label = new Label(13, 1, "Высшее образование", cFormat);
        excelSheet.setColumnView(13, cellView);
        excelSheet.addCell(label);

        label = new Label(14, 1, "Логин", cFormat);
        excelSheet.setColumnView(14, cellView);
        excelSheet.addCell(label);

        label = new Label(15, 1, "Пароль", cFormat);
        excelSheet.setColumnView(15, cellView);
        excelSheet.addCell(label);


        final int STARTING_ROW = 2;

        ArrayList<Teacher> teachersToExport = Main.personDB.getPedagogicalDB().getPedagogicalStaff();
        int teachersNumberToExport = teachersToExport.size();

        Label surNameLabel;
        Label nameLabel;
        Label superNameLabel;
        Label dateOfBirthLabel;
        Label educationLabel;
        Label mobilePhone;
        Label qualificationLabel;
        Label qualificationCoursesLabel;
        Label teachSubjectsLabel;
        Label teachAtClassesLabel;
        Label teachingHoursLabel;
        Label workingExperienceLabel;
        Label higherEducationLabel;
        Label loginLabel;
        Label passWordLabel;

        for(int i = 0; i < teachersNumberToExport; ++i) {
            Teacher currentTeacher = teachersToExport.get(i);
            HashMap<String, ArrayList<String>> hashMap = currentTeacher.getTeachSubjectsAtClasses();
            surNameLabel = new Label(1, STARTING_ROW + i, currentTeacher.getSurname());
            nameLabel = new Label(2, STARTING_ROW + i, currentTeacher.getName());
            superNameLabel = new Label(3, STARTING_ROW + i, currentTeacher.getSuperName());
            dateOfBirthLabel = new Label(4, STARTING_ROW + i, currentTeacher.getDateOfBirth());
            educationLabel = new Label(5, STARTING_ROW + i, ParserUtils.generateStringFromList(currentTeacher.getEducation()));
            mobilePhone = new Label(6, STARTING_ROW + i, currentTeacher.getPhoneNumber());
            qualificationLabel = new Label(7, STARTING_ROW + i, currentTeacher.getTeacherDegree());
            qualificationCoursesLabel = new Label(8, STARTING_ROW + i, ParserUtils.generateStringFromList(currentTeacher.getQualificationCourses()));
            teachSubjectsLabel = new Label(9, STARTING_ROW + i, ParserUtils.generateStringFromKeys(currentTeacher.getTeachSubjectsAtClasses()));
            teachAtClassesLabel = new Label(10, STARTING_ROW + i, ParserUtils.generateStringFromValues(currentTeacher.getTeachSubjectsAtClasses()));
            teachingHoursLabel = new Label(11, STARTING_ROW +i, currentTeacher.getWeeklyTeachingHours().toString());
            workingExperienceLabel = new Label(12, STARTING_ROW + i, currentTeacher.getWorkingExperience());
            if(currentTeacher.isHaveHigherEducation())
                higherEducationLabel = new Label(13, STARTING_ROW + i, "Да");
            else
                higherEducationLabel = new Label(13, STARTING_ROW +i, "Нет");
            loginLabel = new Label(14, STARTING_ROW + i, currentTeacher.getLoginStorage().getLogin());
            passWordLabel = new Label(15, STARTING_ROW + i, currentTeacher.getLoginStorage().getPassword());

            excelSheet.addCell(surNameLabel);
            excelSheet.addCell(nameLabel);
            excelSheet.addCell(superNameLabel);
            excelSheet.addCell(dateOfBirthLabel);
            excelSheet.addCell(mobilePhone);
            excelSheet.addCell(educationLabel);
            excelSheet.addCell(qualificationLabel);
            excelSheet.addCell(qualificationCoursesLabel);
            excelSheet.addCell(teachSubjectsLabel);
            excelSheet.addCell(teachAtClassesLabel);
            excelSheet.addCell(teachingHoursLabel);
            excelSheet.addCell(workingExperienceLabel);
            excelSheet.addCell(higherEducationLabel);
            excelSheet.addCell(loginLabel);
            excelSheet.addCell(passWordLabel);
            }
        teachersWBook.write();
        teachersWBook.close();
    }

    @Override
    public void createTemplateForPerson() throws IOException, WriteException {
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(null);

            if (selectedDirectory != null) {
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
            } else {
                AlertWarner.showAlert("Предупреждение", "Экспорт шаблона: " +
                                "Шаблон(Пользовательский).xls","Вероятно, файл шаблона уже открыт в Microsoft Excel",
                        Alert.AlertType.WARNING );
            }

        } catch (IOException e) {

            AlertWarner.showAlert("Предупреждение", "Экспорт шаблона: " +
                    "Шаблон(Пользовательский).xls","Вероятно, файл шаблона уже открыт в Microsoft Excel",
                    Alert.AlertType.WARNING );

            e.printStackTrace();
        }

    }

    @Override
    public void createTemplateForAdministry() throws IOException, WriteException {
        try {
            WritableWorkbook administryWBook = Workbook.createWorkbook(new File("res/Шаблон(Административный).xls"));
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
        } catch (IOException e) {

            AlertWarner.showAlert("Предупреждение", "Экспорт шаблона", "Вероятно, файл шаблона уже открыт в Microsoft Excel", Alert.AlertType.WARNING);
            e.printStackTrace();
        }

    }

    @Override
    public WritableWorkbook createTemplateForTeacher(WritableWorkbook teachersWBook, WritableSheet excelSheet, String filePath) throws IOException, WriteException, FileNotFoundException {
        try {
            WritableCellFormat cFormat = new WritableCellFormat();
            cFormat.setAlignment(Alignment.CENTRE);
            cFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            WritableFont font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            cFormat.setFont(font);

            createDefaultTemplate(teachersWBook, excelSheet, cFormat);

            CellView cellView = new CellView();
            cellView.setSize(20 * dimensionMultiplier);

            Label label = new Label(7, 1, "Квалификационная категория", cFormat);
            cellView.setSize(30 * dimensionMultiplier);
            excelSheet.setColumnView(7, cellView);
            excelSheet.addCell(label);

            label = new Label(8, 1, "Курсы повышения квалификации", cFormat);
            cellView.setSize(50 * dimensionMultiplier);
            excelSheet.setColumnView(8, cellView);
            excelSheet.addCell(label);

            label = new Label(9, 1, "Преподает предмет", cFormat);
            excelSheet.setColumnView(9, cellView);
            excelSheet.addCell(label);

            label = new Label(10, 1, "В следующих классах", cFormat);
            excelSheet.setColumnView(10, cellView);
            excelSheet.addCell(label);

            label = new Label(11, 1, "Учебные часы", cFormat);
            excelSheet.setColumnView(11, cellView);
            excelSheet.addCell(label);

            label = new Label(12, 1, "Стаж работы", cFormat);
            excelSheet.setColumnView(12, cellView);
            excelSheet.addCell(label);

            label = new Label(13, 1, "Высшее образование", cFormat);
            excelSheet.setColumnView(13, cellView);
            excelSheet.addCell(label);

            label = new Label(14, 1, "Логин", cFormat);
            excelSheet.setColumnView(14, cellView);
            excelSheet.addCell(label);

            label = new Label(15, 1, "Пароль", cFormat);
            excelSheet.setColumnView(15, cellView);
            excelSheet.addCell(label);

            teachersWBook.write();


        } catch (IOException e) {

            AlertWarner.showAlert("Предупреждение", "Экспорт шаблона: ", "Вероятно, файл шаблона уже открыт в Microsoft Excel", Alert.AlertType.WARNING);
            e.printStackTrace();
        }
        return teachersWBook;
    }

    @Override
    public void createTemplateForServiceStaff() throws IOException, WriteException {
        try {
            WritableWorkbook serviceStaffWBook = Workbook.createWorkbook(new File("res/Шаблон(Обслуживающий).xls"));
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
        } catch (IOException e) {

            AlertWarner.showAlert("Предупреждение", "Экспорт шаблона: ", "Вероятно, файл шаблона уже открыт в Microsoft Excel", Alert.AlertType.WARNING);
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
            superNameList = readColumns(columnCounter, rowCounter, sheet);
            columnCounter++;

            rowCounter = 2;
            dateOfBirthList = readColumns(columnCounter, rowCounter, sheet);
            dateOfBirthList = ParserUtils.removeSlashes(dateOfBirthList);
            columnCounter++;

            rowCounter = 2;
            educationList = readColumns(columnCounter, rowCounter, sheet);
            columnCounter++;

            rowCounter = 2;
            phoneNumberList = readColumns(columnCounter, rowCounter, sheet);

            for (int i = 0; i < surNameList.size(); i++) {
                ArrayList<String> parsedEducationList = ParserUtils.parseEducationString(educationList.get(i));
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

            ArrayList<LoginStorage> loginStorages = importLoginData(filePath);

            for (int i = 0; i < importedAdministrators.size(); i++) {
                importedAdministrators.get(i).setJobTitle(jobTitleList.get(i));
                importedAdministrators.get(i).setEditingAvailable(ParserUtils.parseBooleanString(accessRightsList.get(i)));
                importedAdministrators.get(i).setLoginStorage(loginStorages.get(i));
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
            ArrayList<String> haveHigherEducationList;

            Sheet sheet = workbook.getSheet(0);

            int columnCounter = 7;
            int rowCounter = 2;
            qualificationCategory = readColumns(columnCounter, rowCounter, sheet);
            columnCounter++;

            rowCounter = 2;
            qualificationCourses = readColumns(columnCounter, rowCounter, sheet);
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
            workingExperience = readColumns(columnCounter, rowCounter, sheet);
            columnCounter++;

            rowCounter = 2;
            haveHigherEducationList = readColumns(columnCounter,rowCounter,sheet);
            columnCounter++;

            ArrayList<LoginStorage> loginStorages = importLoginData(filePath);

            for (int i = 0; i < importedTeachers.size(); i++) {
                importedTeachers.get(i).setTeacherDegree(qualificationCategory.get(i));
                importedTeachers.get(i).setTeachSubjectsAtClasses(ParserUtils.mapSubjectsWithClasses(teachingSubjects.get(i), teachingClasses.get(i)));
                importedTeachers.get(i).setWorkingExperience(workingExperience.get(i));
                importedTeachers.get(i).setQualificationCourses(ParserUtils.parseEducationString(qualificationCourses.get(i)));
                importedTeachers.get(i).setWeeklyTeachingHours(Integer.valueOf(teachingHoursList.get(i)));
                importedTeachers.get(i).setLoginStorage(loginStorages.get(i));
                if(haveHigherEducationList.get(i).equals("Да") || haveHigherEducationList.get(i).equals("да"))
                    importedTeachers.get(i).setHaveHigherEducation(true);
                else
                    importedTeachers.get(i).setHaveHigherEducation(false);
            }

        } catch (IOException e) {
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

        try {
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
            workRankList = readColumns(columnCounter, rowCounter, sheet);
            columnCounter++;

            rowCounter = 2;
            responsibilityList = readColumns(columnCounter, rowCounter, sheet);
            columnCounter++;

            rowCounter = 2;
            inventoryNeeded = readColumns(columnCounter, rowCounter, sheet);

            ArrayList<LoginStorage> loginStorages = importLoginData(filePath);

            for (int i = 0; i < importedServiceWorkers.size(); i++) {

                importedServiceWorkers.get(i).setTypeOfWork(specializationList.get(i));
                importedServiceWorkers.get(i).setWorkRank(workRankList.get(i));
                importedServiceWorkers.get(i).setResponsibilityZone(responsibilityList.get(i));
                importedServiceWorkers.get(i).setInstrumentsNeeded(ParserUtils.parseBooleanString(inventoryNeeded.get(i)));
                importedServiceWorkers.get(i).setLoginStorage(loginStorages.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return importedServiceWorkers;
    }

    @Override
    public TeacherStatistics importStatisticsData(String filePath) {
        TeacherStatistics teacherStatistics = new TeacherStatistics();
        try {
            Workbook workbook = Workbook.getWorkbook(new File(filePath));
            Sheet sheet = workbook.getSheet(0);

            ArrayList<String> statisticsYearsList;
            ArrayList<Integer> higherEducationCountList;
            ArrayList<Integer> higherQualificationCountList;

            int columnCounter = 1;
            int rowCounter = 2;
            statisticsYearsList = readColumns(columnCounter, rowCounter, sheet);
            columnCounter++;

            rowCounter = 2;
            higherEducationCountList = ParserUtils.convertStringArrayList(readColumns(columnCounter, rowCounter, sheet));
            columnCounter++;

            rowCounter = 2;
            higherQualificationCountList = ParserUtils.convertStringArrayList(readColumns(columnCounter, rowCounter, sheet));

            teacherStatistics = new TeacherStatistics(higherEducationCountList, higherQualificationCountList, statisticsYearsList);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return teacherStatistics;
    }
    @Override
    public ArrayList<LoginStorage> importLoginData(String filePath) throws IOException, BiffException {

        ArrayList<LoginStorage> loginStorages = new ArrayList<>();
        try {
            Workbook workbook = Workbook.getWorkbook(new File(filePath));
            Sheet sheet = workbook.getSheet(0);

            ArrayList<String> loginList;
            ArrayList<String> passwordList;

            int columnCounter = 14;
            int rowCounter = 2;
            loginList = readColumns(columnCounter, rowCounter, sheet);
            columnCounter++;
            passwordList = readColumns(columnCounter, rowCounter, sheet);

            for(int i = 0; i < loginList.size(); i++)
                loginStorages.add(new LoginStorage(loginList.get(i), passwordList.get(i)));
            return loginStorages;

        }catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> readColumns(int columnNumber, int rowNumber, Sheet writableSheet) {
        ArrayList<String> tempDataList = new ArrayList<>();
        Cell currentCell;
        try {
            while ((currentCell = writableSheet.getCell(columnNumber, rowNumber)) != null) {
                tempDataList.add(currentCell.getContents());
                rowNumber++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        return tempDataList;
    }
    public void writeTeacherColumns(ArrayList<Teacher> teachers, int columnNumber, int rowNumber, Sheet sheet) {

        ArrayList<String> surNameList = new ArrayList<>();
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> superNameList = new ArrayList<>();
        ArrayList<String> dateOfBirthList = new ArrayList<>();
        ArrayList<ArrayList<String>> educationList = new ArrayList<>();
        ArrayList<String> phoneNumberList = new ArrayList<>();
        ArrayList<String> qualificationList = new ArrayList<>();
        ArrayList<ArrayList<String>> qualificationCoursesList = new ArrayList<>();
        ArrayList<ArrayList<String>> teachingSubjectsList = new ArrayList<>();
        ArrayList<ArrayList<String>> teachAtClassesList = new ArrayList<>();
        ArrayList<Integer> weeklyTeachingHoursList = new ArrayList<>();
        ArrayList<LoginStorage> loginStorages = new ArrayList<>();
        HashMap<String, ArrayList<String>> teachSubjectsAtClasses = new HashMap<>();
        int iterator = 0;

        for(Teacher teacher : teachers) {

            teachSubjectsAtClasses = teacher.getTeachSubjectsAtClasses();
            Set<String> teachingSubjects = teachSubjectsAtClasses.keySet();
            ArrayList<ArrayList<String>> teachingAtClassess = (ArrayList<ArrayList<String>>) teachSubjectsAtClasses.values();

            surNameList.add(teacher.getSurname());
            nameList.add(teacher.getName());
            superNameList.add(teacher.getDateOfBirth());
            dateOfBirthList.add(teacher.getDateOfBirth());
            educationList.add(teacher.getEducation());
            phoneNumberList.add(teacher.getPhoneNumber());
            qualificationList.add(teacher.getWorkingExperience());
            qualificationCoursesList.add(teacher.getQualificationCourses());

            ArrayList<String> teacherTeachingSubjects = new ArrayList<String>();
            for(String subject : teachingSubjects)
                teacherTeachingSubjects.add(subject + ",");



        }

    }
}