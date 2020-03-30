package br.com.alura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.modelo.Categoria;
import br.com.alura.modelo.Produto;

public class CategoriaDAO {

	private Connection connection;

	public CategoriaDAO(Connection connection) {
		this.connection = connection;
	}

	public void adiciona(Categoria categoria) throws SQLException {
		String sql = "insert into categoria (id, nome) values (?, ?)";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, categoria.getId());
			ps.setString(2, categoria.getNome());
			ps.execute();
			System.out.println("Adicionado com Sucesso!");
		}
	}

	public void remove(Categoria categoria) throws SQLException {
		String sql = "delete from produto where id = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, categoria.getId());
			ps.execute();
			System.out.println("Deletado com Sucesso!");
		}
	}

	public void atualiza(Categoria categoria) throws SQLException {
		String sql = "update from produto set id = ?, nome = ? where id = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, categoria.getId());
			ps.setString(2, categoria.getNome());
			ps.execute();
			System.out.println("Atualizado com Sucesso!");
		}
	}

	public List<Categoria> lista() throws SQLException {
		List<Categoria> listaCategoria = new ArrayList<>();
		String sql = "select * from categoria";
		try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
			ps.execute();
			try (ResultSet result = ps.getResultSet()) {
				while (result.next()) {
					Categoria categoria = new Categoria(result.getInt("id"), result.getString("nome"));
					listaCategoria.add(categoria);
				}
			}
		}
		return listaCategoria;
	}

	public List<Categoria> buscaPorId(Categoria categoria) throws SQLException {
		List<Categoria> listaCategoria = new ArrayList<>();
		String sql = "select * from categoria where id = ?";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, categoria.getId());
			ps.execute();
			try (ResultSet result = ps.getResultSet()) {
				while (result.next()) {
					Categoria categoriaNova = new Categoria(result.getInt("id"), result.getString("nome"));
					listaCategoria.add(categoriaNova);
				}
			}
		}
		return listaCategoria;
	}

	public List<Categoria> listaCategoriasComProdutos() throws SQLException {
		Categoria ultima = null;
		List<Categoria> listaCategoria = new ArrayList<>();
		String sql = "select * from categoria c inner join produto p on c.id = p.categoria_id";
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.execute();
			try (ResultSet result = ps.getResultSet()) {
				while (result.next()) {
					if (ultima == null || !ultima.getNome().equals(result.getString(2))) {
						Categoria categoriaNova = new Categoria(result.getInt(1), result.getString(2));
						ultima = categoriaNova;
						listaCategoria.add(categoriaNova);
					}
					Produto produto = new Produto(result.getInt(3), result.getString(4), result.getString(5));
					ultima.adiciona(produto);
				}
			}
		}
		return listaCategoria;
	}
}