import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class Principal {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
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
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.EAST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblTerreno = new JLabel("Terreno");
		GridBagConstraints gbc_lblTerreno = new GridBagConstraints();
		gbc_lblTerreno.insets = new Insets(0, 0, 5, 0);
		gbc_lblTerreno.gridx = 0;
		gbc_lblTerreno.gridy = 0;
		panel.add(lblTerreno, gbc_lblTerreno);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 1;
		panel.add(comboBox, gbc_comboBox);
		
		JLabel lblCriatura = new JLabel("Criatura");
		GridBagConstraints gbc_lblCriatura = new GridBagConstraints();
		gbc_lblCriatura.insets = new Insets(0, 0, 5, 0);
		gbc_lblCriatura.gridx = 0;
		gbc_lblCriatura.gridy = 2;
		panel.add(lblCriatura, gbc_lblCriatura);
		
		JComboBox comboBox_1 = new JComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 0;
		gbc_comboBox_1.gridy = 3;
		panel.add(comboBox_1, gbc_comboBox_1);
		
		JLabel lblPropiedadesDepincel = new JLabel("Propiedades de \"pincel\"");
		GridBagConstraints gbc_lblPropiedadesDepincel = new GridBagConstraints();
		gbc_lblPropiedadesDepincel.gridx = 0;
		gbc_lblPropiedadesDepincel.gridy = 4;
		panel.add(lblPropiedadesDepincel, gbc_lblPropiedadesDepincel);
		
		JToolBar toolBar = new JToolBar();
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btnAbrir = new JButton("Abrir");
		toolBar.add(btnAbrir);
		
		JButton btnGuardar = new JButton("Guardar");
		toolBar.add(btnGuardar);
		
		JButton btnEjecutar = new JButton("Ejecutar");
		toolBar.add(btnEjecutar);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JLabel lblPropiedadesDeCelda = new JLabel("Propiedades de Celda");
		panel_1.add(lblPropiedadesDeCelda);
		
		JLabel lblX = new JLabel("X");
		panel_1.add(lblX);
		
		JLabel lblY = new JLabel("Y");
		panel_1.add(lblY);
		
		JLabel lblTerreno_1 = new JLabel("Terreno");
		panel_1.add(lblTerreno_1);
		
		JLabel lblCriatura_1 = new JLabel("Criatura");
		panel_1.add(lblCriatura_1);
		
		JLabel lblBanderas = new JLabel("Banderas");
		panel_1.add(lblBanderas);
		
		table = new JTable();
		frame.getContentPane().add(table, BorderLayout.CENTER);
	}

}
