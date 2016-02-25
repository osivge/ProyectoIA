import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Celda extends DefaultTableCellRenderer {
private static final long serialVersionUID = 1L;
	
	public Component getTableCellRendererComponent(JTable Mapa, Object Valor, boolean IsSelected, boolean HasFocus, int Fila, int Columna) {
		super.getTableCellRendererComponent(Mapa, Valor, IsSelected, HasFocus, Fila, Columna);
		
		if(Columna==0 || Fila==0) { 
			this.setOpaque(true);
			this.setBackground(Color.decode("0x666666"));	
			this.setForeground(Color.decode("0x000000"));
		} 
		else {
			String[] token = ((String) Valor).split(","); 
			switch (token[0]) {
			
			
				case "0": //MONTAÑA
					this.setOpaque(true);
					this.setBackground(Color.decode("0x660033"));
					this.setForeground(Color.decode("0x000000"));
					break;
			 
				case "1": //CAMINO
					this.setOpaque(true);
					this.setBackground(Color.decode("0xFFCC66"));
					this.setForeground(Color.decode("0x000000"));
					break;
				
				case "2": //AGUA
					this.setOpaque(true);
					this.setBackground(Color.decode("0x3399FF"));
					this.setForeground(Color.decode("0x000000"));
					break;
					
				case "3": //ARENA
					this.setOpaque(true);
					this.setBackground(Color.decode("0xFFFF99"));
					this.setForeground(Color.decode("0x000000"));
					break;
				
				case "4": //BOSQUE
					this.setOpaque(true);
					this.setBackground(Color.decode("0x009933"));
					this.setForeground(Color.decode("0x000000"));
					break;
			
				case "5": //PANTANO
					this.setOpaque(true);
					this.setBackground(Color.decode("0x666633"));
					this.setForeground(Color.decode("0x000000"));
					break;
			
				case "6": //NIEVE
					this.setOpaque(true);
					this.setBackground(Color.decode("0xFFFFFF"));
					this.setForeground(Color.decode("0x000000"));
					break;
					
				case "-1"://PARED
					this.setOpaque(true);
					this.setBackground(Color.BLACK);	
					this.setForeground(Color.BLACK);
					break;
				
				default: //DESCONOCIDO
					this.setOpaque(true);
					this.setBackground(Color.decode("0x666666"));
					this.setForeground(Color.decode("0x000000"));
					break;
			}
		}
			return this;
	}

}


/**********************************
 * VISIBILIDAD:
 * BLACK: 		ENMASCARAMIENTO.
 * 0x666666: 	FILA Y COLUMNA 0 
 * LIGHT GRAY:	CELDAS VISTAS PERO SIN VISITAR.
 * 
 **********************************/


//System.out.println(this.getBackground());