package br.com.alura.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

	private int id;
	private String nome;
	private List<Produto> produtos = new ArrayList<>();

	public Categoria(int id) {
		this.id = id;
	}

	public Categoria(int id, String nome) {
		this.nome = nome;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return String.format("Id: %d Nome: %s", this.id, this.nome);
	}

	public void adiciona(Produto produto) {
		produtos.add(produto);
	}

	public List<Produto> getProdutos() {
		return produtos;
	}
}