package br.com.artefino.ordermanager.shared.vo;

import java.io.Serializable;
import java.util.Date;

public class DespesaVo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String  descricao;

	private Date dataCadastro;

	private CategoriaDespesaVo categoria;

	private Double valor;

	// JPA requires a no-argument constructor
	public DespesaVo() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public CategoriaDespesaVo getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDespesaVo categoria) {
		this.categoria = categoria;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}


}
