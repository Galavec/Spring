
package com.galavec.findstrings.dto;

import lombok.Data;

@Data
public class RequestFindStrings {
	private String ordenesId;
	private String lineaAEvitar;
	private String fileWhereToLook;

}
