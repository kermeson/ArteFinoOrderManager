package br.com.artefino.ordermanager.server.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.artefino.ordermanager.shared.vo.ClienteVo;

@Entity
@Table(name = "TB_CLIENTE")
public class Cliente {

	private Long id;

	private String nome;

	private String apelido;

	private String endereco;

	private String telefone;

	private Long cnpjf;

	private Integer tipoPessoa;

	// JPA requires a no-argument constructor
	public Cliente() {

	}

	public Cliente(ClienteVo clienteVo) {
		this.nome = clienteVo.getNome();
		this.endereco = clienteVo.getEndereco();
		this.telefone = clienteVo.getTelefone();
		this.cnpjf = clienteVo.getCnpjf();
		this.tipoPessoa = clienteVo.getTipoPessoa();

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

	@Column(name = "DS_NOME")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "DS_APELIDO")
	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	@Column(name = "DS_ENDERECO")
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Column(name = "DS_TELEFONE")
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Column(name = "NU_CNPJF")
	public Long getCnpjf() {
		return cnpjf;
	}

	public void setCnpjf(Long cnpjf) {
		this.cnpjf = cnpjf;
	}

	@Column(name = "FL_TIPO_PESSOA")
	public Integer getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(Integer tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}



}
