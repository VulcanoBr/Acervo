package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class TrataArquvoCapa {
	private static ImageIcon imagemIcom;
	private static BufferedImage image;
	public static ImageIcon trataArquvoCapa(String localCapa)  {
		File file;		
		try
	         {
			  if (!localCapa.isEmpty()) {
				 file = new File(localCapa);
				 if( file.exists() ) {
					 image = ImageIO.read(file);
				 } else  {
					 		file = new File("D:/BdCd_covers/sem capa.png");
					 		image = ImageIO.read(file);
				 		}
			  } else {
				    	file = new File("D:/BdCd_covers/sem capa.png");
			 			image = ImageIO.read(file);
			  		}
	 		}
	        
	         catch (IOException e2)
	         	{
	        		e2.printStackTrace();
	        		System.exit(1);
	         	}
				
	 	imagemIcom = new ImageIcon(image);
	 	imagemIcom.setImage(imagemIcom.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
		
	 	return imagemIcom;
	}
	
}
