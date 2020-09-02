package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.ConnectionFactory;
import model.Tabelas;

public class TabelasDAO {

	private static String descTabela;
	public static void insert(Tabelas tabelas, String descTabela2) {
		
		descTabela = descTabela2;
		PreparedStatement statement = null;
		Connection connection = ConnectionFactory.getConnection();
		
		try {
			statement = connection
					.prepareStatement
					("insert into " + descTabela + "(codigo, descricao) "
							+ "values(?, ?)");
			
			statement.setInt(1, tabelas.getCodigo());
			statement.setString(2, tabelas.getDescricao());
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
	
	public static void update(Tabelas tabelas, String descTabela2) {
		PreparedStatement statement = null;
		Connection connection = ConnectionFactory.getConnection();
		descTabela = descTabela2;
		
		try {
			statement = connection
					.prepareStatement("update " + descTabela + " set descricao =? "
					+ " where codigo  =? ");
			
			statement.setString(1, tabelas.getDescricao());
			
			statement.setInt(2, tabelas.getCodigo());
			
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
	
	public static void delete(Integer codigo, String descTabela2) {
		PreparedStatement statement = null;
		Connection connection = ConnectionFactory.getConnection();
		descTabela = descTabela2;
		try {
			statement = connection
					.prepareStatement("delete from " + descTabela + " where codigo =?");
			statement.setInt(1, codigo);
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
	
	public static Tabelas consult(int codigo, String descTabela2) {
		 ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection connection = ConnectionFactory.getConnection();
		descTabela = descTabela2;
		Tabelas tabelas = null ;
		try {
						
			stmt = connection.prepareStatement("select * from " + descTabela + "  where codigo =  ?");
			stmt.setInt(1, codigo);
		
			rs = stmt.executeQuery();
						
			if(rs.next()){
					int codig = rs.getInt("codigo");
					String descricao = rs.getString("descricao");
					
				tabelas = new Tabelas(codig, descricao);
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
		return tabelas;

	}
	
	public static List<Tabelas> getAllTabelas(String sql, String parametro) {
		ResultSet rs = null;
		PreparedStatement stmt  = null;	
		Connection connection = ConnectionFactory.getConnection();
		// Cria uma lista para armazenar o resultado da consulta 
		List<Tabelas> tabelas = new ArrayList<Tabelas>();
		try {
			stmt = connection.prepareStatement(sql);
			
			rs = stmt.executeQuery();
		
			while (rs.next()) {
				// Percorre o resultado armazenando os valores em uma lista  
				
				Integer codig = rs.getInt("codigo");
				String descricao = rs.getString("descricao");
				
				
				// Cria um objeto para armazenar uma linha da consulta /					
				Tabelas tabelas2 = new Tabelas(codig, descricao);
				

				// Armazena a linha lida em uma lista /
				tabelas.add(tabelas2);
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
		return tabelas;

	}

	public static List<Tabelas> tabelasComboBox(String sql) {
		ResultSet rs = null;
		PreparedStatement stmt  = null;	
		Connection connection = ConnectionFactory.getConnection();
		// Cria uma lista para armazenar o resultado da consulta 
	 	List<Tabelas> tabelas = new ArrayList<Tabelas>();
		try {
			 stmt = connection.prepareStatement(sql);
			 rs = stmt.executeQuery();
		
			while (rs.next()) {
				// Percorre o resultado armazenando os valores em uma lista  
				
				Integer codigo = rs.getInt("codigo");
				String descricao = rs.getString("descricao");
			
				// Cria um objeto para armazenar uma linha da consulta /					
			 	Tabelas tabelas2 = new Tabelas(codigo, descricao);
			
				// Armazena a linha lida em uma lista /
			 	tabelas.add(tabelas2);
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
	 	return tabelas;
	

	}
		
}
