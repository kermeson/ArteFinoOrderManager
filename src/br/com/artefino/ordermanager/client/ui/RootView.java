package br.com.artefino.ordermanager.client.ui;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.smartgwt.client.widgets.layout.VLayout;

public class RootView extends ViewImpl implements RootPresenter.MyView {

	private VLayout panel;

	public RootView() {

		panel = new VLayout();
		panel.setWidth100();
		panel.setHeight100();

	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		Log.debug("setInSlot()");

		if (slot == RootPresenter.TYPE_SetContextAreaContent) {
			if (content != null) {
				this.panel.setMembers((VLayout) content);
			}
		} else {
			super.setInSlot(slot, content);
		}
	}
}
