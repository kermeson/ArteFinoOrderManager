package br.com.artefino.ordermanager.client.ui.despesas;

import br.com.artefino.ordermanager.client.ui.despesas.handlers.CategoriaUIHandlers;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class CategoriaPresenterWidget extends
		PresenterWidget<CategoriaPresenterWidget.MyView>
		implements CategoriaUIHandlers {

	private DispatchAsync dispatcher;
	private EventBus eventBus;

	public interface MyView extends View,
			HasUiHandlers<CategoriaUIHandlers> {


	}

	@Inject
	public CategoriaPresenterWidget(final EventBus eventBus,
			final MyView view, final DispatchAsync dispatcher) {
		super(eventBus, view);
		this.eventBus = eventBus;
		this.dispatcher = dispatcher;
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {

	}



}
