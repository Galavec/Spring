
package com.galavec.findstrings.entity;

public class RequestService {
	private String ordenesId;
	private String lineaAEvitar;
	private String nombreLog;

	public String getOrdenesId() {
		return ordenesId;
	}

	public void setOrdenesId(String ordenesId) {
		this.ordenesId = ordenesId;
	}

	public String getLineaAEvitar() {
		return lineaAEvitar;
	}

	public void setLineaAEvitar(String lineaAEvitar) {
		this.lineaAEvitar = lineaAEvitar;
	}

	public String getNombreLog() {
		return nombreLog;
	}

	public void setNombreLog(String nombreLog) {
		this.nombreLog = nombreLog;
	}

	@Override
	public String toString() {
		return "RequestService [ordenesId=" + ordenesId + ", lineaAEvitar=" + lineaAEvitar + ", nombreLog=" + nombreLog
				+ "]";
	}

}
