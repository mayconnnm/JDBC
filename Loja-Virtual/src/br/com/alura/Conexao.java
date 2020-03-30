package br.com.alura;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Conexao {

	private static DataSource dataSource;

	public Conexao() throws PropertyVetoException, SQLException {
		// Criando um Pool de Conexões utilizando as libs do C3PO,
		// podendo assim limitar a quantidade de conexões simultâneas
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass("com.mysql.jdbc.Driver");
		cpds.setJdbcUrl("jdbc:mysql://localhost:3306/lojavirtual");
		cpds.setUser("root");
		cpds.setPassword("");
		cpds.setMinPoolSize(1);
		cpds.setAcquireIncrement(5);
		cpds.setMaxPoolSize(20);
		cpds.setMaxStatements(180);
		Conexao.dataSource = cpds;
	}

	public Connection getConnection() throws SQLException {
		Connection connection = dataSource.getConnection();
//		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lojavirtual", "root", "");
		return connection;
	}
}