package br.com.fitnesfood.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() {
		// TODO Auto-generated method stub
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/fitnessfood", "postgres", "willian19011995");
		} catch (SQLException e) {
			
			//Relançando a Excessao ou exception
			throw  new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw  new RuntimeException(e);
		}
	}

}
