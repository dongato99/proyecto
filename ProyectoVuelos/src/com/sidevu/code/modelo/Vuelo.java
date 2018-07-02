/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sidevu.code.modelo;

import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 *
 * @author Equipo "joven" primera edicion por Arturo Aguilar Representa un vuelo
 * en el sistema
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
     * @param numeroDeVuelo El numero de vuelo
     */
    public Vuelo(int numeroDeVuelo) {
        this.numeroDeVuelo = numeroDeVuelo;
    }

    /**
     * Crea un vuelo con todos los atributos que este lleva
     *
     * @param avion El avion del vuelo
     * @param numeroDeVuelo El numero del vuelo
     * @param origen El orgien del vuelo
     * @param destino El destino del vuelo
     * @param horaDeSalida La hora de salida
     * @param horaDeLLegada La hora de llegada
     * @param duracion La duraacion del vuelo
     */
    public Vuelo(Avion avion, int numeroDeVuelo, String origen, String destino, String horaDeSalida, String horaDeLLegada, String duracion) {
        this.numeroDeVuelo = numeroDeVuelo;
        this.avion = avion;
        this.origen = origen;
        this.destino = destino;
        this.horaDeSalida = horaDeSalida;
        this.horaDeLLegada = horaDeLLegada;
        this.duracion = duracion;
    }

        public Boleto crearBoleto(String nombre, int asiento) {
		if(avion.obtenerCupo()==vendidos())
			return null;
		if(asiento > avion.obtenerCupo() || asiento < 1)
			return null;
		Boleto b;
		Asiento a = new Asiento(asiento);		
		if(avion.asientoDisponible(asiento)) {
			b = new Boleto(nombre);
			b.asignarAsiento(a);
			avion.asientos.add(a);
		} else {
			asiento = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("El asiento ha sido escogido, escoge otro"));
			return crearBoleto(nombre, asiento);
		}
		return b;
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

     
    public Vuelo leerJSON() {
        Vuelo vuelo = new Vuelo(numeroDeVuelo);
        JSONParser parser = new JSONParser();

        try {
            FileReader file = new FileReader("./sidevu/" + obtenerNumeroDeVuelo() + ".txt");
            Object obj = parser.parse(file);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray strings = (JSONArray) jsonObject.get("lista");
            vuelo.numeroDeVuelo = Integer.parseInt(strings.get(0).toString().replaceAll("Numero:", ""));
            vuelo.origen = strings.get(1).toString().replaceAll("Origen:", "");
            vuelo.destino = strings.get(2).toString().replaceAll("Destino:", "");
            vuelo.horaDeSalida = strings.get(3).toString().replaceAll("Salida:", "");
            vuelo.horaDeLLegada = strings.get(4).toString().replaceAll("LLegada:", "");
            vuelo.duracion = strings.get(5).toString().replaceAll("Duracion:", "");
            vuelo.vendidos = Integer.parseInt(strings.get(6).toString().replaceAll("Vendidos:", ""));
            vuelo.avion = new Avion(strings.get(7).toString().replaceAll("AvionNombre:", ""),
                    Integer.parseInt(strings.get(8).toString().replaceAll("AvionCupo:", "")));
            for (int i = 9; i < strings.size(); i = i + 2) {
                Boleto boleto = new Boleto(strings.get(i).toString().replaceAll("BoletoNombre:", ""));
                Asiento asiento = new Asiento(Integer.parseInt(strings.get(i + 1).toString().replaceAll("BoletoAsiento:", "")));
                boleto.asignarAsiento(asiento);
                vuelo.avion.asientos.add(asiento);
                vuelo.boletos.add(boleto);
            }
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return vuelo;
    }

    public void eliminarBoleto(String nombre) {
        for (Boleto b : boletos) {
            if (nombre.equalsIgnoreCase(b.obtenerNombre())) {
                boletos.remove(b);
                avion.asientos.remove(b.obtenerAsiento());
                 // escribirAJSON();
                JOptionPane.showMessageDialog(null, "Boleto eliminado");
                return;
            }
        }
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

        
    
   
}
