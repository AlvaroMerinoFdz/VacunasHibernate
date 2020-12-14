package com.ifpvg.DAM2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import practica2020.SessionFactoryUtil;
import practica2020.Vacuna;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CodVacuna_denoMediaEdad {

	private JFrame frame;
	private JTextField txtcodTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CodVacuna_denoMediaEdad window = new CodVacuna_denoMediaEdad();
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
	public CodVacuna_denoMediaEdad() {
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
		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblIntroduzcaElCodigo = new JLabel("Introduzca el Codigo de la vacuna");
		lblIntroduzcaElCodigo.setBounds(10, 31, 176, 27);
		panel.add(lblIntroduzcaElCodigo);
		
		txtcodTipo = new JTextField();
		txtcodTipo.setBounds(196, 34, 86, 20);
		panel.add(txtcodTipo);
		txtcodTipo.setColumns(10);
		
		JLabel lblDenominacin = new JLabel("Denominaci\u00F3n");
		lblDenominacin.setBounds(10, 107, 176, 27);
		panel.add(lblDenominacin);
		
		JLabel lblMediaDeEdad = new JLabel("Media de edad de los voluntarios");
		lblMediaDeEdad.setBounds(10, 164, 176, 27);
		panel.add(lblMediaDeEdad);
		
		JLabel lblDeno = new JLabel("");
		lblDeno.setBounds(196, 113, 186, 21);
		panel.add(lblDeno);
		
		JLabel lblMedia = new JLabel("");
		lblMedia.setBounds(196, 164, 186, 21);
		panel.add(lblMedia);
		
		JButton btnNewButton = new JButton("Consultar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char codtipo = txtcodTipo.getText().charAt(0);
				getDenoTipo(codtipo);
				getMediaEdad(codtipo);
				
				
			}
			private void getDenoTipo(char codtipo) {
				SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx =  session.beginTransaction();
				try {
					//COMPROBAMOS SI TIENE PACIENTES
					Query cons = session.createQuery("select vacuna.denoTipo from Vacuna as vacuna where vacuna.codTipo=?").setCharacter(0, codtipo);
					tx.commit();
					if(cons.uniqueResult().equals(null)) {
						lblDeno.setText("Vacuna no existente");
					}
					lblDeno.setText(" "+cons.uniqueResult());
				}catch( Exception t) {
					lblDeno.setText("Vacuna no existente");
					tx.rollback();
				}
				session.close();	
			}
			private void getMediaEdad(char codtipo) {
				SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx =  session.beginTransaction();
				try {
					//COMPROBAMOS SI TIENE PACIENTES
					Query cons = session.createQuery("select avg(voluntarios.edad) from Voluntarios as voluntarios where vacuna.codTipo=?").setCharacter(0, codtipo);
					tx.commit();
					lblMedia.setText(" "+cons.uniqueResult());
					if(cons.uniqueResult().equals(null)) {
						lblMedia.setText("Vacuna no tiene pacientes");
					}
					lblMedia.setText(" "+cons.uniqueResult());
				}catch( Exception t) {
					lblDeno.setText("Vacuna no existente");
					tx.rollback();
				}
				session.close();
			}
		});
		
		btnNewButton.setBounds(10, 214, 167, 36);
		panel.add(btnNewButton);
	}
}
