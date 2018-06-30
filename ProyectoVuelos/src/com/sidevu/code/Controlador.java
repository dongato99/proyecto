/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sidevu.code;

import java.util.ArrayList;

import java.util.List;
import com.sidevu.code.modelo.*;
import javax.swing.JTextField;
import com.sidevu.code.Utilidades;

/**
 * a
 *
 * @author DELL
 */
public class Controlador {

    public static void main(String args[]) {

    }
    public static List<Avion> aviones = new ArrayList<Avion>();// Una lista global con todos los aviones registrados
    public static List<Vuelo> vuelos = new ArrayList<Vuelo>();// Una lista global con todos los vuelos registrados

    static Vuelo obtenerVuelo(int numeroDeVuelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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

    private static void eliminarVuelo(int numeroDeVuelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
