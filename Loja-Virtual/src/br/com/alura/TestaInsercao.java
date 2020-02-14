package br.com.alura;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException, PropertyVetoException {
		try (Connection connection = new Conexao().getConnection(); /* Statement stm = connection.createStatement(); */) {
			// Desabilita o commit automático, permitindo somente comitar no banco de dados
			// se todas as transações efetuarem
			connection.setAutoCommit(false);
			String sql = "insert into Produto (nome, descricao) values (?, ?);";
			try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

				adiciona("TV LCD", "32 Polegadas", ps);
				adiciona("Blueray", "Full HDMI", ps);
				// Se as transações efetuarem commita
				connection.commit();

				// stm.execute("insert into Produto (nome, descricao) values ('Notebook',
				// 'Notebook I5');",
				// Statement.RETURN_GENERATED_KEYS);
				// ResultSet result = stm.getGeneratedKeys();
				// while (result.next()) {
				// int id = result.getInt(1);
				// System.out.println(id);
				// }

				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
				// Volta a atrás de todas as modificações
				connection.rollback();
			}
		}
	}

	private static void adiciona(String nome, String descricao, PreparedStatement ps) throws SQLException {
		ps.setString(1, nome);
		ps.setString(2, descricao);
		ps.execute();
		ResultSet result = ps.getGeneratedKeys();
		while (result.next()) {
			int id = result.getInt(1);
			System.out.println(id);
		}
	}
}