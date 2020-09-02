package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AcervoMusicItem;
import db.ConnectionFactory;

public class ConsultMusicItemDAO {
	
	public static void insert(AcervoMusicItem acervoMusicItem) {
		PreparedStatement statement = null;
		Connection connection = ConnectionFactory.getConnection();
		try {
			statement = connection
					.prepareStatement
					("insert into acervomusicitem(tipo, codigocatalogo, numseq, tituloItem, tempoduracao,"
						+ "compositor, participante, formato) values(?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setString(1, acervoMusicItem.getTipo());
			statement.setString(2, acervoMusicItem.getCodigoCatalogo());
			statement.setInt(3, acervoMusicItem.getNumSeq());
			statement.setString(4, acervoMusicItem.getTituloItem());
			statement.setString(5, acervoMusicItem.getTempoDuracao());
			statement.setString(6, acervoMusicItem.getCompositor());
			statement.setString(7, acervoMusicItem.getParticipante());
			statement.setString(8, acervoMusicItem.getFormato());
			
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
	
	public static void update(AcervoMusicItem acervoMusicItem) {
		PreparedStatement statement = null;
		Connection connection = ConnectionFactory.getConnection();
		try {
			statement = connection
					.prepareStatement("update acervomusicitem set tituloitem=?, tempoduracao=?, "
						+ "compositor =?, participante=?, formato=?"
							+ " where tipo = ? and codigocatalogo =? and numseq=?");
			
			statement.setString(1, acervoMusicItem.getTituloItem());
			statement.setString(2, acervoMusicItem.getTempoDuracao());
			statement.setString(3, acervoMusicItem.getCompositor());
			statement.setString(4, acervoMusicItem.getParticipante());
			statement.setString(5, acervoMusicItem.getFormato());
			statement.setString(6, acervoMusicItem.getTipo());
			statement.setString(7, acervoMusicItem.getCodigoCatalogo());
			statement.setInt(8, acervoMusicItem.getNumSeq());
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
	
	public static void delete(String codigoCatalogo) {
		PreparedStatement statement = null;
		Connection connection = ConnectionFactory.getConnection();
		try {
			statement = connection
					.prepareStatement("delete from acervomusicitem where tipo = 'Music' and codigocatalogo=?");
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
	
	public static List<AcervoMusicItem> consult(String codigoCatalogo) {
		 ResultSet rs = null;
		PreparedStatement stmt = null;
		
		Connection connection = ConnectionFactory.getConnection();
		AcervoMusicItem acervoMusicItem = null; 
		List<AcervoMusicItem> acervoMusicItens = null;
		acervoMusicItens = new ArrayList<AcervoMusicItem>();
		try {
						
			stmt = connection.prepareStatement("select * from acervomusicitem where tipo = 'Music' and codigocatalogo= ?"  +  " order by numseq" );
			stmt.setString(1, codigoCatalogo);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				String tipo = rs.getString("tipo");	
				String codigCatalogo = rs.getString("codigocatalogo");
				Integer numSeq = rs.getInt("numseq");
				String tituloItem = rs.getString("tituloitem");
				String tempoDuracao = rs.getString("tempoduracao");
				String compositor = rs.getString("compositor");
				String participante = rs.getString("participante");
				String formato = rs.getString("formato");
																			
				acervoMusicItem = new AcervoMusicItem(tipo, codigCatalogo, numSeq, tituloItem, tempoDuracao,
											compositor, participante, formato);
				acervoMusicItens.add(acervoMusicItem);				
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
		return acervoMusicItens;

	}
	
	public static List<AcervoMusicItem> getAllAcervoMusicItens(String sql, String parametro) {
		ResultSet rs = null;
		PreparedStatement stmt  = null;
		Connection connection = ConnectionFactory.getConnection();
		// Cria uma lista para armazenar o resultado da consulta 
		List<AcervoMusicItem> acervoMusicItens = new ArrayList<AcervoMusicItem>();
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
				Integer numSeq = rs.getInt("numseq");
				String tituloItem = rs.getString("tituloitem");
				String tempoDuracao = rs.getString("tempoduracao");
				String compositor = rs.getString("compositor");
				String participante = rs.getString("participante");
				String formato = rs.getString("formato");
				// Cria um objeto para armazenar uma linha da consulta /					
				AcervoMusicItem acervoMusicItem = new AcervoMusicItem(tipo, codigCatalogo, numSeq, 
						tituloItem, tempoDuracao, compositor, participante, formato);
	
				// Armazena a linha lida em uma lista /
				acervoMusicItens.add(acervoMusicItem);
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
		return acervoMusicItens;

	}
	
}
