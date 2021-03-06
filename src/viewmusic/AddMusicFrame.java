package viewmusic;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;

import model.AcervoMusic;
import model.AcervoMusicItem;
import view.EscolherFile;
import view.GBC;
import view.ImportAcervoMusic;
import view.ImportCapa;
import view.MontaJComboBox;
import view.TrataArquvoCapa;
import viewtable.TableMusicItem;
import db.dao.ConsultMusicDAO;
import db.dao.ConsultMusicItemDAO;

// tela de inclus�o de um acervo

@SuppressWarnings("serial")
public class AddMusicFrame extends JDialog {
	
	private JLabel codigoCatalogoLabel, artistaLabel, tituloLabel, anoLancamentoLabel, 
	 				generoLabel, estiloLabel, totalLabel, tempoTotalLabel, formatoLabel, 
	 				gravadoraLabel, paisLabel, codigoIdLabel,codigoBarraLabel, capaLabel;
	 JTextField codigoCatalogoField;
	private static JTextField artistaField;
	private static JTextField tituloField;
	private static JTextField codigoIdField;
	private static JTextField codigoBarraField;
	private JTextField capaField;
	private static JFormattedTextField tempoTotalField;
	protected JButton cadButton;
	private  JButton cancelButton;
	private JButton importButton, capaButton;
	 private static JComboBox<Object> formatoComboBox;
	private static JComboBox<Integer> anoLancamentoComboBox;
	private static JComboBox<Object> generoComboBox;
	private static JComboBox<Object> estiloComboBox;
	private static JComboBox<Integer> qtdTotalComboBox;
	private static JComboBox<Object> paisLancamentoComboBox;
	private static JComboBox<Object> patrocinadorComboBox;
	 private JPanel panel, panel1, panel2, panel3, panel4, panel5, panel6;
	 protected String button = null, extensao = null;
	 private TableMusicItem model;
	 private JTable table;
	 private JScrollPane  scrollPane;
	 public int count = 25;
	 private static String mediaId;
	private static String midiaId = " ";
	private String tab;
	 
	public AddMusicFrame (JFrame frame) {
		super(frame);
		buildMusicFrame();
		pack();
		setModal(true);
		setTitle("Cadastrar Acervo Musica");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setLocationRelativeTo(null);
		setVisible(true);
	}
	// Construindo 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected  void buildMusicFrame() {
		setLayout(new GridBagLayout());
		
		codigoCatalogoLabel = new JLabel("** C�digo de Catalogo :");
		codigoCatalogoLabel.setToolTipText("Codigo de Catalogo Obrigatorio"); 
		codigoCatalogoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(codigoCatalogoLabel, new GBC(0,0).insets(5, 10, 5, 10).left());
		codigoCatalogoField = new JTextField(15);
		codigoCatalogoField.setToolTipText("Informe o codigo e digite <ENTER>");
		
		add(codigoCatalogoField, new GBC(1,0).insets(5, 0, 0, 10).left());
		codigoCatalogoField.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed (KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					if ( codigoCatalogoField.getText() == null || codigoCatalogoField.getText().trim().isEmpty())  {
						JOptionPane.showMessageDialog(AddMusicFrame.this,
				 				"Codigo de Catalogo  � obrigatorio.");
				 	} else {
					
				 			String codigoCatalogo = codigoCatalogoField.getText();
					
				 			AcervoMusic acervoMusic = ConsultMusicDAO.consult(codigoCatalogo);
					
				 			if (acervoMusic != null)  {
				 				JOptionPane.showMessageDialog(AddMusicFrame.this,
						 				" Codigo  " + codigoCatalogo + "  j� existe !!!.",
						 				"Cadastrar Acervo ",JOptionPane.INFORMATION_MESSAGE );
				 				codigoCatalogoField.setText("");
				 				codigoCatalogoField.requestFocus();
				 			} else {
								artistaField.requestFocus();
								importButton.setEnabled(true);
								capaButton.setEnabled(true);
								  
							}
				 	}
				}
			}
		});
		
		ImageIcon importarDados = new ImageIcon("imagens/importar_dados.png");
		importButton = new JButton(importarDados);
		importButton.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		panel5 = new JPanel();
		panel5.add(importButton);
		importButton.setEnabled(false);
		getContentPane().add(panel5, new GBC(1, 0).right().insets(5, 260, 5, 10));
		
		add(new JSeparator(SwingConstants.HORIZONTAL),
				new GBC(0, 1).gridwh(2, 1).insets(5, 10, 0, 10).horizontal());
		
		artistaLabel = new JLabel("Artista :");
		artistaLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(artistaLabel, new GBC(0,2).insets( 5, 10, 5, 10).left());
		artistaField = new JTextField(30);
		add(artistaField, new GBC(1,2).insets(5, 0, 0, 10).left());
		
		tituloLabel = new JLabel("Titulo : ");
		tituloLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(tituloLabel, new GBC(0,3).insets(5, 10, 5, 10).left());
		tituloField = new JTextField(30);
		add(tituloField, new GBC(1,3).insets(5,  0,  0, 10).left());
		
		anoLancamentoLabel = new JLabel("Ano Lan�amento : ");
		anoLancamentoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(anoLancamentoLabel, new GBC(0,4).insets(5, 10, 5, 10).left());
		anoLancamentoComboBox = new JComboBox();
		anoLancamentoComboBox.addItem(0000);
		for (int i = 1969; i < 2031; i++) {
			anoLancamentoComboBox.addItem(i);
		}
		add(anoLancamentoComboBox, new GBC(1,4).insets(5,  0,  0, 10).left());
		
		generoLabel = new JLabel("    Genero : ");
		generoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		tab = "genero";
		generoComboBox = new JComboBox();
		new MontaJComboBox (tab, generoComboBox);
		
		estiloLabel = new JLabel("     Estilo : ");
		estiloLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		estiloComboBox = new JComboBox( );
		tab = "estilo";
		new MontaJComboBox (tab, estiloComboBox);

		panel = new JPanel();
		panel.add(generoLabel);
		panel.add(generoComboBox);
		panel.add(estiloLabel);
		panel.add(estiloComboBox);
		getContentPane().add(panel, new GBC(0, 4).gridwh(2, 1).right().insets( 5, 30, 5, 10));
		
		totalLabel = new JLabel("Total de Musicas : ");
		totalLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(totalLabel, new GBC(0,5).insets(5, 10, 5, 10).left());
		qtdTotalComboBox = new JComboBox();
		for (int i = 0; i < 30; i++) {
			qtdTotalComboBox.addItem(i);
		}
		add(qtdTotalComboBox, new GBC(1,5).insets(5,  0,  0, 0).left());
		
		tempoTotalLabel = new JLabel("      Tempo Total : ");
		tempoTotalLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		tempoTotalField = new JFormattedTextField((setMascara("##:##:##")));
		tempoTotalField.setColumns(05);
				
		formatoLabel = new JLabel("         Formato : ");
		formatoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		formatoComboBox = new JComboBox( );
		tab = "formato";
		new MontaJComboBox (tab, formatoComboBox);
		
		panel1 = new JPanel();
		panel1.add(tempoTotalLabel);
		panel1.add(tempoTotalField);
		panel1.add(formatoLabel);
		panel1.add(formatoComboBox);
		getContentPane().add(panel1, new GBC(0, 5).gridwh(2, 1).right().insets(5, 0, 5, 10));
		
		gravadoraLabel = new JLabel("Gravadora : ");
		gravadoraLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(gravadoraLabel, new GBC(0,6).insets(5, 10, 5, 10).left());
		patrocinadorComboBox = new JComboBox();
		tab = "gravadora";
		new MontaJComboBox (tab, patrocinadorComboBox);
		add(patrocinadorComboBox, new GBC(1,6).insets(5,  0,  0, 10).left());
		
		paisLabel = new JLabel("      Pais Lan�amento : ");
		paisLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		paisLancamentoComboBox = new JComboBox();
		tab = "pais";
		new MontaJComboBox (tab, paisLancamentoComboBox);
		
		panel2 = new JPanel();
		panel2.add(paisLabel);
		panel2.add(paisLancamentoComboBox);
		getContentPane().add(panel2, new GBC(0, 6).gridwh(2, 1).right().insets(5, 0, 5, 10));
				
		codigoIdLabel = new JLabel("C�digo Id : ");
		codigoIdLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(codigoIdLabel, new GBC(0,7).insets(5, 10, 5, 10).left());
		codigoIdField = new JTextField(10);
		add(codigoIdField, new GBC(1,7).insets(5,  0,  0, 10).left());
		
		codigoBarraLabel = new JLabel("     C�digo de Barra : ");
		codigoBarraLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		codigoBarraField = new JTextField(15);
	
		panel3 = new JPanel();
		panel3.add(codigoBarraLabel);
		panel3.add(codigoBarraField);
		getContentPane().add(panel3, new GBC(1, 7).right().insets(5, 60, 5, 10));
		
		capaLabel = new JLabel("Local da Capa : ");
		capaLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(capaLabel, new GBC(0,8).insets(5, 10, 5, 10).left());
		capaField = new JTextField(27);
		add(capaField, new GBC(1,8).insets(5,  0,  0, 10).left());
		
		ImageIcon capa = new ImageIcon("imagens/importar_capa.png");
		capaButton = new JButton(capa);
	//	capaButton = new JButton("Importar Capa ");
	//	capaButton.setForeground(Color.BLUE);
	//	capaButton.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		capaButton.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		capaButton.setEnabled(false);
		add(capaButton, new GBC(1,8).insets(5, 280, 5, 10).right());
			
		montaTabela ();
		
		ImageIcon cadastrar = new ImageIcon("imagens/cadastrar.png");
		cadButton = new JButton(cadastrar);
	//	cadButton = new JButton(" Cadastrar ");
	//	cadButton.setForeground(Color.BLUE);
	//	cadButton.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		cadButton.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		add(cadButton, new GBC(1,10).insets(5, 10, 5, 10).left());
		
		ImageIcon cancelar = new ImageIcon("imagens/cancelar.png");
		cancelButton = new JButton(cancelar);
	//	cancelButton.setIcon(new ImageIcon(getClass().getResource("D:/Workspace/Acervo/imagens/cancelar.png")));
		
		// cancelButton.setForeground(Color.RED);
		cancelButton.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		cancelButton.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		add(cancelButton, new GBC(2,10).insets(5, 10, 5, 10).right());
		
		panel4 = new JPanel();
		panel4.add(cadButton);
		panel4.add(cancelButton);
		getContentPane().add(panel4, new GBC(1, 10).right().insets(5, 60, 5, 10));
		
		// trata botoes da tela de inclus�o		
		trataBotoes();
	}

	private void trataBotoes() {
		
		// trata bot�o Importar Dados
		importButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// define a extems�o do arquivo a ser lido
				String extensao = "Excel";
				String arqFile = null;
				// Escolhe arquivo para importa��o das informa��es do Acervo
				arqFile = EscolherFile.escolherFile(arqFile, extensao);
				
				if (arqFile != null) {
					AcervoMusic acervoBooKMusic = null ;
					acervoBooKMusic = ImportAcervoMusic.importDados(arqFile);
					importDadosTela(acervoBooKMusic);
					List<AcervoMusicItem> acervoBookMusicItem = null;
					acervoBookMusicItem = ImportAcervoMusic.importDadosItem (arqFile);
					importDadosTabela(acervoBookMusicItem);	
						 	
			 		JOptionPane.showMessageDialog(AddMusicFrame.this,
			 				" Importa��o dos dados realizada com sucesso.",
			 				"Importar Dados",JOptionPane.INFORMATION_MESSAGE );
			 	} else {
			 			JOptionPane.showMessageDialog(AddMusicFrame.this,
			 				" Importa��o dos dados n�o realizada",
			 				"Importar Dados",JOptionPane.ERROR_MESSAGE );
			 			artistaField.requestFocus();
			 	}
	 					 
	 			return;
			}
		});
		
		// trata bot�o Importar Capa
		capaButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// define a extems�o do arquivo a ser lido
				String extensao = "Imagem";
				String arqFile = null;
				// Escolhe arquivo para importa��o da Capa
				arqFile = EscolherFile.escolherFile(arqFile, extensao);
				
				if (arqFile != null) {
					arqFile = ImportCapa.impotarCapa(arqFile);
									}
				if (arqFile != null) {
					capaField.setText(arqFile);
					ImageIcon imagemIcom = null;
					imagemIcom = TrataArquvoCapa.trataArquvoCapa(arqFile);
					incluiCapaNaTela(imagemIcom);
					pack();
		 		
					JOptionPane.showMessageDialog(AddMusicFrame.this,
		 				" Importa��o da capa realizada com sucesso !!!.",
		 				"Importar Capa",JOptionPane.INFORMATION_MESSAGE );
				} else {
		 			JOptionPane.showMessageDialog(AddMusicFrame.this,
		 				" Importa��o da  capa  n�o realizada !!!",
		 				"Importar Capa",JOptionPane.ERROR_MESSAGE );
		 			
		 			artistaField.requestFocus();
		 			}
				return;
			}

		});
		
		
		//  trata bot�o Cadastrar
		cadButton.addActionListener(new ActionListener() {
			
			@Override
			public  void actionPerformed(ActionEvent e) {
				String codigo = codigoCatalogoField.getText(); 
			 	if ( codigo == null || codigo.trim().isEmpty())  {
			 		JOptionPane.showMessageDialog(AddMusicFrame.this,
			 				"Codigo de Catalogo  � obrigatorio.");
			 	} else {
			 			ConsultMusicDAO.insert(getAcervoMusic());
			 			InsertMusicItem();
			 			JOptionPane.showMessageDialog(AddMusicFrame.this,
			 					"Acervo cadastrado com sucesso.");
			 			AddMusicFrame.this.dispose();
			 	//		AcervoMenuPrincipal.updateBuildBookMusic();
			 			return;
			 			
			 		  }
				}
			 
		});
		
		// trata bot�o Cancelar
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			int confirm = JOptionPane.showConfirmDialog(null,  "Deseja Realmente Cancelar "
					+ "a Atualiza��o do Acervo Music ?? ", "Atualizar Acervo Music", 
					JOptionPane.YES_NO_OPTION);
			if (confirm == 0) {
				
				   limparTela();
				   
			  } 
			}
		});
		
	}
 	
	
	// Preenche dados da tela a partir da importa��o de arquivo de dados	
	protected static void importDadosTela(AcervoMusic acervoBooKMusic) {
			
		artistaField.setText(acervoBooKMusic.getArtista());
		tituloField.setText(acervoBooKMusic.getTitulo());
		anoLancamentoComboBox.setSelectedItem((acervoBooKMusic.getAnoLancamento()));
		generoComboBox.setSelectedItem(String.valueOf(acervoBooKMusic.getGenero()));
		estiloComboBox.setSelectedItem(String.valueOf(acervoBooKMusic.getEstilo()));
		qtdTotalComboBox.setSelectedItem((acervoBooKMusic.getQtdTotal()));
		tempoTotalField.setText(acervoBooKMusic.getTempoTotal());
		formatoComboBox.setSelectedItem(String.valueOf(acervoBooKMusic.getFormato()));
		patrocinadorComboBox.setSelectedItem(String.valueOf(acervoBooKMusic.getPatrocinador()));
		paisLancamentoComboBox.setSelectedItem(String.valueOf(acervoBooKMusic.getPaisLancamento()));
		midiaId = acervoBooKMusic.getMediaId();
		codigoIdField.setText(acervoBooKMusic.getCodigoId());
		codigoBarraField.setText(acervoBooKMusic.getCodigoDeBarras());
		
	}
	
	// Preenche dados da tabela a partir da importa��o de arquivo de dados
	protected void importDadosTabela(List<AcervoMusicItem> acervoBookMusicItens)  { 
				
		if (acervoBookMusicItens != null) {
		   	 model = new TableMusicItem (acervoBookMusicItens);
			 table.setModel(model);
			 alignCenter(table, 0);
			 alignCenter(table, 2);
		 }
 	}
	
	// Coloca capa na tela
	protected   void incluiCapaNaTela(ImageIcon imageIcon) {
					
				capaLabel.setIcon(imageIcon);
			    capaLabel.setVisible(true);
				return;
	}
		
	// Prepara os dados para inserir na tabela de Acervo
	protected AcervoMusic getAcervoMusic() {
		String codifoCatalogo = codigoCatalogoField.getText();
		String artista = artistaField.getText();
		String titulo = tituloField.getText();
		Integer anoLancamento = (Integer) anoLancamentoComboBox.getSelectedItem();
		String genero = (String) generoComboBox.getSelectedItem();
		String estilo = (String) estiloComboBox.getSelectedItem();
		Integer qtdTotal = (Integer) qtdTotalComboBox.getSelectedItem();
		String tempoTotal = tempoTotalField.getText();
		String formato = (String) formatoComboBox.getSelectedItem();
		String patrocinador = (String) patrocinadorComboBox.getSelectedItem();
		String paisLancamento = (String) paisLancamentoComboBox.getSelectedItem();
		String codigoId = codigoIdField.getText();
		mediaId = midiaId;
		String codigoDeBarras = codigoBarraField.getText();
		String capa = capaField.getText();
		Integer edicao = 00;
		String tipo = "Music";
		
		Date dataAtualizacao = new Date(System.currentTimeMillis());
			
		return new AcervoMusic(tipo, codifoCatalogo, artista, titulo, anoLancamento,
								genero, estilo, qtdTotal, tempoTotal, formato,
								patrocinador, paisLancamento, codigoId, mediaId, codigoDeBarras,
								capa, edicao, dataAtualizacao );
		
	}
	
	// Prepara os dados para inserir dados na tabela de Itens
	protected void InsertMusicItem() {
		
		for(int i=0; i<table.getRowCount(); i++){  
			int vazia = 0;  
			// K = 1 para verificar colunas depois de NumSeq
			for( int k =1; k < table.getColumnCount(); k++){ 
				
				if ((table.getValueAt(i, k).toString().trim().length()  > 0) &&
					 (table.getValueAt(i, k)!=null)) {
						vazia = +1;
					}
			}
			
			if (vazia > 0) {
				Integer numSeq  = Integer.parseInt((String) table.getValueAt(i, 0).toString().trim());    
				String tituloItem  = (String) table.getValueAt(i, 1).toString();
				String tempoDuracao = (String)table.getValueAt(i, 2).toString();
				String compositor =  (String)table.getValueAt(i, 3).toString();;
				String participante =  (String)table.getValueAt(i, 4).toString();;
				String formato = (String) formatoComboBox.getSelectedItem();
				String codigoCatalogo = codigoCatalogoField.getText();
				String tipo = "Music";
				AcervoMusicItem acervoMusicItem = new AcervoMusicItem (tipo, codigoCatalogo, 
						numSeq, tituloItem, tempoDuracao, compositor, participante, formato);
				ConsultMusicItemDAO.insert(acervoMusicItem);
				}
		}
	}
	
	// monta 25 linhas em branco e editavel da tabela de itens
	private void montaTabela() {
		 MaskFormatter numSeq = null, tempoDuracao = null;  
				 
		this.table = new JTable();
		try {
			tempoDuracao = new MaskFormatter("##:##:##");
			numSeq  = new MaskFormatter("##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			
		table.getTableHeader().setFont( new Font( "Dialog" , Font.BOLD, 14 ));
		table.getTableHeader().setBackground(Color.LIGHT_GRAY);	 
	    
	    model = new TableMusicItem ();
 	   	table.setModel(model);   
 	    model.addLinha(new AcervoMusicItem());
	 
 	    table.setSelectionBackground(Color.yellow);
		table.getColumnModel().getColumn(0).setPreferredWidth(2);
		table.getColumnModel().getColumn(2).setPreferredWidth(7);
		
		// mascara celular
		mascaraTable (table, 0, numSeq);
		mascaraTable (table, 2, tempoDuracao);
				
		// Centralizar celulas
		alignCenter(table, 0);
		alignCenter(table, 2);
	
		panel6 = new JPanel();
		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
		scrollPane.setPreferredSize(new Dimension(600, (count * 7)));
		
		panel6.add(scrollPane);
		
		getContentPane().add(panel6, new GBC(0, 9).gridwh(3, 1).left().insets(05, 10, 10, 10));
		
		
	}
		
	public void limparTela() { 
		
		codigoCatalogoField.setText("");
		artistaField.setText("");
		tituloField.setText("");
		anoLancamentoComboBox.setSelectedIndex(0);
		generoComboBox.setSelectedIndex(0);
		estiloComboBox.setSelectedIndex(0);
		qtdTotalComboBox.setSelectedIndex(0);
		tempoTotalField.setText("");
		patrocinadorComboBox.setSelectedIndex(0);
		paisLancamentoComboBox.setSelectedIndex(0);
		formatoComboBox.setSelectedIndex(0);
		codigoIdField.setText("");
		codigoBarraField.setText("");
		capaField.setText("");
		capaLabel.setIcon(null);
		
		importButton.setEnabled(false);
		capaButton.setEnabled(false);
		codigoCatalogoField.requestFocus();
		midiaId = " ";
		// limpa tabela music itens
				
		model.limpar();
		model.addLinha(new AcervoMusicItem());
	}   
  
	private MaskFormatter setMascara(String mascara){  
        MaskFormatter mask = null;  
        try{  
            mask = new MaskFormatter(mascara); 
            
            }catch(java.text.ParseException ex){}  
        return mask;  
    } 
	
	private void alignCenter(JTable table, int column) {
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	    table.getColumnModel().getColumn(column).setCellRenderer(centerRenderer);
	}
	private void mascaraTable (JTable table, int column, MaskFormatter mascara) {
		JFormattedTextField jftf;
		// Jogando a m�scara no JFTF  
	    jftf = new JFormattedTextField(mascara);  
	    TableColumn col = table.getColumnModel().getColumn(column); 
	 // Aqui a m�gica acontece!  
	    col.setCellEditor(new DefaultCellEditor(jftf));   
	}
	
}
