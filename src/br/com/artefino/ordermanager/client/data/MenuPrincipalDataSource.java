package br.com.artefino.ordermanager.client.data;

import br.com.artefino.ordermanager.client.place.NameTokens;

public class MenuPrincipalDataSource extends NavigationPaneSectionDataSource {

  // the name of the default ListGrid item to select
  // see NavigationPaneSectionListGrid -> onDataArrived()
  // see MainPageView -> initNavigationPane()
  public static final String DEFAULT_RECORD_NAME = NameTokens.main;

  private static final String DATA_SOURCE = "MenuPrincipal";
  private static final String URL_PREFIX = "datasource/data/MenuPrincipal";
  private static final String URL_SUFFIX = ".xml";

  private static MenuPrincipalDataSource instance;

  public static MenuPrincipalDataSource getInstance() {
    if (instance == null) {
      instance = new MenuPrincipalDataSource(DATA_SOURCE);
    }

    return instance;
  }

  public MenuPrincipalDataSource(String id) {
    super(id);

    setDataURL(URL_PREFIX, URL_SUFFIX);
  }
}
