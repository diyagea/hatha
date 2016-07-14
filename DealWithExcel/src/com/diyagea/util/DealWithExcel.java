package com.diyagea.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.diyagea.pojo.Hatha;

public class DealWithExcel{

	public DealWithExcel() {
	}

	public static List<Hatha> readXlsx(File f) throws IOException {
        InputStream is = new FileInputStream(f);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        // Read the Sheet
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        List<Hatha> hathas = new ArrayList<Hatha>();
            // Read the Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                Hatha h = null;
                if (xssfRow != null) {
                    XSSFCell name = xssfRow.getCell(0);
                    XSSFCell code = xssfRow.getCell(1);
                    XSSFCell date = xssfRow.getCell(2);
                    XSSFCell shop = xssfRow.getCell(3);
                    XSSFCell totalPrice = xssfRow.getCell(4);
                    XSSFCell cost = xssfRow.getCell(5);
                    XSSFCell amount = xssfRow.getCell(6);
                    XSSFCell turnover = xssfRow.getCell(7);
                    XSSFCell quotedPrice = xssfRow.getCell(8);
                    
                    h = new Hatha(name.getStringCellValue(), getValue(code), date.getDateCellValue(),
                    		shop.getStringCellValue(), totalPrice.getNumericCellValue(), 
                    		cost.getNumericCellValue(), amount.getNumericCellValue(),
                    		turnover.getNumericCellValue(), quotedPrice.getNumericCellValue());
                }
                hathas.add(h);
            }
            
            return hathas;
    }
	
	private static String getValue(XSSFCell xssfCell) {
        if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
        	DecimalFormat df = new DecimalFormat("0");
        	String whatYourWant = df.format(xssfCell.getNumericCellValue());
            return whatYourWant;
        } else {
            return String.valueOf(xssfCell.getStringCellValue());
        }
    }
}
