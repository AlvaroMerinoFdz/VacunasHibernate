package com.ifpvg.DAM2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import practica2020.SessionFactoryUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ejercicio6 {

	private JFrame frame;
	private JTextField txtID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejercicio6 window = new Ejercicio6();
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
	public Ejercicio6() {
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
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(10, 29, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblTipoDeVacuna = new JLabel("Tipo de Vacuna");
		lblTipoDeVacuna.setBounds(10, 61, 93, 19);
		frame.getContentPane().add(lblTipoDeVacuna);
		
		JLabel lblNombreYApellidos = new JLabel("Nombre y apellidos");
		lblNombreYApellidos.setBounds(10, 107, 114, 14);
		frame.getContentPane().add(lblNombreYApellidos);
		
		JLabel lblGrupo = new JLabel("Grupo");
		lblGrupo.setBounds(10, 153, 46, 14);
		frame.getContentPane().add(lblGrupo);
		
		JLabel lblPas = new JLabel("Pa\u00EDs");
		lblPas.setBounds(10, 189, 46, 14);
		frame.getContentPane().add(lblPas);
		
		txtID = new JTextField();
		txtID.setBounds(51, 26, 302, 20);
		frame.getContentPane().add(txtID);
		txtID.setColumns(10);
		
		JLabel lblVacuna = new JLabel("");
		lblVacuna.setBounds(126, 61, 251, 14);
		frame.getContentPane().add(lblVacuna);
		
		JLabel lblNombre = new JLabel("");
		lblNombre.setBounds(151, 107, 262, 14);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblGrup = new JLabel("");
		lblGrup.setBounds(115, 153, 262, 14);
		frame.getContentPane().add(lblGrup);
		
		JLabel lblPai = new JLabel("");
		lblPai.setBounds(102, 189, 275, 14);
		frame.getContentPane().add(lblPai);
		
		JLabel lblLimpiar = new JLabel("");
		lblLimpiar.setBounds(171, 231, 253, 14);
		frame.getContentPane().add(lblLimpiar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblLimpiar.setText("");
					mostrarDatos(txtID.getText().charAt(0));
				}catch(StringIndexOutOfBoundsException s) {
					lblLimpiar.setText("Introduzca un valor válido");
				}
			}
			private void mostrarDatos(char id) {
				SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx =  session.beginTransaction();
				try {
					//COMPROBAMOS SI TIENE PACIENTES
					//Query vacuna = session.createQuery("select voluntario.vacuna from Voluntarios as voluntario where voluntario.id=?").setCharacter(0, id);
					Query nombre = session.createQuery("select voluntario.nya from Voluntarios as voluntario where voluntario.id=?").setCharacter(0, id);
					/*Query pais = session.createQuery("select vacuna.pais from Vacuna as vacuna where vacuna.codTipo=?").setCharacter(0, id);
					Query grupo = session.createQuery("select vacuna.pais from Vacuna as vacuna where vacuna.codTipo=?").setCharacter(0, id);*/
					tx.commit();
					/*if(vacuna.uniqueResult().equals(null)) {
						lblLimpiar.setText("Voluntario no existente");
					}else {*/
						//lblVacuna.setText(""+vacuna.uniqueResult());
						lblNombre.setText(""+nombre.uniqueResult());
						/*lblGrup.setText(""+grupo.uniqueResult());
						lblPai.setText(""+pais.uniqueResult());*/
				//	}
					
				}catch( NullPointerException t) {
					lblLimpiar.setText("Paciente no existente");
					//tx.rollback();
				}
				session.close();
			}
		});
		btnConsultar.setBounds(14, 227, 147, 23);
		frame.getContentPane().add(btnConsultar);
		
		
	}

}
