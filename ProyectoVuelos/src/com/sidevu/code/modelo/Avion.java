/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.c
 */
package com.sidevu.code.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Equipo "Los chicos" primera edicion Arturo Aguilar
 * 
 * La clase avion, ya que se podran crear aviones al gusto del usuario
 */ 
public class Avion {

	/**
	 * El nombre del avion y el cupo del avion
	 */
	private String nombre;
	private int cupo;
	
	
	/**
	 * Todos los asientos del avion
	 */
	public List<Asiento> asientos = new ArrayList<Asiento>();
	
	/**
	 * Da vida a la instancia del avion
	 * @param nombre
	 * 	El nombre del avion
	 * @param cupo
	 * 	El cupo del avion
	 */
	public Avion(String nombre, int cupo) {
		this.nombre = nombre;
		this.cupo = cupo;
	}
	
	/**
	 * 
	 * @return
	 * 		Regresa el nombre del avion
	 */
	public String obtenerNombre() {
		return nombre;
	}
	
	/**
	 * 
	 * @return
	 * 		Regresa el cupo del avion
	 */
	public int obtenerCupo() {
		return cupo;
	}
	
	
	/**
	 * 
	 * @return
	 * 	Regresa la cantidad de asientos
	 */
	public int obtenerCantidadDeAsientos() {
		return asientos.size();
	}
	
	/**
	 * Asigna el nombre a un avion
	 * 
	 * @param nombre
	 * 		El nombre que sera asignado
	 */
	public void asignarNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Asigna el cupo a un avion
	 * 
	 * @param cupo
	 * 		El cupo que sera asignado al avion
	 */
	public void asignarCupo(int cupo) {
		this.cupo = cupo;
	}
        
	public boolean asientoDisponible(int numero) {
		for(int i = 0; i < asientos.size(); i++)
			if(numero==asientos.get(i).obtenerNumeroDeAsiento()) {
				System.out.println(asientos.get(i).obtenerNumeroDeAsiento());
				return false;
			}
				
		return true;
	}
        
        public List<Integer> asientosDisponibless() {
		List<Integer> asientosDisponibles = new ArrayList<Integer>();
		for(int i = 0; i < asientos.size(); i++)
			if(i!=asientos.get(i).obtenerNumeroDeAsiento())
				asientosDisponibles.add(i);
		return asientosDisponibles;
	}
}