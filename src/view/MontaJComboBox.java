package view;

import java.util.List;
import javax.swing.JComboBox;

import db.dao.TabelasDAO;
import model.Tabelas;

public class MontaJComboBox {

	private JComboBox<Object>  parmComboBox;
	private String tabela;
	
	public MontaJComboBox (String sql , JComboBox<Object> comboBox) {
		
		parmComboBox = comboBox;
		tabela = sql;
		String sql2 = "select * from " + tabela + " order by descricao";
		List<Tabelas>  tabelas = TabelasDAO.tabelasComboBox(sql2);
		parmComboBox.removeAllItems();
		parmComboBox.addItem("");
		
		
		for (int registro = 0; registro < tabelas.size(); registro++)
			{
				Tabelas tab = (Tabelas) tabelas.get(registro);
	        	parmComboBox.addItem(tab.getDescricao());
		
			}
	}
}