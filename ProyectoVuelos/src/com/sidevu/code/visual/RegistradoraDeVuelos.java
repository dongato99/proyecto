/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sidevu.code.visual;

import com.sidevu.code.Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

/**
 *
 * @author DELL
 */
public class RegistradoraDeVuelos extends JFrame {

    private JPanel contentPane;//el panel donde se pondran todos los componentes

    private JTextField numeroDeVuelo;//textfield donde se insertara el numero de vuelo
    private JTextField horaDeLLegada;//textfield donde se insertara la fecha de llegada
    private JTextField origen;//textfield donde se insertara el origen
    private JTextField destino;//textfield donde se insertara el destino
    private JTextField horaDeSalida;//textfield donde se insertara la hora de salida
    private String aviones[];//String que se llenara con los aviones para para la comboBox
    private JComboBox<String> comboBox;//la comboBox que tendra todos los aviones
    private JLabel lblvuelo;//label que indicara que poner en una textfield
    private JLabel lblorigen;//label que indicara que poner en una textfield
    private JLabel lblDestino;//label que indicara que poner en una textfield
    private JLabel lblHoraDeSalida;//label que indicara que poner en una textfield
    private JLabel lblHoraDeLlegada;//label que indicara que poner en una textfield
    private JLabel lblNombreAvion;//label que indicara que poner en una textfield
    private JButton btnCrearVuelo;//el boton para crear/modificar el vuelo

    public RegistradoraDeVuelos() {

        setTitle("Alta Vuelos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 427, 183);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        numeroDeVuelo = new JTextField();
        numeroDeVuelo.setBounds(10, 35, 86, 20);
        contentPane.add(numeroDeVuelo);
        numeroDeVuelo.setColumns(10);

        horaDeLLegada = new JTextField();
        horaDeLLegada.setText("16-05-2011 02:00");
        horaDeLLegada.setBounds(125, 106, 100, 20);
        contentPane.add(horaDeLLegada);
        horaDeLLegada.setColumns(10);

        origen = new JTextField();
        origen.setBounds(108, 35, 86, 20);
        contentPane.add(origen);
        origen.setColumns(10);

        lblvuelo = new JLabel("#Vuelo");
        lblvuelo.setBounds(25, 11, 46, 14);
        contentPane.add(lblvuelo);

        lblorigen = new JLabel("Origen");
        lblorigen.setBounds(125, 11, 46, 14);
        contentPane.add(lblorigen);

        destino = new JTextField();
        destino.setBounds(204, 35, 86, 20);
        contentPane.add(destino);
        destino.setColumns(10);

        horaDeSalida = new JTextField();
        horaDeSalida.setText("16-05-2011 02:00");
        horaDeSalida.setBounds(10, 106, 100, 20);
        contentPane.add(horaDeSalida);
        horaDeSalida.setColumns(10);

        lblDestino = new JLabel("Destino");
        lblDestino.setBounds(220, 11, 46, 14);
        contentPane.add(lblDestino);

        lblHoraDeSalida = new JLabel("Fecha de Salida");
        lblHoraDeSalida.setBounds(25, 81, 86, 14);
        contentPane.add(lblHoraDeSalida);

        lblHoraDeLlegada = new JLabel("Fecha de llegada");
        lblHoraDeLlegada.setBounds(125, 81, 86, 14);
        contentPane.add(lblHoraDeLlegada);

        comboBox = new JComboBox(aviones);
        comboBox.setBounds(300, 35, 101, 20);
        contentPane.add(comboBox);

        lblNombreAvion = new JLabel("Avion");
        lblNombreAvion.setBounds(315, 11, 70, 14);
        contentPane.add(lblNombreAvion);

        btnCrearVuelo = new JButton("Crear Vuelo");
        btnCrearVuelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                dispose();

            }

        });
        btnCrearVuelo.setBounds(265, 81, 108, 41);
        contentPane.add(btnCrearVuelo);

    }

    public RegistradoraDeVuelos(int numero, String origenVuelo, String destinoVuelo, String salida, String llegada,
            String duracionVuelo, String nombreAvion, int cupoAvion) {

        setTitle("Modificar Vuelos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 427, 183);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        numeroDeVuelo = new JTextField();
        numeroDeVuelo.setText("" + numero);
        numeroDeVuelo.setBounds(10, 35, 86, 20);
        contentPane.add(numeroDeVuelo);
        numeroDeVuelo.setColumns(10);

        horaDeLLegada = new JTextField();
        horaDeLLegada.setText(llegada);
        horaDeLLegada.setBounds(10, 95, 100, 20);
        contentPane.add(horaDeLLegada);
        horaDeLLegada.setColumns(10);

        origen = new JTextField();
        origen.setText(origenVuelo);
        origen.setBounds(108, 35, 86, 20);
        contentPane.add(origen);
        origen.setColumns(10);

        lblvuelo = new JLabel("#Vuelo");
        lblvuelo.setBounds(25, 11, 46, 14);
        contentPane.add(lblvuelo);

        lblorigen = new JLabel("Origen");
        lblorigen.setBounds(125, 11, 46, 14);
        contentPane.add(lblorigen);

        destino = new JTextField();
        destino.setText(destinoVuelo);
        destino.setBounds(204, 35, 86, 20);
        contentPane.add(destino);
        destino.setColumns(10);

        horaDeSalida = new JTextField();
        horaDeSalida.setText(salida);
        horaDeSalida.setBounds(153, 95, 100, 20);
        contentPane.add(horaDeSalida);
        horaDeSalida.setColumns(10);

        lblDestino = new JLabel("Destino");
        lblDestino.setBounds(220, 11, 46, 14);
        contentPane.add(lblDestino);

        lblHoraDeSalida = new JLabel("Fecha de Salida");
        lblHoraDeSalida.setBounds(153, 76, 86, 14);
        contentPane.add(lblHoraDeSalida);

        lblHoraDeLlegada = new JLabel("Fecha de llegada");
        lblHoraDeLlegada.setBounds(10, 76, 86, 14);
        contentPane.add(lblHoraDeLlegada);

        comboBox = new JComboBox(aviones);
        comboBox.setSelectedItem(Controlador.obtenerAvion(nombreAvion));
        comboBox.setBounds(300, 35, 101, 20);
        contentPane.add(comboBox);

        lblNombreAvion = new JLabel("Avion");
        lblNombreAvion.setBounds(315, 11, 70, 14);
        contentPane.add(lblNombreAvion);

        btnCrearVuelo = new JButton("Modificar Vuelo");
        btnCrearVuelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                dispose();
            }

        });
        btnCrearVuelo.setBounds(303, 85, 108, 41);
        contentPane.add(btnCrearVuelo);
    }
}
