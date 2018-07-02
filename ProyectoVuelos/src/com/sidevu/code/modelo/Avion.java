/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.c
 */
package com.sidevu.code.modelo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Equipo "Los chicos" primera edicion Arturo Aguilar
 *
 * La clase avion, ya que se podran crear aviones al gusto del usuario
 */
public class Avion {

    /**
     * El nombre del avion y el cupo del avion
     */
    private String nombre;
    private int cupo;

    public List<Asiento> asientos = new ArrayList<Asiento>();//todos los asientos del avion

    /**
     * Da vida a la instancia del avion
     *
     * @param nombre El nombre del avion
     * @param cupo El cupo del avion
     */
    public Avion(String nombre, int cupo) {
        this.nombre = nombre;
        this.cupo = cupo;
    }

    /**
     * Crea una instancia sin cupo
     *
     * @param nombre El nombre del avion
     */
    public Avion(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return Regresa el nombre del avion
     */
    public String obtenerNombre() {
        return nombre;
    }

    /**
     *
     * @return Regresa el cupo del avion
     */
    public int obtenerCupo() {
        return cupo;
    }

    /**
     *
     * @return Regresa la cantidad de asientos
     */
    public int obtenerCantidadDeAsientos() {
        return asientos.size();
    }

    /**
     * Asigna el nombre a un avion
     *
     * @param nombre El nombre que sera asignado
     */
    public void asignarNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Asigna el cupo a un avion
     *
     * @param cupo El cupo que sera asignado al avion
     */
    public void asignarCupo(int cupo) {
        this.cupo = cupo;
    }

    public boolean asientoDisponible(int numero) {
        for (int i = 0; i < asientos.size(); i++) {
            if (numero == asientos.get(i).obtenerNumeroDeAsiento()) {
                System.out.println(asientos.get(i).obtenerNumeroDeAsiento());
                return false;
            }
        }

        return true;
    }

    public List<Integer> asientosDisponibless() {
        List<Integer> asientosDisponibles = new ArrayList<Integer>();
        for (int i = 0; i < asientos.size(); i++) {
            if (asientoDisponible(i)) {
                asientosDisponibles.add(i);
            }
        }
        return asientosDisponibles;
    }

    public Avion leerJSON() {
        Avion avion = null;
        JSONParser parser = new JSONParser();

        try {
            FileReader file = new FileReader("./sidevu/aviones/" + nombre + ".txt");
            Object obj = parser.parse(file);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray strings = (JSONArray) jsonObject.get("lista");
            avion = new Avion(strings.get(0).toString().replaceAll("nombre:", ""),
                    Integer.parseInt(strings.get(1).toString().replaceAll("cupo:", "")));
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return avion;
    }

    public void escribirAJSON() {
        JSONObject obj = new JSONObject();
        JSONArray strings = new JSONArray();
        strings.add("nombre:" + nombre);
        strings.add("cupo:" + cupo);
        obj.put("lista", strings);
        try (FileWriter file = new FileWriter("./sidevu/aviones/" + nombre + ".txt")) {
            file.write(obj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + obj);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
