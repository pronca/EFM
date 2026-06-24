package it.eng.care.domain.flow.core.utility;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jxl.Cell;
import jxl.CellType;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);
	
    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;
    private String inputFile;
    private WritableSheet excelSheet;
    private WritableWorkbook workbook;

    public ExcelUtil() {
        // TODO Auto-generated constructor stub
    }

    public ExcelUtil(String sheet, List<String> headers, String inputFile, File dir) throws IOException, WriteException {
        File file = new File(inputFile);
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setUseTemporaryFileDuringWrite(true);
        wbSettings.setTemporaryFileDuringWriteDirectory(dir);
        wbSettings.setLocale(new Locale("it", "IT"));

        workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet(sheet, 0);
        excelSheet = workbook.getSheet(0);
        createLabel(excelSheet, headers);

    }

    public void flush() throws IOException {
        workbook.write();
    }

    public void close() throws Exception {
        workbook.write();
        workbook.close();
    }

    public void setOutputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public void write(String sheet, List<String> headers, List<List<Object>> values) throws IOException, WriteException {
        File file = new File(inputFile);
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("it", "IT"));

        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet(sheet, 0);
        excelSheet = workbook.getSheet(0);
        createLabel(excelSheet, headers);
        createContent(excelSheet, values);

        workbook.write();
        workbook.close();
    }

    private void createLabel(WritableSheet sheet, List<String> headers) throws WriteException {
        // Lets create a times font
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
        // Define the cell format
        times = new WritableCellFormat(times10pt);
        // Lets automatically wrap the cells
        // times.setWrap(true);

        // create create a bold font with unterlines
        WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        // Lets automatically wrap the cells
        // timesBoldUnderline.setWrap(true);

        CellView cv = new CellView();
        cv.setFormat(times);
        cv.setFormat(timesBoldUnderline);
        cv.setAutosize(true);

        // Write headers
        int col = 0;
        for (String header : headers) {
            addCaption(sheet, col, 0, header);
            col++;
        }

    }

    private void createContent(WritableSheet sheet, List<List<Object>> values) throws WriteException {
        int colIdx = 0;
        int rowIdx = 1;
        for (List<Object> columns : values) {
            for (Object value : columns) {
                addLabel(sheet, colIdx, rowIdx, value.toString());
                rowIdx++;
            }
            rowIdx = 1;
            colIdx++;
        }
    }

    public void addRow(List<Object> row, int rowIdx) throws WriteException {
        int colIdx = 0;
        for (Object cell : row) {
            addLabel(excelSheet, colIdx, rowIdx, cell.toString());
            colIdx++;
        }
    }

    private void addCaption(WritableSheet sheet, int column, int row, String s) throws WriteException {
        Label label;
        label = new Label(column, row, s, timesBoldUnderline);
        sheet.addCell(label);
    }

    private void addLabel(WritableSheet sheet, int column, int row, String s) throws WriteException {
        Label label;
        label = new Label(column, row, s, times);
        sheet.addCell(label);
    }

    public void read() throws IOException {
        File inputWorkbook = new File(inputFile);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            // Get the first sheet
            Sheet sheet = w.getSheet(0);
            // Loop over first 10 column and lines

            for (int j = 0; j < sheet.getColumns(); j++) {
                for (int i = 0; i < sheet.getRows(); i++) {
                    Cell cell = sheet.getCell(j, i);
                    CellType type = cell.getType();
                    if (type == CellType.LABEL) {
                        System.out.println("I got a label " + cell.getContents());
                    }

                    if (type == CellType.NUMBER) {
                        System.out.println("I got a number " + cell.getContents());
                    }

                }
            }
        } catch (BiffException e) {
        	LogUtil.logException(LOGGER, "", e);
//            e.printStackTrace();
        }
    }

}
