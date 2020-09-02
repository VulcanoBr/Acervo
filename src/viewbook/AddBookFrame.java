package viewbook;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import controler.AcervoMenuPrincipal;
import view.EscolherFile;
import view.GBC;
import view.ImportAcervoBook;
import view.ImportCapa;
import view.LimetedNoCharacters;
import view.MontaJComboBox;
import view.TrataArquvoCapa;
import viewtable.TableBookItem;

import model.AcervoBook;
import model.AcervoBookItem;
import db.dao.ConsultBookDAO;
import db.dao.ConsultBookItemDAO;


// tela de inclusão de um acervo

@SuppressWarnings("serial")
public class AddBookFrame extends JDialog {
	
	private JLabel codigoCatalogoLabel, tituloLabel, tituloOriginalLabel, 
				   escritorLabel, tradutorLabel, anoLancamentoLabel, anoPrimEdicaoLabel,
				   edicaoLabel, qtdPageLabel, linguagemLabel, generoLabel, categoriaLabel, 
				   formatoLabel, editoraLabel, paisLabel, codigoBarraLabel, codigoCDDLabel,
				   codigoCDULabel, sinopseLabel, seriesLabel, obsLabel, capaLabel;
	 JTextField codigoCatalogoField;
	private static JTextField tradutorField;
	private static JTextField escritorField;
	private static JTextField capaField;
	private static JTextField tituloField;
	private static JTextField tituloOriginalField;
	private static JTextField anoLancamentoField;
	private static JTextField anoPrimEdicaoField;
	private static JTextField codigoBarraField;
	private static JTextField codigoCDDField;
	private static JTextField codigoCDUField;
	private static JTextField edicaoField;
	private static JTextField qtdPageField; 
	private JButton importButton, capaButton, cancelButton, cadButton;
	private static JTextArea sinopseArea;
	private static JTextArea seriesArea;
	private static JTextArea obsArea;
	private static JComboBox<Object> formatoComboBox;
	private static JComboBox<Object> generoComboBox;
	private static JComboBox<Object> categoriaComboBox;
	private static JComboBox<Object> paisLancamentoComboBox;
	private static JComboBox<Object> linguagemComboBox;
	private static JComboBox<Object> editoraComboBox;
	
	 private JPanel panel, panel2, panel3, panel4, panel5, panel6, panel7;
	 protected String button = null;
	 private JTable table;
	 private TableBookItem model;
	 private JScrollPane  scrollPane, scrollPane1, scrollPane2, scrollPane3;
	 private String tab;
		 
	public AddBookFrame (JFrame frame) {
		super(frame);
		buildBookFrame();
		pack();
		setModal(true);
		setResizable(false);
		setTitle("Cadastrar Acervo Livros");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);;
		setVisible(true);
	}
	// Construindo 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected  void buildBookFrame() {
		setLayout(new GridBagLayout());
		
		codigoCatalogoLabel = new JLabel("** Código ISBN :");
		codigoCatalogoLabel.setToolTipText("Codigo ISBN Obrigatorio"); 
		codigoCatalogoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(codigoCatalogoLabel, new GBC(0,0).insets(15, 10, 10, 10).left());
		codigoCatalogoField = new JTextField(18);
		codigoCatalogoField.setToolTipText("Informe o codigo e digite <ENTER>");
		add(codigoCatalogoField, new GBC(1,0).insets(5, 0, 0, 10).left());
		codigoCatalogoField.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed (KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					if ( codigoCatalogoField.getText() == null || codigoCatalogoField.getText().trim().isEmpty())  {
						JOptionPane.showMessageDialog(AddBookFrame.this,
				 				"Codigo ISBN  é obrigatorio.");
				 	} else {
					
				 			String codigoCatalogo = codigoCatalogoField.getText();
					
				 			AcervoBook acervoBook = ConsultBookDAO.consult(codigoCatalogo);
					
				 			if (acervoBook != null)  {
				 				JOptionPane.showMessageDialog(AddBookFrame.this,
						 				" Codigo  " + codigoCatalogo + "  já existe !!!.",
						 				"Cadastrar Acervo ",JOptionPane.INFORMATION_MESSAGE );
				 				codigoCatalogoField.setText("");
				 				codigoCatalogoField.requestFocus();;
				 			} else {
								tituloField.requestFocus();
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
		getContentPane().add(panel5, new GBC(1, 0).left().insets(5, 260, 5, 10));
		
		add(new JSeparator(SwingConstants.HORIZONTAL),
				new GBC(0, 1).gridwh(2, 1).insets(5, 10, 0, 10).horizontal());
				
		tituloLabel = new JLabel("Titulo : ");
		tituloLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		tituloField = new JTextField(28);
	
		tituloOriginalLabel = new JLabel("Titulo Original : ");
		tituloOriginalLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		tituloOriginalField = new JTextField(28);
	
		panel = new JPanel();
		panel.add(tituloLabel);
		panel.add(tituloField);
		panel.add(tituloOriginalLabel);
		panel.add(tituloOriginalField);
		getContentPane().add(panel, new GBC(0, 2).gridwh(2, 1).left().insets( 5, 5, 5, 5));

		escritorLabel = new JLabel("Escritor :");
		escritorLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		escritorField = new JTextField(28);
		
		tradutorLabel = new JLabel("  Tradutor :");
		tradutorLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		tradutorField = new JTextField(28);
	
		panel2 = new JPanel();
		panel2.add(escritorLabel);
		panel2.add(escritorField);
		panel2.add(tradutorLabel);
		panel2.add(tradutorField);
		getContentPane().add(panel2, new GBC(0, 3).gridwh(2, 1).left().insets( 5, 5, 5, 5));
		
		anoLancamentoLabel = new JLabel("Ano Lançamento : ");
		anoLancamentoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		anoLancamentoField = new JTextField(3);
		anoLancamentoField.setDocument(new LimetedNoCharacters(4));
		anoLancamentoField.setToolTipText("Informe o ano no formato YYYY");
		
		anoPrimEdicaoLabel = new JLabel("   Ano Primeira Edição : ");
		anoPrimEdicaoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		anoPrimEdicaoField = new JTextField(3);
		anoPrimEdicaoField.setDocument(new LimetedNoCharacters(4));
		anoPrimEdicaoField.setToolTipText("Informe o ano no formato YYYY");

		edicaoLabel = new JLabel("   Edicao : ");
		edicaoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		edicaoField = new JTextField(2);
		edicaoField.setDocument(new LimetedNoCharacters(2));
		edicaoField.setToolTipText("Informe a edicao fr 01 a 99");
	
		qtdPageLabel = new JLabel("   Qtd. Paginas : ");
		qtdPageLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		qtdPageField = new JTextField(3);
		qtdPageField.setDocument(new LimetedNoCharacters(4));
		qtdPageField.setToolTipText("Informe a qtd, de 0000 a 9999");
	
		panel3 = new JPanel();
		panel3.add(anoLancamentoLabel);
		panel3.add(anoLancamentoField);
		panel3.add(anoPrimEdicaoLabel);
		panel3.add(anoPrimEdicaoField);
		panel3.add(edicaoLabel);
		panel3.add(edicaoField);
		panel3.add(qtdPageLabel);
		panel3.add(qtdPageField);
		getContentPane().add(panel3, new GBC(0, 4).gridwh(4, 1).left().insets( 5, 5, 5, 5));
			
		linguagemLabel = new JLabel("Lingua : ");
		linguagemLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		tab = "linguagem";
		linguagemComboBox = new JComboBox();
		new MontaJComboBox (tab, linguagemComboBox);
				
		generoLabel = new JLabel("   Genero : ");
		generoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		tab = "generobook";
		generoComboBox = new JComboBox();
		new MontaJComboBox (tab, generoComboBox);
				
		categoriaLabel = new JLabel("   Categoria : ");
		categoriaLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		categoriaComboBox = new JComboBox( );
		tab = "categoria";
		new MontaJComboBox (tab, categoriaComboBox);
			
		panel4 = new JPanel();
		panel4.add(linguagemLabel);
		panel4.add(linguagemComboBox);
		panel4.add(generoLabel);
		panel4.add(generoComboBox);
		panel4.add(categoriaLabel);
		panel4.add(categoriaComboBox);
		getContentPane().add(panel4, new GBC(0, 5).gridwh(3, 1).left().insets( 5, 5, 5, 5));
		
		formatoLabel = new JLabel("Formato : ");
		formatoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		formatoComboBox = new JComboBox( );
		tab = "formatobook";
		new MontaJComboBox (tab, formatoComboBox);
		
		editoraLabel = new JLabel("   Editora : ");
		editoraLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(editoraLabel, new GBC(0,6).insets(5, 10, 5, 10).left());
		editoraComboBox = new JComboBox();
		tab = "editora";
		new MontaJComboBox (tab, editoraComboBox);
		
		paisLabel = new JLabel("   Pais Lançamento : ");
		paisLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		paisLancamentoComboBox = new JComboBox();
		tab = "pais";
		new MontaJComboBox (tab, paisLancamentoComboBox);
		
		panel5 = new JPanel();
		panel5.add(formatoLabel);
		panel5.add(formatoComboBox);
		panel5.add(editoraLabel);
		panel5.add(editoraComboBox);
		panel5.add(paisLabel);
		panel5.add(paisLancamentoComboBox);
		getContentPane().add(panel5, new GBC(0, 6).gridwh(3, 1).left().insets( 5, 5, 5, 5));
		
		codigoBarraLabel = new JLabel("Código de Barra : ");
		codigoBarraLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		codigoBarraField = new JTextField(15);

		codigoCDDLabel = new JLabel(" Código CDD : ");
		codigoCDDLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		codigoCDDField = new JTextField(10);
	
		codigoCDULabel = new JLabel(" Código CDU : ");
		codigoCDULabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		codigoCDUField = new JTextField(10);
	
		panel6 = new JPanel();
		panel6.add(codigoBarraLabel);
		panel6.add(codigoBarraField);
		panel6.add(codigoCDDLabel);
		panel6.add(codigoCDDField);
		panel6.add(codigoCDULabel);
		panel6.add(codigoCDUField);
		getContentPane().add(panel6, new GBC(0, 7).gridwh(3, 1).left().insets( 5, 5, 5, 5));
		
		sinopseLabel = new JLabel(" Sinopse : ");
		sinopseLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		sinopseArea = new JTextArea(4,15);
		sinopseArea.setLineWrap(true);
		sinopseArea.setWrapStyleWord(true);
		sinopseArea.setFont(new Font("Serif", Font.ITALIC, 14));
		scrollPane1 = new JScrollPane(sinopseArea); //Adiciona Scroll no TextArea
		scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
		
		seriesLabel = new JLabel(" Series : ");
		seriesLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		seriesArea = new JTextArea(4,15);
		seriesArea.setLineWrap(true);
		seriesArea.setWrapStyleWord(true);
		seriesArea.setFont(new Font("Serif", Font.ITALIC, 14));
		scrollPane2 = new JScrollPane(seriesArea); //Adiciona Scroll no TextArea
		scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
		
		obsLabel = new JLabel(" Observação : ");
		obsLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		obsArea = new JTextArea(4,15);
		obsArea.setLineWrap(true);
		obsArea.setWrapStyleWord(true);
		obsArea.setFont(new Font("Serif", Font.ITALIC, 14));
		scrollPane3 = new JScrollPane(obsArea); //Adiciona Scroll no TextArea
		scrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
		
		panel7 = new JPanel();
	    panel7.add(sinopseLabel);
		panel7.add(scrollPane1);
		panel7.add(seriesLabel);
		panel7.add(scrollPane2);
		panel7.add(obsLabel);
		panel7.add(scrollPane3);
		getContentPane().add(panel7, new GBC(0, 8).gridwh(3, 1).left().insets( 5, 5, 5, 5));
		
		capaLabel = new JLabel(" Local da Capa : ");
		capaLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
	 	add(capaLabel, new GBC(0,9).insets(5, 5, 5, 5).left());
		capaField = new JTextField(27);
		add(capaField, new GBC(1,9).insets(5,  0,  0, 5).left());
		
		ImageIcon capa = new ImageIcon("imagens/importar_capa.png");
		capaButton = new JButton(capa);
		capaButton.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		capaButton.setEnabled(false);
		add(capaButton, new GBC(1,9).insets(5, 350, 5, 10).left());
	
		montaTabela ();
		
		ImageIcon cadastrar = new ImageIcon("imagens/cadastrar.png");
		cadButton = new JButton(cadastrar);
		cadButton.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

		ImageIcon cancelar = new ImageIcon("imagens/cancelar.png");
		cancelButton = new JButton(cancelar);
		cancelButton.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
	
		panel4 = new JPanel();
		panel4.add(cadButton);
		panel4.add(cancelButton);
		getContentPane().add(panel4, new GBC(1,10).right().insets(10, 80, 10, 10));
		
		// trata botoes da tela de inclusão		
		trataBotoes();
			
	}
	
	private void trataBotoes() {
		
		// trata botão Importar Dados
		importButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// define a extemsão do arquivo a ser lido
				String extensao = "Excel";
				String arqFile = null;
				// Escolhe arquivo para importação das informações do Acervo
				arqFile = EscolherFile.escolherFile(arqFile, extensao);
				
				if (arqFile != null) {
					AcervoBook acervoBooK = null ;
					acervoBooK = ImportAcervoBook.importDados(arqFile);
					importDadosTela(acervoBooK);
					List<AcervoBookItem> acervoBookItem = null;
					acervoBookItem = ImportAcervoBook.importDadosItem (arqFile);
					importDadosTabela(acervoBookItem);	
						 	
			 		JOptionPane.showMessageDialog(AddBookFrame.this,
			 				" Importação dos dados realizada com sucesso.",
			 				"Importar Dados",JOptionPane.INFORMATION_MESSAGE );
			 	} else {
			 			JOptionPane.showMessageDialog(AddBookFrame.this,
			 				" Importação dos dados não realizada",
			 				"Importar Dados",JOptionPane.ERROR_MESSAGE );
			 			tituloField.requestFocus();
			 	}
	 					 
	 			return;
			}
		});
		
		// trata botão Importar Capa
		capaButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// define a extemsão do arquivo a ser lido
				String extensao = "Imagem";
				String arqFile = null;
				// Escolhe arquivo para importação da Capa
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
		 		
					JOptionPane.showMessageDialog(AddBookFrame.this,
		 				" Importação da capa realizada com sucesso !!!.",
		 				"Importar Capa",JOptionPane.INFORMATION_MESSAGE );
				} else {
		 			JOptionPane.showMessageDialog(AddBookFrame.this,
		 				" Importação da  capa  não realizada !!!",
		 				"Importar Capa",JOptionPane.ERROR_MESSAGE );
		 			
		 			tituloField.requestFocus();
		 			}
				return;
			}

		});
		
		
		//  trata botão Cadastrar
		cadButton.addActionListener(new ActionListener() {
			
			@Override
			public  void actionPerformed(ActionEvent e) {
				String codigo = codigoCatalogoField.getText(); 
			 	if ( codigo == null || codigo.trim().isEmpty())  {
			 		JOptionPane.showMessageDialog(AddBookFrame.this,
			 				"Codigo ISBN  é obrigatorio.");
			 	} else {
			 			ConsultBookDAO.insert(getAcervoBook());
			 			InsertBookItem();
			 			JOptionPane.showMessageDialog(AddBookFrame.this,
			 					"Acervo cadastrado com sucesso.");
			 			AddBookFrame.this.dispose();
			 			AcervoMenuPrincipal.updateBuildBookMusic();
			 			return;
			 			
			 		  }
				}
			 
		});
		
		// trata botão Cancelar
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			int confirm = JOptionPane.showConfirmDialog(null,  "Deseja Realmente Cancelar "
					+ "a Atualização do Acervo Livro ?? ", "Atualizar Acervo Livro", 
					JOptionPane.YES_NO_OPTION);
			if (confirm == 0) {
				
				   limparTela();
				   
			  } 
			}
		});
		
	}
 	
	
		
	//Preenche dados da tela a partir da importação de arquivo de dados	
	protected static void importDadosTela(AcervoBook acervoBooK) {
				
		tituloField.setText(acervoBooK.getTitulo());
		tituloOriginalField.setText(acervoBooK.getTituloOriginal());
		escritorField.setText(acervoBooK.getArtista());
		tradutorField.setText(acervoBooK.getTradutor());
		anoPrimEdicaoField.setText(String.valueOf(acervoBooK.getAnoPrimeiraEdicao()));
		anoLancamentoField.setText(String.valueOf(acervoBooK.getAnoLancamento()));
		edicaoField.setText(String.valueOf(acervoBooK.getEdicao()));
		qtdPageField.setText(String.valueOf(acervoBooK.getQtdTotal()));
		linguagemComboBox.setSelectedItem(String.valueOf(acervoBooK.getLinguagem()));
		generoComboBox.setSelectedItem(String.valueOf(acervoBooK.getGenero()));
		categoriaComboBox.setSelectedItem(String.valueOf(acervoBooK.getCategoria()));
		formatoComboBox.setSelectedItem(String.valueOf(acervoBooK.getFormato()));
		editoraComboBox.setSelectedItem(String.valueOf(acervoBooK.getPatrocinador()));
		paisLancamentoComboBox.setSelectedItem(String.valueOf(acervoBooK.getPaisLancamento()));
		codigoBarraField.setText(acervoBooK.getCodigoDeBarras());
		codigoCDDField.setText(acervoBooK.getCodigoCDD());
		codigoCDUField.setText(acervoBooK.getCodigoCDU());
		sinopseArea.setText(acervoBooK.getSinopse());
		seriesArea.setText(acervoBooK.getSeries());
		obsArea.setText(acervoBooK.getObservacao());
 		capaField.setText("");
		
	}
	
	// Preenche dados da tabela a partir da importação de arquivo de dados
	protected void importDadosTabela(List<AcervoBookItem> acervoBookItens)  { 
				
		if (acervoBookItens != null) {
		   	 model = new TableBookItem (acervoBookItens);
			 table.setModel(model);
			 alignCenter(table, 0);
			 alignCenter(table, 2);
			 alignCenter(table, 3);
			 
			// Limita a qtd de caracteres e so aceita numros
			mascaraTable(table, 2);
			mascaraTable(table, 3);
				
		 }
	}
	
	// Coloca capa na tela
	protected   void incluiCapaNaTela(ImageIcon imageIcon) {
					
				capaLabel.setIcon(imageIcon);
			    capaLabel.setVisible(true);
				return;
	}
		

	protected AcervoBook getAcervoBook() {
		String codigoCatalogo = codigoCatalogoField.getText();
		String titulo = tituloField.getText();
		String tituloOriginal = tituloOriginalField.getText();
		String artista = escritorField.getText();
		String tradutor = tradutorField.getText();
		Integer anoPrimeiraEdicao = Integer.valueOf(anoPrimEdicaoField.getText());
		Integer anoLancamento = Integer.parseInt( anoLancamentoField.getText());
		Integer edicao = Integer.parseInt((String) edicaoField.getText());
		Integer qtdPage = Integer.parseInt((String) qtdPageField.getText());
		String linguagem = (String) linguagemComboBox.getSelectedItem();
		String genero = (String) generoComboBox.getSelectedItem();
		String categoria = (String) categoriaComboBox.getSelectedItem();
		String formato = (String) formatoComboBox.getSelectedItem();
		String patrocinador = (String) editoraComboBox.getSelectedItem();
		String paisLancamento = (String) paisLancamentoComboBox.getSelectedItem();
		String codigoDeBarras = codigoBarraField.getText();
		String codigoCDD = codigoCDDField.getText();
		String codigoCDU = codigoCDUField.getText();
		String capa = capaField.getText();
		String sinopse = sinopseArea.getText();
		String series = seriesArea.getText();
		String observacao = obsArea.getText();
		
		String tipo = "Book";
		
		Calendar gc= Calendar.getInstance();
		
	  Date dtAtualizacao = gc.getTime();
	 //	LocalDate  dtAtualizacao = new LocalDate.
				
		return  new AcervoBook(tipo, codigoCatalogo, artista,titulo, tituloOriginal, 
				 tradutor, anoPrimeiraEdicao,anoLancamento,  edicao,
				 linguagem, genero, categoria, formato, qtdPage,  
				 patrocinador, paisLancamento, codigoDeBarras, codigoCDD,
				 codigoCDU, capa, sinopse, series, observacao, dtAtualizacao);
		
	}
	
	protected void InsertBookItem() {
		
		for(int i=0; i<table.getRowCount(); i++){  
			int vazia = 0;  
			
			for( int k =0; k < table.getColumnCount(); k++){ 
				
				if ((table.getValueAt(i, k).toString().trim().length()  > 0) &&
					 (table.getValueAt(i, k)!=null)) {
						vazia = +1;
					}
				}
			
			if (vazia > 0) {
				Integer numCapitulo = Integer.parseInt((String) table.getValueAt(i, 0).toString().trim());    
				String descCapitulo  = (String) table.getValueAt(i, 1).toString();
				Integer numPage = Integer.parseInt((String) table.getValueAt(i, 2).toString().trim());
				Integer qtdPage = Integer.parseInt((String) table.getValueAt(i, 3).toString().trim());
				String formato = (String) formatoComboBox.getSelectedItem();
				String codigoCatalogo = codigoCatalogoField.getText();
				String tipo = "Book";
				AcervoBookItem acervoBookItem = new AcervoBookItem (tipo, codigoCatalogo, 
						numCapitulo, descCapitulo, numPage, qtdPage, formato);
				ConsultBookItemDAO.insert(acervoBookItem);
				}
		}
		
	}
	
	// monta 25 linhas em branco e editavel da tabela de itens
		private void montaTabela() {
			
			this.table = new JTable();
			
			table.getTableHeader().setFont( new Font( "Dialog" , Font.BOLD, 14 ));
			table.getTableHeader().setBackground(Color.LIGHT_GRAY);	 
		    
		    model = new TableBookItem ();
	 	   	table.setModel(model);   
	 	    model.addLinha(new AcervoBookItem());
		 
	 	    table.setSelectionBackground(Color.yellow);
			table.getColumnModel().getColumn(0).setPreferredWidth(2);
			table.getColumnModel().getColumn(2).setPreferredWidth(7);
								
			// Centralizar celulas
			alignCenter(table, 0);
			alignCenter(table, 2);
			alignCenter(table, 3);
			
			// Limita a qtd de caracteres e so aceita numros
			mascaraTable(table, 2);
			mascaraTable(table, 3);
			
			panel6 = new JPanel();
			scrollPane = new JScrollPane(table);
			scrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
			//scrollPane.setPreferredSize(new Dimension(600, (count * 7)));
			scrollPane.setPreferredSize(new Dimension(500, 100));
			panel6.add(scrollPane);
		
			getContentPane().add(panel6, new GBC(0,10).gridwh(3, 1).left().insets(05, 10, 10, 10));
		
	}
		
	public void limparTela() { 
		
		codigoCatalogoField.setText("");
		tituloField.setText("");
		tituloOriginalField.setText("");
		escritorField.setText("");
		tradutorField.setText("");
		anoPrimEdicaoField.setText("");
		anoLancamentoField.setText("");
		edicaoField.setText("");
		qtdPageField.setText("");
		linguagemComboBox.setSelectedItem(0);
		generoComboBox.setSelectedItem(0);
		categoriaComboBox.setSelectedItem(0);
		formatoComboBox.setSelectedItem(0);
		editoraComboBox.setSelectedItem(0);
		paisLancamentoComboBox.setSelectedItem(0);
		codigoBarraField.setText("");
		codigoCDDField.setText("");
		codigoCDUField.setText("");
		sinopseArea.setText("");
		seriesArea.setText("");
		obsArea.setText("");
 		capaField.setText("");
 		capaLabel.setIcon(null);
 		
 		importButton.setEnabled(false);
		capaButton.setEnabled(false);
		codigoCatalogoField.requestFocus();
		// limpa tabela music itens
		model.limpar();
		model.addLinha(new AcervoBookItem());
	}   
  
	private void alignCenter(JTable table, int column) {
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	    table.getColumnModel().getColumn(column).setCellRenderer(centerRenderer);
	}

	private void mascaraTable (JTable table, int column) {
	
		JTextField pageNum = new JTextField();
		pageNum.setDocument(new LimetedNoCharacters(4));
	    TableColumn col = table.getColumnModel().getColumn(column); 
	 // Aqui a mágica acontece!  
	    col.setCellEditor(new DefaultCellEditor(pageNum));   
	}
}
