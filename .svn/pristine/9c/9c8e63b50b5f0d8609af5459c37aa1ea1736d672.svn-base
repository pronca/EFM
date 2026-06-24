package it.eng.care.domain.flow.anagraficaassistito.utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.eng.care.domain.flow.core.entity.AnagraficaAssistitoDO;
import it.eng.care.domain.flow.core.utility.LogUtil;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

/**
 * class with some useful methods
 * used in export,import xls files
 * @author vladimir.blagojevic
 *
 */
public class AnagraficaAssistitoUtility {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AnagraficaAssistitoUtility.class);

	public static AnagraficaAssistitoDO updateAnagraficaAssistitoFromSheet(Sheet sheet, String inputFilename) {
		//todo
		return null;	
	}
	
	public static Sheet checkSheetName(File inputWorkbook) {
		Sheet sheet = null;
		WorkbookSettings ws = new WorkbookSettings();
		if (inputWorkbook != null) {
			Workbook w;
			try {
				w = Workbook.getWorkbook(inputWorkbook, ws);
				sheet = w.getSheet(0);
			} catch (BiffException | IOException e) {
				LogUtil.logException(LOGGER, "", e);
//				e.printStackTrace();
			}						
		}
		return sheet;		
	}
	
	public static String getCleanSheetName(String sheetName) {
		sheetName = sheetName.trim();
		sheetName = sheetName.replace(" ", "_");
		sheetName = sheetName.replace("Ã ", "a'");
		sheetName = sheetName.replace("Ã¨", "e'");
		sheetName = sheetName.replace("Ã¬", "i'");
		sheetName = sheetName.replace("Ã²", "o'");
		sheetName = sheetName.replace("Ã¹", "u'");
		sheetName = sheetName.replace("Ã€", "A'");
		sheetName = sheetName.replace("Ãˆ", "E'");
		sheetName = sheetName.replace("ÃŒ", "I'");
		sheetName = sheetName.replace("Ã’", "O'");
		sheetName = sheetName.replace("Ã™", "U'");
		sheetName = sheetName.replace(".", "");
		sheetName = sheetName.replace(",", "");
		sheetName = sheetName.replace("'", "");
		sheetName = sheetName.toUpperCase();
		return sheetName;
	}
	
	public static  List<AnagraficaAssistitoDO> getAnagraficaAssistitoFromSheet(AnagraficaAssistitoDO assistitoDO,Sheet sheet) throws ParseException {
		Integer columnNum = sheet.getColumns();		
		Integer rowNum = sheet.getRows();	
		//System.out.println("SheetColumns::"+columnNum+" rows "+rowNum);
		List<AnagraficaAssistitoDO> anagraficaList = new ArrayList<AnagraficaAssistitoDO>();
		
		for(int i = 1; i<rowNum;i++) {
			
			AnagraficaAssistitoDO anagraficaAssistitoDOTmp = new AnagraficaAssistitoDO();
			
			if(sheet.getCell(0, i).getContents().isEmpty())
				break;
			
			for(int j = 0; j<columnNum;j++) {
				Cell cell = sheet.getCell(j, i);
				if(cell.getContents()!= null && !(cell.getContents().equals(""))) {
					if(j==0)
						anagraficaAssistitoDOTmp.setNome(cell.getContents());						 
					if(j==1)
						anagraficaAssistitoDOTmp.setCognome(cell.getContents());						 
					if(j==2)
						anagraficaAssistitoDOTmp.setDatanascita(new SimpleDateFormat("dd-MM-yyyy").parse(cell.getContents()));						
					if(j==3)
						anagraficaAssistitoDOTmp.setComunenascita(cell.getContents());						
					if(j==4)
						anagraficaAssistitoDOTmp.setSesso(cell.getContents());						
					if(j==5)
						anagraficaAssistitoDOTmp.setCodiceFiscale(cell.getContents());
					if(j==6)
						anagraficaAssistitoDOTmp.setCodicePaziente(cell.getContents());
					if(j==7)
						anagraficaAssistitoDOTmp.setComuneResidenza(cell.getContents());
					if(j==8)
						anagraficaAssistitoDOTmp.setNazionalita(cell.getContents());
					if(j==10)
						anagraficaAssistitoDOTmp.setAslResidenza(cell.getContents());
					if(j==9) {
						if(cell.getContents().toLowerCase().equals("Si".toLowerCase())) {
							anagraficaAssistitoDOTmp.setAbilitazione((byte)1);
						}else if(cell.getContents().toLowerCase().equals("No".toLowerCase())) {
							anagraficaAssistitoDOTmp.setAbilitazione((byte)0);
						}else {
							break;
						}
					}							
			}						
		}
			anagraficaList.add(anagraficaAssistitoDOTmp);
	  }
		//System.out.println("AnaListSheetSize:: "+anagraficaList.size());
		return anagraficaList;	
	}
	
	public static String isNullOrEmpty(String data) {
		String dataTmp = data;
		if(dataTmp == null || dataTmp.isEmpty()) {
			return "";
		}
		return dataTmp;
	}
	
	public static byte[] zipBytes(String filename, byte[] input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        ZipEntry entry = new ZipEntry(filename);
        entry.setSize(input.length);
        zos.putNextEntry(entry);
        zos.write(input);
        zos.closeEntry();
        zos.close();
        return baos.toByteArray();
    }
	
	public static  HSSFWorkbook workBook(List<AnagraficaAssistitoDO> data) {
		HSSFWorkbook workBook = new HSSFWorkbook();
        CreationHelper creationHelper = workBook.getCreationHelper();
        HSSFCellStyle style = workBook.createCellStyle();
        HSSFCellStyle styleData = workBook.createCellStyle();
        HSSFFont font = workBook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
     
        HSSFSheet sheet = workBook.createSheet("Angrafica_Dati");

        style.setBorderTop(BorderStyle.valueOf((short) 6)); // double lines border
        style.setBorderBottom(BorderStyle.valueOf((short) 1)); // single line border
        style.setFont(font);
        styleData.setDataFormat(creationHelper.createDataFormat().getFormat("dd-mm-yyyy"));
      
        List<String> headerString = new ArrayList<>();
        headerString.add("Nome");
        headerString.add("Cognome");
        headerString.add("Data Nascita");
        headerString.add("Comune Nascita");
        headerString.add("Sesso");
        headerString.add("Codice Fiscale");
        headerString.add("Codice Paziente");
        headerString.add("Comune Residenza");
        headerString.add("Nazionalita");
        headerString.add("Abilitazione");
        headerString.add("Asl Residenza");
      
        int rowCount = 0;
        Row row = sheet.createRow(rowCount);
        int columnCount = -1;
        for (String head : headerString) {
        	org.apache.poi.ss.usermodel.Cell cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            cell.setCellValue(head);
        }
       
        for (AnagraficaAssistitoDO anagraficaAssistitoData : data) {
            rowCount ++;
            row = sheet.createRow(rowCount);
            org.apache.poi.ss.usermodel.Cell cellData = row.createCell(0);
            cellData.setCellValue(anagraficaAssistitoData.getNome());
           
            cellData = row.createCell(1);
            cellData.setCellValue(anagraficaAssistitoData.getCognome());
           
            DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
           
            cellData = row.createCell(2);
            if(anagraficaAssistitoData.getDatanascita() == null) {
                String datanascita = "";
                cellData.setCellValue(datanascita);
            }else {
                String datanascita = dateFormatter.format(anagraficaAssistitoData.getDatanascita());
                cellData.setCellValue(datanascita);
            }
           
            cellData = row.createCell(3);
            cellData.setCellValue(anagraficaAssistitoData.getComunenascita());
           
            cellData = row.createCell(4);
            cellData.setCellValue(anagraficaAssistitoData.getSesso());
           
            cellData = row.createCell(5);
            cellData.setCellValue(anagraficaAssistitoData.getCodiceFiscale());
           
            cellData = row.createCell(6);
            cellData.setCellValue(anagraficaAssistitoData.getCodicePaziente());
           
            cellData = row.createCell(7);
            cellData.setCellValue(anagraficaAssistitoData.getComuneResidenza());
           
            cellData = row.createCell(8);
            cellData.setCellValue(anagraficaAssistitoData.getNazionalita());
           
            cellData = row.createCell(9);
            if(anagraficaAssistitoData.getAbilitazione() == null){
                String abilitazione = "";
                cellData.setCellValue(abilitazione);
            }else {
                byte abilitazioneTmp = anagraficaAssistitoData.getAbilitazione();
                String abilitazione = abilitazioneTmp==0?"No":"Si";
                cellData.setCellValue(abilitazione);
            }
           
            cellData = row.createCell(10);
            cellData.setCellValue(anagraficaAssistitoData.getAslResidenza());
        }
		return workBook;
	}
}
