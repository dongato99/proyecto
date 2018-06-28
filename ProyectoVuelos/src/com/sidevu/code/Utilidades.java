/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sidevu.code;

/**
 *
 * @author DELL
 */
public class Utilidades {

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
}
