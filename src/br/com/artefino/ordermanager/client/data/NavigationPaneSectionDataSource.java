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

package br.com.artefino.ordermanager.client.data;

import com.google.gwt.i18n.client.LocaleInfo;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.FieldType;

public abstract class NavigationPaneSectionDataSource extends DataSource {

  public static final String ICON = "icon";
  public static final String ICON_DISPLAY_NAME = "Icon";
  public static final String NAME = "name";
  public static final String NAME_DISPLAY_NAME = "Name";
  public static final String DISPLAY_NAME = "displayName";
  public static final String DISPLAY_NAME_DISPLAY_NAME = "Display Name";

  private static final String RECORD_XPATH = "/list/record";

  public NavigationPaneSectionDataSource(String id) {
    setID(id);
    setDataFormat(DSDataFormat.XML);
    setRecordXPath(RECORD_XPATH);
    DataSourceField iconField = new DataSourceField(ICON, FieldType.TEXT, ICON_DISPLAY_NAME);
    DataSourceField nameField = new DataSourceField(NAME, FieldType.TEXT, NAME_DISPLAY_NAME);
    DataSourceField displayNameField = new DataSourceField(DISPLAY_NAME, FieldType.TEXT, DISPLAY_NAME_DISPLAY_NAME);
    setFields(iconField, nameField, displayNameField);
  }

  public void setDataURL(String urlPrefix, String urlSuffix) {
    String url = urlPrefix;
    LocaleInfo localeInfo = LocaleInfo.getCurrentLocale();
    String localeName = localeInfo.getLocaleName();

    if (localeName.length() > 0) {
      url = url + "_" + localeName;
    }

    url = url + urlSuffix;

    // Log.debug("setDataURL: " + url);

    setDataURL(url);
  }
}
