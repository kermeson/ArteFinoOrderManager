package br.com.artefino.ordermanager.server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.artefino.ordermanager.shared.vo.SituacaoPedidoVo;

@Entity
@Table(name = "TB_SITUACAO_PEDIDO")
public class SituacaoPedido {

	private Long id;

	private String nome;

	// JPA requires a no-argument constructor
	public SituacaoPedido() {

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

	@Column(name = "NM_SITUACAO")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public SituacaoPedidoVo converterParaVo() {
		SituacaoPedidoVo situacao = new SituacaoPedidoVo();
		situacao.setId(this.getId());
		situacao.setNome(this.getNome());
		return situacao;
	}



}
