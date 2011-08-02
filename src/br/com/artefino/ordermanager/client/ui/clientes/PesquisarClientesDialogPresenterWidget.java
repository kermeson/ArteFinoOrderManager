package br.com.artefino.ordermanager.client.ui.clientes;

import br.com.artefino.ordermanager.client.ui.clientes.handlers.PesquisarClientesDialogUIHandlers;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.smartgwt.client.widgets.events.ClickEvent;

public class PesquisarClientesDialogPresenterWidget extends
		PresenterWidget<PesquisarClientesDialogPresenterWidget.MyView> implements PesquisarClientesDialogUIHandlers  {

	public interface MyView extends View, HasUiHandlers<PesquisarClientesDialogUIHandlers> {
		void show();
	}

	@Inject
	public PesquisarClientesDialogPresenterWidget(final EventBus eventBus,
			final MyView view) {
		super(eventBus, view);
		
		getView().setUiHandlers(this);
	}

	@Override
	public void onTestButtonCliked(ClickEvent event) {				
	}
	
	public void show() {
		getView().show();
	}
	
	

}
