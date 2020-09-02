package controler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import viewbook.AddBookFrame;
import viewbook.ConsultBookFrame;
import viewbook.DelBookFrame;
import viewbook.ListBookFrame;
import viewbook.ListBookItemFrame;
import viewbook.UpdBookFrame;
import viewmusic.AddMusicFrame;
import viewmusic.ConsultMusicFrame;
import viewmusic.DelMusicFrame;
import viewmusic.ListMusicFrame;
import viewmusic.ListMusicItemFrame;
import viewmusic.UpdMusicFrame;
import viewtabelas.TabelasFrame;
import viewtable.TableMusic;
import model.AcervoBook;
import model.AcervoBookItem;
import model.AcervoMusic;
import model.AcervoMusicItem;
import view.GBC;
import db.dao.ConsultBookDAO;
import db.dao.ConsultBookMusicDAO;
import db.dao.ConsultMusicDAO;
import db.dao.ConsultMusicItemDAO;
import db.dao.ConsultBookItemDAO;

@SuppressWarnings("serial")
public class AcervoMenuPrincipal extends JFrame {
		private String sql = null;
		private static String parametro = null;
		private String descTabela;
		private static JTable table;
		private static TableMusic model;
		private JMenuItem addMusicItem, updMusicItem, delMusicItem,
				artistaMusicItem, tituloMusicItem, musicaMusicItem,
				listMusicItem, addBookItem, updBookItem, delBookItem,
				escritorBookItem, tituloBookItem, capituloBookItem,
				listBookItem, tabEditoraItem, tabEstiloItem, 
				tabFormatoItem, tabGeneroItem, tabGravadoraItem, 
				tabPaisItem, tabCategoriaItem, tabLinguagemItem, 
				tabGeneroBookItem, tabFormatoBookItem;
		
		public AcervoMenuPrincipal () {

			super("Acervo Menu Principal");
			
			setSize(800, 640);
			MenuCollection();
		
			setLocationRelativeTo(null);
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(false);
			setVisible(true);
			
		}

		private void MenuCollection() {
			
			
			setLayout(new GridBagLayout());
			JMenuBar menuBar = new JMenuBar();
			MenuTabela (menuBar);
			MenuMusic (menuBar);
			MenuBook (menuBar);
			setJMenuBar(menuBar);
			
		 	trataOpcoesMenuTabelas();
			trataOpcoesMenuMusic();
			trataOpcoesMenuBook();
			
			buildBookMusic();
			
			
		}
		

		// Montagem Menu Tabelas
		private void MenuTabela(JMenuBar menuBar) {
		//	setLayout(new GridBagLayout());
			JMenu menuTabela = new JMenu("   Tabelas         ");
			menuTabela.setForeground(Color.BLUE);
			menuTabela.setFont(new Font("Lucida Fax", Font.BOLD, 16));
			menuTabela.setOpaque(true);
			menuTabela.setBackground(Color.LIGHT_GRAY);
			menuTabela.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
				
			tabEditoraItem = new JMenuItem("Editora");
			tabEditoraItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			tabEstiloItem = new JMenuItem("Estilo");
			tabEstiloItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			tabFormatoItem = new JMenuItem("Formato");
			tabFormatoItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			tabGeneroItem = new JMenuItem("Genero");
			tabGeneroItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			tabGravadoraItem = new JMenuItem("Gravadora");
			tabGravadoraItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			tabPaisItem = new JMenuItem("Pais");
			tabPaisItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			tabCategoriaItem = new JMenuItem("Categoria");
			tabCategoriaItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			tabLinguagemItem = new JMenuItem("Linguagem");
			tabLinguagemItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			tabGeneroBookItem = new JMenuItem("Genero Book");
			tabGeneroBookItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			tabFormatoBookItem = new JMenuItem("Formato Book");
			tabFormatoBookItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			
			menuTabela.addSeparator();
			menuTabela.add(tabEditoraItem);
			menuTabela.addSeparator();
			menuTabela.add(tabEstiloItem);
			menuTabela.addSeparator();
			menuTabela.add(tabFormatoItem);
			menuTabela.addSeparator();
			menuTabela.add(tabGeneroItem);
			menuTabela.addSeparator();
			menuTabela.add(tabGravadoraItem);
			menuTabela.addSeparator();
			menuTabela.add(tabPaisItem);
			menuTabela.addSeparator();
			menuTabela.add(tabCategoriaItem);
			menuTabela.addSeparator();
			menuTabela.add(tabLinguagemItem);
			menuTabela.addSeparator();
			menuTabela.add(tabGeneroBookItem);
			menuTabela.addSeparator();
			menuTabela.add(tabFormatoBookItem);
			
			
			menuTabela.addSeparator();
			menuBar.add(menuTabela);
			setJMenuBar(menuBar);
			
			menuTabela.addMouseListener(new MouseAdapter() {
	            @Override
	            //evento para mostra os itens do menu ao passar o mouse por cima
	            public void mouseEntered(MouseEvent e) {
	                //torna os itens visíveis
	                menuTabela.setPopupMenuVisible(true);
	                //da um click para ativar o menu
	                menuTabela.doClick(1);
	            }
	        });
			//Evento para que quanto tirado o mouse de cima do jmenu,ele torne os itens do menu não visível
	        MouseListener fechar = new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                menuTabela.setPopupMenuVisible(false);
	            }
	        };
	        getContentPane().addMouseListener(fechar);
		}
		// Montagem Menu Musica
		private void MenuMusic(JMenuBar menuBar) {
		
			JMenu menuMusic = new JMenu("   Musica         ");
			menuMusic.setForeground(Color.BLUE);
			menuMusic.setFont(new Font("Lucida Fax", Font.BOLD, 16));
			menuMusic.setOpaque(true);
			menuMusic.setBackground(Color.LIGHT_GRAY);
			menuMusic.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
					
			addMusicItem = new JMenuItem("Incluir");
		 	addMusicItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			updMusicItem = new JMenuItem("Alterar");
			updMusicItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			delMusicItem = new JMenuItem("Excluir");
	 		delMusicItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			 
			JMenu  searchMusicItem = new JMenu ("Consultar");
			searchMusicItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
						
			menuMusic.addSeparator();
			
			JMenu  pesqMusicItem = new JMenu ("Pesquisar");
			pesqMusicItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			
			artistaMusicItem = new JMenuItem("Por Artista");
			artistaMusicItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			tituloMusicItem = new JMenuItem("Por Titulo");
			tituloMusicItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			musicaMusicItem = new JMenuItem("Por Titulo da Musica");
			musicaMusicItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			listMusicItem = new JMenuItem("Listar");
			listMusicItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
						
			pesqMusicItem.add(artistaMusicItem);
			pesqMusicItem.addSeparator();
			pesqMusicItem.add(tituloMusicItem);
			pesqMusicItem.addSeparator();
			pesqMusicItem.add(musicaMusicItem);
			
			searchMusicItem.add(pesqMusicItem);
			searchMusicItem.addSeparator();
			searchMusicItem.add(listMusicItem); 
			
			menuMusic.add(addMusicItem);
			menuMusic.addSeparator();
			menuMusic.add(updMusicItem);
			menuMusic.addSeparator();
			menuMusic.add(delMusicItem);
			menuMusic.addSeparator();
			menuMusic.add(searchMusicItem);
						
			menuMusic.addSeparator();
			
			menuBar.add(menuMusic);
			setJMenuBar(menuBar);
			 
			menuMusic.addMouseListener(new MouseAdapter() {
	            @Override
	            //evento para mostra os itens do menu ao passar o mouse por cima
	            public void mouseEntered(MouseEvent e) {
	                //torna os itens visíveis
	                menuMusic.setPopupMenuVisible(true);
	                //da um click para ativar o menu
	                menuMusic.doClick(1);
	            }
	        });
			//Evento para que quanto tirado o mouse de cima do jmenu,ele torne os itens do menu não visível
	        MouseListener fechar = new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                menuMusic.setPopupMenuVisible(false);
	            }
	        };
	        getContentPane().addMouseListener(fechar);
		}
		
			// Montagem Menu Livros
		private void MenuBook(JMenuBar menuBar) {
		
			JMenu menuBook = new JMenu("   Livros         ");
			menuBook.setForeground(Color.BLUE);
			menuBook.setFont(new Font("Lucida Fax", Font.BOLD, 16));
			menuBook.setOpaque(true);
			menuBook.setBackground(Color.LIGHT_GRAY);
			menuBook.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
			addBookItem = new JMenuItem("Incluir");
			addBookItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			updBookItem = new JMenuItem("Atualizar");
			updBookItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			delBookItem = new JMenuItem("Excluir");
			delBookItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			JMenu  searchBookItem = new JMenu ("Consultar");
			searchBookItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
						
			menuBook.addSeparator();
			
			JMenu  pesqBookItem = new JMenu ("Pesquisar");
			pesqBookItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			
			escritorBookItem = new JMenuItem("Por Escritor");
			escritorBookItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			tituloBookItem = new JMenuItem("Por Titulo");
			tituloBookItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			capituloBookItem = new JMenuItem("Por Capitulo");
			capituloBookItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
			
			listBookItem = new JMenuItem("Listar");
			listBookItem.setFont(new Font("Lucida Console", Font.BOLD, 16));
						
			pesqBookItem.add(escritorBookItem);
			pesqBookItem.addSeparator();
			pesqBookItem.add(tituloBookItem);
			pesqBookItem.addSeparator();
			pesqBookItem.add(capituloBookItem);
			
			searchBookItem.add(pesqBookItem);
			searchBookItem.addSeparator();
			searchBookItem.add(listBookItem); 
			
			menuBook.add(addBookItem);
			menuBook.addSeparator();
			menuBook.add(updBookItem);
			menuBook.addSeparator();
			menuBook.add(delBookItem);
			menuBook.addSeparator();
			menuBook.add(searchBookItem);
						
			menuBook.addSeparator();
			menuBar.add(menuBook);
			setJMenuBar(menuBar);
						
			menuBook.addMouseListener(new MouseAdapter() {
	            @Override
	            //evento para mostra os itens do menu ao passar o mouse por cima
	            public void mouseEntered(MouseEvent e) {
	                //torna os itens visíveis
	                menuBook.setPopupMenuVisible(true);
	                //da um click para ativar o menu
	                menuBook.doClick(1);
	            }
	        });
			//Evento para que quanto tirado o mouse de cima do jmenu,ele torne os itens do menu não visível
	        MouseListener fechar = new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                menuBook.setPopupMenuVisible(false);
	            }
	        };
	        getContentPane().addMouseListener(fechar);
	       
		}
		
		// Trata das opções do Menu Tabelas
		private void trataOpcoesMenuTabelas() {
			// 	Chama montagem da tela de Atualizacao da tabela de editora		
			tabEditoraItem.addActionListener(new ActionListener() {
						
				@Override
				public void actionPerformed(ActionEvent e) {
					   descTabela = "editora";
					   parametro = "Editora";
					   new TabelasFrame(AcervoMenuPrincipal.this, descTabela, parametro);
					   									    
						}
				});
 			 //	Chama montagem da tela de Atualizacao da tabela de estilo		
			tabEstiloItem.addActionListener(new ActionListener() {
								
				@Override
				public void actionPerformed(ActionEvent e) {
					    descTabela = "estilo";
						parametro = "Estilo";
						new TabelasFrame(AcervoMenuPrincipal.this, descTabela, parametro);
						
				}
		});
				
	 	//	Chama montagem da tela de Atualizacao da tabela de formato		
		tabFormatoItem.addActionListener(new ActionListener() {
									
			@Override
			public void actionPerformed(ActionEvent e) {
				    descTabela = "formato";
					parametro = "Formato";
					new TabelasFrame(AcervoMenuPrincipal.this, descTabela, parametro);
					
					}
			});
	
 			// Chama montagem da tela de Atualizacao da tabela de genero		
			tabGeneroItem.addActionListener(new ActionListener() {
											
					@Override
					public void actionPerformed(ActionEvent e) {
						    descTabela = "genero";
							parametro = "Genero";
							new TabelasFrame(AcervoMenuPrincipal.this, descTabela, parametro);
							
							}
		});
					
		// Chama montagem da tela de Atualizacao da tabela de gravadora		
		tabGravadoraItem.addActionListener(new ActionListener() {
											
		@Override
		public void actionPerformed(ActionEvent e) {
			    descTabela = "gravadora";
				parametro = "Gravadora";
				new TabelasFrame(AcervoMenuPrincipal.this, descTabela, parametro);
				
			}
	});
		
		//	Chama montagem da tela de Atualizacao da tabela de pais		
		tabPaisItem.addActionListener(new ActionListener() {
										
				@Override
				public void actionPerformed(ActionEvent e) {
					    descTabela = "pais";
						parametro = "Pais";
						new TabelasFrame(AcervoMenuPrincipal.this, descTabela, parametro);
						
						}
				});
		
			//	Chama montagem da tela de Atualizacao da tabela de Categoria		
			tabCategoriaItem.addActionListener(new ActionListener() {
											
					@Override
					public void actionPerformed(ActionEvent e) {
						    descTabela = "categoria";
							parametro = "Categoria";
							new TabelasFrame(AcervoMenuPrincipal.this, descTabela, parametro);
							
							}
		});
			
		//	Chama montagem da tela de Atualizacao da tabela de linguagem		
			tabLinguagemItem.addActionListener(new ActionListener() {
											
					@Override
					public void actionPerformed(ActionEvent e) {
						    descTabela = "linguagem";
							parametro = "Linguagem";
							new TabelasFrame(AcervoMenuPrincipal.this, descTabela, parametro);
							
							}
		});
			
		//  Chama montagem da tela de Atualizacao da tabela de Genero Book		
			tabGeneroBookItem.addActionListener(new ActionListener() {
											
					@Override
					public void actionPerformed(ActionEvent e) {
						    descTabela = "generobook";
							parametro = "Genero Book";
							new TabelasFrame(AcervoMenuPrincipal.this, descTabela, parametro);
							
							}
		});
		
		//	Chama montagem da tela de Atualizacao da tabela de formato book	
			tabFormatoBookItem.addActionListener(new ActionListener() {
											
					@Override
					public void actionPerformed(ActionEvent e) {
						    descTabela = "formatobook";
							parametro = "Formato Book";
							new TabelasFrame(AcervoMenuPrincipal.this, descTabela, parametro);
							
							}
					});
			
			
	}

		
		// Trata das opções do Menu Musica
		private void trataOpcoesMenuMusic() {
			
			// 	Chama montagem da tela de inclusao		
			addMusicItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new AddMusicFrame(AcervoMenuPrincipal.this);
					if (true) {
						updateBuildBookMusic();
				    } 
				}
			});
			
			// Chama montahem da tela de atualização
			updMusicItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new UpdMusicFrame(AcervoMenuPrincipal.this);
					if (true) {
						updateBuildBookMusic();
				    } 
				}
			});
			
			// Chama montagem da tela de exclusão
			delMusicItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new DelMusicFrame(AcervoMenuPrincipal.this);
					if (true) {
						// TableBookMusic.remover(getSelectedRow());
						  updateBuildBookMusic();
					    } 
				}
			});
			// Chama montagem da tela de pesquisa por Artista  de Musica
			artistaMusicItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String pergunta = "Qual o nome do artista desejado ?";
					String titulo = "Pesquisa por Artista";
										
					parametro = JOptionPane.showInputDialog(null, pergunta, titulo, JOptionPane.PLAIN_MESSAGE );
					if (parametro == null) {
						 parametro = "";
					} else { if (parametro.length() > 0)   {
							String sql = "select * from acervomusic where tipo = 'Music' and artista =? ";
							List<AcervoMusic> allAcervoMusics = ConsultMusicDAO.getAllAcervoMusics(sql, parametro);
							if (allAcervoMusics == null )  {
								JOptionPane.showMessageDialog(null, "Artista não encontrado", "Pesquisa por Artista", JOptionPane.PLAIN_MESSAGE);
															
							} else {
								new ListMusicFrame(AcervoMenuPrincipal.this, allAcervoMusics);
							}
						} else  { if (parametro.equals("") || (parametro.isEmpty())) {
								  JOptionPane.showMessageDialog(null,					
											"Você não respondeu a pergunta.");
							}
						}
					}
				 }
			});
		// Chama montagem da tela de pesquisa por Tutilo 
		tituloMusicItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String pergunta = "Qual o Titulo desejado ?";
				String titulo = "Pesquisa por Titulo";
									
				parametro = JOptionPane.showInputDialog(null, pergunta, titulo, JOptionPane.PLAIN_MESSAGE );
				if (parametro == null) {
					 parametro = "";
				} else { if (parametro.length() > 0)   {
						String sql = "select * from acervomusic where  tipo = 'Music' and titulo =? ";
						List<AcervoMusic> allAcervoMusics = ConsultMusicDAO.getAllAcervoMusics(sql, parametro);
						if (allAcervoMusics == null )  {
							JOptionPane.showMessageDialog(null, "Titulo não encontrado", "Pesquisa por Titulo", JOptionPane.PLAIN_MESSAGE);
														
						} else {
							new ListMusicFrame(AcervoMenuPrincipal.this, allAcervoMusics);
						}
					} else  { if (parametro.equals("") || (parametro.isEmpty())) {
							  JOptionPane.showMessageDialog(null,					
										"Você não respondeu a pergunta.");
						}
					}
				}
			 }
		});
		// Chama montagem da tela de pesquisa por Tutilo de Musica
		musicaMusicItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String pergunta = "Qual a Musica desejada ?";
				String titulo = "Pesquisa por Titulo da Musica";
									
				parametro = JOptionPane.showInputDialog(null, pergunta, titulo, JOptionPane.PLAIN_MESSAGE );
				if (parametro == null) {
					 parametro = "";
				} else { if (parametro.length() > 0)   {
						String sql = "select * from acervomusicitem where  tipo = 'Music' and tituloitem =? ";
						List<AcervoMusicItem> allAcervoMusicItens = ConsultMusicItemDAO.getAllAcervoMusicItens(sql, parametro);
						if (allAcervoMusicItens == null )  {
							JOptionPane.showMessageDialog(null, "Titulo da Musica  não encontrado", "Pesquisa por Titulo", JOptionPane.PLAIN_MESSAGE);
														
						} else {
							new ListMusicItemFrame(AcervoMenuPrincipal.this, allAcervoMusicItens);
						}
					} else  { if (parametro.equals("") || (parametro.isEmpty())) {
							  JOptionPane.showMessageDialog(null,					
										"Você não respondeu a pergunta.");
						}
					}
				}
			 }
		});
	    //  Monta tabela com lista  do acervo de musicas
		listMusicItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					sql = "select * from acervomusic where tipo = 'Music'";
					List<AcervoMusic> allAcervoMusics = ConsultMusicDAO.getAllAcervoMusics(sql, parametro);
					new ListMusicFrame(AcervoMenuPrincipal.this, allAcervoMusics);
				}
			});
			
		}
		
		// Trata das opçoes de Munu de Book
		private void trataOpcoesMenuBook() {
			
			/**
			 * 	Chama montagem da tela de inclusao		
			 */
			
			addBookItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new AddBookFrame(AcervoMenuPrincipal.this);
					if (true) {
						  buildBookMusic();
					    } 
				}
			});
			
			// Chama montagem da tela de atualização
			updBookItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new UpdBookFrame(AcervoMenuPrincipal.this);
					if (true) {
						  buildBookMusic();
					    } 
				}
			});
			
			// Chama montagem da tela de exclusão
			delBookItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new DelBookFrame(AcervoMenuPrincipal.this);
					if (true) {
						  buildBookMusic();
					    } 
				}
			});
			
			// Chama montagem da tela de pesquisa por Escritor
			escritorBookItem.addActionListener(new ActionListener() {

			@Override
				public void actionPerformed(ActionEvent e) {
				  	String pergunta = "Qual o nome do escritor desejado ?";
					String titulo = "Pesquisa por Escritor";
											
					parametro = JOptionPane.showInputDialog(null, pergunta, titulo, JOptionPane.PLAIN_MESSAGE );
					if (parametro == null) {
						 parametro = "";
					} else { if (parametro.length() > 0)   {
								String sql = "select * from acervobook where tipo = 'Book' and artista =? ";
								List<AcervoBook> allAcervoBooks = ConsultBookDAO.getAllAcervoBooks(sql, parametro);
								if (allAcervoBooks == null )  {
									JOptionPane.showMessageDialog(null, "Escritor não encontrado", "Pesquisa por Escritor", JOptionPane.PLAIN_MESSAGE);
																		
									} else {
											new ListBookFrame(AcervoMenuPrincipal.this, allAcervoBooks);
										}
							} else  { if (parametro.equals("") || (parametro.isEmpty())) {
									     JOptionPane.showMessageDialog(null,					
									  		 "Você não respondeu a pergunta.");
										}
									}
								}
							 }
						});
			// Chama montagem da tela de pesquisa por Tutilo de Livro
			tituloBookItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
						String pergunta = "Qual o Titulo desejado ?";
						String titulo = "Pesquisa por Titulo";
												
						parametro = JOptionPane.showInputDialog(null, pergunta, titulo, JOptionPane.PLAIN_MESSAGE );
						if (parametro == null) {
								 parametro = "";
						} else { if (parametro.length() > 0)   {
						     		String sql = "select * from acervobook where  tipo = 'Book' and titulo =? ";
									List<AcervoBook> allAcervoBooks = ConsultBookDAO.getAllAcervoBooks(sql, parametro);
									if (allAcervoBooks == null )  {
										JOptionPane.showMessageDialog(null, "Titulo não encontrado", "Pesquisa por Titulo", JOptionPane.PLAIN_MESSAGE);
																	
									} else {
										new ListBookFrame(AcervoMenuPrincipal.this, allAcervoBooks);
									}
								} else  { if (parametro.equals("") || (parametro.isEmpty())) {
										  JOptionPane.showMessageDialog(null,					
													"Você não respondeu a pergunta.");
									}
								}
							}
						 }
					});
					
			capituloBookItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
						String pergunta = "Qual o Capitulo desejado ?";
						String titulo = "Pesquisa por Descrição do Capitulo";
												
						parametro = JOptionPane.showInputDialog(null, pergunta, titulo, JOptionPane.PLAIN_MESSAGE );
						if (parametro == null) {
								 parametro = "";
						} else { if (parametro.length() > 0)   {
									String sql = "select * from acervobookitem where  tipo = 'Book' and desccapitulo =? ";
									List<AcervoBookItem> allAcervoBookItens = ConsultBookItemDAO.getAllAcervoBookItens(sql, parametro);
									if (allAcervoBookItens == null )  {
										JOptionPane.showMessageDialog(null, "Capitulo  não encontrado", "Pesquisa por Capitulo", JOptionPane.PLAIN_MESSAGE);
																	
									} else {
										new ListBookItemFrame(AcervoMenuPrincipal.this, allAcervoBookItens);
									}
								} else  { if (parametro.equals("") || (parametro.isEmpty())) {
										  JOptionPane.showMessageDialog(null,					
													"Você não respondeu a pergunta.");
									}
								}
							}
						 }
					});
			// Monta tabela com s lista de Book do acervo
			listBookItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					sql = "select * from acervobook where tipo = 'Book'";
					List<AcervoBook> allAcervoBooks = ConsultBookDAO.getAllAcervoBooks(sql, parametro);
					new ListBookFrame(AcervoMenuPrincipal.this, allAcervoBooks);
				}
			});
			
		}
		
		// Monta Tabela das ultimas 20 atualizações do acervo
		public   void buildBookMusic() {
			
			String sql = "select * from acervomusic order by dataatualizacao desc";
			List<AcervoMusic> acervoBookMusics = ConsultBookMusicDAO.getAllAcervoBookMusics(sql, parametro);
		
			JLabel cabLabel = new JLabel("Relação das 20  ultimas atualizações do acervo");
			cabLabel.setFont(new Font("Lucida Console", Font.BOLD, 15));
			cabLabel.setForeground(Color.BLUE);
			cabLabel.setOpaque(true);
		    cabLabel.setBackground(Color.white);
		    
			add(cabLabel, new GBC(0,0).insets(02, 10, 0, 0).left());
			
			JScrollPane  scrollPane;
			JPanel  panel;
			 table = new JTable();
			table.setModel(new TableMusic (acervoBookMusics));
			TableMusic.ajustaTamanhoColunas (table);
			table.setSelectionBackground(Color.orange);
			
			table.getTableHeader().setFont( new Font( "Dialog" , Font.BOLD, 16 ));
			table.getTableHeader().setBackground(Color.LIGHT_GRAY);	 
		    			
			scrollPane = new JScrollPane(table);
			panel = new JPanel();
			panel.setBorder(new LineBorder(Color.BLUE, 2));
			scrollPane.setPreferredSize(new Dimension(650, 450));
			panel.add(scrollPane);
			
			getContentPane().add(panel, new GBC(0, 1).left().insets(05, 10, 10, 10));
			
			table.addMouseListener(new MouseAdapter() {  
	            @Override  
	            public void mouseClicked(MouseEvent e) {  
	                if(e.getClickCount() == 2) {  
	                    int row = table.getSelectedRow();  
	                     
	                    String codigoCatalogo = String.valueOf(table.getValueAt(row, 2));
	                    
	                    String tipo = String.valueOf(table.getValueAt(row, 1));
	                   
	                    if (tipo.contentEquals("Book"))
	                    	new ConsultBookFrame(AcervoMenuPrincipal.this, codigoCatalogo);
	                    else 
	                    	new ConsultMusicFrame(AcervoMenuPrincipal.this, codigoCatalogo);
	                    	                   	
	                }  
	            } 
	        	            
	        });  
			
		}

		public static   void updateBuildBookMusic() {
				
			String sql = "select * from acervomusic order by dataatualizacao desc";
			List<AcervoMusic> acervoBookMusics = ConsultBookMusicDAO.getAllAcervoBookMusics(sql, parametro);
			model = new TableMusic (acervoBookMusics);
		  	table.setModel(model);
		  	table.repaint(); 
			
		//	table.setModel(new TableMusic (acervoBookMusics));
		 } 
}
