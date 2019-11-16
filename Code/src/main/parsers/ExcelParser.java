package main.parsers;

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
import java.io.IOException;

public class ExcelParser implements Exporter, Importer {
    @Override
    public void exportData() {

    }

    @Override
    public void createTemplateForPerson() {
        final String EXCEL_FILE_LOCATION = "F:\\Code\\SchoolStaffManager\\res\\Шаблон.xls";

        WritableWorkbook myFirstWbook = null;
        try {
            myFirstWbook = Workbook.createWorkbook(new File(EXCEL_FILE_LOCATION));
            WritableSheet excelSheet = myFirstWbook.createSheet("Лист", 0);
            WritableCellFormat cFormat = new WritableCellFormat();
            cFormat.setAlignment(Alignment.CENTRE);
            cFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            WritableFont font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);

            CellView cellView = new CellView();
            cellView.setSize(20 * 256);
            for(int i = 1; i <= 5; i ++)
                excelSheet.setColumnView(i,cellView);

            cFormat.setFont(font);
            Label label = new Label(1, 1, "Фамилия", cFormat);
            excelSheet.addCell(label);

            label = new Label(2, 1, "Имя",cFormat);
            excelSheet.addCell(label);

            label = new Label(3, 1, "Отчество",cFormat);
            excelSheet.addCell(label);

            label = new Label(4, 1, "Дата рождения",cFormat);
            excelSheet.addCell(label);

            label = new Label(5,1, "Образование", cFormat);
            excelSheet.addCell(label);

            myFirstWbook.write();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } finally {

            if (myFirstWbook != null) {
                try {
                    myFirstWbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    @Override
    public void createTemplateForAdministry() {

    }

    @Override
    public void createTemplateForTeacher() {

    }

    @Override
    public void createTemplateForServiceStaff() {

    }

    @Override
    public void importData() {

    }
}