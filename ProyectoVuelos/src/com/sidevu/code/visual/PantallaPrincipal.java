/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sidevu.code.visual;

import com.sidevu.code.Controlador;
import com.sidevu.code.Utilidades;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author equipo "Los chicos"
 *
 * Esta clase es la primera interfaz que será desplegada al usuario, es una
 * interfaz que contiene un menu con todas las opciones del sistema
 */
public class PantallaPrincipal extends JFrame {

    private JPanel contentPane;

    //El menu de la combo
    public static final String[] MENU = {"Crear Vuelo", "Eliminar Vuelo", "Eliminar Boleto", "Crear Boleto",
        "Ver Vuelos", "Ver Pasajeros", "Modificar Vuelo", "Crear Avion"};

    /**
     * Basicamente crea la interfaz con todos los componentes, que son la combo
     * box y un boton.
     */
    public PantallaPrincipal() {
        setTitle("Sistema de Vuelos");
        setBackground(SystemColor.desktop);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 95);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JComboBox<String> menu = new JComboBox<String>(MENU);
        menu.setBackground(SystemColor.activeCaption);
        menu.setBounds(142, 0, 292, 52);
        contentPane.add(menu);
        JButton seleccionar = new JButton("Seleccionar ");
        seleccionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                switch (menu.getSelectedItem().toString().toUpperCase()) {
                    case "CREAR VUELO":
                        Controlador.crearInstanciaRegistradora().setVisible(true);
                        break;
                    case "ELIMINAR VUELO":
                        Controlador.eliminarVuelo(Utilidades.leerEntero("Inserta el numero del vuelo a borrar"));
                        break;
                    case "MODIFICAR VUELO":
                        Controlador.crearInstanciaModificarVuelo();
                        break;
                    case "VER PASAJEROS":
                        Controlador.crearInstanciaLista(Utilidades.obtenerListaDePasajerosOrdenada(Utilidades.leerEntero("Inserta el vuelo")), "Lista de pasajeros")
                                .setVisible(true);
                        ;
                        break;
                    case "CREAR BOLETO":
                        Controlador.crearBoleto(Utilidades.leerEntero("Inserta el numero de vuelo "));
                        break;
                    case "ELIMINAR BOLETO":
                        Controlador.obtenerVuelo(Utilidades.leerEntero("Inserta el numero de vuelo ")).
                                eliminarBoleto(JOptionPane.showInputDialog("Inserta el nombre de la persona "));
                        break;
                    case "VER VUELOS":
                        Controlador.crearInstanciaLista(Utilidades.obtenerListaDeVuelos(), "Lista de vuelos").setVisible(true);
                        break;
                    case "CREAR AVION":
                        Controlador.crearAvion(JOptionPane.showInputDialog("Inserta el nombre del avion"), Utilidades.leerEntero("Inserta el cupo"));
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opcion Invalida");
                        break;
                }

            }
        });
        seleccionar.setBackground(SystemColor.control);
        seleccionar.setBounds(0, 0, 132, 52);
        contentPane.add(seleccionar);
    }
}
