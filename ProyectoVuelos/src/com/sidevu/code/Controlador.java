/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sidevu.code;

import com.sidevu.code.modelo.Avion;
import com.sidevu.code.modelo.Vuelo;
import java.util.ArrayList;
import java.util.List;

/**a
 *
 * @author DELL
 */
public class Controlador {
    public static void main(String args[]) {
        
    }
    
    public static Avion obtenerAvion(String text) 
    {
		for (Avion v : aviones) {
			if (text.equalsIgnoreCase(v.obtenerNombre()))
				return v;
		}
		return null;
    }
    
    public static void crearAvion(String nombre, int cupo) 
    {
		Avion avion = new Avion(nombre, cupo);
		eliminarAvion(nombre);
		aviones.add(avion);
		avion.escribirAJSON();
    }
    
    public static List<Avion> aviones = new ArrayList<Avion>();// Una lista global con todos los aviones registrados
    
    public static List<Vuelo> vuelos = new ArrayList<Vuelo>();// Una lista global con todos los vuelos registrados

    private static void eliminarAvion(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
