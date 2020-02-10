package br.com.alura;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {
	public static void main(String[] args) throws SQLException {
		Connection connection = Conexao.getConnection();
		// jdbc:tipo do banco://servidor:porta/nome da base,"login","password"
		// ex: ("jdbc:mysql://localhost:3306/teste", "root", "")

		System.out.println("Abrindo uma conexão com sucesso");
		connection.close();

	}
}