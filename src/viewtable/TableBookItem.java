package viewtable;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import model.AcervoBookItem;

@SuppressWarnings("serial")
public class TableBookItem extends AbstractTableModel {
	
private List<AcervoBookItem> tableBookItem;
	
	String[] columnNames = { "Capitulo", "Descrição Capitulo", "Pagina", "Total Paginas"};
	
	/* Cria um CadastroTableModel vazio. */
	public TableBookItem() {
		tableBookItem = new ArrayList<AcervoBookItem>();
	}
	
	public  TableBookItem (List <AcervoBookItem> tableBookItem) {
			this.tableBookItem = tableBookItem;
		
			fireTableStructureChanged();
	}
	@Override
	public int getColumnCount() {
		// retorna a quantidade de colunas
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		
		// retorna quantidade de linhas
		  		return tableBookItem.size();
		//	return 25;
	}

	@Override
	public String getColumnName(int nameColuna) {
		// retorna nome das colunas
	      return columnNames[nameColuna];
	    }
		
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// retorna linhas e dados das colunas
		AcervoBookItem tbmi= tableBookItem.get(rowIndex);
		switch(columnIndex) {
			case 0:
				 return tbmi.getNumCaptulo();
			case 1:
				 return tbmi.getDescCaptulo();
			case 2:
			     return tbmi.getNumPage();
			case 3:
			     return tbmi.getQtdPage();
			default:
				 return "";
		}
		
	}

	
/*	public void setValueAt(AcervoBookItem tabBookItem, int rowIndex) {
		// retorna linhas e dados das colunas alteradas
 			
		tableBookItem.set(rowIndex, tabBookItem);
	 	fireTableDataChanged();
	 }  */
	@Override
	public void setValueAt(Object valor, int linha, int coluna){
		AcervoBookItem tbmi= tableBookItem.get(linha);
		String val = (String) valor;
        if( val == null || val.isEmpty() ) {
        
        	return;
        }
         
        switch(coluna){
        	case 0:  tbmi.setNumCaptulo(Integer.parseInt(val.toString()));break;
           	case 1:  tbmi.setDescCaptulo((String)valor);break;
            case 2:  tbmi.setNumPage(Integer.parseInt(val));break;
            case 3:  tbmi.setQtdPage(Integer.parseInt(val.toString())) ;break;
            
        } 
        this.fireTableCellUpdated(linha, coluna);
    }
	
	// adiciona uma linha  na tebela
	public void adiconar(AcervoBookItem tbmi) {
		this.tableBookItem.add(tbmi);
		// atualizar view da tabela
		fireTableRowsInserted(tableBookItem.size() - 1, tableBookItem.size() - 1);
	}
	// remover linha da tabela
	public void remover (AcervoBookItem tbmi) {
		this.tableBookItem.remove(tbmi);
		// atualizar view da tabela
		fireTableRowsInserted(tableBookItem.size() - 1, tableBookItem.size() - 1);
	}
	
	
	/* Remove todos os registros da tabela. */
	public  void limpar() {
		// Remove todos os elementos da table.
		tableBookItem.clear();

		// Reporta a mudança. O JTable recebe a notificação
		// e se redesenha permitindo que visualizemos a atualização.
		fireTableDataChanged();
	}

	/* Retorna um valor booleano que define se a célula em questão
	 * pode ser editada ou não.
	 * Este método é utilizado pela JTable na hora de definir o editor da célula.
	 * Neste caso, estará sempre retornando false, não permitindo que nenhuma
	 * célula seja editada. */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
		      return false;
		   } else {
		      return true;
		   }
		
	}
	// cria 25 linhas vazias na jtable, para edição das inofrmações 
	// sobre itens(titulo,  da musica 
	public void addLinha(AcervoBookItem tbmi) {
		
		List<AcervoBookItem> acervoBookItens = new ArrayList<AcervoBookItem>();
		
		int a = 0;
		int c = 1;
		for ( a = 0; a < 25; a++) {
			Integer numCapitulo = c;
			String descCapitulo = "";
			Integer numPage = null;
			Integer qtdPage =  null;
			String formato = "";
			String tipo = "";
			String codigCatalogo = "";
			tbmi = new AcervoBookItem(tipo, codigCatalogo, numCapitulo, descCapitulo,
					numPage, qtdPage, formato);
			acervoBookItens.add(tbmi);
			 c = c+1;  
		}
			
			this.tableBookItem.addAll(acervoBookItens); 
		
       fireTableDataChanged();
    }

	// autosize das colunas
	public void ajustaTamanhoColunas(JTable table) {
		
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionBackground(Color.orange);
		table.getTableHeader().setFont( new Font( "Dialog" , Font.BOLD, 14 ));
		table.getTableHeader().setBackground(Color.LIGHT_GRAY);	 
		
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 20; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	    
	    return;
	}
	

}
