
package com.galavec.findstrings.dto;

import java.util.Arrays;

public class RegistrosRepetidos {
	private int cantidadTextoRepetido;
	private String[] textoRepetido;

	public int getCantidadTextoRepetido() {
		return cantidadTextoRepetido;
	}

	public void setCantidadTextoRepetido(int cantidadTextoRepetido) {
		this.cantidadTextoRepetido = cantidadTextoRepetido;
	}

	public String[] getTextoRepetido() {
		return textoRepetido;
	}

	public void setTextoRepetido(String[] textoRepetido) {
		this.textoRepetido = textoRepetido;
	}

	@Override
	public String toString() {
		return "RegistrosRepetidos [cantidadTextoRepetido=" + cantidadTextoRepetido + ", textoRepetido="
				+ Arrays.toString(textoRepetido) + "]";
	}

}
