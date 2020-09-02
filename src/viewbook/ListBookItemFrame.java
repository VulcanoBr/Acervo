package viewbook;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import view.GBC;
import model.AcervoBookItem;

@SuppressWarnings("serial")
public class ListBookItemFrame extends JDialog{
	
	private JTable table;
	// Definição das colunas da tabela /
	private String[] columnNames = { "Codigo Catalogo", "Capitulo", "Descrição do Capitulo", 
									 "Num Pagina", "Qtd Páginas", "Formato"};

	private List<AcervoBookItem> acervoBookItens;

	public ListBookItemFrame(JFrame owner, List<AcervoBookItem> allAcervoBookItens) {
		super(owner);
		this.acervoBookItens = allAcervoBookItens;
		setTitle("Listar Acervo Item Livro");
		buildBook();
		pack();
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(owner);
		setVisible(true);
	}

	private void buildBook() {
		setLayout(new GridBagLayout());
		add(new JScrollPane(buildTable()), new GBC(0, 0));
		// dois clique sobre a linha da lista apresentada
		table.addMouseListener(new MouseAdapter() {  
            @Override  
            public void mouseClicked(MouseEvent e) {  
                if(e.getClickCount() == 2) {  
                    int row = table.getSelectedRow();  
                     
                    String codigoCatalogo = String.valueOf(table.getValueAt(row, 0));
                   
                    new ConsultBookFrame(null, codigoCatalogo);
                    
                }  
            }  
        });
	}

	private JTable buildTable() {
		this.table = new JTable();
		table.getTableHeader().setFont( new Font( "Dialog" , Font.BOLD, 16 ));
		table.getTableHeader().setBackground(Color.LIGHT_GRAY);	
		this.table.setModel(new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			 // Captura o total de linhas da tabela 
			public int getRowCount() {
				return acervoBookItens.size();
			}
			// Captura o total de colunas da tabela 
			public int getColumnCount() {
				return columnNames.length;
			}
			// Captura o nome da coluna 
			public String getColumnName(int column) {
				return columnNames[column];
			}

			public Object getValueAt(int row, int column) {
				// Captura o registro informado 
				AcervoBookItem acervoBookItem = acervoBookItens.get(row);
				switch (column) {
				case 0:
					return acervoBookItem.getCodigoCatalogo();
				case 1:
					return acervoBookItem.getNumCaptulo();
				case 2:
					return acervoBookItem.getDescCaptulo();
				case 3:
					return acervoBookItem.getNumPage();
				case 4:
					return acervoBookItem.getQtdPage();
				case 5:
					return acervoBookItem.getFormato();
				default:
					return "???";
				}
			}
		});
		return table;
	}

}
