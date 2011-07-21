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

import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuBar;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuItemSeparator;
import com.smartgwt.client.widgets.menu.events.ClickHandler;

/**
 * @author Rob Ferguson
 *
 */
public class ApplicationMenu extends HLayout {

  private static final int APPLICATION_MENU_HEIGHT = 27;
  private static final int DEFAULT_SHADOW_DEPTH = 10;

  private static final String SEPARATOR = "separator";
  private static final String ICON_PREFIX = "icons/16/";
  private static final String ICON_SUFFIX = ".png";

  private MenuBar menuBar;
  private int menuPosition; // = 0

  public ApplicationMenu() {
    super();

    // Log.debug("ApplicationMenu()");

    // initialise the Application Menu layout container
    this.setStyleName("applicationMenu");
    this.setHeight(APPLICATION_MENU_HEIGHT);

    // initialise the Menu Bar
    menuBar = new MenuBar();

    // add the Menu Bar to the Application Menu layout container
    this.addMember(menuBar);
  }

//  public void addClickHandler(String menuName, ClickHandler clickHandler) {
//
//    Log.debug("addClickHandler(menuName, clickHandler) - " + menuName);
//
//    Canvas[] members = menuBar.getMembers();
//
//    for (int i = 0; i < members.length; i++) {
//
//      Log.debug("addClickHandler() - " + members[i].getTitle());
//
//      // if (menuName.contentEquals(members[i].getTitle())) {
//      // }
//    }
//  }

  public Menu addMenu(String menuName, int width, String menuItemNames,
      ClickHandler clickHandler) {

    // initialise the new menu
    Menu menu = new Menu();
    menu.setTitle(menuName);
    menu.setShowShadow(true);
    menu.setShadowDepth(DEFAULT_SHADOW_DEPTH);
    menu.setWidth(width);
    // menu.setAutoWidth();
    // menu.setOverflow(Overflow.VISIBLE);

    // create an array of menu item names
    String[] menuItems = process(menuItemNames);

    for (int i = 0; i < menuItems.length; i++) {
      // remove any whitespace
      String menuItemName = menuItems[i].replaceAll("\\W", "");

      if (menuItemName.contentEquals(SEPARATOR)) {
        MenuItemSeparator separator = new MenuItemSeparator();
        menu.addItem(separator);
        continue;
      }

      MenuItem menuItem = new MenuItem(menuItems[i], getIcon(menuItems[i]));
      if (clickHandler != null) {
        menuItem.addClickHandler(clickHandler);
      }

      menu.addItem(menuItem);
    }

    Menu[] menus = new Menu[1];
    menus[0] = menu;
    menuBar.addMenus(menus, menuPosition);
    menuPosition++;

    return menus[0];
  }

  public Menu addMenu(String menuName, int width) {

    // initialise the new menu
    Menu menu = new Menu();
    menu.setTitle(menuName);
    menu.setShowShadow(true);
    menu.setShadowDepth(DEFAULT_SHADOW_DEPTH);
    menu.setWidth(width);
    // menu.setAutoWidth();
    // menu.setOverflow(Overflow.VISIBLE);

    Menu[] menus = new Menu[1];
    menus[0] = menu;
    menuBar.addMenus(menus, menuPosition);
    menuPosition++;

    return menu;
  }

  public Menu addSubMenu(Menu parentMenu, String subMenuName, String menuItemNames,
      ClickHandler clickHandler) {

    // initialise the new sub menu
    Menu menu = new Menu();
    menu.setShowShadow(true);
    menu.setShadowDepth(DEFAULT_SHADOW_DEPTH);

    MenuItem menuItem = new MenuItem(subMenuName);

    // create an array of menu item names
    String[] menuItems = process(menuItemNames);

    for (int i = 0; i < menuItems.length; i++) {
      // remove any whitespace
      String menuItemName = menuItems[i].replaceAll("\\W", "");

      if (menuItemName.contentEquals(SEPARATOR)) {
        MenuItemSeparator separator = new MenuItemSeparator();
        menu.addItem(separator);
        continue;
      }

      menuItem = new MenuItem(menuItems[i], getIcon(menuItems[i]));
      if (clickHandler != null) {
        menuItem.addClickHandler(clickHandler);
      }

      menu.addItem(menuItem);
    }

    // add the sub menu to the menu item
    menuItem = new MenuItem(subMenuName);
    // we want a 'blue' submenu '>' icon
    // menuItem.setAttribute("icon", SUBMENU_ICON);
    parentMenu.addItem(menuItem);
    menuItem.setSubmenu(menu);

    return menu;
  }

  public static final String DELIMITER = ",";

  public static String[] process(String line) {
    return line.split(DELIMITER);
  }

  private String getIcon(String applicationName) {
    // remove any whitespace
    String name = applicationName.replaceAll("\\W", "");
    // e.g. "icons/16/" + "activities" + ".png"
    String icon = ICON_PREFIX + name.toLowerCase() + ICON_SUFFIX;
    return icon;
  }
}
