package com.modelo;


public class Mapa {
	private Terreno[][] marco;
	private static int ancho=0;
	private static int alto=0;
	
	public static int obtenerAncho(){
		return ancho;
	}
	
	private static int obtenerAlto(){
		return alto;
	}
	
	public Mapa(int x, int y){
		ancho = x;
		alto = y;
		this.marco = new Terreno[alto][ancho];
		for(int i = 0 ; i < alto ; i++){
			for(int j = 0 ; j < ancho ; j++){
				marco[i][j] = new Terreno();
			}
		}
	}
	
	public void fijarTerreno(int x, int y, String tipoTerreno){
		marco[x][y].fijarDatos(tipoTerreno);
	}

}
