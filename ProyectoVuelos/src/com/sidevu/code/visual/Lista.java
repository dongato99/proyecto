/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.ini
 */
package com.sidevu.code.visual;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;


import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
/**
 *
 * @author DELL
 */
public class Lista extends JFrame {
    protected JPanel contentPane;
    private JTextField textoABuscar;
    private int pos = 0;
    public void configuracionDeLista(String listaOrdenada, String titulo) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JTextArea textArea = new JTextArea();
		textArea.setBounds(142, 103, 4, 22);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(66, 32, 300, 218);
		contentPane.add(scrollPane);
		JLabel vueloHeader = new JLabel(titulo);
		vueloHeader.setBounds(133, 11, 136, 14);
		contentPane.add(vueloHeader);
		textArea.setText(listaOrdenada);

		textArea.setSelectionStart(0);
		textArea.setSelectionEnd(0);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String find = textoABuscar.getText().toLowerCase();

				textArea.requestFocusInWindow();

				if (find != null && find.length() > 0) {
					Document document = textArea.getDocument();
					int findLength = find.length();
					try {
						boolean found = false;

						if (pos + findLength > document.getLength()) {
							pos = 0;
						}

						while (pos + findLength <= document.getLength()) {

							String match = document.getText(pos, findLength).toLowerCase();

							if (match.equals(find)) {
								found = true;
								break;
							}
							pos++;
						}

						if (found) {

							Rectangle viewRect = textArea.modelToView(pos);

							textArea.scrollRectToVisible(viewRect);

							textArea.setCaretPosition(pos + findLength);
							textArea.moveCaretPosition(pos);

							pos += findLength;
						}

					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}

			}
		});
		btnBuscar.setBounds(66, 261, 89, 23);
		contentPane.add(btnBuscar);

		textoABuscar = new JTextField();
		textoABuscar.setBounds(219, 262, 147, 20);
		contentPane.add(textoABuscar);
		textoABuscar.setColumns(10);
	}

	public Lista(String lista, String titulo) {
		configuracionDeLista(lista, titulo);
	}
}
