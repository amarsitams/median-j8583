package com.rumango.median.iso.dao.serviceimpl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Amar
 *
 */
public class Test {
	private JdbcTemplate template;

	private final static Logger logger = Logger.getLogger(Test.class);

	public static void main(String[] args) throws ParseException {
		// new Test().readFromFile("MedUpload.xlsx");
		// System.err.println(new
		// java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");// "yyyy-MM-dd"
		java.util.Date date = format.parse("17-May-2019");//20-May-2019
		System.err.println("Date" + new java.sql.Date(date.getTime()));
//		System.err.println("VARCHAR2(255)".toLowerCase());

//		VARCHAR2(255)
	}

	public boolean readFromFile(String fileName) {
		logger.info("****FCDServiceImpl.getData****");
		List<List<String>> data = new LinkedList<>();
		boolean status = false;
		try {
//
//			String fname = Test.class.getResource("MedUpload.xlsx").toString();
//			System.err.println("file " + fname);

			Workbook workbook = WorkbookFactory.create(Test.class.getResourceAsStream(fileName));
			Sheet sheet = workbook.getSheetAt(0);
			logger.info("Total Rows" + sheet.getLastRowNum());
			List<String> columnsFromExcel = new LinkedList<>();
			// Finding column names
			Row row;// = sheet.getRow(0);
//			row = sheet.getRow(0);
//			for (Cell cell : row) {
//				columnsFromExcel.add(cell.toString());
//			}
			String crf[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "CM" };
//			for (int i = 0; i < crf.length; i++) {
//				CellReference ref = new CellReference(crf[i]);
//				Row r = sheet.getRow(1);
//				Cell cel = r.getCell(ref.getCol());
//				System.err.println("Cell ");
//			}

			CellReference ref;
			List<String> oneRow = null;
			Cell cell;
			long count = sheet.getLastRowNum();
			for (int i = 0; i <= count; i++) {
				try {
					row = sheet.getRow(i);
				} catch (Exception e1) {
					row = null;
					continue;
				}
				if (row == null)
					continue;
				if (row.getLastCellNum() <= 0)
					continue;
				oneRow = new LinkedList<>();
				for (int c = 0; c < crf.length; c++) {
					ref = new CellReference(crf[c]);
					System.err.println(crf[c] + " " + ref.getCol());

					cell = row.getCell(ref.getCol());
					try {
						if (cell == null || cell.getCellType() == CellType.BLANK) {
							oneRow.add("");
						} else
							oneRow.add(cell.toString().trim());
					} catch (Exception e) {
						oneRow.add("");
					}
				}
				System.err.println("oneRow " + oneRow);
				data.add(oneRow);
//					if (count < 100 && i % count == 0) {
//						status = fcdDao.batchUpdate(execDate, query, data);
//						data.clear();
//					} else if (count > 100 && i % 100 == 0) {
//						status = fcdDao.batchUpdate(execDate, query, data);
//						data.clear();
//					} else if (count > 100 && i == count) {
//						status = fcdDao.batchUpdate(execDate, query, data);
//						data.clear();
//					}
			}
		} catch (IOException e) {
			logger.error("Error while reading file", e);
			return false;
		}
		return status;
	}

}
