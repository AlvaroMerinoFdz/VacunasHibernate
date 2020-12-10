package com.ifpvg.DAM2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmPractica2020 extends JFrame {

	private JPanel contentPane;
	private JTextField txtVacuna;
	private JTextField txtLaboratorio;
	private JTextField txtPais;

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
		
		JLabel lblNDeDepartamentos = new JLabel("N\u00BA de Vacuna");
		lblNDeDepartamentos.setBounds(28, 31, 111, 14);
		panel.add(lblNDeDepartamentos);
		
		JLabel lblNombre = new JLabel("Pa\u00EDs");
		lblNombre.setBounds(28, 122, 46, 14);
		panel.add(lblNombre);
		
		JLabel lblNombre_1 = new JLabel("Laboratorio");
		lblNombre_1.setBounds(27, 76, 72, 14);
		panel.add(lblNombre_1);
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//lblLimpiar.setText("Vacuna dada de alta correctamente");
				
			}
		});
		btnAlta.setBounds(10, 192, 89, 23);
		panel.add(btnAlta);
		
		JButton btnBaja = new JButton("Baja");
		btnBaja.setBounds(96, 192, 89, 23);
		panel.add(btnBaja);
		
		JButton btnMoficacion = new JButton("Moficacion");
		btnMoficacion.setBounds(187, 192, 89, 23);
		panel.add(btnMoficacion);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(275, 192, 89, 23);
		panel.add(btnLimpiar);
		
		txtVacuna = new JTextField();
		txtVacuna.setBounds(115, 36, 86, 20);
		panel.add(txtVacuna);
		txtVacuna.setColumns(10);
		
		txtLaboratorio = new JTextField();
		txtLaboratorio.setColumns(10);
		txtLaboratorio.setBounds(115, 73, 86, 20);
		panel.add(txtLaboratorio);
		
		txtPais = new JTextField();
		txtPais.setColumns(10);
		txtPais.setBounds(115, 119, 86, 20);
		panel.add(txtPais);
		
		JLabel lblLimpiar = new JLabel("");
		lblLimpiar.setBounds(28, 158, 336, 14);
		panel.add(lblLimpiar);
	}
}
