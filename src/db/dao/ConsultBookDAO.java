package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import db.ConnectionFactory;
import model.AcervoBook;

public class ConsultBookDAO {

	public static void insert(AcervoBook acervoBook) {
		PreparedStatement statement = null;
		Connection connection = ConnectionFactory.getConnection();
		try {
			statement = connection
					.prepareStatement
					("insert into acervobook(tipo, codigocatalogo, "
							+ "artista, titulo, titulooriginal, tradutor, "
							+ "anoprimeiraedicao, anolancamento, edicao,"
							+ "linguagem, genero, categoria, formato, "
							+ "qtdtotal, patrocinador, paislancamento, "
							+ "codigodebarras, codigocdd, codigocdu, capa, "
							+ "sinopse, series, observacao, "
							+ "dtatualizacao) "
							+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ? )");
							
			statement.setString(1, "Book");
			statement.setString(2, acervoBook.getCodigoCatalogo());
			statement.setString(3, acervoBook.getArtista());
			statement.setString(4, acervoBook.getTitulo());
			statement.setString(5, acervoBook.getTituloOriginal());
			statement.setString(6, acervoBook.getTradutor());
			statement.setInt(7, acervoBook.getAnoPrimeiraEdicao());
			statement.setInt(8, acervoBook.getAnoLancamento());
			statement.setInt(9, acervoBook.getEdicao());
			statement.setString(10, acervoBook.getLinguagem());
			statement.setString(11, acervoBook.getGenero());
			statement.setString(12, acervoBook.getCategoria());
			statement.setString(13, acervoBook.getFormato());
			statement.setInt(14, acervoBook.getQtdTotal());
			statement.setString(15, acervoBook.getPatrocinador());
			statement.setString(16, acervoBook.getPaisLancamento());
			statement.setString(17, acervoBook.getCodigoDeBarras());
			statement.setString(18, acervoBook.getCodigoCDD() );
			statement.setString(19, acervoBook.getCodigoCDU());
			statement.setString(20, acervoBook.getCapa());
			statement.setString(21, acervoBook.getSinopse());
			statement.setString(22, acervoBook.getSeries());
			statement.setString(23, acervoBook.getObservacao());
			statement.setDate(24, new Date(acervoBook.getDtAtualizacao().getTime()));
			
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
	
	public static void update(AcervoBook acervoBook) {
		PreparedStatement statement = null;
		Connection connection = ConnectionFactory.getConnection();
		try {
			statement = connection
					.prepareStatement("update acervobook set artista=?, titulo=?, "
							+ "titulooriginal=?, tradutor=?, "
							+ "anoprimeiraedicao=?, anolancamento=?, edicao=?,"
							+ "linguagem=?, genero=?, categoria=?, formato=?, "
							+ "qtdtotal=?, patrocinador=?, paislancamento=?, "
							+ "codigodebarras=?, codigocdd=?, codigocdu=?, capa=?, "
							+ "sinopse=?, series=?, observacao=?, "
							+ "dtatualizacao=?"
							+ " where tipo =? and codigocatalogo = ?");
			
			
			statement.setString(1, acervoBook.getArtista());
			statement.setString(2, acervoBook.getTitulo());
			statement.setString(3, acervoBook.getTituloOriginal());
			statement.setString(4, acervoBook.getTradutor());
			statement.setInt(5, acervoBook.getAnoPrimeiraEdicao());
			statement.setInt(6, acervoBook.getAnoLancamento());
			statement.setInt(7, acervoBook.getEdicao());
			statement.setString(8, acervoBook.getLinguagem());
			statement.setString(9, acervoBook.getGenero());
			statement.setString(10, acervoBook.getCategoria());
			statement.setString(11, acervoBook.getFormato());
			statement.setInt(12, acervoBook.getQtdTotal());
			statement.setString(13, acervoBook.getPatrocinador());
			statement.setString(14, acervoBook.getPaisLancamento());
			statement.setString(15, acervoBook.getCodigoDeBarras());
			statement.setString(16, acervoBook.getCodigoCDD() );
			statement.setString(17, acervoBook.getCodigoCDU());
			statement.setString(18, acervoBook.getCapa());
			statement.setString(19, acervoBook.getSinopse());
			statement.setString(20, acervoBook.getSeries());
			statement.setString(21, acervoBook.getObservacao());
			statement.setDate(22, new Date(acervoBook.getDtAtualizacao().getTime()));
						
			statement.setString(23, acervoBook.getTipo());
			statement.setString(24, acervoBook.getCodigoCatalogo());
			
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
					.prepareStatement("delete from acervobook where tipo = 'Book' and codigocatalogo=?");
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
	
	public static AcervoBook consult(String codigoCatalogo) {
		 ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection connection = ConnectionFactory.getConnection();
		
		AcervoBook acervoBook = null ;
		try {
						
			stmt = connection.prepareStatement("select * from acervobook where tipo = 'Book' and codigocatalogo = ?");
			stmt.setString(1, codigoCatalogo);
		
			rs = stmt.executeQuery();
						
			if(rs.next()){
					String tipo = rs.getString("tipo");
					String codigCatalogo = rs.getString("codigocatalogo");
					String artista = rs.getString("artista");
					String titulo = rs.getString("titulo");
					String tituloOriginal = rs.getString("titulooriginal");
					String tradutor = rs.getString("tradutor");
					Integer anoPrimeiraEdicao = rs.getInt("anoprimeiraedicao");
					Integer anoLancamento2 = rs.getInt("anolancamento");
					Integer edicao = rs.getInt("edicao");
					String linguagem = rs.getString("linguagem");
					String genero = rs.getString("genero");
					String categoria = rs.getString("categoria");
					String formato = rs.getString("formato");
					Integer qtdTotal = rs.getInt("qtdtotal");
					String patrocinador = rs.getString("patrocinador");
					String paisLancamento = rs.getString("paislancamento");
					String codigoDeBarras = rs.getString("codigodebarras");
					String codigoCDD = rs.getString("codigocdd");
					String codigoCDU = rs.getString("codigocdu");
					String capa = rs.getString("capa");
					String sinopse = rs.getString("sinopse");
					String series = rs.getString("series");
					String observacao = rs.getString("observacao");
					java.util.Date dtAtualizacao = rs.getDate("dtatualizacao");
										
				acervoBook = new AcervoBook(tipo, codigCatalogo, artista,titulo, tituloOriginal, 
						 tradutor, anoPrimeiraEdicao,anoLancamento2,  edicao,
						 linguagem, genero, categoria, formato, qtdTotal,  
						 patrocinador, paisLancamento, codigoDeBarras, codigoCDD,
						 codigoCDU, capa, sinopse, series, observacao, dtAtualizacao);
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
		return acervoBook;

	}
	
	public static List<AcervoBook> getAllAcervoBooks(String sql, String parametro) {
		ResultSet rs = null;
		PreparedStatement stmt  = null;	
		Connection connection = ConnectionFactory.getConnection();
		// Cria uma lista para armazenar o resultado da consulta 
		List<AcervoBook> acervoBooks = new ArrayList<AcervoBook>();
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
				String tituloOriginal = rs.getString("titulooriginal");
				String tradutor = rs.getString("tradutor");
				Integer anoPrimeiraEdicao = Integer.valueOf(rs.getInt("anoprimeiraedicao"));
				Integer anoLancamento2 = rs.getInt("anolancamento");
				Integer edicao = rs.getInt("edicao");
				String linguagem = rs.getString("linguagem");
				String genero = rs.getString("genero");
				String categoria = rs.getString("categoria");
				String formato = rs.getString("formato");
				Integer qtdTotal = rs.getInt("qtdtotal");
				String patrocinador = rs.getString("patrocinador");
				String paisLancamento = rs.getString("paislancamento");
				String codigoDeBarras = rs.getString("codigodebarras");
				String codigoCDD = rs.getString("codigocdd");
				String codigoCDU = rs.getString("codigocdu");
				String capa = rs.getString("capa");
				String sinopse = rs.getString("sinopse");
				String series = rs.getString("series");
				String observacao = rs.getString("observacao");
				java.util.Date dtAtualizacao = rs.getDate("dtatualizacao");
									
			AcervoBook acervoBook = new AcervoBook(tipo, codigCatalogo, artista,titulo, tituloOriginal, 
					 tradutor, anoPrimeiraEdicao,anoLancamento2,  edicao,
					 linguagem, genero, categoria, formato, qtdTotal,  
					 patrocinador, paisLancamento, codigoDeBarras, codigoCDD,
					 codigoCDU, capa, sinopse, series, observacao, dtAtualizacao);;
		
				// Armazena a linha lida em uma lista /
				acervoBooks.add(acervoBook);
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
		return acervoBooks;

	}
	
}

