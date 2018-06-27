/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sidevu.code.visual;

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
 * @author DELL
 */
public class PantallaPrincipal extends JFrame {

    private JPanel contentPane;
    public static final String[] MENU = {"Crear Vuelo", "Eliminar Vuelo", "Eliminar Boleto", "Crear Boleto",
         "Ver Vuelos", "Ver Pasajeros", "Modificar Vuelo", "Crear Avion"};

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

                        break;
                    case "ELIMINAR VUELO":

                        break;
                    case "MODIFICAR VUELO":

                        break;
                    case "VER PASAJEROS":

                        break;
                    case "CREAR BOLETO":

                        break;
                    case "ELIMINAR BOLETO":

                        break;
                    case "VER VUELOS":

                        break;
                    case "CREAR AVION":

                        break;
                    default:

                        break;
                }

            }
        });
        seleccionar.setBackground(SystemColor.control);
        seleccionar.setBounds(0, 0, 132, 52);
        contentPane.add(seleccionar);
    }
}
