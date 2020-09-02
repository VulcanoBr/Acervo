package viewmusic;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.MaskFormatter;

import view.GBC;

@SuppressWarnings("serial")
public class AddMusicItemFrame extends JDialog {

	// tela de inclusão de itens de um acervo

	private JLabel numSeqLabel, tituloMusicLabel, tempoDuracaoLabel, 
		 				compositorLabel, participanteLabel;
		 
	private JTextField tituloMusicField;
	private JTextField compositorField;
	private JTextField participanteField;
	private JFormattedTextField tempoDuracaoField;
	protected JButton cadButton;
	private  JButton cancelButton;
		
	private JComboBox<Object> numSeqComboBox;
	
	private JPanel panel4;
	protected String button = null;
	public AddMusicItemFrame (JFrame frame) {
			super(frame);
			buildMusicItemFrame();
			pack();
			setModal(true);
			// setResizable(false).;
			setTitle("Cadastrar Musica");
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setLocationRelativeTo(null);;
			setVisible(true);
		}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected  void buildMusicItemFrame() {
		setLayout(new GridBagLayout());
		
		numSeqLabel = new JLabel("Numero de Sequencia : ");
		numSeqLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(numSeqLabel, new GBC(0,0).insets(15, 10, 10, 10).left());
		String [] qtd = {"0","1","2","3","4","5","6","7","8","9","10","11","12","13",
				"14","15","16","17","18","19","20","21","22","23","24","25"};
		numSeqComboBox = new JComboBox(qtd);
		add(numSeqComboBox, new GBC(1,0).insets(10,  0,  0, 0).left());
		
		tituloMusicLabel = new JLabel("Nome Musica :");
		tituloMusicLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(tituloMusicLabel, new GBC(0,1).insets(15, 10, 10, 10).left());
		tituloMusicField = new JTextField(40);
		add(tituloMusicField, new GBC(1,1).insets(10, 0, 0, 10).left());
		
		tempoDuracaoLabel = new JLabel("      Tempo Duração : ");
		tempoDuracaoLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(tempoDuracaoLabel, new GBC(0,2).insets(15, 10, 10, 10).left());
		tempoDuracaoField = new JFormattedTextField((setMascara("##:##:##")));
		tempoDuracaoField.setColumns(05);
		add(tituloMusicField, new GBC(1,2).insets(10, 0, 0, 10).left());
			
		compositorLabel = new JLabel("Compositor : ");
		compositorLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(compositorLabel, new GBC(0,3).insets(15, 10, 10, 10).left());
		compositorField = new JTextField(40);
		add(compositorField, new GBC(1,3).insets(10,  0,  0, 10).left());
		
		participanteLabel = new JLabel("Participante : ");
		participanteLabel.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		add(participanteLabel, new GBC(0,4).insets(15, 10, 10, 10).left());
		participanteField = new JTextField(40);
		add(participanteField, new GBC(1,4).insets(10,  0,  0, 10).left());
		
		// JButton cadButton = new JButton(" Cadastrar ");
		cadButton.setIcon(new ImageIcon(getClass().getResource("/imagens/cancelar.png")));
		cadButton.setForeground(Color.BLUE);
		cadButton.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		cadButton.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		add(cadButton, new GBC(1,5).insets(10, 10, 10, 10).left());
		
		cancelButton = new JButton("  Cancelar  ");
		cancelButton.setForeground(Color.RED);
		cancelButton.setFont(new Font("Lucida Fax", Font.BOLD, 14));
		cancelButton.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		add(cancelButton, new GBC(2,5).insets(10, 10, 10, 10).right());
		
		panel4 = new JPanel();
		panel4.add(cadButton);
		panel4.add(cancelButton);
		getContentPane().add(panel4, new GBC(1, 9).right().insets(10, 60, 10, 10));
	}	
		private MaskFormatter setMascara(String mascara){  
	        MaskFormatter mask = null;  
	        try{  
	            mask = new MaskFormatter(mascara); 
	            
	            }catch(java.text.ParseException ex){}  
	        return mask;  
	    }
		
}
