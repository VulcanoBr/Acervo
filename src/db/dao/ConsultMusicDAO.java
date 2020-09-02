package db.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AcervoMusic;
import db.ConnectionFactory;

public class ConsultMusicDAO {
	
	public static void insert(AcervoMusic acervoMusic) {
		
		PreparedStatement statement = null;
		Connection connection = ConnectionFactory.getConnection();
		try {
			statement = connection
					.prepareStatement
					("insert into acervomusic(tipo, codigocatalogo, artista, titulo, "
						+ "anolancamento,"
						+ "genero, estilo, qtdtotal, tempototal,"
						+ "formato, patrocinador, paislancamento,"
						+ "codigoid, mediaid, codigodebarras, capa, "
						+ "edicao,"
						+ "dataatualizacao) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setString(1, "Music");
			statement.setString(2, acervoMusic.getCodigoCatalogo());
			statement.setString(3, acervoMusic.getArtista());
			statement.setString(4, acervoMusic.getTitulo());
			statement.setInt(5, acervoMusic.getAnoLancamento());
			statement.setString(6, acervoMusic.getGenero());
			statement.setString(7, acervoMusic.getEstilo());
			statement.setInt(8, acervoMusic.getQtdTotal());
			statement.setString(9, acervoMusic.getTempoTotal());
			statement.setString(10, acervoMusic.getFormato());
			statement.setString(11, acervoMusic.getPatrocinador());
			statement.setString(12, acervoMusic.getPaisLancamento());
			statement.setString(13, acervoMusic.getCodigoId());
			statement.setString(14, acervoMusic.getMediaId());
			statement.setString(15, acervoMusic.getCodigoDeBarras());
			statement.setString(16, acervoMusic.getCapa());
			statement.setInt(17, 0);
			statement.setDate(18, new Date(acervoMusic.getDataAtualizacao().getTime()));
			
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void update(AcervoMusic acervoMusic) {
		PreparedStatement statement = null;
		Connection connection = ConnectionFactory.getConnection();
		try {
			statement = connection
					.prepareStatement("update acervomusic set artista=?, titulo=?, "
						+ "anolancamento=?, genero=?, estilo=?, qtdtotal=?, "
						+ "tempototal=?, formato=?, patrocinador=?, "
						+ "paislancamento=?, codigoid=?, mediaid=?, codigodebarras=?, "
						+ "capa=?, edicao=?, dataatualizacao=?"
							+ " where tipo =? and codigocatalogo = ?");
			
			statement.setString(1, acervoMusic.getArtista());
			statement.setString(2, acervoMusic.getTitulo());
			statement.setInt(3, acervoMusic.getAnoLancamento());
			statement.setString(4, acervoMusic.getGenero());
			statement.setString(5, acervoMusic.getEstilo());
			statement.setInt(6, acervoMusic.getQtdTotal());
			statement.setString(7, acervoMusic.getTempoTotal());
			statement.setString(8, acervoMusic.getFormato());
			statement.setString(9, acervoMusic.getPatrocinador());
			statement.setString(10, acervoMusic.getPaisLancamento());
			statement.setString(11, acervoMusic.getCodigoId());
			statement.setString(12, acervoMusic.getMediaId());
			statement.setString(13, acervoMusic.getCodigoDeBarras());
			statement.setString(14, acervoMusic.getCapa());
			statement.setInt(15, acervoMusic.getEdicao());
			
			statement.setDate(16, new Date(acervoMusic.getDataAtualizacao().getTime()));
			statement.setString(17, acervoMusic.getTipo());
			statement.setString(18, acervoMusic.getCodigoCatalogo());
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void delete(String codigoCatalogo) {
		PreparedStatement statement = null;
		Connection connection = ConnectionFactory.getConnection();
		try {
			statement = connection
					.prepareStatement("delete from acervomusic where tipo = 'Music' and codigocatalogo=?");
			statement.setString(1, codigoCatalogo);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static AcervoMusic consult(String codigoCatalogo) {
		 ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection connection = ConnectionFactory.getConnection();
		
		AcervoMusic acervoMusic = null ;
		try {
						
			stmt = connection.prepareStatement("select * from acervomusic where tipo = 'Music' and codigocatalogo = ?");
			stmt.setString(1, codigoCatalogo);
		
			rs = stmt.executeQuery();
						
			if(rs.next()){
					String tipo = rs.getString("tipo");
					String codigCatalogo = rs.getString("codigocatalogo");
					String artista = rs.getString("artista");
					String titulo = rs.getString("titulo");
					Integer anoLancamento = rs.getInt("anolancamento");
					String genero = rs.getString("genero");
					String estilo = rs.getString("estilo");
					Integer qtdTotal = rs.getInt("qtdtotal");
					String tempoTotal = rs.getString("tempototal");
					
					String formato = rs.getString("formato");
					String patrocinador = rs.getString("patrocinador");
					String paisLancamento = rs.getString("paislancamento");
					String codigoId = rs.getString("codigoid");
					String mediaId = rs.getString("mediaid");
					String codigoDeBarras = rs.getString("codigodebarras");
					String capa = rs.getString("capa");
					Integer edicao = rs.getInt("edicao");
					
					Date dataAtualizacao = rs.getDate("dataatualizacao");
										
				acervoMusic = new AcervoMusic(tipo, codigCatalogo, artista, titulo, anoLancamento,
											genero, estilo, qtdTotal, tempoTotal, formato,
											patrocinador, paisLancamento, codigoId, mediaId, codigoDeBarras,
											capa, edicao, dataAtualizacao );
					}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return acervoMusic;

	}
	
	public static List<AcervoMusic> getAllAcervoMusics(String sql, String parametro) {
		ResultSet rs = null;
		PreparedStatement stmt  = null;	
		Connection connection = ConnectionFactory.getConnection();
		// Cria uma lista para armazenar o resultado da consulta 
		List<AcervoMusic> acervoMusics = new ArrayList<AcervoMusic>();
		try {
			if (parametro != null) {
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, parametro);
			} else  {
				 stmt = connection.prepareStatement(sql);
			}
			rs = stmt.executeQuery();
		
			while (rs.next()) {
				// Percorre o resultado armazenando os valores em uma lista  
				String tipo = rs.getString("tipo");
				String codigCatalogo = rs.getString("codigocatalogo");
				String artista = rs.getString("artista");
				String titulo = rs.getString("titulo");
				Integer anoLancamento = rs.getInt("anolancamento");
				String genero = rs.getString("genero");
				String estilo = rs.getString("estilo");
				Integer qtdTotal = rs.getInt("qtdtotal");
				String tempoTotal = rs.getString("tempototal");
				String formato = rs.getString("formato");
				String patrocinador = rs.getString("patrocinador");
				String paisLancamento = rs.getString("paislancamento");
				String codigoId = rs.getString("codigoid");
				String mediaId = rs.getString("mediaid");
				String codigoDeBarras = rs.getString("codigodebarras");
				String capa = rs.getString("capa");
				Integer edicao = rs.getInt("edicao");
								   
				 Date dataAtualizacao = rs.getDate("dataatualizacao");
				// Cria um objeto para armazenar uma linha da consulta /					
				AcervoMusic acervoMusic = new AcervoMusic(tipo, codigCatalogo, artista, titulo, anoLancamento,
						genero, estilo, qtdTotal, tempoTotal, formato,
						patrocinador, paisLancamento, codigoId, mediaId, codigoDeBarras,
						capa, edicao, dataAtualizacao );
				

				// Armazena a linha lida em uma lista /
				acervoMusics.add(acervoMusic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
			 	stmt.close();
				rs.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// Retorna a lista contendo o resultado da consulta 
		return acervoMusics;

	}
	
}
