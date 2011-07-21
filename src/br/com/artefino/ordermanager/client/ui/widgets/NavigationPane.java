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

import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.events.SectionHeaderClickHandler;

import com.allen_sauer.gwt.log.client.Log;

public class NavigationPane extends VLayout {

  private static final String WEST_WIDTH = "20%";
  private static final String SECTION_STACK_WIDTH = "100%";

  // private static final int HEADER_HEIGHT = 31;

  private SectionStack sectionStack;

  public NavigationPane() {
    super();

    // initialise the Navigation Pane layout container
    this.setStyleName("crm-NavigationPane");
    this.setWidth(WEST_WIDTH);
    // this.setShowResizeBar(true);

    // initialise the Section Stack
    sectionStack = new SectionStack();
    sectionStack.setWidth(SECTION_STACK_WIDTH);
    sectionStack.setVisibilityMode(VisibilityMode.MUTEX);
    sectionStack.setShowExpandControls(true);
    sectionStack.setAnimateSections(true);

    // sectionStack.setHeaderHeight(HEADER_HEIGHT);
    // can only set this in a derived class

    // add the Section Stack to the Navigation Pane layout container
    this.addMember(sectionStack);
  }

  public void addSection(String sectionName, DataSource dataSource) {
    sectionStack.addSection(new NavigationPaneSection(sectionName, dataSource));
  }

  public void expandSection(int section) {
    sectionStack.expandSection(section);
  }

  public void expandSection(String name) {
    sectionStack.expandSection(name);
  }

  public void selectRecord(String name) {

    Log.debug("selectRecord(place) - " + name);

    SectionStackSection[] sections = sectionStack.getSections();

    // Log.debug("Number of sections: " + sections.length);

    for (int i = 0; i < sections.length; i++) {
      SectionStackSection sectionStackSection = sections[i];

      if (((NavigationPaneSection) sectionStackSection).getRecord(name) != -1) {

        if (!sectionStack.sectionIsExpanded(i)) {
          Log.debug("sectionStack.expandSection(i)");
          sectionStack.expandSection(i);
        }

        ((NavigationPaneSection) sectionStackSection).selectRecord(name);
        break;
      }
    }
  }

  public void addSectionHeaderClickHandler(SectionHeaderClickHandler clickHandler) {
    sectionStack.addSectionHeaderClickHandler(clickHandler);
  }

  public void addRecordClickHandler(String sectionName, RecordClickHandler clickHandler) {

    // Log.debug("addRecordClickHandler(sectionName, clickHandler) - " + sectionName);

    SectionStackSection[] sections = sectionStack.getSections();

    for (int i = 0; i < sections.length; i++) {
      SectionStackSection sectionStackSection = sections[i];

      if (sectionName.contentEquals(sections[i].getTitle())) {
        ((NavigationPaneSection) sectionStackSection).addRecordClickHandler(clickHandler);
      }
    }
  }
}
