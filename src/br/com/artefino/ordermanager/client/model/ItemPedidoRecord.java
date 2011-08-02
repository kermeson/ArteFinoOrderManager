package br.com.artefino.ordermanager.client.model;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class ItemPedidoRecord extends ListGridRecord {

	public static final String ID_NAME = "#";
	public static final String ID = "id";
	public static final String REFERENCIA = "referencia";
	public static final String QUANTIDADE = "quantidade";
	public static final String VALOR_UNITARIO = "valorUnitario";

	public ItemPedidoRecord() {
	}

	public ItemPedidoRecord(int id, String referencia, int quantidade, double valorUnitario) {
		setId(id);
		setReferencia(referencia);
		setQuantidade(quantidade);
		setValorUnitario(valorUnitario);
	}

	public void setId(int attribute) {
		setAttribute(ID, attribute);
	}

	public void setReferencia(String attribute) {
		setAttribute(REFERENCIA, attribute);
	}

	public void setQuantidade(int attribute) {
		setAttribute(QUANTIDADE, attribute);
	}

	public void setValorUnitario(double attribute) {
		setAttribute(VALOR_UNITARIO, attribute);
	}

	public int getId() {
		return getAttributeAsInt(ID);
	}

	public String getReferencia() {
		return getAttributeAsString(REFERENCIA);
	}

	public int getQuantidade() {
		return getAttributeAsInt(QUANTIDADE);
	}

	public double getTipoPessoa() {
		return getAttributeAsDouble(VALOR_UNITARIO);
	}
}
