package br.com.artefino.ordermanager.client.model;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class CategoriaDespesaRecord extends ListGridRecord {

	public static final String ID_NAME = "#";
	public static final String ID = "id";
	public static final String NOME = "nome";

	public CategoriaDespesaRecord() {
	}

	public CategoriaDespesaRecord(int id, String nome) {
		setId(id);
		setNome(nome);

	}

	public void setId(int attribute) {
		setAttribute(ID, attribute);
	}

	public void setNome(String attribute) {
		setAttribute(NOME, attribute);
	}

	public int getId() {
		return getAttributeAsInt(ID);
	}

	public String getNome() {
		return getAttributeAsString(NOME);
	}

}
