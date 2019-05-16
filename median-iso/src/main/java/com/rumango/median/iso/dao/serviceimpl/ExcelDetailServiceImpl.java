package com.rumango.median.iso.dao.serviceimpl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.rumango.median.dao.ExcelDetailsRepository;
import com.rumango.median.entity.EmbedId;
import com.rumango.median.entity.ExcelMappingDetails;
import com.rumango.median.iso.dao.service.ExcelDetailService;
import com.rumango.median.iso.dto.ColumnMappingDto;
import com.rumango.median.iso.dto.ExcelMapping;
import com.rumango.median.iso.dto.ExcelMappingDto;

@Service
public class ExcelDetailServiceImpl implements ExcelDetailService {
	private final static Logger logger = Logger.getLogger(ExcelDetailServiceImpl.class);

	@Autowired
	private ExcelDetailsRepository excelDetailsRepository;

	@Autowired
	private JdbcTemplate template;

	private Map<String, String> map;

	public List<List<String>> getExcelData(String fileName, List<ExcelMappingDetails> excelMappedColumns, boolean isHeaderPresent) {
		List<List<String>> data = new LinkedList<>();
		try {
			Workbook workbook = WorkbookFactory.create(ExcelDetailServiceImpl.class.getResourceAsStream(fileName));
			Sheet sheet = workbook.getSheetAt(0);
			logger.info("Total Rows" + sheet.getLastRowNum());
			Row row;
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
				for (int c = isHeaderPresent ? 1 : 0; c < excelMappedColumns.size(); c++) {
					ref = new CellReference(excelMappedColumns.get(c));
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
				data.add(oneRow);
			}
		} catch (IOException e) {
			logger.error("Error while reading file", e);
			return null;
		}
		return data;
	}

	private void setMapValues() {
		map = new ConcurrentHashMap<>();
		map.put("date", "date_0");
		map.put("number", "number_250");
		map.put("char", "char_500");
		map.put("varchar", "varchar_750");
	}

	public List<List<String>> readFromFile(String fileName, List<String> excelMappedColumns, boolean isHeaderPresent) {
		List<List<String>> data = new LinkedList<>();
		try {
			Workbook workbook = WorkbookFactory.create(ExcelDetailServiceImpl.class.getResourceAsStream(fileName));
			Sheet sheet = workbook.getSheetAt(0);
			logger.info("Total Rows" + sheet.getLastRowNum());
			Row row;
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
				for (int c = isHeaderPresent ? 1 : 0; c < excelMappedColumns.size(); c++) {
					ref = new CellReference(excelMappedColumns.get(c));
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
				data.add(oneRow);
			}
		} catch (IOException e) {
			logger.error("Error while reading file", e);
			return null;
		}
		return data;
	}

	private List<String> getTableColumnsList(EmbedId embedId) {
		List<ExcelMappingDetails> details = excelDetailsRepository.findByEmbedId(embedId);
		List<String> colNames = new LinkedList<>();
		for (ExcelMappingDetails excelDetails : details) {
			colNames.add(excelDetails.getTableMappingColumn());
		}
		logger.info("All columns colNames  " + colNames);
		return colNames;
	}

	private List<String> getExcelColumnsList(EmbedId embedId) {
		List<ExcelMappingDetails> details = excelDetailsRepository.findByEmbedId(embedId);
		List<String> colNames = new LinkedList<>();
		for (ExcelMappingDetails excelDetails : details) {
			colNames.add(excelDetails.getExcelMappingColumn());
		}
		logger.info("All columns colNames  " + colNames);
		return colNames;
	}

	@Override
	public void doProcess(EmbedId embedId) {
		try {
			List<String> tableColumns = getTableColumnsList(embedId);
			logger.info("Columns list" + tableColumns);
			String query = buildQuery("testing3", tableColumns);
			logger.info("query " + query);
			List<String> excelColumns = getExcelColumnsList(embedId);
			List<List<String>> data = readFromFile("MedUpload.xlsx", excelColumns, false);
			logger.info("Excel data  " + data);
			// insertBatch(query, data);
			template.batchUpdate(query, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					try {
						List<String> oneRow = data.get(i);
						logger.info("oneRow in insert batch" + oneRow);
						logger.info("oneRow size" + oneRow.size());
						java.util.Date date = null;
						SimpleDateFormat format;
						for (int j = 1; j < oneRow.size() + 1; j++) {
							logger.info("J ::" + j);
							if (tableColumns.get(j - 1).startsWith("date")) {
								String dateStr = oneRow.get(j - 1);
								try {
									format = new SimpleDateFormat("dd-MMM-yyyy");// "yyyy-MM-dd"
									date = format.parse(dateStr);
									ps.setDate(j, new java.sql.Date(date.getTime()));
								} catch (Exception e) {
									try {
										format = new SimpleDateFormat("yyyy-MM-dd");
										date = format.parse(dateStr);
										ps.setDate(j, new java.sql.Date(date.getTime()));
									} catch (Exception e1) {
										ps.setDate(j, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
									}
								}
								logger.info("dateStr ::" + dateStr + " date ::" + date);
							}
							if (tableColumns.get(j - 1).startsWith("varchar")) {
								try {
									ps.setString(j, oneRow.get(j - 1));
								} catch (Exception e) {
									ps.setNull(j, java.sql.Types.VARCHAR);
								}
							}
							if (tableColumns.get(j - 1).startsWith("char")) {
								try {
									ps.setString(j, oneRow.get(j - 1));
								} catch (Exception e) {
									ps.setNull(j, java.sql.Types.CHAR);
								}
							}
							if (tableColumns.get(j - 1).startsWith("number")) {
								try {
									ps.setBigDecimal(j, new BigDecimal(oneRow.get(j - 1)));
								} catch (Exception e) {
									ps.setNull(j, java.sql.Types.BIGINT);
								}
							}

//							logger.info("J " + j);
//							ps.setString(j, oneRow.get(j - 1));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				@Override
				public int getBatchSize() {
					return data.size();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String buildQuery(String tableName, List<String> columns) {
		String temp;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " + tableName + " ");
		sb.append("(");
		for (String colName : columns)
			sb.append(colName + ",");
		temp = sb.toString();
		sb.setLength(0);
		sb.append(temp.substring(0, temp.length() - 1));
		sb.append(") VALUES(");
		temp = String.join("", Collections.nCopies(columns.size(), "?,"));
		sb.append(temp.substring(0, temp.length() - 1));
		sb.append(")");
		return sb.toString();
	}

	@Override
	public void convertToExcelDetailAndSave(ColumnMappingDto columnMappingDto, EmbedId id) {
		setMapValues();
		List<ExcelMapping> excelMapping = columnMappingDto.getExcelMapping();
		ExcelMappingDetails excelDetails;
		List<ExcelMappingDto> excelMappingDto = columnMappingDto.getExcelMappingDto();
		List<ExcelMappingDetails> excelDetailsList = new LinkedList<>();
		for (ExcelMappingDto dto : excelMappingDto) {
			excelDetails = new ExcelMappingDetails();
			excelDetails.setEmbedId(id);
			excelDetails.setColumnnName(dto.getColumnnName());
			excelDetails.setDataType(dto.getDataType());
			excelDetails.setDefaultValue(dto.getDefaultValue());
			excelDetails.setExcelMappingColumn(dto.getMappingColumn());
			excelDetails.setTableMappingColumn(findTableColumn(dto.getDataType()));
			excelDetailsList.add(excelDetails);
		}

		for (ExcelMapping dto : excelMapping) {
			excelDetails = new ExcelMappingDetails();
			excelDetails.setEmbedId(id);
			excelDetails.setColumnnName(dto.getColumnnName());
			excelDetails.setDataType(dto.getDataType());
			excelDetails.setDefaultValue(dto.getDefaultValue());
			excelDetails.setExcelMappingColumn(dto.getMappingColumn());
			excelDetails.setTableMappingColumn(findTableColumn(dto.getDataType()));
			excelDetailsList.add(excelDetails);
		}

		excelDetailsRepository.saveAll(excelDetailsList);

	}

	public String findTableColumn(String dataType) {
		try {
			String key, value = "";
			key = dataType.toLowerCase();
			if (key.startsWith("date"))
				key = "date";
			if (key.startsWith("varchar"))
				key = "varchar";
			if (key.startsWith("number"))
				key = "number";
			if (key.startsWith("char"))
				key = "char";
			int col;
			if (map.containsKey(key)) {
				value = map.get(key);
				col = 1 + Integer.parseInt(value.substring(value.indexOf("_") + 1));
				logger.info("col ::" + col);
				map.put(key, key + "_" + col);
			}
			return value;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public void convertToExcelDetail(List<ExcelMappingDto> excelMappingDtoList) {
//		ExcelDetail excelDetail = new ExcelDetail();
//		for (ExcelMappingDto excelMappingDto : excelMappingDtoList)
//			set(excelDetail, excelMappingDto.getColumnnName(), excelMappingDto.getMappingColumn());
//			map.put(excelMappingDto.getColumnnName(), excelMappingDto);
	}

	public boolean set(Object object, String fieldName, Object fieldValue) {
		logger.info("fieldName  :" + fieldName + " fieldValue  : " + fieldValue);
		Class<?> clazz = object.getClass();
		while (clazz != null) {
			try {
				Field field = clazz.getDeclaredField(fieldName);
				field.setAccessible(true);
				field.set(object, fieldValue);
				return true;
			} catch (NoSuchFieldException e) {
				clazz = clazz.getSuperclass();
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}
		return false;
	}

	@Override
	public void convertToExcelDetailAndSave(List<ExcelMappingDto> excelMappingDtoList, EmbedId id) {
		for (ExcelMappingDto excelMappingDto : excelMappingDtoList) {
			logger.info("excelMappingDto :" + excelMappingDto);
		}

//		ExcelDetail ed = new ExcelDetail();
//		set(ed, "id", id);
//		for (ExcelMappingDto excelMappingDto : excelMappingDtoList) {
//			logger.info("excelMappingDto :" + excelMappingDto);
//			set(ed, excelMappingDto.getColumnnName().toLowerCase(), excelMappingDto.getMappingColumn());
//		}
//
//		if (excelDetailRepository.existsById(id))
//			throw new MedianException("Record exists");
////		throw new MedianException("Record exists",new Exception());
//		else
//			logger.error(ed);
//		excelDetailRepository.save(ed);
	}

	@Override
	public void save() {
//		ExcelDetail ed = new ExcelDetail();
//		ExcelProcessEmbedId id = new ExcelProcessEmbedId();
//		id.setExternalSystem("ITAX");
//		id.setProcessName("Itax process");
//		ed.setId(id);
//		ed.setAccount("sample acc");
//		if (excelDetailRepository.existsById(id))
//			throw new MedianException("Record exists");
//		else
//			excelDetailRepository.save(ed);
	}
}
