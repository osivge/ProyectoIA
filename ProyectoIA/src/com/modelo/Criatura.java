package com.modelo;

import java.util.ArrayList;

	public class Criatura {
	private ArrayList<Object> refCriatura;
	private Terreno[][] memoria;
	
	public void fijarDatos(String tipoCriatura){
		ArrayList<Object> auxList = CostMov.singTab.array.get(1);
		int i = auxList.indexOf(tipoCriatura);
		for(ArrayList<Object> x : CostMov.singTab.array)
			auxList.add(x.get(i));
		refCriatura = auxList;
	}
	
	public ArrayList<Object> obtenerDatos(){
		return refCriatura;
	}
	
	public void moverArriba(){
		
	}
	
	public void moverAbajo(){
			
		}
	
	public void moverDerecha(){
		
	}
	
	public void moverIzquierda(){
		
	}
}
