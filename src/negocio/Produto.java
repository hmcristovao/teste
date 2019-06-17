package negocio;

import java.sql.ResultSet;

public class Produto {
	private int idProduto;
	private String descricao;
	private double preco;
	
	public Produto(int _idProduto, String _descricao, double _preco) {
		this.idProduto = _idProduto;
		this.descricao = _descricao;
		this.preco = _preco;
	}
	public Produto(ResultSet conjuntoResultados) throws Exception {
		this.idProduto = conjuntoResultados.getInt("idProduto");
		this.descricao = conjuntoResultados.getString("descricao");
		this.preco = conjuntoResultados.getDouble("preco");
	}	
	public int getIdProduto() {
		return this.idProduto;
	}
	public String getDescricao() {
		return this.descricao;
	}
	public double getPreco() {
		return this.preco;
	}
	public String toString() {
		return this.getIdProduto() + " - " + this.getDescricao() + " - " + this.getPreco();
	}
}
