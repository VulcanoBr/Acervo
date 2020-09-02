package viewbook;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import view.GBC;
import view.LimetedNoCharacters;
import view.TrataArquvoCapa;
import viewtable.TableBookItem;
import model.AcervoBook;
import model.AcervoBookItem;
import db.dao.ConsultBookDAO;
import db.dao.ConsultBookItemDAO;


@SuppressWarnings("serial")
public class ConsultBookFrame extends JDialog {
		
	private JLabel codigoCatalogoLabel, tituloLabel, tituloOriginalLabel, 
	   escritorLabel, tradutorLabel, anoLancamentoLabel, anoPrimEdicaoLabel,
	   edicaoLabel, qtdPageLabel, linguagemLabel, generoLabel, categoriaLabel, 
	   formatoLabel, editoraLabel, paisLabel, codigoBarraLabel, codigoCDDLabel,
	   codigoCDULabel, sinopseLabel, seriesLabel, obsLabel, capaLabel, jLabel;
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
	private static JTextArea sinopseArea;
	private static JTextArea seriesArea;
	private static JTextArea obsArea;
	private static JTextField formatoField;
	private static JTextField generoField;
	private static JTextField categoriaField;
	private static JTextField paisLancamentoField;
	private static JTextField linguagemField;
	private static JTextField editoraField;
	
	private JPanel panel, panel2, panel3, panel4, panel5, panel6, panel7;
	protected String button = null;
	private JTable table;
	private TableBookItem model;
	private JScrollPane  scrollPane1, scrollPane2, scrollPane3;
		
	public   ConsultBookFrame (JFrame frame, String codigoCatalogo) {
	    super(frame);	
		
		buildBookFrame(codigoCatalogo);
		pack();
		setModal(true);
		setResizable(false);
		setTitle("Consulta  Acervo");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(frame);
		setVisible(true);
	}

	protected  void buildBookFrame(String codigoCatalogo) {
		setLayout(new GridBagLayout());

		codigoCatalogoLabel = new JLabel("** Código ISBN :");
		codigoCatalogoLabel.setToolTipText("Codigo ISBN Obrigatorio"); 
		codigoCatalogoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(codigoCatalogoLabel, new GBC(0,0).insets(15, 10, 10, 10).left());
		codigoCatalogoField = new JTextField(18);
		codigoCatalogoField.setToolTipText("Informe o codigo e digite <ENTER>");
		add(codigoCatalogoField, new GBC(1,0).insets(5, 0, 0, 10).left());
			
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
		
		linguagemField = new JTextField(10);
		
				
		generoLabel = new JLabel("   Genero : ");
		generoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		generoField = new JTextField(10);
				
		categoriaLabel = new JLabel("   Categoria : ");
		categoriaLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		categoriaField = new JTextField(10);
			
		panel4 = new JPanel();
		panel4.add(linguagemLabel);
		panel4.add(linguagemField);
		panel4.add(generoLabel);
		panel4.add(generoField);
		panel4.add(categoriaLabel);
		panel4.add(categoriaField);
		getContentPane().add(panel4, new GBC(0, 5).gridwh(3, 1).left().insets( 5, 5, 5, 5));
		
		formatoLabel = new JLabel("Formato : ");
		formatoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		formatoField = new JTextField(10);
		
		editoraLabel = new JLabel("   Editora : ");
		editoraLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		editoraField = new JTextField(10);
		
		paisLabel = new JLabel("   Pais Lançamento : ");
		paisLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		paisLancamentoField = new JTextField(10);
		
		panel5 = new JPanel();
		panel5.add(formatoLabel);
		panel5.add(formatoField);
		panel5.add(editoraLabel);
		panel5.add(editoraField);
		panel5.add(paisLabel);
		panel5.add(paisLancamentoField);
		getContentPane().add(panel5, new GBC(0, 6).gridwh(3, 1).left().insets( 5, 5, 5, 5));
		
		codigoBarraLabel = new JLabel("Código de Barra : ");
		codigoBarraLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		codigoBarraField = new JTextField(15);

		codigoCDDLabel = new JLabel(" Código CDD : ");
		codigoCDDLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		codigoCDDField = new JTextField(10);
	
		codigoCDULabel = new JLabel(" Código CDD : ");
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
		
		AcervoBook acervoBook = ConsultBookDAO.consult(codigoCatalogo);

		tituloField.setText(acervoBook.getTitulo());
		tituloOriginalField.setText(acervoBook.getTituloOriginal());
		escritorField.setText(acervoBook.getArtista());
		tradutorField.setText(acervoBook.getTradutor());
		anoPrimEdicaoField.setText(String.valueOf(acervoBook.getAnoPrimeiraEdicao()));
		anoLancamentoField.setText(String.valueOf(acervoBook.getAnoLancamento()));
		edicaoField.setText(String.valueOf(acervoBook.getEdicao()));
		qtdPageField.setText(String.valueOf(acervoBook.getQtdTotal()));
		linguagemField.setText(String.valueOf(acervoBook.getLinguagem()));
		generoField.setText(String.valueOf(acervoBook.getGenero()));
		categoriaField.setText(String.valueOf(acervoBook.getCategoria()));
		formatoField.setText(String.valueOf(acervoBook.getFormato()));
		editoraField.setText(String.valueOf(acervoBook.getPatrocinador()));
		paisLancamentoField.setText(String.valueOf(acervoBook.getPaisLancamento()));
		codigoBarraField.setText(acervoBook.getCodigoDeBarras());
		codigoCDDField.setText(acervoBook.getCodigoCDD());
		codigoCDUField.setText(acervoBook.getCodigoCDU());
		sinopseArea.setText(acervoBook.getSinopse());
		seriesArea.setText(acervoBook.getSeries());
		obsArea.setText(acervoBook.getObservacao());
 		capaField.setText(acervoBook.getCapa());
	
 		ImageIcon imageIcon = null;
		imageIcon = TrataArquvoCapa.trataArquvoCapa(acervoBook.getCapa());
		incluiCapaNaTela(imageIcon);
		   
		List<AcervoBookItem> allAcervoBookItens = ConsultBookItemDAO.consult(codigoCatalogo);
		if (allAcervoBookItens != null) {
			populaTabelaItem (allAcervoBookItens);
		} 
		
		codigoCatalogoField.setText(acervoBook.getCodigoCatalogo());
		codigoCatalogoField.setEditable(false);
		tituloField.setEditable(false);
		tituloOriginalField.setEditable(false);
		escritorField.setEditable(false);
		tradutorField.setEditable(false);
		anoPrimEdicaoField.setEditable(false);
		anoLancamentoField.setEditable(false);
		edicaoField.setEditable(false);
		qtdPageField.setEditable(false);
		linguagemField.setEditable(false);
		generoField.setEditable(false);
		categoriaField.setEditable(false);
		formatoField.setEditable(false);
		editoraField.setEditable(false);
		paisLancamentoField.setEditable(false);
		codigoBarraField.setEditable(false);
		codigoCDDField.setEditable(false);
		codigoCDUField.setEditable(false);
		sinopseArea.setEditable(false);
		seriesArea.setEditable(false);
		obsArea.setEditable(false);
		capaField.setEditable(false);
		
		
	}
	
	public  void populaTabelaItem(List<AcervoBookItem> acervoBookItens)  { 
		
	  	if (acervoBookItens != null) {
	  		model = new TableBookItem (acervoBookItens);
	  		table.setModel(model);
	  		alignCenter(table, 0);
	  		alignCenter(table, 2);
	  		alignCenter(table, 3);
	  	}
	}
  	//Coloca capa na tela
	protected   void incluiCapaNaTela(ImageIcon imageIcon) {
					
				jLabel = new JLabel();
				jLabel.setIcon(imageIcon);
				add(jLabel, new GBC(1,0).insets(10, 280, 0, 10).left());
		
				return;
	}
 
	private  void alignCenter(JTable table2, int column) {
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	    table.getColumnModel().getColumn(column).setCellRenderer(centerRenderer);
	}
	
}
