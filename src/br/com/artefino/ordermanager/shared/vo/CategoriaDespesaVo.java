package br.com.artefino.ordermanager.shared.vo;

import java.io.Serializable;

public class CategoriaDespesaVo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1354736690492397272L;

	private Long id;

	private String nome;

	// JPA requires a no-argument constructor
	public CategoriaDespesaVo() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
