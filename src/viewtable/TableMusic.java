package viewtable;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import model.AcervoMusic;

@SuppressWarnings("serial")
public class TableMusic  extends AbstractTableModel {
	private static List<AcervoMusic> tableBookMusic;
	String[] columnNames = { "Dt. Atualização", "Tipo", "Codigo", "Artista", "Titulo", "Genero"};
	@SuppressWarnings("static-access")
	public  TableMusic(List <AcervoMusic> tableBookMusic) {
			this.tableBookMusic = tableBookMusic;
			
		//	fireTableStructureChanged();
	}
	@Override
	public int getColumnCount() {
		// retorna a quantidade de colunas
			// return 5;
		return columnNames.length;
	}
	@Override
	public String getColumnName(int nameColuna) {
		// retorna nome das colunas
	      return columnNames[nameColuna];
	    }
	
	@Override
	public int getRowCount() {
		
		// retorna quantidade de linhas
				return tableBookMusic.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// retorna linhas e dados das colunas
	//	AcervoBookMusic acervoBookMusic = (AcervoBookMusic) acervoBookMusics.get(rowIndex);
		AcervoMusic tbmi= tableBookMusic.get(rowIndex);
		switch(columnIndex) {
			case 0:
				//aqui você passa a data (AAAA-MM-DD e formata para DD-MM-AAAA
				 return formataData(tbmi.getDataAtualizacao());
			//     return tbmi.getDataAtualizacao();
			case 1:
				 return tbmi.getTipo();
			case 2:
			     return tbmi.getCodigoCatalogo();
			case 3:
			     return tbmi.getArtista();
			case 4:
			     return tbmi.getTitulo();
			case 5:
			     return tbmi.getGenero();     
			default:
				
				 return "";
		}
	}
	void fireTableDataChanged1() {
	}
	
	private Object formataData(Date dtAtual) {
		String dataBR;
		if(dtAtual == null) {
			dataBR = null;
		}
		else {
			dataBR = new SimpleDateFormat("dd-MM-yyyy").format(dtAtual);
		//	dataBR = dtAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
		return dataBR;
	}
	// adiciona uma linha  na tebela
	public void adiconar(AcervoMusic tbmi) {
		TableMusic.tableBookMusic.add(tbmi);
		// atualizar view da tabela
		fireTableRowsInserted(tableBookMusic.size() - 1, tableBookMusic.size() - 1);
	}
	// remover linha da tabela
	public void remover (AcervoMusic tbmi) {
		TableMusic.tableBookMusic.remove(tbmi);
		// atualizar view da tabela
		fireTableRowsInserted(tableBookMusic.size() - 1, tableBookMusic.size() - 1);
	}
	
	public   void fireTableDataChanged() {
	}  
		
	// * Seta o valor da célula especificada
		// * pelos índices da linha e da coluna.
	public void setValueAt(AcervoMusic tbmi, int rowIndex) {
			// retorna linhas e dados das colunas alteradas
	 		// podia fazer com switch como no getValueAt	
	 		tableBookMusic.set(rowIndex, tbmi);
		 	fireTableDataChanged();
		 }  
	
	// autosize das colunas
	public static void ajustaTamanhoColunas(JTable table) {
		
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionBackground(Color.orange);
		table.getTableHeader().setFont( new Font( "Dialog" , Font.BOLD, 14 ));
		table.getTableHeader().setBackground(Color.LIGHT_GRAY);	 
		
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 40; // Min width
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
