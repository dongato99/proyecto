/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sidevu.code;

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
    public static Vuelo obtenerVuelo(int numero){
        return null;
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

    public static List<Avion> aviones = new ArrayList<Avion>();// Una lista global con todos los aviones registrados
    public static List<Vuelo> vuelos = new ArrayList<Vuelo>();// Una lista global con todos los vuelos registrados

    public static void main(String args[]) {

    }

    public static  Avion obtenerAvion(String nombreAvion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
