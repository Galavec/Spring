package com.galavec.findstrings.dto;

import lombok.Data;

@Data
public class RequestFindStrings {
	private String stringToSearch;
	private String lineToAvoid;
	private String fileWhereToLook;

}
