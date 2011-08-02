package br.com.artefino.ordermanager.client.ui.clientes;

import br.com.artefino.ordermanager.client.ui.clientes.handlers.PesquisarClientesDialogUIHandlers;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.PopupViewCloseHandler;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class PesquisarClientesDialogView extends
		ViewWithUiHandlers<PesquisarClientesDialogUIHandlers> implements
		PesquisarClientesDialogPresenterWidget.MyView {

	private Window panel;

	@Inject
	public PesquisarClientesDialogView() {
		super();

		panel = new Window();

		panel.setShowMinimizeButton(false);
		panel.setIsModal(true);
		panel.setShowModalMask(true);
		panel.setShowShadow(true);
		panel.setShadowDepth(10);
		panel.setSmoothFade(true);
		panel.centerInPage();

		VLayout vLayout = new VLayout();
		Button button = new Button("teste");
		vLayout.addMember(button);

		panel.addItem(vLayout);

		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().onTestButtonCliked(event);
				}
			}
		});

		// panel.setModal(true);
		// VerticalPanel v = new VerticalPanel();
		// SimplePanel simplePanel = new SimplePanel();
		// simplePanel.add(new HTML("sdfdsf"));
		// Button b =new Button("Close", new ClickHandler() {
		//			
		// @Override
		// public void onClick(ClickEvent event) {
		// panel.hide();
		//				
		// }
		// });
		// v.add(simplePanel);
		// v.add(b);
		//
		// panel.setWidget(v);

	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	@Override
	public void show() {
		panel.show();
	}

}
