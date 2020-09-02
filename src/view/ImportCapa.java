package view;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ImportCapa {

	private static String localCapa;
	
	public static  String impotarCapa (String arq)  {
			// define a extemsão do arquivo a ser lido
		
				localCapa = arq;
				
			 	if (localCapa == null) {
			 		JOptionPane.showMessageDialog(null, "Importar Capa,"
						     + " Não foi escolhida nenhuma capa");
				 }	
			 	
			 	 if (localCapa != null) {
			 	/*	MostraCapa capa = new  MostraCapa(localCapa);  // montar a capa
			 		
					// tornar visivel a capa		
					capa.setModal(true);
					capa.setSize(400,430);
					capa.setTitle("Mostra Capa ");
					capa.setLocationRelativeTo(null);
					capa.setVisible(true);
				//	capaField.setText(arqFile);  */
			 		 
			 		  ImageIcon icone = new ImageIcon(localCapa);
					 
			 		  icone.setImage(icone.getImage().getScaledInstance(300, 300, 300));
					
			 		   int confirm = JOptionPane.showConfirmDialog(null, icone, "Esta e a capa desejada "
					 			+ "?? ", 
					 			JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null);
					   
					 	if (confirm != 0) {
					 		   localCapa = null;
						   }   
					  
				} 
			 	arq = localCapa;
			return localCapa;
	}
}
