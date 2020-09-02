package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection getConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection("jdbc:sqlite:acervo.db",
					"root", "");
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		Connection connection = ConnectionFactory.getConnection();
		System.out.println("Conexão criada com sucesso.");
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
