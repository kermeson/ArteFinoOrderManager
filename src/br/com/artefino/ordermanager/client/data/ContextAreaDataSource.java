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

public class ContextAreaDataSource extends DataSource {
  
  public static final String COLUMN1_ICON = "column1Icon";
  public static final String COLUMN1_ICON_DISPLAY_NAME = "Column 1 Icon";
  public static final String COLUMN1_NAME = "column1Name";
  public static final String COLUMN1_NAME_DISPLAY_NAME = "Column 1 Name";
  public static final String COLUMN1_DISPLAY_NAME = "column1DisplayName";
  public static final String COLUMN1_DISPLAY_NAME_DISPLAY_NAME = "Column 1 Display Name";
  public static final String COLUMN1_DESCRIPTION = "column1Description";
  public static final String COLUMN1_DESCRIPTION_DISPLAY_NAME = "Column 1 Description";
  
  public static final String COLUMN2_ICON = "column2Icon";
  public static final String COLUMN2_ICON_DISPLAY_NAME = "Column 2 Icon";
  public static final String COLUMN2_NAME = "column2Name";
  public static final String COLUMN2_NAME_DISPLAY_NAME = "Column 2 Name";
  public static final String COLUMN2_DISPLAY_NAME = "column2DisplayName";
  public static final String COLUMN2_DISPLAY_NAME_DISPLAY_NAME = "Column 2 Display Name";
  public static final String COLUMN2_DESCRIPTION = "column2Description";
  public static final String COLUMN2_DESCRIPTION_DISPLAY_NAME = "Column 2 Description";

  private static final String RECORD_XPATH = "/list/record";
  
  public ContextAreaDataSource(String id) {  
    
    setID(id);  
    setDataFormat(DSDataFormat.XML);  
    setRecordXPath(RECORD_XPATH);  
    
    DataSourceField column1IconField = new DataSourceField(COLUMN1_ICON, FieldType.TEXT,
        COLUMN1_ICON_DISPLAY_NAME);  
    DataSourceField column1NameField = new DataSourceField(COLUMN1_NAME, FieldType.TEXT,
        COLUMN1_NAME_DISPLAY_NAME);  
    DataSourceField column1DisplayNameField = new DataSourceField(COLUMN1_DISPLAY_NAME, FieldType.TEXT,
        COLUMN1_DISPLAY_NAME_DISPLAY_NAME); 
    DataSourceField column1DescriptionField = new DataSourceField(COLUMN1_DESCRIPTION, FieldType.TEXT,
        COLUMN1_DESCRIPTION_DISPLAY_NAME); 
    
    DataSourceField column2IconField = new DataSourceField(COLUMN2_ICON, FieldType.TEXT,
        COLUMN2_ICON_DISPLAY_NAME);  
    DataSourceField column2NameField = new DataSourceField(COLUMN2_NAME, FieldType.TEXT,
        COLUMN2_NAME_DISPLAY_NAME);  
    DataSourceField column2DisplayNameField = new DataSourceField(COLUMN2_DISPLAY_NAME, FieldType.TEXT,
        COLUMN2_DISPLAY_NAME_DISPLAY_NAME); 
    DataSourceField column2DescriptionField = new DataSourceField(COLUMN2_DESCRIPTION, FieldType.TEXT,
        COLUMN2_DESCRIPTION_DISPLAY_NAME);     
    
    setFields(column1IconField, column1NameField, column1DisplayNameField, column1DescriptionField,
        column2IconField, column2NameField, column2DisplayNameField, column2DescriptionField);  
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
