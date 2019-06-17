package negocio;

import java.sql.ResultSet;

public class Item {
	private Pedido pedido;
	private Produto produto;
	private int qtde;

	public Item(Pedido _pedido, Produto _produto, int _qtde) {
		this.pedido = _pedido;
		this.produto = _produto;
		this.qtde = _qtde;
	}
	public Item(Pedido _pedido, Produto _produto, ResultSet conjuntoResultados) throws Exception {
		this.pedido = _pedido;
		this.produto = _produto;
		this.qtde = conjuntoResultados.getInt("qtde");	
	}
	public Pedido getPedido() {
		return this.pedido;
	}
	public Produto getProduto() {
		return this.produto;
	}
	public int getQtde() {
		return this.qtde;
	}
	public String toString() {
		return this.getPedido() + " - " + this.getProduto() + " - " + this.getQtde();
	}
}
