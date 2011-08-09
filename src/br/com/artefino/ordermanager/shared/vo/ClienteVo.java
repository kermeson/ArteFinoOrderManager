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

	private Long cnpjf;

	private Integer tipoPessoa;

	private String cidade;

	private String bairro;

	private Long numero;

	private String telefoneFixo;

	private String telefoneCelular;

	private String uf;

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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}


	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getUf() {
		return uf;
	}




}
