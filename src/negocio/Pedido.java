package negocio;

import java.sql.ResultSet;

public class Pedido {
	private int idPedido;
	private Data data;
	private Cliente cliente;

	public Pedido(int _idPedido, Data _data, Cliente _cliente) {
		this.idPedido = _idPedido;
		this.data = _data;
		this.cliente = _cliente;
	}
	public Pedido(Cliente _cliente, ResultSet conjuntoResultados) throws Exception {
		this.idPedido = conjuntoResultados.getInt("idPedido");
		this.data = new Data(conjuntoResultados.getDate("data"));
		this.cliente = _cliente;	
	}	
	public int getIdPedido() {
		return this.idPedido;
	}
	public Data getData() {
		return this.data;
	}
	public Cliente getCliente() {
		return this.cliente;
	}
	public String toString() {
		return this.getIdPedido() + " - " + this.getData() + " - " + this.getCliente();
	}
}
