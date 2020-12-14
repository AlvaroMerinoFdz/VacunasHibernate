package com.ifpvg.DAM2;

import java.awt.*;
import javax.swing.*;
import org.hibernate.*;
import practica2020.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.List;

public class Pantalla {

	private JFrame frame;
	private JTextField depar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pantalla window = new Pantalla();
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
	public Pantalla() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 10, 10);
		frame.getContentPane().add(panel);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(304, 17, 120, 48);
		frame.getContentPane().add(btnConsultar);
		
		depar = new JTextField();
		depar.setBounds(212, 18, 86, 20);
		frame.getContentPane().add(depar);
		depar.setColumns(10);
		
		JLabel lblIntroduceElCodigo = new JLabel("Introduce el codigo de la vacuna");
		lblIntroduceElCodigo.setBounds(34, 21, 168, 14);
		frame.getContentPane().add(lblIntroduceElCodigo);
		
		JLabel etiqueta = new JLabel("");
		etiqueta.setBounds(34, 46, 260, 29);
		frame.getContentPane().add(etiqueta);
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(34, 76, 360, 156);
		frame.getContentPane().add(scroll);
		
		JTextArea area = new JTextArea();
		scroll.setViewportView(area);
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				area.setText("");
				if (e.getSource() == btnConsultar) {
					//se pulsa el botón
					etiqueta.setText("Vacuna a consultar: "+depar.getText());
					char dep;
					try {
						dep = depar.getText().toUpperCase().charAt(0);
						
						VisualizarDep(dep);//visualiza datos del departameto
					}catch(NumberFormatException ex) {
						etiqueta.setText("Vacuna erroneo");
					}
				}
			}
			void VisualizarDep(char dep) {
				SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
				Session session = sesion.openSession();
				
				Vacuna depart = (Vacuna) session.createQuery("from Vacuna as de where de.codTipo=?").setCharacter(0, dep).uniqueResult();
				if(depart!=null) {
					etiqueta.setText("Nombre Vacuna: "+depart.getDenoTipo());
					visualizarPaciente(dep);//visualizo los pacientes;
				}else {
					etiqueta.setText("NO EXISTE LA VACUNA (TODAVÍA)...");
					session.close();
				}
				
			}
			//localiza datos de los pacientes
			void visualizarPaciente(char dep) {
				SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
				Session session = sesion.openSession();
				Query q = session.createQuery(" from Voluntarios v where v.vacuna.codTipo = ?").setCharacter(0, dep);
				Voluntarios emple = new Voluntarios();
				List<Voluntarios> lista = q.list();
				
				area.append("Número de voluntarios : "+lista.size()+ "\n \n");
				area.append("NOMBRE Y APELLIDOS \t EDAD \t GRUPO \t PAÍS \n");
				Iterator<Voluntarios> iter = lista.iterator();
				while(iter.hasNext()) {
					emple = iter.next();
					String cad = "\n "+emple.getNya()+" \t"+emple.getEdad()+" \t"+emple.getGrupo()+" \t"+emple.getPais();
					area.append(cad);
				}
				session.close();
				
			}
			
		});
	}
}
