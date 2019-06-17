package usuario;

import conexaoBD.*;
import negocio.*;

public class Aplicacao {
	public static void main(String args[]) {
		try {
			Conexao conexao = new Conexao();
			conexao.conectar();
			if(!conexao.isConectado()) 
				throw new Exception ("banco de dados não conectado");

			// objetos auxiliares
			String query, query1, query2, query3, query4, query5, query6;
			Cliente cliente;
			Pedido pedido;
			Produto produto;
			Item item;

			//--------------------------------------------------------------------
			//Teste 1: cria 2 objetos da classe Cliente (1,Pedro e 2,Maria) e insere os seus dados na tabela Cliente 
			System.out.println("\nTeste 1: insere os clientes Pedro e Maria");
			Cliente cliente1 = new Cliente(1, "Pedro");
			Cliente cliente2 = new Cliente(2, "Maria");
			query1 = "INSERT INTO cliente (idCliente, nome) VALUES ('" + cliente1.getIdCliente() + "', '" + cliente1.getNome() + "');";
			query2 = "INSERT INTO cliente (idCliente, nome) VALUES ('" + cliente2.getIdCliente() + "', '" + cliente2.getNome() + "');";
			conexao.atualiza(query1);
			conexao.atualiza(query2);
			System.out.println("Query SQL executada:\n" + query1 + "\n" + query2);

			//--------------------------------------------------------------------
			// Teste 2: altera o nome da cliente Maria, na tabela Cliente, para Mariana
			System.out.println("\nTeste 2: altera o nome de Maria para Mariana na tabela Cliente");
			query = "UPDATE cliente SET nome = 'Mariana' WHERE nome = 'Maria'";
			conexao.atualiza(query);
			System.out.println("Query SQL executada:\n" + query);

			//--------------------------------------------------------------------
			// Teste 3: exclui o cliente com nome Pedro, na tabela Cliente
			System.out.println("\nTeste 3: exclui Pedro na tabela Cliente");
			query = "DELETE FROM cliente WHERE nome = 'Pedro'";
			conexao.atualiza(query);			
			System.out.println("Query SQL executada:\n" + query);

			//--------------------------------------------------------------------
			// Teste 4: exibe todos os clientes em ordem alfabética de nome
			System.out.println("\nTeste 4: exibe todos os clientes em ordem alfabética de nome");
			query = "SELECT * FROM cliente ORDER BY nome";
			conexao.iniciaQuery(query);
			while(conexao.getConjuntoResultados().next()) {
				// cria um objeto da classe Cliente a partir do registro corrente da consulta feita na tabela Cliente
				cliente = new Cliente(conexao.getConjuntoResultados());
				System.out.println(cliente);
			}
			System.out.println("Query SQL executada:\n" + query);

			//--------------------------------------------------------------------
			// Teste 5: cria dois objetos da classe Produto (1,teclado e 2,mouse) e os insere na tabela Produto
			System.out.println("\nTeste 5: insere 2 produtos: teclado e mouse");
			Produto produto1 = new Produto(1, "teclado", 99.98);
			Produto produto2 = new Produto(2, "mouse", 37.50);
			query1 = "INSERT INTO produto (idProduto, descricao,preco) VALUES ('" 
					+ produto1.getIdProduto() + "', '" + produto1.getDescricao() + "', '" + produto1.getPreco() + "');";
			conexao.atualiza(query1);
			query2 = "INSERT INTO produto (idProduto, descricao,preco) VALUES ('" 
					+ produto2.getIdProduto() + "', '" + produto2.getDescricao() + "', '" + produto2.getPreco() + "');";
			conexao.atualiza(query2);
			System.out.println("Query SQL executada:\n" + query1 + "\n" + query2);

			//--------------------------------------------------------------------
			// Teste 6: exibe todos os produtos em ordem de Preco
			System.out.println("\nTeste 6: exibe todos os produtos em ordem de preco");
			query = "SELECT * FROM produto ORDER BY preco";
			conexao.iniciaQuery(query);
			while(conexao.getConjuntoResultados().next()) {
				// cria um objeto da classe Produto a partir do registro corrente da consulta feita na tabela Produto
				produto = new Produto(conexao.getConjuntoResultados());
				System.out.println(produto);
			}
			System.out.println("Query SQL executada:\n" + query);

			//--------------------------------------------------------------------
			// Teste 7: insere no banco de dados:
			// "Ana(id_10) fez pedido(id_30) de 3 pendrives(id_20), 75.oo cada, e 2 estabilizadores(id_21), 250.00 cada, em 11-jun-2019"
			// Informações a serem inseridas em cada tabela:
			// tabela Cliente: 10, Ana 
			// tabela Produto: 20, pendrive, 75.00
			// tabela Produto: 21, estabilizador, 250.00
			// tabela Pedido: 30, 11-jun-2019, 10
			// tabela Item: 30, 20, 3
			// tabela Item: 30, 21, 2
			System.out.println("\nTeste 5: insere \"Ana fez pedido de 3 pendrives (75.oo cada) e 2 estabilizadores (250.00 cada) em 11-jun-2019");
			//
			// Cria um objeto da classe Cliente: 10, Ana 
			Cliente cliente_10 = new Cliente(10, "Ana");
			// Cria um objeto da classe Produto: 20, pendrive, 75.00
			Produto produto_20 = new Produto(20, "pendrive", 75);
			// Cria um objeto da classe Produto: 21, estabilizador, 250.00
			Produto produto_21 = new Produto(21, "estabilizador", 250);
			// Cria um objeto da classe Pedido: 30, 11-jun-2019, cliente_10
			Pedido pedido_30 = new Pedido(30, new Data(11,6,2019), cliente_10);
			// Cria um objeto da classe Item: pedido_30, produto_20, 3
			Item item_30_20 = new Item(pedido_30, produto_20, 3);
			// Cria um objeto da classe Item: pedido_30, produto_21, 2
			Item item_30_21 = new Item(pedido_30, produto_21, 2);
			//
			// SQL para: inserir os dados do objeto cliente_10 na tabela Cliente 
			query1 = "INSERT INTO cliente (idCliente, nome) VALUES ('" + cliente_10.getIdCliente() + "', '" + cliente_10.getNome() + "');";;
			// SQL para: inserir os dados do objeto produto_20 na tabela Produto
			query2 = "INSERT INTO produto (idProduto, descricao,preco) VALUES ('" 
					+ produto_20.getIdProduto() + "', '" + produto_20.getDescricao() + "', '" + produto_20.getPreco() + "');";
			// SQL para: inserir os dados do objeto produto_21 na tabela Produto
			query3 = "INSERT INTO produto (idProduto, descricao,preco) VALUES ('" 
					+ produto_21.getIdProduto() + "', '" + produto_21.getDescricao() + "', '" + produto_21.getPreco() + "');";
			// SQL para: inserir os dados do objeto pedido_30 na tabela Pedido
			query4 = "INSERT INTO pedido (idPedido, data, Cliente_idCliente) VALUES ('" 
					+ pedido_30.getIdPedido() + "', '" + pedido_30.getData() + "', '" + pedido_30.getCliente().getIdCliente() + "');";
			// SQL para: inserir os dados do objeto item_30_20 na tabela Item
			query5 = "INSERT INTO item (Pedido_idPedido, Produto_idProduto, qtde) VALUES ('"
					+ item_30_20.getPedido().getIdPedido() + "', '" + item_30_20.getProduto().getIdProduto() + "', '" + item_30_20.getQtde() + "');";
			// SQL para: inserir os dados do objeto item_30_21 na tabela Item
			query6 = "INSERT INTO item (Pedido_idPedido, Produto_idProduto, qtde) VALUES ('"
					+ item_30_21.getPedido().getIdPedido() + "', '" + item_30_21.getProduto().getIdProduto() + "', '" + item_30_21.getQtde() + "');";
			//
			// realiza as inserções no banco de dados
			conexao.atualiza(query1);
			conexao.atualiza(query2);
			conexao.atualiza(query3);
			conexao.atualiza(query4);
			conexao.atualiza(query5);
			conexao.atualiza(query6);
			System.out.println("Query SQL executada:\n" + query1 + "\n" + query2+ "\n" + query3+ "\n" + query4+ "\n" + query5+ "\n" + query6);

			//--------------------------------------------------------------------
			// Teste 8: exibe os pedidos (com os itens) da Ana
			// 1º) busca a cliente Ana na tabela cliente
			query = "SELECT * FROM cliente WHERE nome = 'Ana'";
			conexao.iniciaQuery(query);
			if(conexao.getConjuntoResultados().next())
				cliente = new Cliente(conexao.getConjuntoResultados());
			else
				throw new Exception("Nome 'Ana' não encontrado na tabela Cliente");
			// SQL para: buscar todos os dados ligados a Ana (objeto cliente)
			query = "SELECT * FROM Cliente, Pedido, Item, Produto WHERE idCliente = " + cliente.getIdCliente()
			+ " AND idCliente = Cliente_idCliente AND idPedido = Pedido_idPedido AND Produto_idProduto = idProduto";
			conexao.iniciaQuery(query);
			// percorre o conjunto de respostas da consulta SQL e exibe cada linha do resultado 
			while(conexao.getConjuntoResultados().next()) {
				pedido = new Pedido(cliente, conexao.getConjuntoResultados());
				produto = new Produto(conexao.getConjuntoResultados());
				item = new Item(pedido, produto, conexao.getConjuntoResultados());
				System.out.println("\nCliente: " + cliente + "\nPedido: " + pedido + "\nItem: " + item + "\nProduto: " + produto);
			}

			//--------------------------------------------------------------------
			// Teste 9: exibe todos os pedidos (com os itens)
			query = "SELECT * FROM Cliente, Pedido, Item, Produto WHERE idCliente = Cliente_idCliente "
					+ "AND idPedido = Pedido_idPedido AND Produto_idProduto = idProduto";
			conexao.iniciaQuery(query);
			// percorre o conjunto de respostas da consulta SQL e exibe cada linha do resultado 
			while(conexao.getConjuntoResultados().next()) {
				pedido = new Pedido(cliente, conexao.getConjuntoResultados());
				produto = new Produto(conexao.getConjuntoResultados());
				item = new Item(pedido, produto, conexao.getConjuntoResultados());
				System.out.println("\nCliente: " + cliente + "\nPedido: " + pedido + "\nItem: " + item + "\nProduto: " + produto);
			}

			// finaliza a conexão ao banco de dados 
			conexao.desconectar(); 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
