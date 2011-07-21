/**
 * (C) Copyright 2010, 2011 upTick Pty Ltd
 *
 * Licensed under the terms of the GNU General Public License version 3
 * as published by the Free Software Foundation. You may obtain a copy of the
 * License at: http://www.gnu.org/copyleft/gpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package br.com.artefino.ordermanager.client.ui.widgets;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;

public class NavigationPaneHeader extends HLayout {

  private static final String WEST_WIDTH = "20%";
  private static final String NAVIGATION_PANE_HEADER_HEIGHT = "27px";

  private Label navigationPaneHeaderLabel;
  private Label contextAreaHeaderLabel;

  public NavigationPaneHeader() {
    super();

    // Log.debug("NavigationPaneHeader()");

    // initialise the Navigation Pane Header layout container
    this.setStyleName("crm-NavigationPane-Header");
    this.setHeight(NAVIGATION_PANE_HEADER_HEIGHT);

    // initialise the Navigation Pane Header Label
    navigationPaneHeaderLabel = new Label();
    navigationPaneHeaderLabel.setStyleName("crm-NavigationPane-Header-Label");
    navigationPaneHeaderLabel.setWidth(WEST_WIDTH);
    navigationPaneHeaderLabel.setContents("Workplace");
    navigationPaneHeaderLabel.setAlign(Alignment.LEFT);
    navigationPaneHeaderLabel.setOverflow(Overflow.HIDDEN);

    // initialise the Context Area Header Label
    contextAreaHeaderLabel = new Label();
    contextAreaHeaderLabel.setStyleName("crm-ContextArea-Header-Label");
    contextAreaHeaderLabel.setContents("actividades");
    contextAreaHeaderLabel.setAlign(Alignment.LEFT);
    contextAreaHeaderLabel.setOverflow(Overflow.HIDDEN);

    // add the Labels to the Navigation Pane Header layout container
    this.addMember(navigationPaneHeaderLabel);
    this.addMember(contextAreaHeaderLabel);
  }

  public Label getNavigationPaneHeaderLabel() {
    return navigationPaneHeaderLabel;
  }

  public Label getContextAreaHeaderLabel() {
    return contextAreaHeaderLabel;
  }

  public void setNavigationPaneHeaderLabelContents(String contents) {
    navigationPaneHeaderLabel.setContents(contents);
  }

  public String getNavigationPaneHeaderLabelContents() {
    return navigationPaneHeaderLabel.getContents();
  }

  public void setContextAreaHeaderLabelContents(String contents) {
    contextAreaHeaderLabel.setContents(contents);
  }

  public String getContextAreaHeaderLabelContents() {
    return contextAreaHeaderLabel.getContents();
  }
}
