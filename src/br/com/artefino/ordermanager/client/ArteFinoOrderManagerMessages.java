package br.com.artefino.ordermanager.client;

public interface ArteFinoOrderManagerMessages extends
		com.google.gwt.i18n.client.Messages {

	@DefaultMessage("Preencha os campos obrigatórios.")
	String preenchaCamposObrigatorios();
	
	@DefaultMessage("O cliente {0} foi atualizado com sucesso.")
	String clienteAtualizado(String nome);

	@DefaultMessage("Operação realizada com sucesso.")
	String operacaoRealizadoComSucesso();

}
