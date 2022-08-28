
package com.galavec.findstrings.dto;

import lombok.Data;

@Data
public class ResponseWillExist {
	private Integer responseCode;
	private String responseMessage;
	private RegistrosEncontrados registrosEncontrados;
	private RegistrosNoEncontrados registrosNoEncontrados;
	private RegistrosRepetidos registrosRepetidos;

	public ResponseWillExist(Integer responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public ResponseWillExist() {
		super();
	}

	public ResponseWillExist(Integer responseCode, String responseMessage, RegistrosEncontrados registrosEncontrados,
			RegistrosNoEncontrados registrosNoEncontrados, RegistrosRepetidos registrosRepetidos) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.registrosEncontrados = registrosEncontrados;
		this.registrosNoEncontrados = registrosNoEncontrados;
		this.registrosRepetidos = registrosRepetidos;
	}

}
