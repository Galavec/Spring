package com.galavec.findstrings.dto;

import lombok.Data;

@Data
public class ResponseWillExist {
	private Integer responseCode;
	private String responseMessage;
	private RecordsFoundDto recordsFoundDto;
	private RecordsNotFoundDto recordsNotFoundDto;
	private RepeatedRecordsDto repeatedRecordsDto;

	public ResponseWillExist(Integer responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public ResponseWillExist() {
		super();
	}

	public ResponseWillExist(Integer responseCode, String responseMessage, RecordsFoundDto recordsFoundDto,
			RecordsNotFoundDto recordsNotFoundDto, RepeatedRecordsDto repeatedRecordsDto) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.recordsFoundDto = recordsFoundDto;
		this.recordsNotFoundDto = recordsNotFoundDto;
		this.repeatedRecordsDto = repeatedRecordsDto;
	}

}
