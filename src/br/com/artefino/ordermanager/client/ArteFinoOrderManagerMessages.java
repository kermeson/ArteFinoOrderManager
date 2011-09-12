package br.com.artefino.ordermanager.client;

public interface ArteFinoOrderManagerMessages extends
		com.google.gwt.i18n.client.Messages {

	@DefaultMessage("Preencha os campos obrigatórios.")
	String preenchaCamposObrigatorios();
	
	@DefaultMessage("O cliente <b>{0}</b> foi atualizado com sucesso.")
	String clienteAtualizado(String nome);

	@DefaultMessage("Operação realizada com sucesso.")
	String operacaoRealizadaComSucesso();

	@DefaultMessage("Selecione o cliente do pedido.")
	String selecioneClientePedido();

	@DefaultMessage("Informe os itens do pedido.")
	String preenchaItensPedido();

	@DefaultMessage("Preencha as informações obrigatórias dos itens do pedido.")
	String preenchaCamposObrigatoriosItensPedido();

	@DefaultMessage("Informe o nome da Categoria de despesas.")
	String nomeCategoriaObrigatorio();

	@DefaultMessage("Deseja remover a categoria <b>{0}</b>?.")
	String confirmarRemoverCategoria(String nomeCategoria);

	@DefaultMessage("A sessão expirou. É necessário efeutar o login novamente.")
	String sessaoExpirou();

	@DefaultMessage("Este usuário não tem permissão para executar essa operação.")
	String usuariosemPermissao();

	@DefaultMessage("página {0} de  {1}")
	String pagina(int paginaAtual, int totalPaginas);

	@DefaultMessage("Total de registros: {0}")
	String totalRegistros(int totalResults);

}
