/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sidevu.code;

import com.sidevu.code.modelo.*;
/**
 *
 * @author DELL
 */
public class Utilidades {

    public static boolean esEntero(String s) {
        try {
            int num = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int leerEntero(String mensaje) {
        try {
            int num = Integer.parseInt(javax.swing.JOptionPane.showInputDialog(mensaje));
            return num;
        } catch (NumberFormatException e) {
            return leerEntero(mensaje);
        }

    }

    
    public static String obtenerListaDeVuelos() {
		String lista = "";
		for (Vuelo v : Controlador.vuelos) {
			lista = lista + "numero de vuelo: " + v.obtenerNumeroDeVuelo() + "\n";
			lista = lista + "origen: " + v.obtenerOrigen() + "\n";
			lista = lista + "destino: " + v.obtenerDestino() + "\n";
			lista = lista + "hora de salida: " + v.obtenerHoraDeSalida() + "\n";
			lista = lista + "hora de llegada: " + v.obtenerHoraDeLlegada() + "\n";
			lista = lista + "duracion: " + v.obtenerDuracion() + "\n";
			lista = lista + "nombre de avion : " + v.obtenerAvion().obtenerNombre() + "\n";
			lista = lista + "cupo del vuelo : " + v.obtenerAvion().obtenerCupo() + "\n";
			lista = lista + "vendidos : " + v.vendidos() + "\n";
			lista = lista + "\n";
		}
		return lista;
	}
    
    public static String obtenerListaDePasajerosOrdenada(int numeroDeVuelo) {
		String lista = "";
		Vuelo v = Controlador.obtenerVuelo(numeroDeVuelo);
		v.ordenarPasajeros();
		for (Boleto b : v.boletos)
			lista = lista + b.obtenerNombre() + "\n";

		return lista;
	}
}
