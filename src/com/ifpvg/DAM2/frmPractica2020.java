package com.ifpvg.DAM2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.transaction.SystemException;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
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
		lblGestinDeVacunas.setBounds(151, 11, 190, 14);
		panel.add(lblGestinDeVacunas);
		
		JLabel lblLimpiar = new JLabel("");
		lblLimpiar.setBounds(20, 226, 336, 14);
		panel.add(lblLimpiar);
		
		JLabel lblNombre = new JLabel("Laboratorio");
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
					
					InsertarVacuna(txtCod_tipo.getText().charAt(0), txtNombre.getText(), txtPais.getText(), txtDeno_tipo.getText());
				} catch (IllegalStateException | SystemException e1) {
					e1.printStackTrace();
				}catch(StringIndexOutOfBoundsException s) {
					lblLimpiar.setText("Introduzca un valor válido");
				}
			}
			public void InsertarVacuna(char codtipo, String deno_tipo, String laboratorio, String pais) throws IllegalStateException, SystemException {
				//INSERTO UN DEPARTAMENTO
				SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx =  session.beginTransaction();
				
				Vacuna vacuna =  (Vacuna) session.createQuery("From Vacuna as vacuna "+
				"where vacuna.codTipo = ?").setCharacter(0,codtipo).uniqueResult();
				if(vacuna !=null) {
					lblLimpiar.setText("Vacuna existente, por tanto no se puede dar de alta");
					tx.rollback();
				}else {
					Vacuna d = new Vacuna();
					if(deno_tipo.length()>15) deno_tipo.substring(0,15);
					d.setDenoTipo(deno_tipo);
					if(laboratorio.length()>15) laboratorio.substring(0, 15);
					d.setLaboratorio(laboratorio);
					if(pais.length()>15) pais.substring(0,15);
					d.setPais(pais);
					session.save(d);//modificamos el objeto
					tx.commit();
					lblLimpiar.setText("Vacuna insertada correctamente");
				}
				session.close();
			}//fin insertar vacuna
		});
				btnAlta.setBounds(10, 192, 89, 23);
				panel.add(btnAlta);
				
				JButton btnBaja = new JButton("Baja");
				btnBaja.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							bajaVacuna(txtCod_tipo.getText().charAt(0));
						}catch(StringIndexOutOfBoundsException s) {
							lblLimpiar.setText("Introduzca un valor válido");
						}
						
					}
					void bajaVacuna(char codtipo) {
						SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
						Session session = sesion.openSession();
						Transaction tx =  session.beginTransaction();
						Vacuna de = new Vacuna();
						de = (Vacuna) session.load(Vacuna.class, (char) codtipo);
						try {
							//COMPROBAMOS SI TIENE PACIENTES
							Query cons = session.createQuery("select count(vol.vacuna)from Voluntarios as vol where vol.vacuna=?").setCharacter(0, codtipo);
							Long numVoluntarios = (Long) cons.uniqueResult();
							if (numVoluntarios==0) { //no tiene pacientes
								session.delete(de);//eliminamos el objeto
								tx.commit();
								lblLimpiar.setText("Vacuna eliminada correctamente");
							}else {//tiene pacientes asignados
								lblLimpiar.setText("NO SE PUEDE ELIMINAR TIENE "+numVoluntarios+" pacientes asignados...");
								tx.rollback();
							}
						}catch( ObjectNotFoundException t) {
							lblLimpiar.setText("Vacuna no existente, no se puede eliminar");
							tx.rollback();
						}
						session.close();
					}//fin bajaVacuna
				});
				btnBaja.setBounds(96, 192, 89, 23);
				panel.add(btnBaja);
				
				JButton btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							modificarVacuna(txtCod_tipo.getText().charAt(0), txtNombre.getText(), txtPais.getText(), txtDeno_tipo.getText());	
						}catch(StringIndexOutOfBoundsException s) {
							lblLimpiar.setText("Introduzca un valor válido");
						}
					}
					private void modificarVacuna(char codtipo, String deno_tipo, String laboratorio, String pais) {
						SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
						Session session = sesion.openSession();
						Transaction tx =  session.beginTransaction();
						Vacuna de = new Vacuna();
						de = (Vacuna) session.load(Vacuna.class, (char) codtipo);
						try {
							if(deno_tipo.length()>15) deno_tipo.substring(0,15);
							de.setDenoTipo(deno_tipo);
							if(laboratorio.length()>15) laboratorio.substring(0, 15);
							de.setLaboratorio(laboratorio);
							if(pais.length()>15) pais.substring(0,15);
							de.setPais(pais);
							session.update(de);//modificamos el objeto
							tx.commit();
							lblLimpiar.setText("Vacuna modificada correctamente");
						}catch( ObjectNotFoundException t) {
							lblLimpiar.setText("Vacuna no existente, no se puede modificar");
							tx.rollback();
						}
						session.close();
					}
				});
				btnModificar.setBounds(187, 192, 122, 23);
				panel.add(btnModificar);
				
				JButton btnLimpiar = new JButton("Limpiar");
				btnLimpiar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						txtCod_tipo.setText("");
						txtNombre.setText("");
						txtPais.setText("");
						txtDeno_tipo.setText("");
						
					}
				});
				btnLimpiar.setBounds(304, 192, 89, 23);
				panel.add(btnLimpiar);
	}
}
