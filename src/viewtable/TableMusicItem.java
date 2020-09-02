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
import model.AcervoMusicItem;

@SuppressWarnings("serial")
public class TableMusicItem  extends AbstractTableModel {
	private List<AcervoMusicItem> tableBookMusicItem;
	
	String[] columnNames = { "Seq", "Nome Musica", "Tempo", "Compositor", "Participante"};
	
	/* Cria um CadastroTableModel vazio. */
	public TableMusicItem() {
		tableBookMusicItem = new ArrayList<AcervoMusicItem>();
	}
	
	public  TableMusicItem (List <AcervoMusicItem> tableBookMusicItem) {
			this.tableBookMusicItem = tableBookMusicItem;
		
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
		  		return tableBookMusicItem.size();
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
		AcervoMusicItem tbmi= tableBookMusicItem.get(rowIndex);
		switch(columnIndex) {
			case 0:
				 return tbmi.getNumSeq();
			case 1:
				 return tbmi.getTituloItem();
			case 2:
			     return tbmi.getTempoDuracao();
			case 3:
			     return tbmi.getCompositor();
			case 4:
			     return tbmi.getParticipante();
			default:
				 return "";
		}
		
	}
	
	
/*	public void setValueAt(AcervoBookMusicItem tabBookMusicItem, int rowIndex) {
		// retorna linhas e dados das colunas alteradas
 			
		tableBookMusicItem.set(rowIndex, tabBookMusicItem);
	 	fireTableDataChanged();
	 }  */
	@Override
	public void setValueAt(Object valor, int linha, int coluna){
		AcervoMusicItem tbmi= tableBookMusicItem.get(linha);
		String val = (String) valor;
        if( val == null || val.isEmpty() ) {
        	
        	return;
        }
         
        switch(coluna){
        	case 0:  tbmi.setNumSeq(Integer.parseInt(val.toString()));break;
           	case 1:  tbmi.setTituloItem((String)valor);break;
            case 2:  tbmi.setTempoDuracao((String)valor);break;
            case 3:  tbmi.setCompositor((String)valor) ;break;
            case 4:  tbmi.setPaticipante((String)valor) ;break;
        } 
        this.fireTableCellUpdated(linha, coluna);
    }
	
	// adiciona uma linha  na tebela
	public void adiconar(AcervoMusicItem tbmi) {
		this.tableBookMusicItem.add(tbmi);
		// atualizar view da tabela
		fireTableRowsInserted(tableBookMusicItem.size() - 1, tableBookMusicItem.size() - 1);
	}
	// remover linha da tabela
	public void remover (AcervoMusicItem tbmi) {
		this.tableBookMusicItem.remove(tbmi);
		// atualizar view da tabela
		fireTableRowsInserted(tableBookMusicItem.size() - 1, tableBookMusicItem.size() - 1);
	}
	
	
	/* Remove todos os registros da tabela. */
	public  void limpar() {
		// Remove todos os elementos da table.
		tableBookMusicItem.clear();

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
	public void addLinha(AcervoMusicItem tbmi) {
		
		List<AcervoMusicItem> acervoMusicItens = new ArrayList<AcervoMusicItem>();
		
		int a = 0;
		int c = 1;
		for ( a = 0; a < 25; a++) {
			Integer numSeq = c;
			String tituloItem = "";
			String tempoDuracao = "";
			String compositor =  "";
			String formato = "";
			String tipo = "";
			String participante = "";
			String codigCatalogo = "";
			tbmi = new AcervoMusicItem(tipo, codigCatalogo, numSeq, tituloItem, tempoDuracao,
					compositor, participante, formato);
			acervoMusicItens.add(tbmi);
			 c = c+1;  
		}
			
			this.tableBookMusicItem.addAll(acervoMusicItens); 
		
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
