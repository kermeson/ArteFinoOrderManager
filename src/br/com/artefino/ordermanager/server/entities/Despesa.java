package br.com.artefino.ordermanager.server.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.artefino.ordermanager.shared.vo.DespesaVo;

@Entity
@Table(name = "TB_DESPESA")
public class Despesa {

	private Long id;

	private String descricao;

	private Date dataCadastro;

	private CategoriaDespesa categoria;

	private Double valor;

	// JPA requires a no-argument constructor
	public Despesa() {

	}

	public Despesa(DespesaVo pedidoVo) {
		this.id = pedidoVo.getId();
		this.dataCadastro = pedidoVo.getDataCadastro();
		this.descricao = pedidoVo.getDescricao();
		this.valor = pedidoVo.getValor();
		this.categoria = new CategoriaDespesa(pedidoVo.getCategoria());
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CADASTRO")
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PID_CATEGORIA", nullable = false)
	public CategoriaDespesa getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDespesa categoria) {
		this.categoria = categoria;
	}


	@Lob
	@Column(name = "DS_DESPESA")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "VL_DESPESA")
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public DespesaVo converterParaVo() {
		DespesaVo despesa = new DespesaVo();
		despesa.setId(getId());
		despesa.setValor(getValor());
		despesa.setDataCadastro(getDataCadastro());
		despesa.setCategoria(getCategoria().converterParaVo());
		despesa.setDescricao(getDescricao());

		return despesa;
	}

}
