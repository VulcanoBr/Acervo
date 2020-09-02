package view;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MostraCapa {


	  private static String minhaImagem;
	  
	
	public static  void mostraCapa(String arq) {
			
		  minhaImagem = arq;
	   	
		  ImageIcon icone = new ImageIcon(minhaImagem);
		  icone.setImage(icone.getImage().getScaledInstance(400, 400, 350));
		  
		  JOptionPane.showMessageDialog(null, icone, "Capa", JOptionPane.PLAIN_MESSAGE, null);
		 
	 }
   
}
