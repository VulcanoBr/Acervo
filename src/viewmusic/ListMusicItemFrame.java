package viewmusic;

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
import model.AcervoMusicItem;

@SuppressWarnings("serial")
public class ListMusicItemFrame extends JDialog{
	
	private JTable table;
	// Definição das colunas da tabela /
	private String[] columnNames = { "Codigo Catalogo", "Seq.", "Musica", 
									 "Tempo Duração", "Compositor", "Participante", "Formato"};

	private List<AcervoMusicItem> acervoMusicItens;

	public ListMusicItemFrame(JFrame owner, List<AcervoMusicItem> acervoMusicItens) {
		super(owner);
		this.acervoMusicItens = acervoMusicItens;
		setTitle("Listar Acervo Item Musica");
		buildMusic();
		pack();
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(owner);
		setVisible(true);
	}

	private void buildMusic() {
		setLayout(new GridBagLayout());
		add(new JScrollPane(buildTable()), new GBC(0, 0));
		// dois clique sobre a linha da lista apresentada
		table.addMouseListener(new MouseAdapter() {  
            @Override  
            public void mouseClicked(MouseEvent e) {  
                if(e.getClickCount() == 2) {  
                    int row = table.getSelectedRow();  
                     
                    String codigoCatalogo = String.valueOf(table.getValueAt(row, 0));
                   
                    new ConsultMusicFrame(null, codigoCatalogo);
                    
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
				return acervoMusicItens.size();
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
				AcervoMusicItem acervoMusicItem = acervoMusicItens.get(row);
				switch (column) {
				case 0:
					return acervoMusicItem.getCodigoCatalogo();
				case 1:
					return acervoMusicItem.getNumSeq();
				case 2:
					return acervoMusicItem.getTituloItem();
				case 3:
					return acervoMusicItem.getTempoDuracao();
				case 4:
					return acervoMusicItem.getCompositor();
				case 5:
					return acervoMusicItem.getParticipante();
				case 6:
					return acervoMusicItem.getFormato();
				default:
					return "???";
				}
			}
		});
		return table;
	}

}
