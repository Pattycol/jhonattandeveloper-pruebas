package pe.com.claro.caef.web.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.bea.common.security.xacml.IOException;

public class Prueba {

	public static void main(String[] args) throws java.io.IOException {
		try {
			FileOutputStream fileOut = new FileOutputStream("E:/poi-test.xls");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("POI Worksheet");

			// index from 0,0... cell A1 is cell(0,0)
			HSSFRow row1 = worksheet.createRow((short) 0);

			HSSFCell cellA1 = row1.createCell((short) 0);
			cellA1.setCellValue("Hello");
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			

			HSSFCell cellB1 = row1.createCell((short) 1);
			cellB1.setCellValue("Goodbye");
			cellStyle = workbook.createCellStyle();
		

			HSSFCell cellC1 = row1.createCell((short) 2);
			cellC1.setCellValue(true);

			HSSFCell cellD1 = row1.createCell((short) 3);

			cellStyle = workbook.createCellStyle();
			cellStyle.setDataFormat(HSSFDataFormat
					.getBuiltinFormat("m/d/yy h:mm"));
			cellD1.setCellStyle(cellStyle);

			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
	  
