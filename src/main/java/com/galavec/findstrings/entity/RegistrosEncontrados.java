
package com.galavec.findstrings.entity;

import java.util.Arrays;

public class RegistrosEncontrados {
	private int cantidadEncontrada;
	private String[] textoEncontrado;

	public int getCantidadEncontrada() {
		return cantidadEncontrada;
	}

	public void setCantidadEncontrada(int cantidadEncontrada) {
		this.cantidadEncontrada = cantidadEncontrada;
	}

	public String[] getTextoEncontrado() {
		return textoEncontrado;
	}

	public void setTextoEncontrado(String[] textoEncontrado) {
		this.textoEncontrado = textoEncontrado;
	}

	@Override
	public String toString() {
		return "RegistrosEncontrados [cantidadEncontrada=" + cantidadEncontrada + ", textoEncontrado="
				+ Arrays.toString(textoEncontrado) + "]";
	}

}
