/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. ed
 */
package com.sidevu.code;


import java.util.Date;



import javax.swing.JTextField;
import com.sidevu.code.Utilidades;

import com.sidevu.code.modelo.Avion;
import com.sidevu.code.modelo.Vuelo;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * a
 *
 * @author DELL
 */
public class Controlador {


   

    public static void cargarVuelos() {
        int num;
        File carpeta = new File("./sidevu/");
        File[] listaDeArchivos = carpeta.listFiles();
        if (listaDeArchivos != null) {
            for (File child : listaDeArchivos) {
                if (Utilidades.esEntero(child.getName().replaceAll(".txt", ""))) {
                    num = Integer.parseInt(child.getName().replaceAll(".txt", ""));
                    Vuelo v = new Vuelo(num);
                    v = v.leerJSON();
                    vuelos.add(v);
                }
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "No existen vuelos para cargar");
        }
    }

    public static void eliminarAvion(String nombre) {
        Avion v;
        v = Controlador.obtenerAvion(nombre);
        if (v == null) {
            return;
        }
        File file = new File("./sidevu/aviones/" + nombre + ".txt");
        aviones.remove(v);
        if (file.delete()) {
            javax.swing.JOptionPane.showMessageDialog(null, "avion eliminado " + nombre + "");
        }
        return;
    }
  

    public static void eliminarVuelo(int numeroDeVuelo) {
        
        Vuelo v = Controlador.obtenerVuelo(numeroDeVuelo);
        if (v == null) {
            return;
        }
        File file = new File("./sidevu/" + numeroDeVuelo + ".txt");
        vuelos.remove(v);
        if (file.delete()) {
            javax.swing.JOptionPane.showMessageDialog(null, "Vuelo eliminado " + numeroDeVuelo + "");
        }
        return;

    }

    public static void cargarAviones() {
        String num;
        File carpeta = new File("./sidevu/aviones");
        File[] listaDeArchivos = carpeta.listFiles();
        if (listaDeArchivos != null) {
            for (File child : listaDeArchivos) {
                num = child.getName().replaceAll(".txt", "");
                Avion avion = new Avion(num);
                avion = avion.leerJSON();
                aviones.add(avion);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "No existen vuelos para cargar");
        }
    }

   
    public static void main(String args[]) {

    }

    
    public static List<Avion> aviones = new ArrayList<Avion>();// Una lista global con todos los aviones registrados
    public static List<Vuelo> vuelos = new ArrayList<Vuelo>();// Una lista global con todos los vuelos registrados

 

    /**
     * Esta funcion sirve para modificar el vuelo, se pasan como parametros los
     * componentes de la interfaz para poder extraer el texto de ellas, despues
     * se asignan al vuelo y el vuelo se escribe para guardarlo
     *
     * @param destino La textfield que contiene la cadena del destino
     * @param duracion La textfield que contiene la cadena de duracion
     * @param horaDeLLegada La textfield que contiene la cadena de la hora de
     * llegada
     * @param origen La textfield que contiene la cadena de origen
     * @param cupo La textfield que contiene una cadena a ser parseada para
     * obtener el entero del cupo
     * @param nombreDeAvion La textfield que contiene la cadena del nombre del
     * avion
     * @param numeroDeVuelo La textfield que contiene la cadena a ser parseada
     * para obtener el entero del numero de vuelo
     */
    public static void modificarVuelo(JTextField destino, JTextField horaDeLLegada, JTextField horaDeSalida,
            JTextField origen, Avion av, JTextField numeroDeVuelo) {
        Vuelo v = Controlador.obtenerVuelo(Integer.parseInt(numeroDeVuelo.getText()));
        v.asignarDestino(destino.getText());
        v.asignarDuracion(
                Utilidades.obtenerHoras(Utilidades.fecha(horaDeSalida.getText()), Utilidades.fecha(horaDeLLegada.getText())));
        v.asignarLlegada(horaDeLLegada.getText());
        v.asignarOrigen(origen.getText());
        v.asignarSalida(horaDeSalida.getText());
        v.obtenerAvion().asignarNombre(av.obtenerNombre());
        v.obtenerAvion().asignarCupo(av.obtenerCupo());
        v.escribirAJSON();
    }

    public static void crearVuelo(Avion avion, int numeroDeVuelo, String origen, String destino, String horaDeSalida,
            String horaDeLLegada, String duracion) {
        Vuelo vuelo = new Vuelo(avion, numeroDeVuelo, origen, destino, horaDeSalida, horaDeLLegada, duracion);
        eliminarVuelo(numeroDeVuelo);
        vuelos.add(vuelo);
        vuelo.escribirAJSON();
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
      

}
