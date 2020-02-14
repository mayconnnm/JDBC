package br.com.alura;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException, PropertyVetoException {

		try (Connection connection = new Conexao().getConnection(); Statement stm = connection.createStatement();) {
			stm.execute("delete from Produto where id = 24");
			int linha = stm.getUpdateCount();
			System.out.println("Linhas atualizadas: " + linha);
		}
	}
}