package com.modelo;

import java.awt.Point;
import java.util.ArrayList;

	public class Criatura {
	private int refCriatura;
	private Point posicion;
	private String[][] Mapa;
	private ArrayList<Integer> costos;
	
	public Criatura(ArrayList<Integer> costos,String[][] Mapa){
		this.Mapa = Mapa;
		this.costos = costos;		
	}
	public void fijarPosicion(Point posicion){
		this.posicion = posicion;
	}
	
	public int obtenerIndice(){
		return refCriatura;
	}
	public Point obtenerPosicion(){
		return this.posicion;
	}
	
	public Point moverArriba(){
		if(this.posicion.y!=0){
			if(costos.get(Integer.parseInt(Mapa[this.posicion.y-1][this.posicion.x]))!= -1)
			this.posicion.y--;
		}
		return this.posicion;
		
	}
	
	public Point moverAbajo(){
		if(this.posicion.y!=Mapa.length-1){
			if(costos.get(Integer.parseInt(Mapa[this.posicion.y+1][this.posicion.x]))!= -1)
			this.posicion.y++;
		}
		return this.posicion;
	}
	
	public Point moverDerecha(){
		if(this.posicion.x!=Mapa[0].length-1){
			if(costos.get(Integer.parseInt(Mapa[this.posicion.x+1][this.posicion.x]))!= -1)
			this.posicion.x++;
		}
		return this.posicion;
	}
	
	public Point moverIzquierda(){
		if(this.posicion.x!=0){
			if(costos.get(Integer.parseInt(Mapa[this.posicion.x-1][this.posicion.x]))!= -1)
			this.posicion.x--;
		}
		return this.posicion;
		
	}
}
