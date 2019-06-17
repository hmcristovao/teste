package negocio;

import java.sql.ResultSet;

public class Cliente {
	private int idCliente;
	private String nome;
	
	public Cliente(int _idCliente, String _nome) {
		this.idCliente = _idCliente;
		this.nome = _nome;
	}	
	public Cliente(ResultSet conjuntoResultados) throws Exception {
		this.idCliente = conjuntoResultados.getInt("idCliente");
		this.nome = conjuntoResultados.getString("nome");
	}
	public int getIdCliente() {
		return this.idCliente;
	}
	public String getNome() {
		return this.nome;
	}
	public String toString() {
		return this.getIdCliente() + " - " + this.getNome();
	}
}
