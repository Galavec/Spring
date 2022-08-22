
package com.galavec.findstrings.entity;

public class ResponseService {
	private RegistrosEncontrados registrosEncontrados;
	private RegistrosNoEncontrados registrosNoEncontrados;
	private RegistrosRepetidos registrosRepetidos;

	public RegistrosEncontrados getRegistrosEncontrados() {
		return registrosEncontrados;
	}

	public void setRegistrosEncontrados(RegistrosEncontrados registrosEncontrados) {
		this.registrosEncontrados = registrosEncontrados;
	}

	public RegistrosNoEncontrados getRegistrosNoEncontrados() {
		return registrosNoEncontrados;
	}

	public void setRegistrosNoEncontrados(RegistrosNoEncontrados registrosNoEncontrados) {
		this.registrosNoEncontrados = registrosNoEncontrados;
	}

	public RegistrosRepetidos getRegistrosRepetidos() {
		return registrosRepetidos;
	}

	public void setRegistrosRepetidos(RegistrosRepetidos registrosRepetidos) {
		this.registrosRepetidos = registrosRepetidos;
	}

	@Override
	public String toString() {
		return "ResponseService [registrosEncontrados=" + registrosEncontrados + ", registrosNoEncontrados="
				+ registrosNoEncontrados + ", registrosRepetidos=" + registrosRepetidos + "]";
	}

}
