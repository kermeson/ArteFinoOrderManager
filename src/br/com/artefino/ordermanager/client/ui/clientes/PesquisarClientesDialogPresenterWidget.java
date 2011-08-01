package br.com.artefino.ordermanager.client.ui.clientes;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;

public class PesquisarClientesDialogPresenterWidget extends
		PresenterWidget<PesquisarClientesDialogPresenterWidget.MyView> {

	public interface MyView extends PopupView {
		// TODO Put your view methods here
	}

	@Inject
	public PesquisarClientesDialogPresenterWidget(final EventBus eventBus,
			final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	protected void onUnbind() {
		super.onUnbind();
	}
}
