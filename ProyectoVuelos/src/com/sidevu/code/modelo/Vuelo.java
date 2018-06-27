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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        
        public void escribirAJSON() {
		JSONObject obj = new JSONObject();
		JSONArray strings = new JSONArray();
		strings.add("Numero:"+ numeroDeVuelo);
		strings.add("Origen:"+ origen);
		strings.add("Destino:"+ destino);
		strings.add("Salida:"+ horaDeSalida);
		strings.add("LLegada:"+ horaDeLLegada);
		strings.add("Duracion:"+ duracion);
		strings.add("Vendidos:"+ vendidos());
		strings.add("AvionNombre:"+ avion.obtenerNombre());
		strings.add("AvionCupo:"+ avion.obtenerCupo());
	
		
		for(int i = 0; i < vendidos(); i++) {
			strings.add("BoletoNombre:"+ boletos.get(i).obtenerNombre());
			strings.add("BoletoAsiento:"+ boletos.get(i).obtenerAsiento().obtenerNumeroDeAsiento());
		}
		obj.put("lista", strings);
		try (FileWriter file = new FileWriter("./sidevu/"+obtenerNumeroDeVuelo()+".txt")) {
			file.write(obj.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + obj);
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}
}
