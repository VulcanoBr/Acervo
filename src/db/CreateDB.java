package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {

	public static void main(String[] args) {
		Connection connection = null;
		try {
			connection = ConnectionFactory.getConnection();
			createDB(connection);
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Criação finalizada.");
	}

	private static void createDB(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate("drop table if exists acervo");
		statement.executeUpdate("drop table if exists acervoitem");
		statement.executeUpdate("drop table if exists acervomusic");
		statement.executeUpdate("drop table if exists acervomusicitem");
  	    statement.executeUpdate("drop table if exists acervobook");
		statement.executeUpdate("drop table if exists acervobookitem");
		statement.executeUpdate("drop table if exists editora");
		statement.executeUpdate("drop table if exists estilo");
		statement.executeUpdate("drop table if exists formato");
		statement.executeUpdate("drop table if exists genero");
		statement.executeUpdate("drop table if exists gravadora");
		statement.executeUpdate("drop table if exists pais");
		statement.executeUpdate("drop table if exists categoria");
		statement.executeUpdate("drop table if exists linguagem");
		statement.executeUpdate("drop table if exists generobook");
		statement.executeUpdate("drop table if exists formatobook");
		
 		statement
		 		.executeUpdate("create table editora(codigo integer not null, "
		 			+ "descricao string, primary key (codigo))");
		 	
		 	statement.executeUpdate("insert into editora values('9999', 'descricao teste')");
		 	
		statement
	 		.executeUpdate("create table estilo(codigo integer not null, "
	 			+ "descricao string, primary key (codigo))");
	 	
	 	statement.executeUpdate("insert into estilo values('9999', 'descricao teste')");
	 	
	 	statement
 		.executeUpdate("create table formato(codigo integer not null, "
 			+ "descricao string, primary key (codigo))");
 	
 	statement.executeUpdate("insert into formato values('9999', 'descricao teste')");
 
 	statement
		.executeUpdate("create table genero(codigo integer not null, "
			+ "descricao string, primary key (codigo))");
	
	statement.executeUpdate("insert into genero values('9999', 'descricao teste')");

	statement
		.executeUpdate("create table gravadora(codigo integer not null, "
			+ "descricao string, primary key (codigo))");
	
	statement.executeUpdate("insert into gravadora values('9999', 'descricao teste')");

	statement
		.executeUpdate("create table pais(codigo integer not null, "
			+ "descricao string, primary key (codigo))");
	
	statement.executeUpdate("insert into pais values('9999', 'descricao teste')");
	
	statement
		.executeUpdate("create table categoria(codigo integer not null, "
			+ "descricao string, primary key (codigo))");
	
	statement.executeUpdate("insert into categoria values('9999', 'descricao categoria')");
	
	statement
		.executeUpdate("create table linguagem(codigo integer not null, "
			+ "descricao string, primary key (codigo))");
	
	statement.executeUpdate("insert into linguagem values('9999', 'descricao linguagem')");
	
	statement
		.executeUpdate("create table generobook(codigo integer not null, "
			+ "descricao string, primary key (codigo))");
	
	statement.executeUpdate("insert into generobook values('9999', 'descricao gemero')");
	
	statement
		.executeUpdate("create table formatobook(codigo integer not null, "
			+ "descricao string, primary key (codigo))");
	
	statement.executeUpdate("insert into formatobook values('9999', 'descricao teste')");
	
		statement
				.executeUpdate("create table acervomusic(tipo string not null, codigocatalogo string not null, "
						+ "artista string, titulo string, anolancamento integer, "
						+ "genero string, estilo string, qtdtotal integer, tempototal string, "
						+ "formato string, patrocinador string, paislancamento string,"
						+ "codigoid string, mediaid string, codigodebarras string, capa string, "
						+ "edicao integer, "
						+ "dataatualizacao datetime, primary key (tipo, codigocatalogo))");
				
		statement
		.executeUpdate("create table acervomusicitem(tipo string, codigocatalogo string, "
				+ "numseq integer, tituloitem string, tempoduracao string, "
				+ "compositor string, participante string, "
				+ "formato string, primary key (tipo, codigocatalogo, numseq))");
						
		statement
				.executeUpdate("create table acervobook(tipo string not null, codigocatalogo string not null, "
						+ "artista string, titulo string, titulooriginal string, tradutor string, "
						+ "anoprimeiraedicao integer, anolancamento integer, edicao integer,"
						+ "linguagem string, genero string, categoria string, formato string, "
						+ "qtdtotal integer, patrocinador string, paislancamento string, "
						+ "codigodebarras string, codigocdd, codigocdu, capa string, "
						+ "sinopse string, series string, observacao string, "
						+ "dtatualizacao datetime, primary key (tipo, codigocatalogo))");
			
		statement
		.executeUpdate("create table acervobookitem(tipo string, codigocatalogo string, "
				+ "numcapitulo integer, desccapitulo string, numpage integer, "
				+ "qtdpage integer, "
				+ "formato string, primary key (tipo, codigocatalogo, numcapitulo))");
		 					
	}

}
