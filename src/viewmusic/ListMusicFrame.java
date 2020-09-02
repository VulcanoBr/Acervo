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

import model.AcervoMusic;
import view.GBC;

@SuppressWarnings("serial")
public class ListMusicFrame extends JDialog {

	private JTable table;
	// Definição das colunas da tabela /
	private String[] columnNames = { "Codigo Catalogo", "Artista", "Ttulo", 
									 " Ano ", "Genero", "Total", "Tempo", "Formato", "Pais Lançamento"};

	private List<AcervoMusic> acervoMusics;

	public ListMusicFrame(JFrame owner, List<AcervoMusic> acervoMusics) {
		super(owner);
		this.acervoMusics = acervoMusics;
		setTitle("Listar Acervo Musica");
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
				return acervoMusics.size();
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
				AcervoMusic acervoMusic = acervoMusics.get(row);
				switch (column) {
				case 0:
					return acervoMusic.getCodigoCatalogo();
				case 1:
					return acervoMusic.getArtista();
				case 2:
					return acervoMusic.getTitulo();
				case 3:
					return acervoMusic.getAnoLancamento();
				case 4:
					return acervoMusic.getGenero();
				case 5:
					return acervoMusic.getQtdTotal();
				case 6:
					return acervoMusic.getTempoTotal();
				case 7:
					return acervoMusic.getFormato();
				case 8:
					return acervoMusic.getPaisLancamento();
				default:
					return "???";
				}
			}
		});
		return table;
	}
}
