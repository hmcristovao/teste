package conexaoBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import negocio.Cliente;

public class Conexao {
	public static final String PREFIX 		= "jdbc:mysql:";
	public static final String HOSTNAME 	= "localhost";
	public static final String PORT 		= "3306";
	public static final String DATABASE 	= "compras";
	public static final String TIMEZONE 	= "useTimezone=true&serverTimezone=UTC";
	public static final String USER 		= "root";
	public static final String PASSWORD 	= "Minhasenhamysql";
	
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public Conexao() {
		this.connection = null;
		this.statement = null;
		this.resultSet = null;
	}
	public void conectar() throws Exception {
		// monta a url do banco (exemplo: jdbc:mysql://localhost:3306/compras?useTimezone=true&serverTimezone=UTC) 
		String url = Conexao.PREFIX + "//" + Conexao.HOSTNAME + ":" + Conexao.PORT + "/" + Conexao.DATABASE + "?" + Conexao.TIMEZONE;
		// estabele uma conexão com o banco de dados em 'url' 
		this.connection = DriverManager.getConnection(url,  Conexao.USER, Conexao.PASSWORD);
		this.statement = this.connection.createStatement();
	}
	public boolean isConectado() {
		if(this.connection != null)
			return true;
		else
			return false;
	}
	public void iniciaQuery(String query) throws Exception {
		this.resultSet = this.statement.executeQuery(query);
		this.statement = this.connection.createStatement();
	}
	public ResultSet getConjuntoResultados() {
		return this.resultSet;
	}
	public void atualiza(String query) throws Exception {
		this.statement.executeUpdate(query);
	}
	public void desconectar() throws Exception {
		this.connection.close();
	}
}
