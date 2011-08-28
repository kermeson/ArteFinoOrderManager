package br.com.artefino.ordermanager.client.model;

import java.util.Date;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class DespesaRecord extends ListGridRecord {

	public static final String ID_NAME = "#";
	public static final String ID = "id";
	public static final String NOME_CATEGORIA = "nomeCategoria";
	public static final String DESCRICAO = "descricao";
	public static final String VALOR = "valor";
	public static final String DATA_CADASTRO = "dataCadastro";

	public DespesaRecord() {
	}

	public DespesaRecord(int id, String categoria, String descricao,
			Date dataCadastro, double valor) {
		setId(id);
		setCategoria(categoria);
		setDescricao(descricao);
		setDataCadastro(dataCadastro);
		setValor(valor);
	}

	public void setId(int attribute) {
		setAttribute(ID, attribute);
	}

	public void setCategoria(String attribute) {
		setAttribute(NOME_CATEGORIA, attribute);
	}

	public void setDescricao(String attribute) {
		setAttribute(DESCRICAO, attribute);
	}

	public void setDataCadastro(Date attribute) {
		setAttribute(DATA_CADASTRO, attribute);
	}

	public void setValor(double attribute) {
		setAttribute(VALOR, attribute);
	}

	public int getId() {
		return getAttributeAsInt(ID);
	}

	public String getCategoria() {
		return getAttributeAsString(NOME_CATEGORIA);
	}

	public String getDescricao() {
		return getAttributeAsString(DESCRICAO);
	}

	public Date getDataCadastro() {
		return getAttributeAsDate(DATA_CADASTRO);
	}

	public double getValor() {
		return (Double)getAttributeAsObject(VALOR);
	}

}
