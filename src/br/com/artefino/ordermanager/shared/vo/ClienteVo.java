package br.com.artefino.ordermanager.shared.vo;

import java.io.Serializable;


public class ClienteVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8979038313031009647L;

	private Long id;

	private String nome;

	private String apelido;

	private String endereco;

	private String telefone;

	private Long cnpjf;

	private Integer tipoPessoa;

	public ClienteVo() {

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

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Long getCnpjf() {
		return cnpjf;
	}

	public void setCnpjf(Long cnpjf) {
		this.cnpjf = cnpjf;
	}

	public Integer getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(Integer tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}


}
