package com.ifpvg.DAM2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.transaction.SystemException;


import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import practica2020.SessionFactoryUtil;
import practica2020.Vacuna;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmPractica2020 extends JFrame {

	private JPanel contentPane;
	private JTextField txtCod_tipo;
	private JTextField txtNombre;
	private JTextField txtPais;
	private JTextField txtDeno_tipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPractica2020 frame = new frmPractica2020();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmPractica2020() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblGestinDeVacunas = new JLabel("Gesti\u00F3n de Vacunas");
		lblGestinDeVacunas.setBounds(137, 11, 94, 14);
		panel.add(lblGestinDeVacunas);
		
		JLabel lblLimpiar = new JLabel("");
		lblLimpiar.setBounds(20, 226, 336, 14);
		panel.add(lblLimpiar);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(28, 133, 110, 14);
		panel.add(lblNombre);
		
		JLabel lblPais = new JLabel("Pa\u00EDs");
		lblPais.setBounds(28, 167, 110, 14);
		panel.add(lblPais);
		
		JLabel lblCod_tipo = new JLabel("Cod_tipo");
		lblCod_tipo.setBounds(28, 39, 110, 14);
		panel.add(lblCod_tipo);
		
		JLabel lblDeno_tipo = new JLabel("Deno_tipo");
		lblDeno_tipo.setBounds(28, 86, 122, 14);
		panel.add(lblDeno_tipo);
		
		txtCod_tipo = new JTextField();
		txtCod_tipo.setBounds(160, 36, 86, 20);
		panel.add(txtCod_tipo);
		txtCod_tipo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(160, 130, 86, 20);
		panel.add(txtNombre);
		
		txtPais = new JTextField();
		txtPais.setColumns(10);
		txtPais.setBounds(160, 161, 86, 20);
		panel.add(txtPais);
		
		txtDeno_tipo = new JTextField();
		txtDeno_tipo.setBounds(160, 83, 86, 20);
		panel.add(txtDeno_tipo);
		txtDeno_tipo.setColumns(10);
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			//
			public void actionPerformed(ActionEvent e) {
				try {
					InsertarDep(txtCod_tipo.getText().charAt(0), txtNombre.getText(), txtPais.getText(), txtDeno_tipo.getText());
				} catch (IllegalStateException | SystemException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			public void InsertarDep(char codtipo, String deno_tipo, String laboratorio, String pais) throws IllegalStateException, SystemException {
				//INSERTO UN DEPARTAMENTO
				SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx =  session.beginTransaction();
				
				Vacuna depart =  (Vacuna) session.createQuery("From Vacuna as de "+
				"where de.codTipo = ?").setCharacter(0,codtipo).uniqueResult();
				if (depart!=null) {
					lblLimpiar.setText(" VACUNA EXISTENTE - NO SE PUEDE DAR DE ALTA");
					tx.rollback();		
				}else{
					Vacuna d = new Vacuna();
					d.setCodTipo(codtipo);
					if(deno_tipo.length()<15) {
						d.setDenoTipo(deno_tipo);
					}
					if(laboratorio.length()<15) {
						d.setLaboratorio(laboratorio);
					}
					if(pais.length()<15) {
						d.setPais(pais);
					}
					tx.commit();
					lblLimpiar.setText("Vacuna insertada...");	
				}
				session.close();
			}
		});
				btnAlta.setBounds(10, 192, 89, 23);
				panel.add(btnAlta);
				
				JButton btnBaja = new JButton("Baja");
				btnBaja.setBounds(96, 192, 89, 23);
				panel.add(btnBaja);
				
				JButton btnMoficacion = new JButton("Moficacion");
				btnMoficacion.setBounds(187, 192, 122, 23);
				panel.add(btnMoficacion);
				
				JButton btnLimpiar = new JButton("Limpiar");
				btnLimpiar.setBounds(304, 192, 89, 23);
				panel.add(btnLimpiar);
	}
}
