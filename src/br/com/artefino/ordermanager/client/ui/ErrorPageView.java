package br.com.artefino.ordermanager.client.ui;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

public class ErrorPageView extends ViewWithUiHandlers<ErrorPageUiHandlers> implements 
    ErrorPagePresenter.MyView {

  private static String html = "<div>\n"
    + "<table align=\"center\">\n"
    + "  <tr>\n" + "<td>&nbsp;</td>\n" + "</tr>\n"
    + "  <tr>\n" + "<td>&nbsp;</td>\n" + "</tr>\n"
    + "  <tr>\n" + "<td>&nbsp;</td>\n" + "</tr>\n"
    + "  <tr>\n"    
    + "    <td style=\"font-weight:bold;\">An error has occurred.</td>\n"
    + "  </tr>\n"
    + "  <tr>\n" + "<td>&nbsp;</td>\n" + "</tr>\n"
    + "  <tr>\n" + "<td>Try this action again. If the problem continues,</td>\n" + "</tr>\n"
    + "  <tr>\n" + "<td>check the Serendipity forums for a solution or</td>\n" + "</tr>\n"
    + "  <tr>\n" + "<td>contact your Serendipity administrator.</td>\n" + "</tr>\n"
    + "  <tr>\n" + "<td>&nbsp;</td>\n" + "</tr>\n"    
    + "  <tr>\n"
    + "    <td id=\"okButtonContainer\"></td>\n"  
    + "  </tr>\n"     
    + "</table>\n"
    + "</div>\n";

  HTMLPanel panel = new HTMLPanel(html);

  private final Button okButton;

  @Inject
  public ErrorPageView() {
    okButton = new Button("OK");

    panel.add(okButton, "okButtonContainer");
    
    bindCustomUiHandlers();
  }
  
  protected void bindCustomUiHandlers() {

    okButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        if (getUiHandlers() != null) {
          getUiHandlers().onOkButtonClicked();
        }
      }
    });
  }  

  @Override
  public Widget asWidget() {
    return panel;
  }
}
