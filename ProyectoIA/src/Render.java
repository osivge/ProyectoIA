import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Render extends DefaultTableCellRenderer {
private static final long serialVersionUID = 1L;
	
	public Component getTableCellRendererComponent(JTable Mapa, Object Valor, boolean IsSelected, boolean HasFocus, int Fila, int Columna) {
		super.getTableCellRendererComponent(Mapa, Valor, IsSelected, HasFocus, Fila, Columna);
		switch ((String) Valor) {
			case "0":
				this.setOpaque(true);
		        this.setBackground(Color.GREEN);
				break;
			
			case "1":
				this.setOpaque(true);
		        this.setBackground(Color.RED);
				break;
/*				
			case "2":
				break;
				
			case "3":
				break;
				
			case "4":
				break;
			
			case "5":
				break;
			
			case "6":
				break;
			
			case "7":
				break;
				
			case "8":
				break;
				
			case "9":
				break;
	*/			
			default:
				this.setOpaque(true);
				break;
		}
		return this;
	}
}
