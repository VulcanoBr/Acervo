package view;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class LimetedNoCharacters extends PlainDocument {
	
	private int iMaxLength;
	public LimetedNoCharacters(int maxlen) {
			super();
			iMaxLength = maxlen;
	}
 	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException
 		{
 		if (str == null)  {
 			return;
 		} 
 		if (iMaxLength <= 0)
        {
            super.insertString(offset, str, attr);
            return;
        }
 		
 		int tam = (this.getLength() + str.length());
 		 //Se o tamanho final for menor, chama insertString() aceitando a String
        if (tam <= iMaxLength)  {
            super.insertString(offset, str.replaceAll("[aA-zZ]",""), attr); // so aceita numeros
        	} else {
            //Caso contrário, limita a string e envia para insertString() que aceita a string
        		if (getLength() == iMaxLength) return;
        		String novaStr = str.substring(0, (iMaxLength - getLength()));
        		super.insertString(offset, novaStr.replaceAll("[aA-zZ]",""),  attr);
        	}
 		
	}
}
