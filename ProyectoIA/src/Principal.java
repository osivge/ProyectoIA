import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Principal {

	private JFrame frame;
/*****************************************************/
	private DefaultTableModel Modelo;
	private JTable Tabla;
	private JTable tablaCriaturas;
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*DECLARACION DE LA TABLA*/
		Tabla = new JTable();
		Tabla.setModel(new DefaultTableModel(
			new Object[][] {	},
			new String[]   {	}
		));
		Tabla.setBounds(10, 0, 200, 200);
		//Mapa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		/*DECLARACION DE LA TABLA*/
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelEditorMapa = new JPanel();
		tabbedPane.addTab("Mapa", null, panelEditorMapa, null);

		JPanel panelPropiedadesDibujo = new JPanel();
		panelEditorMapa.add(panelPropiedadesDibujo, BorderLayout.SOUTH);		
		JPanel panelPropiedadesCelda = new JPanel();
		panelEditorMapa.add(panelPropiedadesCelda, BorderLayout.EAST);
		
		/*MEN� ARCHIVO -> ABRIR*/
		panelEditorMapa.setLayout(new BorderLayout(0, 0));
		panelEditorMapa.add(Tabla, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		JMenuItem mntmAbrir = new JMenuItem("Abrir...");
		mnArchivo.add(mntmAbrir);
		/*MEN� ARCHIVO -> ABRIR*/
		
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
				DefaultTableModel modeloCriaturas = (DefaultTableModel)tablaCriaturas.getModel();
				modeloCriaturas.addColumn("Costo a...");
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
				
			}
		});
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.fill = GridBagConstraints.BOTH;
		gbc_btnGuardar.gridx = 0;
		gbc_btnGuardar.gridy = 2;
		panelBotonesCriaturas.add(btnGuardar, gbc_btnGuardar);
		
		JScrollPane scrollPaneTablaCriaturas = new JScrollPane();
		panelEditorCriaturas.add(scrollPaneTablaCriaturas, BorderLayout.CENTER);
		
		tablaCriaturas = new JTable();
		tablaCriaturas.setModel(new DefaultTableModel(
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
		tabbedPane.addTab("Ejecucion", null, panelEjecucion, null);
		
		/*FIN Panel Ejecucion*/
		/*EVENTO ABRIR*/
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = 0, j = 0, Caracter = 65;
				Tabla.setModel(new DefaultTableModel());
				Modelo = (DefaultTableModel)Tabla.getModel();
				Tabla.setDefaultRenderer(Object.class, new Render());
				String[][] Mapa = CargarArchivo();
				Object [] Fila, Columna;
				Fila = new Object[1]; 	Columna = new Object[1];
				
				if(Mapa!=null) {					
					/*CREACI�N DE LA TABLA*/
					for(j=0; j<=Mapa[0].length; j++){
						Modelo.addColumn(Columna);
					}
					for(i=0; i<=Mapa.length; i++){
						Fila[0] = i;
						Modelo.addRow(Fila);
					}
					/*CREACI�N DE LA TABLA*/
					
					/*FIJAR TAMA�O A COLUMNAS*/
					TableColumnModel columnModel = Tabla.getColumnModel();
					for (i = 0; i < columnModel.getColumnCount(); i++) {
					columnModel.getColumn(i).setPreferredWidth(30);
					}
					/*FIJAR TAMA�O A COLUMNAS*/
					
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
		});
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

}
