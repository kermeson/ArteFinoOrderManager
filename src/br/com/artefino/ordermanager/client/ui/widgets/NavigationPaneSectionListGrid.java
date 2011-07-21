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

import br.com.artefino.ordermanager.client.data.NavigationPaneSectionDataSource;

import com.allen_sauer.gwt.log.client.Log;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.DataArrivedEvent;
import com.smartgwt.client.widgets.grid.events.DataArrivedHandler;

public class NavigationPaneSectionListGrid extends ListGrid {

  private static final String URL_PREFIX = "icons/16/";
  private static final String URL_SUFFIX = ".png";

  private static final int ICON_FIELD_WIDTH = 27;

  public NavigationPaneSectionListGrid(DataSource dataSource) {
    super();

    // Log.debug("NavigationPaneSectionListGrid");

    // initialise the List Grid
    this.setBaseStyle("crm-NavigationPaneGridCell");
    this.setDataSource(dataSource);
    this.setWidth100();
    this.setHeight100();
    this.setShowAllRecords(true);
    this.setShowHeader(false);

    // initialise the Icon field
    ListGridField appIconField = new ListGridField(NavigationPaneSectionDataSource.ICON,
        NavigationPaneSectionDataSource.ICON_DISPLAY_NAME, ICON_FIELD_WIDTH);
    appIconField.setImageSize(16);
    appIconField.setAlign(Alignment.RIGHT);
    appIconField.setType(ListGridFieldType.IMAGE);
    appIconField.setImageURLPrefix(URL_PREFIX);
    appIconField.setImageURLSuffix(URL_SUFFIX);
    appIconField.setCanEdit(false);

    // initialise the Display Name field
    ListGridField appDisplayNameField = new ListGridField(NavigationPaneSectionDataSource.DISPLAY_NAME,
        NavigationPaneSectionDataSource.DISPLAY_NAME_DISPLAY_NAME);

    // set the fields into the List Grid
    this.setFields(new ListGridField[] {appIconField, appDisplayNameField});

    // register the Data Arrived Handler
    this.addDataArrivedHandler(new DataArrivedHandler() {
      @Override
      public void onDataArrived(DataArrivedEvent event) {
        Log.debug("onDataArrived()");
        selectRecord(0);
      }
    });

    // populate the List Grid
    this.setAutoFetchData(true);
  }
}
