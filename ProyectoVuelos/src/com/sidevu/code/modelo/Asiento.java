/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sidevu.code.modelo;

/**
 * 
 * @author Equipo "joven" primera edicion por Arturo Aguilar
 *	Representa un asiento en un avion
 */
public class Asiento {
	
	private String tipo; //Si es reclinable, de lujo, etc..
	private int asiento;//el numero de asiento
	
	/**
	 * El constructor del asiento
	 * 
	 * @param asiento
	 * 		El numero de asiento que tendra la instancia creada
	 */
	public Asiento(int asiento) {
		this.asiento = asiento;
	}
	
	
	/**
	 * @return
	 * 		El numero de asiento
	 */
	public int obtenerNumeroDeAsiento() {
		return asiento;
	}
	
	/**
	 * 
	 * @return
	 * 		El tipo de asiento
	 */
	public String obtenerTipo() {
		return tipo;
	}
        
        /**
	 * Asigna el tipo de asiento
	 * 
	 * @param tipo
	 * 		El tipo de asiento que sera asignado
	 */
        public void asignarTipo(String tipo) {
            this.tipo = tipo;
        }
}
