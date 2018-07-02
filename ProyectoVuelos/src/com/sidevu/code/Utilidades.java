/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sidevu.code;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Locale;


import com.sidevu.code.modelo.*;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class Utilidades {


    public static final String FORMATO = "dd-MM-yyyy HH:mm";//El formato de las fechas

    /**
     * Sirve para convertir las entradas a fechas
     *
     * @param fecha 
     *      El string de la fecha que se convertira a un tipo de fecha
     * @return 
     *      Una fecha
     */
    public static Date fecha(String fecha) {
        SimpleDateFormat defaultDateFormat = new SimpleDateFormat(FORMATO, Locale.getDefault());
        Date myDate;
        try {
            myDate = defaultDateFormat.parse(fecha);
            return myDate;
        } catch (ParseException e) {

        }
        return null;
    }

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


    
    /**
     * Sirve para ver si una cadena es una fecha con formato valido
     *
     * @param texto 
     *      la cadena que se revisara para definir si cumple con el formato
     *      especificado de las fechas
     * 
     * @return
     *      regresa verdadero si el formato es valido
     */
    public static boolean formatoValido(String texto) {
        LocalDateTime localDateTime = null;
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(FORMATO, Locale.getDefault());

        try {
            localDateTime = LocalDateTime.parse(texto, fomatter);
            String result = localDateTime.format(fomatter);
            return result.equals(texto);
        } catch (DateTimeParseException e) {
            try {
                LocalDate ld = LocalDate.parse(texto, fomatter);
                String result = ld.format(fomatter);
                return result.equals(texto);
            } catch (DateTimeParseException exp) {
                try {
                    LocalTime lt = LocalTime.parse(texto, fomatter);
                    String result = lt.format(fomatter);
                    return result.equals(texto);
                } catch (DateTimeParseException e2) {

                }
            }
        }
        return false;
    }

    
    /**
     * obitene las horas entre dos fechas
     *
     * @param inicio 
     *      la fecha de inicio
     * @param fin   
     *      La fecha de finalizacion 
     * 
     * @return
     *      una String conteniendo las horas entre ambas fechas
     */
    public static String obtenerHoras(Date inicio, Date fin) {
        long segs = (fin.getTime() - inicio.getTime()) / 1000;
        long horas = segs / 3600;
        //segs = segs % 3600;
       // long mins = segs / 60;
       // segs = segs % 60;
        return "" + horas;
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
