package com.ifpvg.DAM2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Elegir2o4 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Elegir2o4 window = new Elegir2o4();
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
	public Elegir2o4() {
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
		
		JLabel lblEligeQueEjercicios = new JLabel("Elige que ejercicios deseas abrir");
		lblEligeQueEjercicios.setBounds(33, 30, 361, 77);
		frame.getContentPane().add(lblEligeQueEjercicios);
		
		JButton btnEjercicio = new JButton("Ejercicio 2");
		btnEjercicio.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				frmPractica2020 frm = new frmPractica2020();
				frm.show();
			}
		});
		btnEjercicio.setBounds(33, 149, 138, 53);
		frame.getContentPane().add(btnEjercicio);
		
		JButton btnEjercicio_2 = new JButton("Ejercicio 4");
		btnEjercicio_2.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Ejercicio4 ejercicio4 = new Ejercicio4();
				ejercicio4.show();
				
			}
		});
		btnEjercicio_2.setBounds(221, 149, 138, 53);
		frame.getContentPane().add(btnEjercicio_2);
	}

}
