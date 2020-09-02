package viewtable;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import model.Tabelas;

@SuppressWarnings("serial")
public class TableTabelas extends AbstractTableModel {
		
	   private   List<Tabelas> tabelas;
	   String[] columnNames = { "Codigo", "Descrição"};
		
		public  TableTabelas(List<Tabelas> tabelas) {
				this.tabelas = tabelas; // new ArrayList<TabTipo>();
			//	fireTableStructureChanged();
		}
			
		@Override
		public int getRowCount() {
			
			// retorna quantidade de linhas
			return tabelas.size( );
			//    return 1;
		}
		@Override
		public int getColumnCount() {
			// retorna a quantidade de colunas
					return columnNames.length;
		}
		
		@Override
		public String getColumnName(int nameColuna) {
			// retorna nome das colunas
		      return columnNames[nameColuna];
		    }
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// retorna linhas e dados das colunas
		//	AcervoBookMusic acervoBookMusic = (AcervoBookMusic) acervoBookMusics.get(rowIndex);
			Tabelas tbmi= tabelas.get(rowIndex);
			switch(columnIndex) {
				
				case 0:
					 return tbmi.getCodigo();
				case 1:
				     return tbmi.getDescricao();
				default:
					 return "";
			}
		}
			
	 	public void setValueAt(Tabelas tab, int rowIndex) {
			// retorna linhas e dados das colunas alteradas
	 			
	 		tabelas.set(rowIndex, tab);
		 	fireTableDataChanged();
		 }  
					 
					 
		// adiciona uma linha  na tebela
		public  void adiconar(Tabelas tbmi) {
			
			// atualizar view da tabela
			tabelas.add(tbmi);
			fireTableDataChanged();
		}
		
		// remover linha da tabela
		public void remover (Tabelas tbmi, int rowIndex) {
					
			tabelas.remove(rowIndex);
			// atualizar view da tabela
			fireTableDataChanged();
		}
		
		public Tabelas  getTabelas(int linha) {
			// retorna os valores da linha selecionada para formulario
			return tabelas.get(linha);
		}
		
		// autosize das colunas
		public void ajustaTamanhoColunas(JTable table) {
			
			table.getTableHeader().setReorderingAllowed(false);
			table.getTableHeader().setResizingAllowed(false);
			table.setSelectionBackground(Color.orange);
			table.getTableHeader().setFont( new Font( "Dialog" , Font.BOLD, 16 ));
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
