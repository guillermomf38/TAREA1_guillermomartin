/**
 *Clase ComparadorNombreEspectaculo.java
 * 
 *@author Guillermo Martin Fueyo
 *@version 1.0
 */



package utils;

import java.util.Comparator;

import entidades.Espectaculo;

public class ComparadorNombreEspectaculo  implements Comparator <Espectaculo> {

	@Override
	public int compare(Espectaculo espectaculo1, Espectaculo espectaculo2) {
		
		return espectaculo1.getNombre().compareToIgnoreCase(espectaculo2.getNombre());
	}

}
