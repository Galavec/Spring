package com.galavec.findstrings.dto;

import lombok.Data;

@Data
public class ResponseWillExist {
	private Integer responseCode;
	private String responseMessage;
	private RecordsFoundDto recordsFound;
	private RecordsNotFoundDto recordsNotFound;
	private RepeatedRecordsDto repeatedRecords;

	public ResponseWillExist(Integer responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public ResponseWillExist() {
		super();
	}

	public ResponseWillExist(Integer responseCode, String responseMessage, RecordsFoundDto recordsFound,
			RecordsNotFoundDto recordsNotFound, RepeatedRecordsDto repeatedRecords) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.recordsFound = recordsFound;
		this.recordsNotFound = recordsNotFound;
		this.repeatedRecords = repeatedRecords;
	}

}
