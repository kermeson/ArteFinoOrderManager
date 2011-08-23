package br.com.artefino.ordermanager.shared.vo;

import java.io.Serializable;

public class ItemPedidoVo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String referencia;

	private Integer quantidadeItens;

	private Double valorUnitario;

	private String descricao;

	// JPA requires a no-argument constructor
	public ItemPedidoVo() {

	}

	public ItemPedidoVo(ClienteVo clienteVo) {
		this.id = clienteVo.getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Integer getQuantidadeItens() {
		return quantidadeItens;
	}

	public void setQuantidadeItens(Integer quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public double getValorTotal() {
		return getValorUnitario()*getQuantidadeItens();
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
