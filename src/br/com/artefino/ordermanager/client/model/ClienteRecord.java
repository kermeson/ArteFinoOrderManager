package br.com.artefino.ordermanager.client.model;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class ClienteRecord extends ListGridRecord {

	public static final String ID_NAME = "#";
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String ENDERECO = "endereco";
	public static final String TIPO_PESSOA = "tipoPessoa";

	public ClienteRecord() {
	}

	public ClienteRecord(Long id, String nome, String endereco,
			Integer tipoPessoa) {
		setId(id);
		setNome(nome);
		setEndereco(endereco);
		setTipoPessoa(tipoPessoa);
	}

	public void setId(Long attribute) {
		setAttribute(ID, attribute);
	}

	public void setNome(String attribute) {
		setAttribute(NOME, attribute);
	}

	public void setEndereco(String attribute) {
		setAttribute(ENDERECO, attribute);
	}

	public void setTipoPessoa(Integer attribute) {
		setAttribute(TIPO_PESSOA, attribute);
	}

	public Long getId() {
		return (Long) getAttributeAsObject(ID);
	}

	public String getNome() {
		return getAttributeAsString(NOME);
	}

	public String getEndereco() {
		return getAttributeAsString(ENDERECO);
	}

	public Integer getTipoPessoa() {
		return (Integer) getAttributeAsObject(TIPO_PESSOA);
	}

}
