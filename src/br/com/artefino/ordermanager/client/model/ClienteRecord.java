package br.com.artefino.ordermanager.client.model;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class ClienteRecord extends ListGridRecord {

	public static final String ID_NAME = "#";
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String ENDERECO = "endereco";
	public static final String TIPO_PESSOA = "tipoPessoa";
	public static final String CNPJF = "cnpjf";

	public ClienteRecord() {
	}

	public ClienteRecord(int id, String nome, String endereco,
			int tipoPessoa,  long cnpjf) {
		setId(id);
		setNome(nome);
		setEndereco(endereco);
		setTipoPessoa(tipoPessoa);
		setCnpjf(cnpjf);
	}

	public void setId(int attribute) {
		setAttribute(ID, attribute);
	}

	public void setNome(String attribute) {
		setAttribute(NOME, attribute);
	}

	public void setEndereco(String attribute) {
		setAttribute(ENDERECO, attribute);
	}

	public void setTipoPessoa(int attribute) {
		setAttribute(TIPO_PESSOA, attribute);
	}
	
	public void setCnpjf(long attribute) {
		setAttribute(CNPJF, attribute);
	}

	public int getId() {
		return getAttributeAsInt(ID);
	}

	public String getNome() {
		return getAttributeAsString(NOME);
	}

	public String getEndereco() {
		return getAttributeAsString(ENDERECO);
	}

	public int getTipoPessoa() {
		return getAttributeAsInt(TIPO_PESSOA);
	}
	
	public long getCnpjf() {
		return (Long)getAttributeAsObject(CNPJF);
	}

}
