package br.com.artefino.ordermanager.server.handler.validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.gwtplatform.dispatch.server.actionvalidator.ActionValidator;
import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.Result;

public class LoggedInActionValidator implements ActionValidator  {

  private final Provider<HttpServletRequest> requestProvider;

  @Inject
  LoggedInActionValidator(final Provider<HttpServletRequest> requestProvider) {
    this.requestProvider = requestProvider;
  }

  @Override
  @Singleton
  public boolean isValid(Action<? extends Result> action) {
    boolean result = true;

    Log.debug("LoggedIn Action Validator");

    HttpSession session = requestProvider.get().getSession();

    Object authenticated = session.getAttribute("login.authenticated");

    if (authenticated == null) {
      Log.debug("The User has not logged in.");
      result = false;
    }

    return result;
  }
}