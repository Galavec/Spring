package com.galavec.findstrings.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecordsFoundDto {
	private Integer quantityFound;
	private String[] foundTexts;
	private List<DetailedSearchDto> detailedSearch;

}
