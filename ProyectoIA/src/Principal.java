import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.modelo.CostMov;
import com.modelo.Criatura;

public class Principal {

	private JFrame frame;
/*****************************************************/
	private DefaultTableModel Modelo;
	private JTable Tabla;
	private CostMov tablaCostos;
	private JTable TablaEjecucion;
	private String[][] Mapa;
	private Criatura criatura;
	private JTextField txtCoordX;
	private JTextField txtCoordY;
/*****************************************************/
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
		frame.setBounds(100, 100, 600, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*DECLARACION DE LA TABLA*/
		Tabla = new JTable();
		Tabla.setEnabled(false);
		
		Tabla.setModel(new DefaultTableModel(
			new Object[][] {	},
			new String[]   {	}
		));
		Tabla.setBounds(10, 0, 200, 200);
		/*DECLARACION DE LA TABLA*/
		
		/*DECLARACION DE LA TABLA*/
		TablaEjecucion = new JTable();
		TablaEjecucion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar()=='w'||e.getKeyCode()==KeyEvent.VK_UP){
					Point posicion = criatura.moverArriba();
					desenmascarar(posicion);							
				}
				if(e.getKeyChar()=='s'||e.getKeyCode()==KeyEvent.VK_DOWN){
					Point posicion = criatura.moverAbajo();
					desenmascarar(posicion);
				}
				if(e.getKeyChar()=='d'||e.getKeyCode()==KeyEvent.VK_RIGHT){
					Point posicion = criatura.moverDerecha();
					desenmascarar(posicion);
				}
				if(e.getKeyChar()=='a'||e.getKeyCode()==KeyEvent.VK_LEFT){
					Point posicion = criatura.moverIzquierda();
					desenmascarar(posicion);
				}
			}
		});
		TablaEjecucion.setEnabled(false);
		TablaEjecucion.setModel(new DefaultTableModel(
				new Object[][] {	},
				new String[]   {	}
			));
		TablaEjecucion.setBounds(10, 0, 200, 200);
		/*DECLARACION DE LA TABLA*/
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelEditorMapa = new JPanel();
		tabbedPane.addTab("Mapa", null, panelEditorMapa, null);

		panelEditorMapa.setLayout(new BorderLayout(0, 0));
		panelEditorMapa.add(Tabla, BorderLayout.CENTER);
		JPanel panelPropiedadesCelda = new JPanel();
		
		panelEditorMapa.add(panelPropiedadesCelda, BorderLayout.EAST);
		GridBagLayout gbl_panelPropiedadesCelda = new GridBagLayout();
		gbl_panelPropiedadesCelda.columnWidths = new int[]{36, 19, -1, 20, 0};
		gbl_panelPropiedadesCelda.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelPropiedadesCelda.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelPropiedadesCelda.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelPropiedadesCelda.setLayout(gbl_panelPropiedadesCelda);
		
		JLabel lblX = new JLabel("Columna");
		GridBagConstraints gbc_lblX = new GridBagConstraints();
		gbc_lblX.insets = new Insets(0, 0, 5, 5);
		gbc_lblX.anchor = GridBagConstraints.EAST;
		gbc_lblX.gridx = 0;
		gbc_lblX.gridy = 0;
		panelPropiedadesCelda.add(lblX, gbc_lblX);
		
		txtCoordX = new JTextField();
		GridBagConstraints gbc_txtCoordX = new GridBagConstraints();
		gbc_txtCoordX.insets = new Insets(0, 0, 5, 5);
		gbc_txtCoordX.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCoordX.gridx = 1;
		gbc_txtCoordX.gridy = 0;
		panelPropiedadesCelda.add(txtCoordX, gbc_txtCoordX);
		txtCoordX.setColumns(4);
		
		JLabel lblY = new JLabel("Fila");
		GridBagConstraints gbc_lblY = new GridBagConstraints();
		gbc_lblY.insets = new Insets(0, 0, 5, 5);
		gbc_lblY.anchor = GridBagConstraints.EAST;
		gbc_lblY.gridx = 2;
		gbc_lblY.gridy = 0;
		panelPropiedadesCelda.add(lblY, gbc_lblY);
		
		txtCoordY = new JTextField();
		GridBagConstraints gbc_txtCoordY = new GridBagConstraints();
		gbc_txtCoordY.insets = new Insets(0, 0, 5, 0);
		gbc_txtCoordY.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCoordY.gridx = 3;
		gbc_txtCoordY.gridy = 0;
		panelPropiedadesCelda.add(txtCoordY, gbc_txtCoordY);
		txtCoordY.setColumns(4);
		
		JComboBox comboBoxTerreno = new JComboBox();
		comboBoxTerreno.setModel(new DefaultComboBoxModel(new String[] {"Selecciona Terreno"}));
		GridBagConstraints gbc_comboBoxTerreno = new GridBagConstraints();
		gbc_comboBoxTerreno.gridwidth = 4;
		gbc_comboBoxTerreno.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxTerreno.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTerreno.gridx = 0;
		gbc_comboBoxTerreno.gridy = 2;
		panelPropiedadesCelda.add(comboBoxTerreno, gbc_comboBoxTerreno);
		
		JComboBox comboBoxCriatura = new JComboBox();
		comboBoxCriatura.setModel(new DefaultComboBoxModel(new String[] {"Selecciona Criatura"}));
		GridBagConstraints gbc_comboBoxCriatura = new GridBagConstraints();
		gbc_comboBoxCriatura.gridwidth = 4;
		gbc_comboBoxCriatura.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxCriatura.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCriatura.gridx = 0;
		gbc_comboBoxCriatura.gridy = 3;
		panelPropiedadesCelda.add(comboBoxCriatura, gbc_comboBoxCriatura);
		
		JCheckBox chckbxVisitado = new JCheckBox("Visitado");
		GridBagConstraints gbc_chckbxVisitado = new GridBagConstraints();
		gbc_chckbxVisitado.anchor = GridBagConstraints.WEST;
		gbc_chckbxVisitado.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxVisitado.gridwidth = 4;
		gbc_chckbxVisitado.gridx = 0;
		gbc_chckbxVisitado.gridy = 4;
		panelPropiedadesCelda.add(chckbxVisitado, gbc_chckbxVisitado);
		
		JCheckBox chckbxInicio = new JCheckBox("Inicio");
		GridBagConstraints gbc_chckbxInicio = new GridBagConstraints();
		gbc_chckbxInicio.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxInicio.anchor = GridBagConstraints.WEST;
		gbc_chckbxInicio.gridwidth = 4;
		gbc_chckbxInicio.gridx = 0;
		gbc_chckbxInicio.gridy = 5;
		panelPropiedadesCelda.add(chckbxInicio, gbc_chckbxInicio);
		
		JCheckBox chckbxFin = new JCheckBox("Fin");
		GridBagConstraints gbc_chckbxFin = new GridBagConstraints();
		gbc_chckbxFin.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxFin.gridwidth = 4;
		gbc_chckbxFin.anchor = GridBagConstraints.WEST;
		gbc_chckbxFin.gridx = 0;
		gbc_chckbxFin.gridy = 6;
		panelPropiedadesCelda.add(chckbxFin, gbc_chckbxFin);
		
		JCheckBox chckbxFijarEnClick = new JCheckBox("Fijar valores en click");
		GridBagConstraints gbc_chckbxFijarEnClick = new GridBagConstraints();
		gbc_chckbxFijarEnClick.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxFijarEnClick.anchor = GridBagConstraints.WEST;
		gbc_chckbxFijarEnClick.gridwidth = 4;
		gbc_chckbxFijarEnClick.gridx = 0;
		gbc_chckbxFijarEnClick.gridy = 7;
		panelPropiedadesCelda.add(chckbxFijarEnClick, gbc_chckbxFijarEnClick);
		
		JButton btnAplicar = new JButton("Aplicar");
		
		GridBagConstraints gbc_btnAplicar = new GridBagConstraints();
		gbc_btnAplicar.insets = new Insets(0, 0, 5, 0);
		gbc_btnAplicar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAplicar.gridwidth = 4;
		gbc_btnAplicar.gridx = 0;
		gbc_btnAplicar.gridy = 8;
		panelPropiedadesCelda.add(btnAplicar, gbc_btnAplicar);
		
		/*MENï¿½ ARCHIVO -> ABRIR*/
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu mnArchivo = new JMenu("Archivo");
				
		menuBar.add(mnArchivo);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
				mnArchivo.add(mntmNuevo);
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mnArchivo.add(mntmAbrir);
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mnArchivo.add(mntmGuardar);
		/*MENï¿½ ARCHIVO -> ABRIR*/
		
		/*MENU EJECUTAR -> EJECUTAR*/
		JMenu mnEjecutar = new JMenu("Ejecutar");
		menuBar.add(mnEjecutar);
		JMenuItem mntmEjecutar = new JMenuItem("Ejecutar/Reiniciar");
		mnEjecutar.add(mntmEjecutar);
		/*MENU EJECUTAR -> EJECUTAR*/
		
		/*INICIO Panel Criaturas*/
		JPanel panelEditorCriaturas = new JPanel();
		tabbedPane.addTab("Criaturas", null, panelEditorCriaturas, null);
		panelEditorCriaturas.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBotonesCriaturas = new JPanel();
		panelEditorCriaturas.add(panelBotonesCriaturas, BorderLayout.EAST);
		GridBagLayout gbl_panelBotonesCriaturas = new GridBagLayout();
		gbl_panelBotonesCriaturas.columnWidths = new int[]{0, 0};
		gbl_panelBotonesCriaturas.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelBotonesCriaturas.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelBotonesCriaturas.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelBotonesCriaturas.setLayout(gbl_panelBotonesCriaturas);
		
		JButton btnNuevacriatura = new JButton("NuevaCriatura");
		btnNuevacriatura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaCostos.agregarCriatura();
			}
		});
		GridBagConstraints gbc_btnNuevacriatura = new GridBagConstraints();
		gbc_btnNuevacriatura.fill = GridBagConstraints.BOTH;
		gbc_btnNuevacriatura.insets = new Insets(0, 0, 5, 0);
		gbc_btnNuevacriatura.gridx = 0;
		gbc_btnNuevacriatura.gridy = 0;
		panelBotonesCriaturas.add(btnNuevacriatura, gbc_btnNuevacriatura);
		
		JButton btnNuevoTerreno = new JButton("Nuevo Terreno");
		btnNuevoTerreno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaCostos.agregarTerreno();
			}
		});
		GridBagConstraints gbc_btnNuevoTerreno = new GridBagConstraints();
		gbc_btnNuevoTerreno.insets = new Insets(0, 0, 5, 0);
		gbc_btnNuevoTerreno.fill = GridBagConstraints.BOTH;
		gbc_btnNuevoTerreno.gridx = 0;
		gbc_btnNuevoTerreno.gridy = 1;
		panelBotonesCriaturas.add(btnNuevoTerreno, gbc_btnNuevoTerreno);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(tablaCostos.obtenerCosto(0,0)); //Sólo es para probar cómo obtener los costos 
			}
		});
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.fill = GridBagConstraints.BOTH;
		gbc_btnGuardar.gridx = 0;
		gbc_btnGuardar.gridy = 2;
		panelBotonesCriaturas.add(btnGuardar, gbc_btnGuardar);
		
		tablaCostos = new CostMov();
		tablaCostos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point p = e.getPoint();
                int row = tablaCostos.rowAtPoint(p);
                int col = tablaCostos.columnAtPoint(p);
                
                if ((row > -1 && row == 2 && col > 2) || (col > -1 && col == 2 && row > 2)) {
                	Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.RED);
                	tablaCostos.getModel().setValueAt(newColor, row, col);
                }
				
			}
		});
		JScrollPane scrollPaneTablaCriaturas = new JScrollPane(tablaCostos);
		panelEditorCriaturas.add(scrollPaneTablaCriaturas, BorderLayout.CENTER);
		
		
		/*tablaCriaturas.setModel(new DefaultTableModel(
			new Object[][] {
				{"COLOR", "", ""},
				{"TIPO", "", ""}
			},
			new String[] {
				"", "COLOR", "TIPO"
			}
		));
		tablaCriaturas.getColumnModel().getColumn(0).setCellRenderer(
				tablaCriaturas.getTableHeader().getDefaultRenderer());
		scrollPaneTablaCriaturas.setViewportView(tablaCriaturas);
		/*FIN Panel Criaturas*/
		
		
		/*INICIO Panel Ejecucion*/
		JPanel panelEjecucion = new JPanel();
		panelEjecucion.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				TablaEjecucion.setModel(new DefaultTableModel());
				Modelo = (DefaultTableModel)TablaEjecucion.getModel();
				TablaEjecucion.setDefaultRenderer(Object.class, setNewRenderer());
				if(Mapa!=null && Mapa.length>0) {
					ActualizarMapa(Mapa, (DefaultTableModel) Tabla.getModel());
					CrearTabla(Mapa);
					Enmascarar(Mapa);
				}
			}
		});
		tabbedPane.addTab("Ejecucion", null, panelEjecucion, null);
		panelEjecucion.setLayout(new BorderLayout(0, 0));
		panelEjecucion.add(TablaEjecucion, BorderLayout.CENTER);
		/*FIN Panel Ejecucion*/
		
		
		panelEditorMapa.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				if(tablaCostos.getRowCount() > 3){
					comboBoxTerreno.removeAllItems();
					comboBoxTerreno.addItem("Selecciona Terreno");
					for(int i = 3;i<tablaCostos.getRowCount() ;i++)
						comboBoxTerreno.addItem(tablaCostos.getValueAt(i, 1));
				}
				if(tablaCostos.getColumnCount() > 3){
					comboBoxCriatura.removeAllItems();
					comboBoxCriatura.addItem("Selecciona Criatura");
					for(int i = 3;i<tablaCostos.getColumnCount() ;i++)
						comboBoxCriatura.addItem(tablaCostos.getValueAt(1, i));
				}
			}
		});
		/*EVENTO APLICAR*/
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = Integer.parseInt(txtCoordY.getText());
                int col = txtCoordX.getText().getBytes()[0]-'A'+1;
                if ((row > -1 && row < Tabla.getRowCount()) && (col > -1 && col < Tabla.getColumnCount())) {
                	int indiceCriatura =comboBoxCriatura.getSelectedIndex();
                	Mapa[row][col]=Integer.toString(comboBoxTerreno.getSelectedIndex()-1);
                	Mapa[row][col]+=",";
	                Mapa[row][col]+=Integer.toString(indiceCriatura-1);
                	if(chckbxVisitado.isSelected()) Mapa[row][col]+=",V";
                	if(chckbxInicio.isSelected()) Mapa[row][col]+=",I";
                	if(chckbxFin.isSelected()) Mapa[row][col]+=",F";
                	Tabla.getModel().setValueAt(Mapa[row][col], row, col);	                	
                }
			}
		});
		/*EVENTO MOUSECLICKED*/
		Tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(chckbxFijarEnClick.isSelected()){
					Point p = e.getPoint();
	                int row = Tabla.rowAtPoint(p);
	                int col = Tabla.columnAtPoint(p);
	                
	                if ((row > -1 && row < Tabla.getRowCount()) && (col > -1 && col < Tabla.getColumnCount())) {
	                	
	                	int indiceCriatura =comboBoxCriatura.getSelectedIndex()-1;
	                	Mapa[row][col]=Integer.toString(comboBoxTerreno.getSelectedIndex()-1);
	                	Mapa[row][col]+=",";
		                Mapa[row][col]+=Integer.toString(indiceCriatura);
	                	if(chckbxVisitado.isSelected()) Mapa[row][col]+=",V";
	                	if(chckbxInicio.isSelected()) Mapa[row][col]+=",I";
	                	if(chckbxFin.isSelected()) Mapa[row][col]+=",F";
	                	criatura = new Criatura(tablaCostos.obtenerCostosCriatura(indiceCriatura),Mapa);
	                	Tabla.getModel().setValueAt(Mapa[row][col], row, col);
	                	Tabla.setDefaultRenderer(Object.class, setNewRenderer());
	                }
					
				}else{
					Point p = e.getPoint();
	                int row = Tabla.rowAtPoint(p);
	                int col = Tabla.columnAtPoint(p);
	                
	                if ((row > -1 && row < Tabla.getRowCount()) && (col > -1 && col < Tabla.getColumnCount())) {
	                	txtCoordX.setText(Character.toString((char)('A'+col-1)));
	                	txtCoordY.setText(Integer.toString(row));
	                	
	                }
				}
			}
		});
		
		/*EVENTO MOUSEMOTION*/
		Tabla.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(chckbxFijarEnClick.isSelected()){
					Point p = e.getPoint();
	                int row = Tabla.rowAtPoint(p);
	                int col = Tabla.columnAtPoint(p);
	                
	                if ((row > -1 && row < Tabla.getRowCount()) && (col > -1 && col < Tabla.getColumnCount())) {
	                	txtCoordX.setText(Character.toString((char)('A'+col-1)));
	                	txtCoordY.setText(Integer.toString(row));
	                	
	                }
				}
			}
		});
		
		/*EVENTO NUEVO*/
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tabla.setModel(new DefaultTableModel());
				Modelo = (DefaultTableModel)Tabla.getModel();
				Tabla.setDefaultRenderer(Object.class, new Celda());
				Mapa = new String[][]{{"-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1"},
									  {"-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1"},
									  {"-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1"},
									  {"-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1"},
									  {"-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1"},
									  {"-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1"},
									  {"-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1"},
									  {"-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1"},
									  {"-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1"},
									  {"-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1"},
									  {"-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1"},
									  {"-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1"},
									  {"-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1"},
									  {"-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1"},
									  {"-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1","-1"}};
				CrearTabla(Mapa);
			}
		});
		/*EVENTO ABRIR*/
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				Tabla.setModel(new DefaultTableModel());
				Modelo = (DefaultTableModel)Tabla.getModel();
				Tabla.setDefaultRenderer(Object.class, new Celda());
				Mapa = CargarArchivo();
				if(Mapa.length>0)
				CrearTabla(Mapa);
			}
		});
		/*EVENTO GUARDAR DEL MENU ARCHIVO*/
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int SeleccionDeArchivo = 0;
				File Archivo;
				JFileChooser Opcion = new JFileChooser();
				Opcion.setDialogTitle("Selecciona la ubicación del archivo");
				Opcion.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				SeleccionDeArchivo = Opcion.showOpenDialog(frame);
				if(SeleccionDeArchivo == JFileChooser.APPROVE_OPTION) {
					Archivo = Opcion.getSelectedFile();
					try{
						 BufferedWriter writer = new BufferedWriter(new FileWriter(Archivo));
						 for(String[] x : Mapa){
							 String line = "";
							 for(String y : x){
								 String split[] = y.split(",");
								 line+=split[0]+',';
							 }
							 line = line.substring(0, line.length()-1);
							 writer.write(line);
							 writer.newLine();
						 }
						 writer.close();
					}catch(IOException ex){
						ex.printStackTrace();
					}
				}
			}
		});
		/*EVENTO EJECUTAR*/
		mntmEjecutar.addActionListener(new ActionListener() {
		public void actionPerformed (ActionEvent argv0) {
			TablaEjecucion.setModel(new DefaultTableModel());
			Modelo = (DefaultTableModel)TablaEjecucion.getModel();
			TablaEjecucion.setDefaultRenderer(Object.class, setNewRenderer());
			if(Mapa!=null && Mapa.length>0) {
				ActualizarMapa(Mapa, (DefaultTableModel) Tabla.getModel());
				CrearTabla(Mapa);
				Enmascarar(Mapa);
			}
		}
		});
		/*EVENTO EJECUTAR*/
		
		/*EVENTO DE PRUEBA PARA DESENMASCARAR*/
		TablaEjecucion.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int Fila = TablaEjecucion.rowAtPoint(e.getPoint());
		         int Columna = TablaEjecucion.columnAtPoint(e.getPoint());
		         if ((Fila > 0) && (Columna > 0)) {
		            TablaEjecucion.setValueAt(Mapa[Fila-1][Columna-1], Fila, Columna);	
		            
		            if(Fila+1<Mapa.length) {
		            	TablaEjecucion.setValueAt(Mapa[Fila][Columna-1], Fila+1, Columna);
		            }
		            if(Fila-1>0) {
		            	TablaEjecucion.setValueAt(Mapa[Fila-2][Columna-1], Fila-1, Columna);
		            }
		            if(Columna+1<Mapa[0].length) {
			            TablaEjecucion.setValueAt(Mapa[Fila-1][Columna], Fila, Columna+1);
			        }
		            if(Columna-1>0) {
			            TablaEjecucion.setValueAt(Mapa[Fila-1][Columna-2], Fila, Columna-1);
			        }
		         }
			}
			
		});
		/*EVENTO DE PRUEBA PARA DESENMASCARAR*/
	}
	private void desenmascarar(Point posicion){
		int Fila = TablaEjecucion.rowAtPoint(posicion);
        int Columna = TablaEjecucion.columnAtPoint(posicion);
        if ((Fila > 0) && (Columna > 0)) {
           TablaEjecucion.setValueAt(Mapa[Fila-1][Columna-1], Fila, Columna);	
           
           if(Fila+1<Mapa.length) {
           	TablaEjecucion.setValueAt(Mapa[Fila][Columna-1], Fila+1, Columna);
           }
           if(Fila-1>0) {
           	TablaEjecucion.setValueAt(Mapa[Fila-2][Columna-1], Fila-1, Columna);
           }
           if(Columna+1<Mapa[0].length) {
	            TablaEjecucion.setValueAt(Mapa[Fila-1][Columna], Fila, Columna+1);
	        }
           if(Columna-1>0) {
	            TablaEjecucion.setValueAt(Mapa[Fila-1][Columna-2], Fila, Columna-1);
	        }
        }
	}
	private String[][] CargarArchivo () {
		int SeleccionDeArchivo = 0, FilasTotales = 0, ColumnasTotales = 0, Fila = 0, Columna = 0;
		File Archivo;
		Scanner Leer;
		String Renglon = "";
		String[][] Matriz = null;
		JFileChooser Opcion = new JFileChooser();
		StringTokenizer Partir;
		Opcion.setDialogTitle("Selecciona el archivo");
		FileNameExtensionFilter Filtro = new FileNameExtensionFilter("Archivos de Texto", "txt"); 
		Opcion.setFileFilter(Filtro);
		SeleccionDeArchivo = Opcion.showOpenDialog(frame);
		
		if(SeleccionDeArchivo == JFileChooser.APPROVE_OPTION) {
			Archivo = Opcion.getSelectedFile();
			try {
				Leer = new Scanner(Archivo);
				
				/*CONTAR FILAS Y COLUMNAS DEL ARCHIVO*/
				while (Leer.hasNext()){
					Renglon = Leer.nextLine();
					FilasTotales++;
				}
				Partir = new StringTokenizer(Renglon, ",");
				ColumnasTotales = Partir.countTokens();
				Leer.close();
				/*CONTAR FILAS Y COLUMNAS DEL ARCHIVO*/
				Matriz = new String[FilasTotales][ColumnasTotales];
				Leer = new Scanner(Archivo);
				
				/*LLENADO DE MATRIZ*/
				while (Leer.hasNext()){
					Renglon = Leer.nextLine();
					Partir = new StringTokenizer(Renglon, ",");
				
					while(Partir.hasMoreTokens()) {
						Matriz[Fila][Columna] = Partir.nextToken();
						Columna++;
					}
				Fila++;
				Columna = 0;
				}
				Leer.close();
				/*LLENADO DE MATRIZ*/
				
			} catch(FileNotFoundException Excepcion) {
				Excepcion.printStackTrace();
			}
		}
		
		return Matriz;
	}
	
	private void CrearTabla (String[][] Mapa) {
		int i = 0, j = 0, Caracter = 65;
		
		Object [] Fila, Columna;
		Fila = new Object[1]; 	Columna = new Object[1];

		if(Mapa!=null) {					
			/*CREACIï¿½N DE LA TABLA*/
			for(j=0; j<=Mapa[0].length; j++){
				Modelo.addColumn(Columna);
			}
			for(i=0; i<=Mapa.length; i++){
				Fila[0] = i;
				Modelo.addRow(Fila);
			}
			/*CREACIï¿½N DE LA TABLA*/
			/*FIJAR TAMAï¿½O A COLUMNAS*/
			TableColumnModel columnModel = Tabla.getColumnModel();
			for (i = 0; i < columnModel.getColumnCount(); i++) {
			columnModel.getColumn(i).setPreferredWidth(30);
			}
			/*FIJAR TAMAï¿½O A COLUMNAS*/
			
			/*ENCABEZADOS DE COLUMNAS*/
			for(i = 1; i <= Mapa.length; i++) {
				Modelo.setValueAt(Integer.toString(i), i, 0);
			}
			for(j = 1; j <= Mapa[0].length; j++) {
				Modelo.setValueAt(Character.toString((char)Caracter), 0, j);
				Caracter++;
			}
			Modelo.setValueAt("", 0, 0);
			/*ENCABEZADOS DE COLUMNAS*/
		
			/*LLENADO DE MATRIZ*/
			for(i=1; i<=Mapa.length; i++) {
				for(j=1; j<=Mapa[0].length; j++) {
					Modelo.setValueAt(Mapa[i-1][j-1], i, j);
				}
			}
			/*LLENADO DE MATRIZ*/
		}
	}

	private void Enmascarar (String[][] Mapa) {
		for(int i=1; i<=Mapa.length; i++) {
			for(int j=1; j<=Mapa[0].length; j++) {
				Modelo.setValueAt("-1", i, j);
			}
		}
	}
	
	private String[][] ActualizarMapa(String[][] Mapa, DefaultTableModel Modelo) {
		for(int i=1; i<=Mapa.length; i++) {
			for(int j=1; j<=Mapa[0].length; j++) {
				Mapa[i-1][j-1] = (String)Modelo.getValueAt(i, j);
			}
		}
		return Mapa;
	}
	private DefaultTableCellRenderer setNewRenderer(){
		DefaultTableCellRenderer newRenderer = new 	Celda(){
			@Override
			public Component getTableCellRendererComponent(JTable Tabla, Object Valor, boolean IsSelected, boolean HasFocus, int Fila, int Columna) {
				super.getTableCellRendererComponent(Tabla, Valor, IsSelected, HasFocus, Fila, Columna);
				
				if(Columna==0 || Fila==0) { 
					this.setOpaque(true);
					this.setBackground(Color.decode("0x666666"));	
					this.setForeground(Color.decode("0x000000"));
				} 
				else {
					String[] token = Mapa[Fila][Columna].split(",");
					boolean changed = false;
					for(int i = 0; i < tablaCostos.getRowCount()-2;i++){
						if (token[0].equals(Integer.toString(i))){
							this.setOpaque(true);
							if(token.length == 1 ||token[1].equals("-1"))
								this.setBackground((Color)tablaCostos.obtenerColorTerreno(i));
							else
								this.setBackground((Color)tablaCostos.obtenerColorCriatura(Integer.parseInt(token[1])));
							this.setForeground(Color.decode("0x000000"));
							changed =true;
						}
					}
					if(token[0].equals("-1")){
						this.setBackground(Color.decode("0x000000"));
					}else if(!changed){
						this.setBackground(Color.decode("0x666666"));
					}
				}
					return this;
			}
			
		};
		return newRenderer;
	}
}
