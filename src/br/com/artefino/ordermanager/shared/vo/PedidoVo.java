package br.com.artefino.ordermanager.shared.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private ClienteVo cliente;

	private Date dataCadastro;

	private List<ItemPedidoVo> itens = new ArrayList<ItemPedidoVo>();

	// JPA requires a no-argument constructor
	public PedidoVo() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClienteVo getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVo cliente) {
		this.cliente = cliente;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<ItemPedidoVo> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoVo> itens) {
		this.itens = itens;
	}

	public double getValorTotalItens() {
		double valorTotal = 0;
		for (ItemPedidoVo itemPedido : itens) {
			valorTotal += itemPedido.getValorTotal();
		}
		return valorTotal;
	}

}
