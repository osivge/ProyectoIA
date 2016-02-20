package com.modelo;

import java.util.ArrayList;

public class CostMov {
	public static final CostMov singTab = new CostMov();
	public ArrayList<ArrayList<Object>> array = new ArrayList<ArrayList<Object>>();
	
	public CostMov(){
		array.add(new ArrayList<Object>());
		array.get(0).add(".");
		array.get(0).add(".");
		array.add(new ArrayList<Object>());
		array.get(1).add(".");
		array.get(1).add(".");
	}
	
	public void agregarCriatura(ArrayList<Object> listaCosto){
		for(int i=0 ; i < array.size() ; i++){
        	array.get(i).add(listaCosto.get(i));
        }
	}
	
	public void agregarTerreno(ArrayList<Object> listaCosto){
		array.add(new ArrayList<Object>());
		for(int i = 0 ; i < array.get(0).size() ; i++){
			array.get(array.size()-1).add(listaCosto.get(i));
		}
	}
	
	public String toString(){
		String str = "";
		for(int i=0;i<array.size();i++){  //(para cada fila)
			str = "";
            for(int j=0;j<array.get(i).size();j++){  //se recorre todas la columnas de la fila
                str += array.get(i).get(j); //se obtiene el elemento i,j
            }
            System.out.println(str);
		}
		return str;
	}
	
}