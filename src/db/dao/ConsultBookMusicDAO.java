package db.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AcervoMusic;
import db.ConnectionFactory;

public class ConsultBookMusicDAO {

	public static List<AcervoMusic> getAllAcervoBookMusics(String sql, String parametro) {
		ResultSet rs = null;
		PreparedStatement stmt  = null;	
		Connection connection = ConnectionFactory.getConnection();
		// Cria uma lista para armazenar o resultado da consulta 
		List<AcervoMusic> acervoBookMusics = new ArrayList<AcervoMusic>();
		try {
			if (parametro != null) {
				stmt = connection.prepareStatement(sql);
				stmt.setString(1, parametro);
			} else  {
				 stmt = connection.prepareStatement(sql);
			}
			rs = stmt.executeQuery();
			AcervoMusic acervoBookMusic = null;
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
				acervoBookMusic = new AcervoMusic(tipo, codigCatalogo, artista, titulo, anoLancamento,
						genero, estilo, qtdTotal, tempoTotal, formato,
						patrocinador, paisLancamento, codigoId, mediaId, codigoDeBarras,
						capa, edicao, dataAtualizacao );
				

				// Armazena a linha lida em uma lista /
				acervoBookMusics.add(acervoBookMusic);
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
		return acervoBookMusics;

	}
}
