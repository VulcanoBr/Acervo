package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AcervoBookItem;
import db.ConnectionFactory;

public class ConsultBookItemDAO {
	
	public static void insert(AcervoBookItem acervoBookItem) {
		PreparedStatement statement = null;
		Connection connection = ConnectionFactory.getConnection();
		try {
			statement = connection
					.prepareStatement
					("insert into acervobookitem(tipo, codigocatalogo, numcapitulo, desccapitulo, "
							+ "numpage, qtdpage, formato) values(?, ?, ?, ?, ?, ?, ?)");
			statement.setString(1, acervoBookItem.getTipo());
			statement.setString(2, acervoBookItem.getCodigoCatalogo());
			statement.setInt(3, acervoBookItem.getNumCaptulo());
			statement.setString(4, acervoBookItem.getDescCaptulo());
			statement.setInt(5, acervoBookItem.getNumPage());
			statement.setInt(6, acervoBookItem.getQtdPage());
			statement.setString(7, acervoBookItem.getFormato());
			
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
	
	public static void update(AcervoBookItem acervoBookItem) {
		PreparedStatement statement = null;
		Connection connection = ConnectionFactory.getConnection();
		try {
			statement = connection
					.prepareStatement("update acervobookitem set desccapitulo=?, numpage=?, "
						+ "qtdpage =?, formato=?"
							+ " where tipo = ? and codigocatalogo =? and numcapitulo=?");
			
			statement.setString(1, acervoBookItem.getDescCaptulo());
			statement.setInt(2, acervoBookItem.getNumPage());
			statement.setInt(3, acervoBookItem.getQtdPage());
			statement.setString(4, acervoBookItem.getFormato());
			statement.setString(5, acervoBookItem.getTipo());
			statement.setString(6, acervoBookItem.getCodigoCatalogo());
			statement.setInt(7, acervoBookItem.getNumCaptulo());
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
					.prepareStatement("delete from acervobookitem where tipo = 'Book' and codigocatalogo=?");
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
	
	public static List<AcervoBookItem> consult(String codigoCatalogo) {
		 ResultSet rs = null;
		PreparedStatement stmt = null;
		
		Connection connection = ConnectionFactory.getConnection();
		AcervoBookItem acervoBookItem = null ;
		List<AcervoBookItem> acervoBookItens = new ArrayList<AcervoBookItem>();
		try {			stmt = connection.prepareStatement("select * from acervobookitem where tipo = 'Book' and codigocatalogo= ?"  +  " order by numcapitulo" );
						

			stmt.setString(1, codigoCatalogo);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				String tipo = rs.getString("tipo");	
				String codigCatalogo = rs.getString("codigocatalogo");
				Integer numCapitulo = rs.getInt("numcapitulo");
				String descCapitulo = rs.getString("desccapitulo");
				Integer numPage = rs.getInt("numpage");
				Integer qtdPage = rs.getInt("qtdpage");
				String formato = rs.getString("formato");
																			
				acervoBookItem = new AcervoBookItem(tipo, codigCatalogo, numCapitulo, descCapitulo, 
													numPage, qtdPage, formato);
				acervoBookItens.add(acervoBookItem);				
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
		return acervoBookItens;

	}
	
	public static List<AcervoBookItem> getAllAcervoBookItens(String sql, String parametro) {
		ResultSet rs = null;
		PreparedStatement stmt  = null;
		Connection connection = ConnectionFactory.getConnection();
		// Cria uma lista para armazenar o resultado da consulta 
		List<AcervoBookItem> acervoBookItens = new ArrayList<AcervoBookItem>();
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
				Integer numCapitulo = rs.getInt("numcapitulo");
				String descCapitulo = rs.getString("desccapitulo");
				Integer numPage = rs.getInt("numpage");
				Integer qtdPage = rs.getInt("qtdpage");
				String formato = rs.getString("formato");
																			
				AcervoBookItem acervoBookItem = new AcervoBookItem(tipo, codigCatalogo, 
						           numCapitulo, descCapitulo, numPage, qtdPage, formato);
	
				// Armazena a linha lida em uma lista /
				acervoBookItens.add(acervoBookItem);
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
		return acervoBookItens;

	}
	
}
