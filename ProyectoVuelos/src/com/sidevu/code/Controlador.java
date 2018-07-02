/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. ed
 */
package com.sidevu.code;

import com.sidevu.code.Utilidades;
import com.sidevu.code.visual.*;
import com.sidevu.code.modelo.*;

import java.io.File;
import java.util.ArrayList;

import java.util.List;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * el proposito de esta clase es servir como intermediario entre el modelo y el
 * grafico.
 *
 * @author equipo joven
 *
 */
public class Controlador {

    public static List<Avion> aviones = new ArrayList<Avion>();// Una lista global con todos los aviones registrados
    public static List<Vuelo> vuelos = new ArrayList<Vuelo>();// Una lista global con todos los vuelos registrados

    public static void main(String args[]) {
        System.out.println(new File(".").getAbsolutePath());
        PantallaPrincipal frame = new PantallaPrincipal();
        frame.setVisible(true);
    }

    /**
     * Esta funcion crea una nueva instancia de un Avion, Sirve o se utiliza al
     * momento de crear vuelos, ya que un vuelo contiene la instancia de un
     * avion.
     *
     * @param nombre El nombre que tendra el avion
     * @param cupo El cupo que tendra el avion
     * @return El avion creado con los parametros
     */
    public static Avion crearInstanciaAvion(String nombre, int cupo) {
        return new Avion(nombre, cupo);
    }

    /**
     * Crea una instancia de una interfaz lista
     *
     * @param lista La cadena que contiene la lista a mostrar
     * @param titulo El titulo de la lista
     * @return La instancia creada con los parametros
     */
    public static Lista crearInstanciaLista(String lista, String titulo) {
        return new Lista(lista, titulo);
    }

    /**
     * Crea una nueva instancia de la registradora de vuelos
     *
     * @return La instancia para registrar un vuelo nuevo
     */
    public static RegistradoraDeVuelos crearInstanciaRegistradora() {
        return new RegistradoraDeVuelos();
    }

    /**
     * Crea una nueva instancia de la registradora de vuelos con el fin de
     * modificar un vuelo en especifico.
     */
    public static void crearInstanciaModificarVuelo() {
        Vuelo v;
        v = Controlador.obtenerVuelo(Utilidades.leerEntero("Inserta el numero de vuelo"));
        if (v == null) {
            return;
        }
        if (Utilidades.fecha(v.obtenerHoraDeSalida()).before(new Date())) {
            JOptionPane.showMessageDialog(null, "Ya no se puede modificar el vuelo");
            return;
        }
        RegistradoraDeVuelos editora = new RegistradoraDeVuelos(v.obtenerNumeroDeVuelo(), v.obtenerOrigen(),
                v.obtenerDestino(), v.obtenerHoraDeSalida(), v.obtenerHoraDeLlegada(), v.obtenerDuracion(),
                v.obtenerAvion().obtenerNombre(), v.obtenerAvion().obtenerCupo());
        editora.setVisible(true);
    }

    /**
     * Obtiene una instancia de vuelo comparando numeros de vuelo
     *
     * @param num El numero de vuelo
     * @return La instancia del vuelo correspondiente alnumero de vuelo
     */
    public static Vuelo obtenerVuelo(int num) {
        for (Vuelo v : vuelos) {
            if (num == v.obtenerNumeroDeVuelo()) {
                return v;
            }
        }
        return null;
    }

    /**
     * Esta funcion es llamada desde la interfaz grafica para crear un boleto,
     * tiene un proceso sencillo que primero esinsertar el numero de vuelo,
     * despues pedira el nombre de la persona y el asiento a escoger Si las
     * entradas fueron correctas, se añadira el boleto a la lista de boletos y
     * despues se actualizara el archivo de vuelos
     *
     * @param num El numero de vuelo al que sera asignado el boleto
     */
    public static void crearBoleto(int num) {
        for (Vuelo v : vuelos) {
            if (v.obtenerNumeroDeVuelo() == num) {
                Boleto b = v.crearBoleto(JOptionPane.showInputDialog("Inserta el nombre de la persona "),
                        Utilidades.leerEntero("Inserta el numero de asiento "));
                if (b != null) {
                    v.boletos.add(b);
                    JOptionPane.showMessageDialog(null, "Boleto creado");
                    v.escribirAJSON();
                } else {
                    JOptionPane.showMessageDialog(null, "Intentelo de nuevo");
                }
            }
        }
    }

    /**
     * Esta funcion carga todos los vuelos a la lista de vuelos global
     */
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

    /**
     * Revisa que un avion no "sobreponga su uso" en dos intervalos de fechas
     *
     * @param nombre El nombre del avion
     * @param start La fecha de inicio que se pretende utilizar
     * @param end La fecha final en la que se pretende dejar de utilizar el
     * avion
     * @return Si el avion no esta en uso
     */
    public static boolean avionDisponible(String nombre, Date start, Date end) {
        for (Vuelo v : vuelos) {
            if (v.obtenerAvion().obtenerNombre().equalsIgnoreCase(nombre)) {
                if ((start.before(Utilidades.fecha(v.obtenerHoraDeLlegada())) || start.compareTo(Utilidades.fecha(v.obtenerHoraDeLlegada())) == 0)
                        && (Utilidades.fecha(v.obtenerHoraDeSalida()).before(end) || Utilidades.fecha(v.obtenerHoraDeSalida()).compareTo(end) == 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Esta funcion carga todos los vuelos a la lista de vuelos global
     */
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

    /**
     * Elimina un avion del registro y de la lista
     *
     * @param nombre El nombre del avion a eliminar
     */
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

    /**
     * Elimina un vuelo de la lista global y tambien el archivo del mismo.
     *
     * @param numeroDeVuelo El numero de vuelo a ser borrado
     */
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

    /**
     * Esta funcion sirve para crear un vuelo si el <vuelo> ya existe, entonces
     * el vuelo anterior es eliminado posterioirmente se agrega a la lista
     * global de vuelos el vuelo creado, asi como tambien es escrito para
     * guardarlo
     *
     * @param avion Es el nombre del avion
     * @param numeroDeVuelo Es el numero de vuelo
     * @param origen Es el origen del vuelo
     * @param destino Es el destino del vuelo
     * @param horaDeSalida Ees la hora estimada de salida
     * @param horaDeLLegada Es la hora estimada de llegada
     * @param duracion Es la duracion estimada del vuelo
     */
    public static void crearVuelo(Avion avion, int numeroDeVuelo, String origen, String destino, String horaDeSalida,
            String horaDeLLegada, String duracion) {
        Vuelo vuelo = new Vuelo(avion, numeroDeVuelo, origen, destino, horaDeSalida, horaDeLLegada, duracion);
        eliminarVuelo(numeroDeVuelo);
        vuelos.add(vuelo);
        vuelo.escribirAJSON();
    }

    /**
     * Revisa si hay un avion en la lista de aviones que concuerde con el nombre
     * pasado como parametro
     *
     * @param text El nombre del avion que se quiere encontrar
     * @return Una instancia de un avion
     */
    public static Avion obtenerAvion(String text) {
        for (Avion v : aviones) {
            if (text.equalsIgnoreCase(v.obtenerNombre())) {
                return v;
            }
        }
        return null;
    }

    /**
     * Crea un avion nuevo, se añade a la lista y se escribe a JSON
     *
     * @param nombre nombre del avion
     * @param cupo Cupo del avion
     */
    public static void crearAvion(String nombre, int cupo) {
        Avion avion = new Avion(nombre, cupo);
        eliminarAvion(nombre);
        aviones.add(avion);
        avion.escribirAJSON();
    }

}
