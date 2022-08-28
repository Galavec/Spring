
package com.galavec.findstrings.dto;

import java.util.Arrays;

public class RegistrosNoEncontrados {
	private int cantidadNoEncontrada;
	private String[] textoNoEncontrado;

	public int getCantidadNoEncontrada() {
		return cantidadNoEncontrada;
	}

	public void setCantidadNoEncontrada(int cantidadNoEncontrada) {
		this.cantidadNoEncontrada = cantidadNoEncontrada;
	}

	public String[] getTextoNoEncontrado() {
		return textoNoEncontrado;
	}

	public void setTextoNoEncontrado(String[] textoNoEncontrado) {
		this.textoNoEncontrado = textoNoEncontrado;
	}

	@Override
	public String toString() {
		return "RegistrosNoEncontrados [cantidadNoEncontrada=" + cantidadNoEncontrada + ", textoNoEncontrado="
				+ Arrays.toString(textoNoEncontrado) + "]";
	}

}
