package br.com.artefino.ordermanager.server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.artefino.ordermanager.shared.vo.CategoriaDespesaVo;

@Entity
@Table(name = "TB_CATEGORIA_DESPESA")
public class CategoriaDespesa {

	private Long id;

	private String nome;

	// JPA requires a no-argument constructor
	public CategoriaDespesa() {

	}


	public CategoriaDespesa(CategoriaDespesaVo categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
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

	@Column(name = "NM_CATEGORIA")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CategoriaDespesaVo converterParaVo() {
		CategoriaDespesaVo categoria = new CategoriaDespesaVo();
		categoria.setId(this.getId());
		categoria.setNome(this.getNome());
		return categoria;
	}



}
