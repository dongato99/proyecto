/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sidevu.code.modelo;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 
 * @author Equipo "joven" primera edicion por Arturo Aguilar
 *	Representa un vuelo en el sistema
 */
public class Vuelo {

	private int vendidos;//la cantidad de boletos vendidos
	private int numeroDeVuelo;//el numero de vuelo 
	private Avion avion;//El avion del vuelo
	public List<Boleto> boletos = new ArrayList<Boleto>();//los boletos del vuelo
	private String origen, destino, horaDeSalida, horaDeLLegada, duracion; //Los atributos extras del vuelo
	

	/**
	 * Crea un vuelo solo con el numero de vuelo
	 * 
	 * @param numeroDeVuelo
	 * 		El numero de vuelo
	 */
	public Vuelo (int numeroDeVuelo) {
		this.numeroDeVuelo = numeroDeVuelo;
	}
	
	/**
	 * Crea un vuelo con todos los atributos que este lleva
	 * @param avion
	 * 		El avion del vuelo
	 * @param numeroDeVuelo
	 * 		El numero del vuelo
	 * @param origen
	 * 		El orgien del vuelo
	 * @param destino
	 * 		El destino del vuelo
	 * @param horaDeSalida
	 * 		La hora de salida
	 * @param horaDeLLegada
	 * 		La hora de llegada
	 * @param duracion
	 * 		La duraacion del vuelo
	 */
	public Vuelo (Avion avion, int numeroDeVuelo, String origen, String destino, String horaDeSalida, String horaDeLLegada, String duracion) {
		this.numeroDeVuelo = numeroDeVuelo;
		this.avion = avion;
		this.origen = origen;
		this.destino = destino;
		this.horaDeSalida = horaDeSalida;
		this.horaDeLLegada = horaDeLLegada;
		this.duracion = duracion;
	}
        
        
        /*
	 * Posteriormente se encuentran getters y setters que se explican solos,
	 *  ya sea asignan un valor a una variable o obtienen algun valor
	 */
	
	public int vendidos () {
		return avion.obtenerCantidadDeAsientos();
	}
	
	public void asignarOrigen(String origen) { 
		this.origen = origen;
	}
	
	public void asignarDestino(String destino) { 
		this.destino = destino;
	}
	
	public void asignarSalida(String salida) {
		this.horaDeSalida = salida;
	}
	
	public void asignarLlegada(String llegada) {
		this.horaDeLLegada = llegada;
	}
	
	public void asignarDuracion(String duracion) {
		this.duracion = duracion;
	}
	
	public String obtenerOrigen() {
		return origen;
	}
	
	public String obtenerDestino() {
		return destino;
	}
	
	public String obtenerHoraDeSalida() {
		return horaDeSalida;
	}
	
	public String obtenerHoraDeLlegada() {
		return horaDeLLegada;
	}
	
	public String obtenerDuracion () {
		return duracion;
	}
	
	public int obtenerNumeroDeVuelo() {
		return numeroDeVuelo;
	}
	public Avion obtenerAvion() {
		return avion;
	}
        public void ordenarPasajeros() {
		Collections.sort(boletos, Comparator.comparing(Boleto::obtenerNombre));
	}
}
