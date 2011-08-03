package br.com.artefino.ordermanager.client.model;

import java.util.Date;

import br.com.artefino.ordermanager.client.util.FormatadorUtil;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class PedidoRecord extends ListGridRecord {

	public static final String ID_NAME = "#";
	public static final String ID = "id";
	public static final String NOME_CLIENTE = "nomeCliente";
	public static final String QTD_ITENS = "qtdItens";
	public static final String VALOR_TOTAL = "valorTotal";
	public static final String DATA_CADASTRO = "dataCadastro";

	public PedidoRecord() {
	}

	public PedidoRecord(int id, String nomeCliente, int qtdItens, double valorTotal, Date dataCadastro) {
		setId(id);
		setNomeCliente(nomeCliente);
		setQuantidadeItens(qtdItens);
		setValorTotal(valorTotal);
		setDataCadastro(dataCadastro);
	}

	public void setId(int attribute) {
		setAttribute(ID, attribute);
	}

	public void setNomeCliente(String attribute) {
		setAttribute(NOME_CLIENTE, attribute);
	}

	public void setQuantidadeItens(int attribute) {
		setAttribute(QTD_ITENS, attribute);
	}

	public void setValorTotal(double attribute) {
		setAttribute(VALOR_TOTAL, attribute);
	}

	public void setDataCadastro(Date attribute) {
		setAttribute(DATA_CADASTRO, attribute);
	}

	public int getId() {
		return getAttributeAsInt(ID);
	}

	public String getNomeCliente() {
		return getAttributeAsString(NOME_CLIENTE);
	}

	public int getQuantidadeItens() {
		return getAttributeAsInt(QTD_ITENS);
	}

	public double getValorTotal() {
		return FormatadorUtil
				.getFormatDouble(getAttributeAsString(VALOR_TOTAL));
	}

	public Date getDataCadastro() {
		return getAttributeAsDate(DATA_CADASTRO);
	}

}
