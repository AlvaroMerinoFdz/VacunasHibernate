package com.ifpvg.DAM2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ejemplo1Ejercicio6 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejemplo1Ejercicio6 window = new Ejemplo1Ejercicio6();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ejemplo1Ejercicio6() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 250);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblSeleccioneElEjercicio = new JLabel("Seleccione el ejercicio que desea abrir.");
		lblSeleccioneElEjercicio.setBounds(23, 35, 353, 41);
		panel.add(lblSeleccioneElEjercicio);
		
		JButton btnEjemplo = new JButton("Ejemplo1");
		btnEjemplo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pantalla ventana = new Pantalla();
				ventana.initialize();
			}
		});
		btnEjemplo.setBounds(35, 109, 89, 23);
		panel.add(btnEjemplo);
		
		JButton btnEjercicio = new JButton("Ejercicio6");
		btnEjercicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ejercicio6 ejercicio6 = new Ejercicio6();
				//ejercicio6.show();
			}
		});
		btnEjercicio.setBounds(176, 109, 89, 23);
		panel.add(btnEjercicio);
	}

}
