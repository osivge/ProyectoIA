package com.modelo;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CostMov extends JTable{
	
	public CostMov(){
		modelo = (DefaultTableModel)this.getModel();
		this.setModel(modelo);
		modelo.addColumn(columnNames);
		this.getColumnModel().getColumn(0).setWidth(30);
		modelo.addRow(cells);
		modelo.addColumn(columnNames);
		this.getColumnModel().getColumn(1).setWidth(30);
		modelo.addRow(cells);
		modelo.addColumn(columnNames);
		this.getColumnModel().getColumn(2).setWidth(30);
		modelo.addRow(cells);
		this.setValueAt("ID", 0, 0);
		this.setValueAt("Nombre", 1, 1);
		this.setValueAt("Terrenos", 2, 1);
		this.setValueAt("Criaturas", 1, 2);
		this.setValueAt("Color", 2, 2);
		System.out.println(modelo.getDataVector());
		
		this.agregarCriatura();
		this.setValueAt("Humano", 1, contCriaturas+2);
		this.setValueAt(Color.decode("0x660033"), 2, contCriaturas+2);
		
		this.agregarTerreno();
		this.setValueAt("Montaña", contTerrenos+2, 1);
		this.setValueAt(Color.decode("0x660033"), contTerrenos+2, 2);
		
		this.agregarTerreno();
		this.setValueAt("Camino", contTerrenos+2, 1);
		this.setValueAt(Color.decode("0xFFCC66"), contTerrenos+2, 2);
 
		this.agregarTerreno();
		this.setValueAt("Agua", contTerrenos+2, 1);
		this.setValueAt(Color.decode("0x3399FF"), contTerrenos+2, 2);

		this.agregarTerreno();
		this.setValueAt("Arena", contTerrenos+2, 1);
		this.setValueAt(Color.decode("0xFFFF99"), contTerrenos+2, 2);
		
		this.agregarTerreno();
		this.setValueAt("Bosque", contTerrenos+2, 1);
		this.setValueAt(Color.decode("0x009933"), contTerrenos+2, 2);
		
		this.agregarTerreno();
		this.setValueAt("Pantano", contTerrenos+2, 1);
		this.setValueAt(Color.decode("0x666633"), contTerrenos+2, 2);
	
		this.agregarTerreno();
		this.setValueAt("Nieve", contTerrenos+2, 1);
		this.setValueAt(Color.decode("0xFFFFFF"), contTerrenos+2, 2);
		
		this.agregarTerreno();
		this.setValueAt("Pared", contTerrenos+2, 1);
		this.setValueAt(Color.BLACK, contTerrenos+2, 2);
	}
	
	public Object obtenerIdTerreno(int indTerreno){
		return this.getValueAt(indTerreno+3, 0);
	}
	
	public Object obtenerNombreTerreno(int indTerreno){
		return this.getValueAt(indTerreno+3, 1);
	}
	
	public Object obtenerColorTerreno(int indTerreno){
		return this.getValueAt(indTerreno+3, 2);
	}
	
	public Object obtenerIdCriatura(int indCriatura){
		return this.getValueAt(0, indCriatura+3);
	}
	
	public Object obtenerNombreCriatura(int indCriatura){
		return this.getValueAt(1, indCriatura+3);
	}
	
	public Object obtenerColorCriatura(int indCriatura){
		return this.getValueAt(2, indCriatura+3);
	}
	
	public Object obtenerCosto(int indTerreno, int indCriatura){
		return this.getValueAt(indTerreno+3, indCriatura+3);
	}
	public ArrayList<Integer> obtenerCostosCriatura(int indCriatura){
		ArrayList<Integer> costos = new ArrayList<Integer>();
		for(int i = 3; i<this.getRowCount();i++){
			costos.add(Integer.parseInt((String)this.getValueAt(i, indCriatura+3)));
		}
		return costos;
	}
	
	public void agregarCriatura(){
		modelo.addColumn("");
		this.setValueAt(contCriaturas++, 0, modelo.getColumnCount()-1);
		for(int i = 3; i<this.getRowCount();i++){
			this.setValueAt("-1", i, modelo.getColumnCount()-1);
		}
	}
	
	public void agregarTerreno(){
		modelo.addRow(new Object[modelo.getColumnCount()]);
		this.setValueAt(contTerrenos++, modelo.getRowCount()-1, 0);
		for(int i = 3; i<this.getColumnCount();i++){
			this.setValueAt("-1", modelo.getRowCount()-1, i);
		}
	}
	
	private int contCriaturas = 0;
	
	private int contTerrenos = 0;
	
	public DefaultTableModel modelo;
	
	private Object[] cells = {null};
	
	private String columnNames = "";
}