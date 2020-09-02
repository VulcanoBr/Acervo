package view;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EscolherFile {
	private static String arq;
	private static String extensao;
	
	public static String escolherFile (String arq2, String extensao2) {
		extensao = extensao2;
	 	arq = arq2;
		// escolher arquivo para importacao dos dados
		FileNameExtensionFilter filtro = null;
		JFileChooser fileChooser = new JFileChooser();
		
		if (extensao == "Excel") {
			filtro = new FileNameExtensionFilter("*.xls",  "xls");
				
		}
		 else {
			 	filtro = new FileNameExtensionFilter("Image File","jpeg","JPEG"
		           ,"bmp","BMP","jpg","JPG","gif","GIF","png", "PNG");
		      	 
			 }
		fileChooser.setFileFilter(filtro);
		     	    	
		int resposta = fileChooser.showOpenDialog(null);
		    	
		File  file = new  File ("");
		    	
		if(resposta == JFileChooser.APPROVE_OPTION) {
		   		file =	 fileChooser.getSelectedFile();
		   		arq = file.getPath();
		   			
		   		JOptionPane.showMessageDialog(null,  "Arquivo selecionado com sucesso !!!",
		   				"Selecionar Arquivo",JOptionPane.INFORMATION_MESSAGE );
		}  else  if (resposta == JFileChooser.CANCEL_OPTION) {
		   			 JOptionPane.showMessageDialog(null, "cancelado a escolha do arquivo !!",
		   					"Selecionar arquivo",JOptionPane.ERROR_MESSAGE );
		     	 }
		     	
	arq2  = arq;
		return arq;
	}
		
}
