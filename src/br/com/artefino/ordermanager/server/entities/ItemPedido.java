package br.com.artefino.ordermanager.server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.artefino.ordermanager.shared.vo.ItemPedidoVo;

@Entity
@Table(name = "TB_ITEM_PEDIDO")
public class ItemPedido {

	private Long id;

	private String referencia;

	private Integer quantidadeItens;

	private Double valorUnitario;

	// JPA requires a no-argument constructor
	public ItemPedido() {

	}

	public ItemPedido(ItemPedidoVo itemPedidoVo) {
		this.id = itemPedidoVo.getId();
		this.referencia = itemPedidoVo.getReferencia();
		this.quantidadeItens = itemPedidoVo.getQuantidadeItens();
		this.valorUnitario = itemPedidoVo.getValorUnitario();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DS_REFERENCIA")
	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	@Column(name = "QT_ITENS")
	public Integer getQuantidadeItens() {
		return quantidadeItens;
	}

	public void setQuantidadeItens(Integer quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
	}

	@Column(name = "VL_VALOR_UNITARIO")
	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public ItemPedidoVo converterParaVo() {
		ItemPedidoVo itemPedidoVo = new ItemPedidoVo();
		itemPedidoVo.setId(this.getId());
		return itemPedidoVo;
	}

}
