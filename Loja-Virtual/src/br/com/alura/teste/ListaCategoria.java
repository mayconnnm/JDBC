package br.com.alura.teste;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.alura.Conexao;
import br.com.alura.dao.CategoriaDAO;
import br.com.alura.modelo.Categoria;
import br.com.alura.modelo.Produto;

public class ListaCategoria {
	public static void main(String[] args) throws SQLException, PropertyVetoException {

		try (Connection connection = new Conexao().getConnection()) {

			// Categoria c1 = new Categoria(1);
			CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
			List<Categoria> listaCategoria = categoriaDAO.lista();

			// Versão 8
			listaCategoria.stream().forEach(lc -> System.out.println("Id: " + lc.getId() + " Nome: " + lc.getNome()));

			// Versão 7
			// for (Categoria categoria : listaCategoria) {
			// System.out.println(categoria);
			// }

			List<Categoria> lc2 = categoriaDAO.listaCategoriasComProdutos();
			for (Categoria categoria : lc2) {
				for (Produto produto : categoria.getProdutos()) {
					System.out.println("Categoria: " + categoria + " | " + "Produto: " + produto);
				}
			}

			// lc2.stream().forEach(lc -> System.out.println("Id: " + lc.getId() + " Nome: "
			// + lc.getNome()));

		}
	}
}