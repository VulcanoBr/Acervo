package viewtabelas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import db.dao.TabelasDAO;
import model.Tabelas;
import view.GBC;
import view.LimetedNoCharacters;
import viewtable.TableTabelas;

@SuppressWarnings("serial")
public class TabelasFrame extends JDialog{
	
	private JTextField codigoField, descricaoField;
	private JLabel codigoLabel, descricaoLabel;
	private JButton cancelButton;
	private TableTabelas model;
	private String parametro = null;
	private String descTabela = null;
	private int codigo;
	
	public TabelasFrame (JFrame frame, String descTabela, String parametro2) {
		super(frame);
		
		this.descTabela = descTabela;
		parametro = parametro2;
		getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE));
		buildTabFrame();
		pack();
		setModal(true);
		setResizable(false);
		setTitle("Atualizar Tabela " + parametro);
		setFont( new Font( "Lucida"  , Font.BOLD , 12 ) );
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	// construcao da tela
	private void buildTabFrame () {
		String sql = "select * from " + descTabela + " order by codigo";
		List<Tabelas> tabelas = TabelasDAO.getAllTabelas(sql, parametro);
		JTable table = new JTable();
		model = new TableTabelas(tabelas);
		table.setModel(model);
		model.ajustaTamanhoColunas (table);
	
		setLayout(new GridBagLayout());
		((JComponent) getContentPane()).setBorder(
			       new EmptyBorder(10, 10, 10, 10));
		codigoLabel = new JLabel("** Codigo " + parametro + " :");
		codigoLabel.setForeground(Color.BLUE);
		codigoLabel.setToolTipText("Codigo " + parametro + " Obrigatorio");
		codigoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(codigoLabel, new GBC(0,2).insets(15, 10, 10, 10).left());
		codigoField = new JTextField(7);
		codigoField.setDocument(new LimetedNoCharacters(4));
		codigoField.setToolTipText("Informe entre 0001 até 9999");
		Border border = BorderFactory.createLineBorder(Color.RED);
		codigoField.setBorder(border);
		add(codigoField, new GBC(1,2).insets(10, 0, 0, 10).left());
		
		descricaoLabel = new JLabel("Descrição : ");
		descricaoLabel.setForeground(Color.BLUE);
		descricaoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(descricaoLabel, new GBC(0,3).insets(15, 10, 10, 10).left());
		descricaoField = new JTextField(20);
		add(descricaoField, new GBC(1,3).insets(10,  0,  0, 10).left());
		
		// definição dos botões
        JButton cadButton = new JButton(" Cadastrar ");
		cadButton.setForeground(Color.BLUE);
		cadButton.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		cadButton.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		add(cadButton, new GBC(1,9).insets(10, 10, 10, 10).left());
		cadButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (codigoField.getText() == null || codigoField.getText().trim().isEmpty())  {
					JOptionPane.showMessageDialog(null,
		 					"Tem que informar um codigo de " + parametro);
				}  else {
						codigo = Integer.parseInt((String) codigoField.getText());
						Tabelas tabelas = TabelasDAO.consult(codigo, descTabela);
				
			 			if (tabelas != null)  {
			 				JOptionPane.showMessageDialog(null, "Codigo de  " + parametro + " " + codigo + "  já existe !!!");
			 			} else {
							String descricao = descricaoField.getText();
							Tabelas tableTabelas = new Tabelas(codigo, descricao);
							model.adiconar(tableTabelas);
							TabelasDAO.insert(tableTabelas, descTabela);
							JOptionPane.showMessageDialog(null,
										"Tabela " + parametro + " codigo  " + codigo + "  cadastrado com sucesso.");
							
			 				} 
			 			limparCampos();
					}
			}
		});
		
		JButton altButton = new JButton(" Alterar ");
		altButton.setForeground(Color.BLUE);
		altButton.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		altButton.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		add(altButton, new GBC(2,9).insets(10, 10, 10, 10).left());
		altButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (codigoField.getText() == null || codigoField.getText().trim().isEmpty())  {
					JOptionPane.showMessageDialog(null,
		 					"Tem que escolher uma linha de codigo da Tabela " + parametro);
				}  else {
						int i = table.getSelectedRow();
						codigo = Integer.parseInt((String) codigoField.getText());
						Tabelas tabelas = TabelasDAO.consult(codigo, descTabela);
						
			 			if (tabelas == null)  {
			 				JOptionPane.showMessageDialog(codigoField, "Codigo inexistente !!!");
			 			} else {
			 					String descricao = descricaoField.getText();
			 					Tabelas tableTabelas = new Tabelas(codigo, descricao);
			 					model.setValueAt(tableTabelas, i);
			 					TabelasDAO.update(tableTabelas, descTabela);
			 					JOptionPane.showMessageDialog(null,
			 									"Tabela " + parametro + " codigo "  + codigo + " Atualizado com sucesso.");
			 					
			 				} 
			 		  limparCampos();
					}
				}
			});
		
		JButton excButton = new JButton(" Excluir ");
		excButton.setForeground(Color.BLUE);
		excButton.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		excButton.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		add(excButton, new GBC(3,9).insets(10, 10, 10, 10).left());
		excButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			if (codigoField.getText() == null || codigoField.getText().trim().isEmpty())  {
				JOptionPane.showMessageDialog(null,
	 					"Tem que escolher uma linha de codigo da Tabela " + parametro);
			}  else {
					int i = table.getSelectedRow();	
					codigo = Integer.parseInt((String) codigoField.getText());
					Tabelas tabelas = TabelasDAO.consult(codigo, descTabela);
					
		 			if (tabelas == null)  {
		 				JOptionPane.showMessageDialog(null, "Codigo " + parametro + " "+ codigo + " inexistente !!!");
		 			} else {
		 					String descricao = descricaoField.getText();
		 					Tabelas tableTabelas = new Tabelas(codigo, descricao);
							model.remover(tableTabelas,i);
							TabelasDAO.delete(codigo, descTabela);
		 					JOptionPane.showMessageDialog(null,
		 									"Tabela " + parametro + " codigo " + codigo + " excluido com sucesso.");
					
				} 
		 		limparCampos();
			   }
			}
		});
		cancelButton = new JButton("  Cancelar  ");
		cancelButton.setForeground(Color.RED);
		cancelButton.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		cancelButton.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		add(cancelButton, new GBC(4,9).insets(10, 10, 10, 10).right());
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			int confirm = JOptionPane.showConfirmDialog(null,  "Deseja Realmente Cancelar "
					+ "a Atualização da Tabela  ?? ", "Atualizar Tabela" + parametro, 
					JOptionPane.YES_NO_OPTION);
			if (confirm == 0) {
				limparCampos();
				}
			}
		});
		JPanel panel = new JPanel();
		panel.add(cadButton);
		panel.add(altButton);
		panel.add(excButton);
		panel.add(cancelButton);
		getContentPane().add(panel, new GBC(1, 9).left().insets(10, 10, 10, 10));
		
		JScrollPane  scrollPane;
		
		table.setSelectionBackground(Color.orange);
		table.getTableHeader().setFont( new Font( "Dialog" , Font.BOLD, 16 ));
		table.getTableHeader().setBackground(Color.LIGHT_GRAY);	 
	    
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(300, 200));  // largura e altura
		panel = new JPanel();
		panel.setBorder(new LineBorder(Color.BLUE, 2));
		panel.add(scrollPane);
		getContentPane().add(panel, new GBC(1, 4).left().insets(05, 10, 10, 10));
		
		table.addMouseListener(new MouseAdapter() {  
            @Override  
            public void mouseClicked(MouseEvent e) {  
                if(e.getClickCount() == 2) {  
                	Tabelas tabelas = model.getTabelas(table.getSelectedRow());
                	
                	codigoField.setText(Integer.toString (tabelas.getCodigo()));
                	codigoField.setEditable(false);
    				descricaoField.setText(tabelas.getDescricao());
    				Border border = BorderFactory.createLineBorder(Color.BLACK);
    				codigoField.setBorder(border);
    				Border border1 = BorderFactory.createLineBorder(Color.RED);
    				descricaoField.setBorder(border1);
    				descricaoField.requestFocus();          	
                }  
            } 
        	            
        });  
		
	}
	// limpar campos do formulario
	public void limparCampos() {
		codigoField.setText("");
		descricaoField.setText("");
		codigoField.setEditable(true);
		Border border = BorderFactory.createLineBorder(Color.RED);
		codigoField.setBorder(border);
		codigoField.requestFocus();
		Border border1 = BorderFactory.createLineBorder(Color.BLACK);
		descricaoField.setBorder(border1);
		
		
	} 
}
