package viewmusic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;

import view.GBC;
import view.MostraCapa;
import view.TrataArquvoCapa;
import viewtable.TableMusicItem;
import model.AcervoMusic;
import model.AcervoMusicItem;
import db.dao.ConsultMusicDAO;
import db.dao.ConsultMusicItemDAO;


@SuppressWarnings("serial")
public class ConsultMusicFrame extends JDialog {
		
	private JLabel codigoCatalogoLabel, artistaLabel, tituloLabel, anoLancamentoLabel, 
				   generoLabel, estiloLabel, qtdTotalLabel, tempoTotalLabel, formatoLabel, 
				   patrocinadorLabel, paisLabel, codigoIdLabel,codigoBarraLabel, capaLabel,
				   jLabel;
	private	JTextField codigoCatalogoField;
	private JTextField artistaField;
	private JTextField tituloField;
	private JTextField codigoIdField;
	private JTextField codigoBarraField;
	private JFormattedTextField tempoTotalField;
	private JTextField formatoField, anoLancamentoField, generoField, 
			estiloField, qtdTotalField, paisLancamentoField, patrocinadorField,
			capaField;
	private JPanel panel, panel1, panel2, panel3, panel6;
	protected String button = null;
	private JTable table;
	private TableMusicItem model;
	private JScrollPane  scrollPane;
	
	public   ConsultMusicFrame (JFrame frame, String codigoCatalogo) {
	    super(frame);	
		
		buildMusicFrame(codigoCatalogo);
		pack();
		setModal(true);
		setResizable(false);
		setTitle("Consulta  Acervo");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(frame);
		setVisible(true);
	}

	protected  void buildMusicFrame(String codigoCatalogo) {
		setLayout(new GridBagLayout());

		codigoCatalogoLabel = new JLabel("Código de Catalogo :");
		add(codigoCatalogoLabel, new GBC(0,0).insets(5, 10, 5, 10).left());
		codigoCatalogoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		codigoCatalogoField = new JTextField(20);
		codigoCatalogoField.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		codigoCatalogoField.setForeground(Color.BLUE);
		add(codigoCatalogoField, new GBC(1,0).insets(5, 0, 0, 10).left());
	        
		artistaLabel = new JLabel("Artista :");
		artistaLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(artistaLabel, new GBC(0,2).insets(5, 10, 5, 10).left());
		artistaField = new JTextField(40);
		artistaField.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		artistaField.setForeground(Color.BLUE);
		add(artistaField, new GBC(1,2).insets(5, 0, 0, 10).left());

		tituloLabel = new JLabel("Album : ");
		tituloLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(tituloLabel, new GBC(0,3).insets(5, 10, 5, 10).left());
		tituloField = new JTextField(40);
		tituloField.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		tituloField.setForeground(Color.BLUE);
		add(tituloField, new GBC(1,3).insets(5,  0,  0, 10).left());

		anoLancamentoLabel = new JLabel("Ano Lançamento : ");
		anoLancamentoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(anoLancamentoLabel, new GBC(0,4).insets(5, 10, 5, 10).left());
		anoLancamentoField = new JTextField(4);
		anoLancamentoField.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		anoLancamentoField.setForeground(Color.BLUE);
		add(anoLancamentoField, new GBC(1,4).insets(5,  0,  0, 0).left());

		generoLabel = new JLabel("     Genero : ");
		generoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		generoField = new JTextField(8);
		generoField.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		generoField.setForeground(Color.BLUE);

		estiloLabel = new JLabel("  Estilo : ");
		estiloLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		estiloField = new JTextField(8);
		estiloField.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		estiloField.setForeground(Color.BLUE);
		
		panel = new JPanel();
		panel.add(generoLabel);
		panel.add(generoField);
		panel.add(estiloLabel);
		panel.add(estiloField);
		getContentPane().add(panel, new GBC(0, 4).gridwh(2, 1).right().insets(5, 0, 5, 10));

		qtdTotalLabel = new JLabel("Total de Musicas : ");
		qtdTotalLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(qtdTotalLabel, new GBC(0,5).insets(5, 10, 5, 10).left());
		qtdTotalField = new JTextField(4);
		qtdTotalField.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		qtdTotalField.setForeground(Color.BLUE);
		add(qtdTotalField, new GBC(1,5).insets(5,  0,  0, 0).left());

		tempoTotalLabel = new JLabel(" Tempo Total : ");
		tempoTotalLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
	 	tempoTotalField = new JFormattedTextField((setMascara("##:##:##")));
		tempoTotalField.setColumns(5);
		tempoTotalField.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		tempoTotalField.setForeground(Color.BLUE);

		formatoLabel = new JLabel("         Formato : ");
		formatoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		formatoField = new JTextField(8);
		formatoField.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		formatoField.setForeground(Color.BLUE);
		
		panel1 = new JPanel();
		panel1.add(tempoTotalLabel);
		panel1.add(tempoTotalField);
		panel1.add(formatoLabel);
		panel1.add(formatoField);
		getContentPane().add(panel1, new GBC(0, 5).gridwh(2, 1).right().insets(5, 0, 5, 10));

		patrocinadorLabel = new JLabel("Gravadora : ");
		patrocinadorLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(patrocinadorLabel, new GBC(0,6).insets(5, 10, 5, 10).left());
		patrocinadorField = new JTextField(10);
		patrocinadorField.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		patrocinadorField.setForeground(Color.BLUE);
		add(patrocinadorField, new GBC(1,6).insets(5,  0,  0, 10).left());

		paisLabel = new JLabel("      Pais Lançamento : ");
		paisLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		paisLancamentoField = new JTextField(10);
		paisLancamentoField.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		paisLancamentoField.setForeground(Color.BLUE);
		
		panel2 = new JPanel();
		panel2.add(paisLabel);
		panel2.add(paisLancamentoField);
		getContentPane().add(panel2, new GBC(0, 6).gridwh(2, 1).right().insets(5, 0, 5, 10));

		codigoIdLabel = new JLabel("Código Id : ");
		codigoIdLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(codigoIdLabel, new GBC(0,7).insets(5, 10, 5, 10).left());
		codigoIdField = new JTextField(10);
		codigoIdField.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		codigoIdField.setForeground(Color.BLUE);
		add(codigoIdField, new GBC(1,7).insets(5,  0,  0, 10).left());

		codigoBarraLabel = new JLabel("     Código de Barra : ");
		codigoBarraLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		codigoBarraField = new JTextField(17);
		codigoBarraField.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		codigoBarraField.setForeground(Color.BLUE);
		
		panel3 = new JPanel();
		panel3.add(codigoBarraLabel);
		panel3.add(codigoBarraField);
		getContentPane().add(panel3, new GBC(1, 7).right().insets(5, 60, 5, 10));
		
		capaLabel = new JLabel("Local da Capa : ");
		capaLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(capaLabel, new GBC(0,8).insets(5, 10, 5, 10).left());
		capaField = new JTextField(30);
		capaField.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		capaField.setForeground(Color.BLUE);
		add(capaField, new GBC(1,8).insets(5,  0,  0, 10).left());
		
		codigoCatalogoField.setEditable(false);
		artistaField.setEditable(false);
		tituloField.setEditable(false);
		anoLancamentoField.setEditable(false);
		generoField.setEditable(false);
		estiloField.setEditable(false);
		qtdTotalField.setEditable(false);
		tempoTotalField.setEditable(false);
		formatoField.setEditable(false);
		patrocinadorField.setEditable(false);
		paisLancamentoField.setEditable(false);
		codigoIdField.setEditable(false);
		codigoBarraField.setEditable(false);
		capaField.setEditable(false);
		
		AcervoMusic acervoMusic = ConsultMusicDAO.consult(codigoCatalogo);

			artistaField.setText(acervoMusic.getArtista());
			tituloField.setText(acervoMusic.getTitulo());
			anoLancamentoField.setText(String.valueOf(acervoMusic.getAnoLancamento()));
			generoField.setText(String.valueOf(acervoMusic.getGenero()));
			estiloField.setText(String.valueOf(acervoMusic.getEstilo()));
			qtdTotalField.setText(String.valueOf(acervoMusic.getQtdTotal()));
			tempoTotalField.setText((String.valueOf(acervoMusic.getTempoTotal())));
			formatoField.setText(String.valueOf(acervoMusic.getFormato()));
			patrocinadorField.setText(String.valueOf(acervoMusic.getPatrocinador()));
			paisLancamentoField.setText(String.valueOf(acervoMusic.getPaisLancamento()));
			codigoIdField.setText(acervoMusic.getCodigoId());
			codigoBarraField.setText(acervoMusic.getCodigoDeBarras());
			capaField.setText(acervoMusic.getCapa());
			codigoCatalogoField.setText(acervoMusic.getCodigoCatalogo());
			
			ImageIcon imageIcon = null;
 		 	imageIcon = TrataArquvoCapa.trataArquvoCapa(acervoMusic.getCapa());
			incluiCapaNaTela(imageIcon);
	       
	 //       JLabel jLabel = new JLabel();
	//        jLabel.setIcon(imageIcon);
	//        add(jLabel, new GBC(1,0).insets(10, 280, 0, 10).left());
			
			List<AcervoMusicItem> allAcervoMusicItens = ConsultMusicItemDAO.consult(codigoCatalogo);
			
			if (allAcervoMusicItens != null) {
				
				populaTabelaItem (allAcervoMusicItens);
			}
			
			jLabel.addMouseListener(new MouseAdapter() {
	            @Override
	            //evento para mostra os itens do menu ao passar o mouse por cima
	            public void  mouseEntered (MouseEvent e) {
	                   		MostraCapa.mostraCapa(acervoMusic.getCapa());
	            }
	          		
	      });
		
	}
	
	// Mostrar capa na tela
	protected   void incluiCapaNaTela(ImageIcon imageIcon) {
	
		 	jLabel = new JLabel();
		 	jLabel.setToolTipText("Duplo click para mostrar capa ");
		    jLabel.setIcon(imageIcon);
		    add(jLabel, new GBC(1,0).insets(10, 280, 0, 10).left());
			
		 	return;
	}
	
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public  void populaTabelaItem(List allAcervoMusicItens) {
		
		MaskFormatter numSeq = null, tempoDuracao = null;  
			
		table = new JTable();
		
		try {
			tempoDuracao = new MaskFormatter("##:##");
			numSeq  = new MaskFormatter("##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	       model = new TableMusicItem (allAcervoMusicItens);
	       table.setModel(model);
	       model.ajustaTamanhoColunas(table);
	       table.setEnabled(false);  
	   				
		// mascara celula 
	 	mascaraTable (0, numSeq);
	 	mascaraTable (2, tempoDuracao);
				
		// Centralizar celulas
		 alignCenter(0);
		 alignCenter(2);
			
		panel6 = new JPanel();
		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
		scrollPane.setPreferredSize(new Dimension(600, (20 * 7)));
		
		panel6.add(scrollPane);
		
		getContentPane().add(panel6, new GBC(0, 9).gridwh(3, 1).left().insets(05, 10, 10, 10));
	}
 	
 	MaskFormatter setMascara(String mascara){  
		MaskFormatter mask = null;  
		try{  
			mask = new MaskFormatter(mascara); 

		}catch(java.text.ParseException ex){}  
		return mask;  
	}  

 	private  void alignCenter(int column) {
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	    table.getColumnModel().getColumn(column).setCellRenderer(centerRenderer);
	}
	private  void mascaraTable (int column, MaskFormatter mascara) {
		JFormattedTextField jftf;
		// Jogando a máscara no JFTF  
	    jftf = new JFormattedTextField(mascara);  
	    TableColumn col = table.getColumnModel().getColumn(column); 
	 // edita a mascara  
	    col.setCellEditor(new DefaultCellEditor(jftf));   
	}
  
  
}
