/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. ed
 */
package com.sidevu.code;


import java.util.Date;



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
    

    	/**
	 * Obtiene una instancia de vuelo comparando numeros de vuelo
	 * 
	 * @param num
	 *            El numero de vuelo
	 * @return La instancia del vuelo correspondiente alnumero de vuelo
	 */
	public static Vuelo obtenerVuelo(int num) {
		for (Vuelo v : vuelos) {
			if (num == v.obtenerNumeroDeVuelo())
				return v;
		}
		return null;
	}

    
    public static List<Avion> aviones = new ArrayList<Avion>();// Una lista global con todos los aviones registrados
	public static List<Vuelo> vuelos = new ArrayList<Vuelo>();// Una lista global con todos los vuelos registrados
    	/**
	 * Revisa que un avion no "sobreponga su uso" en dos intervalos de fechas
	 * 
	 * @param nombre
	 * 		El nombre del avion
	 * @param start
	 * 		La fecha de inicio que se pretende utilizar
	 * @param end
	 * 		La fecha final en la que se pretende dejar de utilizar el avion
	 * @return
	 * 		Si el avion no esta en uso
	 */
	public static boolean avionDisponible(String nombre, Date start, Date end) {

		for (Vuelo v : vuelos) {
			if (v.obtenerAvion().obtenerNombre().equalsIgnoreCase(nombre)) {
			
					if( (start.before(Utilidades.fecha(v.obtenerHoraDeLlegada())) || start.compareTo(Utilidades.fecha(v.obtenerHoraDeLlegada()))==0 ) 
							&& (Utilidades.fecha(v.obtenerHoraDeSalida()).before(end) || Utilidades.fecha(v.obtenerHoraDeSalida()).compareTo(end)==0 ))  {
						return false;
					}
			}

		}
		return true;
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
    

    private static void eliminarAvion(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
