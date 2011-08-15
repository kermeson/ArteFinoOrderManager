package br.com.artefino.ordermanager.client.model;

import br.com.artefino.ordermanager.client.util.FormatadorUtil;

import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class ItemPedidoRecord extends ListGridRecord {

	public static final String ID = "id";
	public static final String REFERENCIA = "referencia";
	public static final String QUANTIDADE = "quantidade";
	public static final String VALOR_UNITARIO = "valorUnitario";
	public static final String VALOR_TOTAL = "valorTotal";

	public ItemPedidoRecord(JavaScriptObject javaScriptObject) {
		super(javaScriptObject);
	}

	public ItemPedidoRecord(int id, String referencia, int quantidade,
			double valorUnitario, double valorTotal) {
		setId(id);
		setReferencia(referencia);
		setQuantidade(quantidade);
		setValorUnitario(valorUnitario);
		setValorTotal(valorTotal);
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

	public void setValorTotal(double attribute) {
		setAttribute(VALOR_TOTAL, attribute);
	}

	public int getId() {
		return getAttributeAsInt(ID);
	}

	public String getReferencia() {
		return getAttributeAsString(REFERENCIA);
	}

	public int getQuantidade() {
		return Integer.valueOf(getAttributeAsString(QUANTIDADE));
	}

	public double getValorUnitario() {
		return FormatadorUtil.getFormatDouble(getAttributeAsString(VALOR_UNITARIO));
	}

	public double getValorTotal() {
		return FormatadorUtil.getFormatDouble(getAttributeAsString(VALOR_TOTAL));
	}
}
