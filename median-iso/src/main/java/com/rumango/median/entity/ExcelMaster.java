package com.rumango.median.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@Entity
@Table(name = "excel_master")
@JsonAutoDetect
public class ExcelMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "external_system")
	private String externalSystem;

	@Column(name = "process_file_name")
	private String processFileName;

	@Column(name = "processing_date")
	private Date processingDate;

	@Column(name = "number_of_records")
	private Long numberOfRecords;

	@Column(name = "debit_amount")
	private BigDecimal debitAmount;

	@Column(name = "credit_amount")
	private BigDecimal creditAmount;

	@Override
	public String toString() {
		return "ExcelMaster [id=" + id + ", externalSystem=" + externalSystem + ", processFileName=" + processFileName
				+ ", processingDate=" + processingDate + ", numberOfRecords=" + numberOfRecords + ", debitAmount="
				+ debitAmount + ", creditAmount=" + creditAmount + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExternalSystem() {
		return externalSystem;
	}

	public void setExternalSystem(String externalSystem) {
		this.externalSystem = externalSystem;
	}

	public String getProcessFileName() {
		return processFileName;
	}

	public void setProcessFileName(String processFileName) {
		this.processFileName = processFileName;
	}

	public Date getProcessingDate() {
		return processingDate;
	}

	public void setProcessingDate(Date processingDate) {
		this.processingDate = processingDate;
	}

	public Long getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(Long numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	public BigDecimal getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(BigDecimal debitAmount) {
		this.debitAmount = debitAmount;
	}

	public BigDecimal getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}
}
