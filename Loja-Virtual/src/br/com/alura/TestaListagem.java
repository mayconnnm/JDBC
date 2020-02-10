package br.com.alura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {

	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:8080/loja-virtual", "root",
				""); Statement stm = connection.createStatement(); ResultSet result = stm.getResultSet();) {

			stm.execute("select * from produto");
			while (result.next()) {
				int id = result.getInt("id");
				String nome = result.getString("nome");
				String descricao = result.getString("descricao");
				System.out.println(id);
				System.out.println(nome);
				System.out.println(descricao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}