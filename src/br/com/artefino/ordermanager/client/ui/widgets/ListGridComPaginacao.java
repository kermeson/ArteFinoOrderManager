package br.com.artefino.ordermanager.client.ui.widgets;

import br.com.artefino.ordermanager.client.ArteFinoOrderManager;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;

public abstract class ListGridComPaginacao extends VLayout {
	private BarraPaginacao barraPaginacao;

	public static final int DEFAULT_MAX_RESULTS = 30;

	private int maxResults;
	private int firstResult;
	private int pageNumber;
	private int totalResults;

	public ListGridComPaginacao(ListGrid listGrid) {
		super();

		maxResults = DEFAULT_MAX_RESULTS;
		firstResult = 0;
		pageNumber = 1;

		this.barraPaginacao = new BarraPaginacao();

		addMember(listGrid);
		addMember(barraPaginacao);

		bindCustomUiHandlers();

	}

	protected void bindCustomUiHandlers() {

		barraPaginacao.getResultSetFirstButton().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						resultSetFirstButtonClicked();
					}

				});

		barraPaginacao.getResultSetPreviousButton().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						resultSetPreviousButtonClicked();
					}

				});

		barraPaginacao.getResultSetNextButton().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						resultSetNextButtonClicked();
					}

				});

		barraPaginacao.getResultSetLastButton().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						resultSetLastButtonClicked();
					}

				});
	}

	public BarraPaginacao getBarraPaginacao() {
		return barraPaginacao;
	}

	public void setBarraPaginacao(BarraPaginacao barraPaginacao) {
		this.barraPaginacao = barraPaginacao;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	protected abstract void retrieveResultSet();

	protected void refreshButtonClicked() {
		retrieveResultSet();
	}

	public void resultSetFirstButtonClicked() {
		firstResult = 0;
		pageNumber = 1;

		retrieveResultSet();
	}

	protected void resultSetPreviousButtonClicked() {
		firstResult -= maxResults;
		pageNumber--;

		retrieveResultSet();
	}

	protected void resultSetNextButtonClicked() {
		firstResult += maxResults;
		pageNumber++;

		retrieveResultSet();
	}

	protected void resultSetLastButtonClicked() {
		firstResult = maxResults * (getTotalPages() - 1);
		pageNumber = getTotalPages();

		retrieveResultSet();

	}

	public void atualizar() {

		int totalPages = getTotalPages();
		if (totalPages > 0) {
			if (barraPaginacao.getToolStrip().isDisabled()) {
				barraPaginacao.getToolStrip().enable();
			}
			if (getPageNumber() == 1) {
				if (!barraPaginacao.getResultSetFirstButton().isDisabled()) {
					barraPaginacao.getResultSetFirstButton().disable();
				}
				if (!barraPaginacao.getResultSetPreviousButton().isDisabled()) {
					barraPaginacao.getResultSetPreviousButton().disable();
				}
			} else {
				if (barraPaginacao.getResultSetFirstButton().isDisabled()) {
					barraPaginacao.getResultSetFirstButton().enable();
				}
				if (barraPaginacao.getResultSetPreviousButton().isDisabled()) {
					barraPaginacao.getResultSetPreviousButton().enable();
				}
			}
			if (getPageNumber() == totalPages) {
				if (!barraPaginacao.getResultSetLastButton().isDisabled()) {
					barraPaginacao.getResultSetLastButton().disable();
				}
				if (!barraPaginacao.getResultSetNextButton().isDisabled()) {
					barraPaginacao.getResultSetNextButton().disable();
				}
			} else {
				if (barraPaginacao.getResultSetLastButton().isDisabled()) {
					barraPaginacao.getResultSetLastButton().enable();
				}
				if (barraPaginacao.getResultSetNextButton().isDisabled()) {
					barraPaginacao.getResultSetNextButton().enable();
				}
			}

			String pageNumberLabel = ArteFinoOrderManager.getMessages().pagina(
					getPageNumber(), getTotalPages());
			barraPaginacao.getPageNumberLabel().setContents(pageNumberLabel);

		} else {
			barraPaginacao.getToolStrip().disable();
			barraPaginacao.getPageNumberLabel().setContents("");

		}

		barraPaginacao.getSelectedLabel().setContents(
				ArteFinoOrderManager.getMessages().totalRegistros(
						getTotalResults()));

	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public int getTotalResults() {
		return totalResults;
	}

	private int getTotalPages() {
		return (int) (Math.ceil(Double.valueOf(getTotalResults())
				/ Double.valueOf(getMaxResults())));
	}

}
