package com.galavec.findstrings.dto;

import lombok.Data;

@Data
public class RecordsFoundDto {
	private Integer quantityFound;
	private String[] foundText;

}
