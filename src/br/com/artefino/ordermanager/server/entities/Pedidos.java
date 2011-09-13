package br.com.artefino.ordermanager.server.entities;

import java.util.ArrayList;
import java.util.List;

public class Pedidos {
	private List<Pedido> pedidos = new ArrayList<Pedido>();

	public Pedidos() {

	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}
}
