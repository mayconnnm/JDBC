package br.com.alura;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {

	public static void main(String[] args) {

		try (Connection connection = Conexao.getConnection(); Statement stm = connection.createStatement();) {

			stm.execute("select * from produto");
			ResultSet result = stm.getResultSet();
			while (result.next()) {
				int id = result.getInt("id");
				String nome = result.getString("nome");
				String descricao = result.getString("descricao");
				System.out.println(id);
				System.out.println(nome);
				System.out.println(descricao);
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}