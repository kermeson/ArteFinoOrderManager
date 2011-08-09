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

	private Long cnpjf;

	private Integer tipoPessoa;

	private String cidade;

	private String bairro;

	private Long numero;

	private String telefoneFixo;

	private String telefoneCelular;

	private String uf;

	// JPA requires a no-argument constructor
	public Cliente() {

	}

	public Cliente(ClienteVo clienteVo) {
		this.id = clienteVo.getId();
		this.nome = clienteVo.getNome();
		this.endereco = clienteVo.getEndereco();
		this.cnpjf = clienteVo.getCnpjf();
		this.tipoPessoa = clienteVo.getTipoPessoa();
		this.bairro = clienteVo.getBairro();
		this.cidade = clienteVo.getCidade();
		this.numero = clienteVo.getNumero();
		this.telefoneFixo = clienteVo.getTelefoneFixo();
		this.telefoneCelular = clienteVo.getTelefoneCelular();
		this.uf = clienteVo.getUf();
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


	@Column(name = "NM_CIDADE")
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Column(name = "NM_BAIRRO")
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Column(name = "NU_ENDERECO")
	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	@Column(name = "NU_TELEFONE_FIXO")
	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	@Column(name = "NU_TELEFONE_CELULAR")
	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}


	@Column(name = "DS_UF")
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public ClienteVo converterParaVo() {
		ClienteVo clienteVo = new ClienteVo();
		clienteVo.setId(getId());
		clienteVo.setNome(getNome());
		clienteVo.setEndereco(getEndereco());
		clienteVo.setCnpjf(getCnpjf());
		clienteVo.setTipoPessoa(getTipoPessoa());
		clienteVo.setBairro(getBairro());
		clienteVo.setCidade(getCidade());
		clienteVo.setTelefoneFixo(getTelefoneFixo());
		clienteVo.setTelefoneCelular(getTelefoneCelular());
		clienteVo.setUf(getUf());
		return clienteVo;
	}



}
