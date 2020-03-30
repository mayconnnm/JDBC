package br.com.alura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.modelo.Categoria;
import br.com.alura.modelo.Produto;

public class ProdutoDAO {

	private Connection connection;

	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}

	public void adiciona(Produto produto, Categoria categoria) throws SQLException {
		String sql = "insert into Produto (nome, descricao, categoria_id) values (?, ?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, produto.getNome());
			ps.setString(2, produto.getDescricao());
			ps.setInt(3, categoria.getId());
			ps.execute();
			System.out.println("Adicionado com Sucesso!");
		}
	}

	public void remove(Produto produto) throws SQLException {
		String sql = "delete from produto where id = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, produto.getId());
			ps.execute();
			System.out.println("Deletado com Sucesso!");
		}
	}

	public void atualiza(Produto produto) throws SQLException {
		String sql = "update from produto set id = ?, nome = ?, descricao = ? where id = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, produto.getId());
			ps.setString(2, produto.getNome());
			ps.setString(3, produto.getDescricao());
			ps.execute();
			System.out.println("Atualizado com Sucesso!");
		}
	}

	public List<Produto> lista() throws SQLException {
		List<Produto> listaProdutos = new ArrayList<>();
		String sql = "select * from Produto";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.execute();
			ResultSet result = ps.getResultSet();
			while (result.next()) {
				Produto produto = new Produto(result.getInt("id"), result.getString("nome"),
						result.getString("descricao"));
				listaProdutos.add(produto);
			}
		}
		return listaProdutos;
	}

	public List<Produto> buscaPorId(Produto produto) throws SQLException {
		List<Produto> listaProduto = new ArrayList<>();
		String sql = "select * from produto where id = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, produto.getId());
			ps.execute();
			try (ResultSet result = ps.getResultSet()) {
				while (result.next()) {
					Produto produtoNovo = new Produto(result.getInt("id"), result.getString("nome"),
							result.getString("descricao"));
					listaProduto.add(produtoNovo);
				}
			}
		}
		return listaProduto;
	}

	public List<Produto> buscaPorCategoria(Categoria categoria) throws SQLException {
		List<Produto> listaProduto = new ArrayList<>();
		String sql = "select * from produto where categoria_id = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, categoria.getId());
			ps.execute();
			try (ResultSet result = ps.getResultSet()) {
				while (result.next()) {
					Produto produto = new Produto(result.getInt("id"), result.getString("nome"),
							result.getString("descricao"));
					listaProduto.add(produto);
				}
			}
		}
		return listaProduto;
	}
}