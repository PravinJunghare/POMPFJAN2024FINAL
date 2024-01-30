package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/OpenCartTestData.xlsx";
	private static Workbook book;
	private static Sheet sheet;

	public static Object[][] getTestData(String sheetName) {

		System.out.println("reading data from sheet: " + sheetName);

		Object data[][] = null;

		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			book = WorkbookFactory.create(ip);// to create java file
			sheet = book.getSheet(sheetName);// to get sheet name

			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];// for row and column

			for (int i = 0; i < sheet.getLastRowNum(); i++) // for row
			{
				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) // for column
				{
					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();// as data starts from 2nd row in sheet so
																			// i+1 and to string to covert sheet string
																			// inito java string
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;

	}

}