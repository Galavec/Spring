package com.galavec.findstrings.dto;

import lombok.Data;

@Data
public class RepeatedRecordsDto {
	private Integer amountOfRepeatedText;
	private String[] repeatedText;

}
