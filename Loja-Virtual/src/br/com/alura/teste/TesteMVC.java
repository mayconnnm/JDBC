package br.com.alura.teste;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.alura.Conexao;
import br.com.alura.dao.ProdutoDAO;
import br.com.alura.modelo.Categoria;
import br.com.alura.modelo.Produto;

public class TesteMVC {

	public static void main(String[] args) throws SQLException, PropertyVetoException {

		Produto produto = new Produto("Celular", "Samsung Galaxy");

		try (Connection connection = new Conexao().getConnection()) {
			ProdutoDAO dao = new ProdutoDAO(connection);
			Categoria categoria = new Categoria(1);
			dao.adiciona(produto, categoria);
			List<Produto> lista = dao.lista();
			lista.stream().forEach(lp -> System.out.println(lp));
//			for (Produto pl : lista) {
//				System.out.println(pl);
//			}
		}
	}
}