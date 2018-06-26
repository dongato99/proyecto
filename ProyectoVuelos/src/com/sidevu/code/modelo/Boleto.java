/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sidevu.code.modelo;

/**
 * 
 * @author Equipo "joven" primera edicion por Arturo Aguilar
 *	Representa un boleto que esta ligado a un vuelo
 */
public class Boleto {
    /**
	 * El nombre del pasajero y el asiento del pasajero
	 */
	private String nombre;
	private Asiento asiento;
	
	/**
	 * Crea un nuevo boleto a partir del nombre
	 * @param nombre
	 * 	El nombre de la persona
	 */
	public Boleto(String nombre) {
		this.nombre = nombre;
	}
	
	 /**
	  * 
	  * @param asiento
	  * 	El asiento que tendra el pasajero
	  */
	public void asignarAsiento(Asiento asiento) {
		this.asiento = asiento;
	}
	
	/**
	 * 	
	 * @return
	 * 	el nombre del pasajero
	 */
	public String obtenerNombre() {
		return nombre;
	}
	
	/**
	 * 
	 * @return
	 *  El asiento del pasajero
	 */
	public Asiento obtenerAsiento() {
		return asiento;
	}
}
