package viewmusic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import view.GBC;
import view.TrataArquvoCapa;
import viewtable.TableMusicItem;
import model.AcervoMusic;
import model.AcervoMusicItem;
import db.dao.ConsultMusicDAO;
import db.dao.ConsultMusicItemDAO;

@SuppressWarnings("serial")
public class DelMusicFrame extends JDialog {
	
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
	private JButton cadButton;
	private  JButton cancelButton;
	private JTextField formatoField, anoLancamentoField, generoField, 
			estiloField, qtdTotalField, paisLancamentoField, patrocinadorField, capaField;
	private JPanel panel, panel1, panel2, panel3, panel4, panel6;
	protected String button = null;
	private JTable table;
	private TableMusicItem model;
	private JScrollPane  scrollPane;
	
	public  DelMusicFrame (JFrame frame) {
		super(frame);	
		buildMusicFrame();
		pack();
		setModal(true);
		setResizable(false);
		setTitle("Remover do Acervo Cd");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(frame);
		setVisible(true);
	}

	protected  void buildMusicFrame() {
		setLayout(new GridBagLayout());

		codigoCatalogoLabel = new JLabel("**  Código de Catalogo :");
		codigoCatalogoLabel.setToolTipText("Codigo de Catalogo Obrigatorio");
		codigoCatalogoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(codigoCatalogoLabel, new GBC(0,0).insets(5, 10, 5, 10).left());
		codigoCatalogoField = new JTextField(20);
		codigoCatalogoField.setToolTipText("Informe o codigo e digite <ENTER>");
		add(codigoCatalogoField, new GBC(1,0).insets(5, 0, 0, 10).left());
		codigoCatalogoField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed (KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					if ( codigoCatalogoField.getText() == null || codigoCatalogoField.getText().trim().isEmpty())  {
						JOptionPane.showMessageDialog(DelMusicFrame.this,
				 				"Codigo de Catalogo  é obrigatorio.");
				 	} else {
					
				 			String codigoCatalogo = (String)codigoCatalogoField.getText().trim();

				 			AcervoMusic acervoBookMusic = ConsultMusicDAO.consult(codigoCatalogo);

				 			if (acervoBookMusic == null)  {
				 				JOptionPane.showMessageDialog(codigoCatalogoField, "Codigo não existente !!!");
				 			} else {
				 				importDadosTela(acervoBookMusic);
				 				ImageIcon imageIcon = null;
					 		 	imageIcon = TrataArquvoCapa.trataArquvoCapa(acervoBookMusic.getCapa());
					 		 	incluiCapaNaTela(imageIcon);
				 			   				 			   
				 				List<AcervoMusicItem> allAcervoMusicItens = ConsultMusicItemDAO.consult(codigoCatalogo);
				 				if (allAcervoMusicItens != null) {
				 					populaTabelaItem (allAcervoMusicItens);
				 				} 
				 				pack();
						}
					}
				}
			}
		});
		
		add(new JSeparator(SwingConstants.HORIZONTAL),
				new GBC(0, 1).gridwh(2, 1).insets(5, 10, 0, 10).horizontal());

		artistaLabel = new JLabel("Artista :");

		artistaLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(artistaLabel, new GBC(0,2).insets(5, 10, 5, 10).left());
		artistaField = new JTextField(40);
		add(artistaField, new GBC(1,2).insets(5, 0, 0, 10).left());

		tituloLabel = new JLabel("Album : ");
		tituloLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(tituloLabel, new GBC(0,3).insets(5, 10, 5, 10).left());
		tituloField = new JTextField(40);
		add(tituloField, new GBC(1,3).insets(5,  0,  0, 10).left());

		anoLancamentoLabel = new JLabel("Ano Lançamento : ");
		anoLancamentoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(anoLancamentoLabel, new GBC(0,4).insets(5, 10, 5, 10).left());
		anoLancamentoField = new JTextField(4);
		add(anoLancamentoField, new GBC(1,4).insets(5,  0,  0, 10).left());

		generoLabel = new JLabel("     Genero : ");
		generoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		
		generoField = new JTextField(8);

		estiloLabel = new JLabel("  Estilo : ");
		estiloLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		
		estiloField = new JTextField(8);
		panel = new JPanel();
		panel.add(generoLabel);
		panel.add(generoField);
		panel.add(estiloLabel);
		panel.add(estiloField);
		getContentPane().add(panel, new GBC(0, 4).gridwh(2, 1).right().insets(5, 70, 5, 10));

		qtdTotalLabel = new JLabel("Total de Musicas : ");
		qtdTotalLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(qtdTotalLabel, new GBC(0,5).insets(5, 10, 5, 10).left());
		
		qtdTotalField = new JTextField(4);;
		add(qtdTotalField, new GBC(1,5).insets(5,  0,  0, 0).left());

		tempoTotalLabel = new JLabel(" Tempo Total : ");
		tempoTotalLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		tempoTotalField = new JFormattedTextField((setMascara("##:##:##")));
		tempoTotalField.setColumns(5);

		formatoLabel = new JLabel("         Formato : ");
		formatoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		
		formatoField = new JTextField(8);;
		panel1 = new JPanel();
		panel1.add(tempoTotalLabel);
		panel1.add(tempoTotalField);
		panel1.add(formatoLabel);
		panel1.add(formatoField);
		getContentPane().add(panel1, new GBC(0, 5).gridwh(2, 1).right().insets(5, 0, 5, 10));

		patrocinadorLabel = new JLabel("Gravadora : ");
		patrocinadorLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(patrocinadorLabel, new GBC(0,6).insets(5, 10, 5, 10).left());
		
		patrocinadorField = new JTextField(8);;
		add(patrocinadorField, new GBC(1,6).insets(5,  0,  0, 10).left());

		paisLabel = new JLabel("      Pais Lançamento : ");
		paisLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		
		paisLancamentoField = new JTextField(8);
		panel2 = new JPanel();
		panel2.add(paisLabel);
		panel2.add(paisLancamentoField);
		getContentPane().add(panel2, new GBC(0, 6).gridwh(2, 1).right().insets(5, 0, 5, 10));

		codigoIdLabel = new JLabel("Código Id : ");
		codigoIdLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(codigoIdLabel, new GBC(0,7).insets(5, 10, 5, 10).left());
		codigoIdField = new JTextField(10);
		add(codigoIdField, new GBC(1,7).insets(5,  0,  0, 10).left());

		codigoBarraLabel = new JLabel("     Código de Barra : ");
		codigoBarraLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		codigoBarraField = new JTextField(15);
		panel3 = new JPanel();
		panel3.add(codigoBarraLabel);
		panel3.add(codigoBarraField);
		getContentPane().add(panel3, new GBC(1, 7).right().insets(5, 60, 5, 10));
		
		capaLabel = new JLabel("Local da Capa : ");
		capaLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(capaLabel, new GBC(0,8).insets(5, 10, 5, 10).left());
		capaField = new JTextField(30);
		add(capaField, new GBC(1,8).insets(5,  0,  0, 10).left());
		
		
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
		
		montaTabela ();

		ImageIcon remover = new ImageIcon("imagens/remover.png");
		cadButton = new JButton(remover);
	//	cadButton = new JButton(" Remover ");

	//	cadButton.setForeground(Color.BLUE);
	//	cadButton.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		cadButton.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		
		add(cadButton, new GBC(1,10).insets(5, 10, 5, 10).left());

		ImageIcon cancelar = new ImageIcon("imagens/cancelar.png");
		cancelButton = new JButton(cancelar);
	//	cancelButton = new JButton("  Cancelar  ");
//		cancelButton.setForeground(Color.RED);
//		cancelButton.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		cancelButton.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		
		add(cancelButton, new GBC(2,10).insets(5, 10, 5, 10).right());

		panel4 = new JPanel();
		panel4.add(cadButton);
		panel4.add(cancelButton);
		
		getContentPane().add(panel4, new GBC(1, 10).right().insets(5, 60, 5, 10));

		trataBotões();
		
	}
	
		private void trataBotões() {
			
			// trata botão Remover
			cadButton.addActionListener(new ActionListener() {

				@Override
				public  void actionPerformed(ActionEvent e) {
					String codigo = codigoCatalogoField.getText(); 
					if ( codigo == null || codigo.trim().isEmpty())  {
				 		 JOptionPane.showMessageDialog(DelMusicFrame.this,
				 				"Codigo de Catalogo  é obrigatorio.");			 		
				 		} else {
				 			
				 			int confirm = JOptionPane.showConfirmDialog(null,  "Deseja Realmente  EXCLUIR  "
									+ "este Acervo  ?? ", "Atualizar Acervo Music", 
									JOptionPane.YES_NO_OPTION);
							if (confirm == 0) {
								ConsultMusicItemDAO.delete(codigoCatalogoField.getText());
								ConsultMusicDAO.delete(codigoCatalogoField.getText());
								JOptionPane.showMessageDialog(DelMusicFrame.this,
				 					"Codigo de Acervo  removido com sucesso.");
								DelMusicFrame.this.dispose();
							}
							return;
						}
					}
			});
			
			// trata botão Cancelar
			cancelButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int confirm = JOptionPane.showConfirmDialog(null,  "Deseja Realmente Cancelar "
							+ "a Atualização do Acervo Music ?? ", "Atualizar Acervo Music", 
							JOptionPane.YES_NO_OPTION);
					if (confirm == 0) {
						limparTela();
					 
						pack();
					} 
				}
			});
		
	}

		// Preenche dados da tela a partir da consulta  de dados	
		 protected void importDadosTela(AcervoMusic acervoBookMusic) {
			 artistaField.setText(acervoBookMusic.getArtista());
				tituloField.setText(acervoBookMusic.getTitulo());
				anoLancamentoField.setText(String.valueOf(acervoBookMusic.getAnoLancamento()));
				generoField.setText(String.valueOf(acervoBookMusic.getGenero()));
				estiloField.setText(String.valueOf(acervoBookMusic.getEstilo()));
				qtdTotalField.setText(String.valueOf(acervoBookMusic.getQtdTotal()));
				tempoTotalField.setText(acervoBookMusic.getTempoTotal());
				formatoField.setText(String.valueOf(acervoBookMusic.getFormato()));
				patrocinadorField.setText(String.valueOf(acervoBookMusic.getPatrocinador()));
				paisLancamentoField.setText(String.valueOf(acervoBookMusic.getPaisLancamento()));
				codigoIdField.setText(acervoBookMusic.getCodigoId());
				codigoBarraField.setText(acervoBookMusic.getCodigoDeBarras());
				capaField.setText(acervoBookMusic.getCapa());  
									
				codigoCatalogoField.setEditable(false);
			
			}   
			
			
		// Mostrar capa na tela
		protected   void incluiCapaNaTela(ImageIcon imageIcon) {
		
			 	jLabel = new JLabel();
			    jLabel.setIcon(imageIcon);
			    add(jLabel, new GBC(1,0).insets(10, 280, 0, 10).left());
				
			 	return;
		}

	private void montaTabela() {
		 
		this.table = new JTable();
				
		model = new TableMusicItem ();
	    table.setModel(model);  
	    table.setEnabled(false);  	    	   		    
		
		table.setSelectionBackground(Color.yellow);
		table.getTableHeader().setFont( new Font( "Dialog" , Font.BOLD, 14 ));
		table.getTableHeader().setBackground(Color.LIGHT_GRAY);	 
		
		table.getColumnModel().getColumn(0).setPreferredWidth(2);
		table.getColumnModel().getColumn(2).setPreferredWidth(5);
				
		panel6 = new JPanel();
		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
		scrollPane.setPreferredSize(new Dimension(600, (20 * 7)));
		
		panel6.add(scrollPane);
		
		getContentPane().add(panel6, new GBC(0, 9).gridwh(3, 1).left().insets(05, 10, 5, 10));
	
	}

	protected void populaTabelaItem(List<AcervoMusicItem> allAcervoMusicItens) {
		
		MaskFormatter numSeq = null, tempoDuracao = null;  
		
		try {
			tempoDuracao = new MaskFormatter("##:##");
			numSeq  = new MaskFormatter("##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
				
		model = new TableMusicItem (allAcervoMusicItens);
	    table.setModel(model);  
	    table.setEnabled(false); 
	     
		// mascara celulas 0 e 2
		mascaraTable (0, numSeq);
		mascaraTable (2, tempoDuracao);
				
		// Centralizar celulas
		alignCenter(0);
		alignCenter(2);
				
	}
	
	public void limparTela() { 

		codigoCatalogoField.setText("");
		artistaField.setText("");
		tituloField.setText("");
		anoLancamentoField.setText("");
		generoField.setText("");
		estiloField.setText("");
		qtdTotalField.setText("");
		tempoTotalField.setText("");
		patrocinadorField.setText("");
		paisLancamentoField.setText("");
		formatoField.setText("");
		codigoIdField.setText("");
		codigoBarraField.setText("");
		capaField.setText("");
	 	
		model.limpar();
	   
		jLabel.setIcon(null); // limpa capa
		
	    codigoCatalogoField.setEditable(true);
	    codigoCatalogoField.requestFocus();
       
	} 
	
	private MaskFormatter setMascara(String mascara){  
		MaskFormatter mask = null;  
		try{  
			mask = new MaskFormatter(mascara); 

		}catch(java.text.ParseException ex){}  
		return mask;  
	}  

	private void alignCenter(int column) {
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	    table.getColumnModel().getColumn(column).setCellRenderer(centerRenderer);
	}
	private void mascaraTable (int column, MaskFormatter mascara) {
		JFormattedTextField jftf;
		// Jogando a máscara no JFTF  
	    jftf = new JFormattedTextField(mascara);  
	    TableColumn col = table.getColumnModel().getColumn(column); 
	 // edita a mascara  
	    col.setCellEditor(new DefaultCellEditor(jftf));   
	}
}
