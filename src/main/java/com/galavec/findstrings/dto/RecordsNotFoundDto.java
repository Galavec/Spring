package com.galavec.findstrings.dto;

import lombok.Data;

@Data
public class RecordsNotFoundDto {
	private Integer quantityNotFound;
	private String[] textNotFound;

}
