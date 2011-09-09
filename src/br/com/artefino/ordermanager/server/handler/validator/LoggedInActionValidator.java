package br.com.artefino.ordermanager.server.handler.validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.artefino.ordermanager.server.entities.Usuario;
import br.com.artefino.ordermanager.shared.exception.SessionTimeOutException;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.gwtplatform.dispatch.server.actionvalidator.ActionValidator;
import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.ActionException;
import com.gwtplatform.dispatch.shared.Result;

public class LoggedInActionValidator implements ActionValidator {

	private final Provider<HttpServletRequest> requestProvider;

	@Inject
	LoggedInActionValidator(final Provider<HttpServletRequest> requestProvider) {
		this.requestProvider = requestProvider;
	}

	@Override
	@Singleton
	public boolean isValid(Action<? extends Result> action)
			throws ActionException {
		boolean result = true;

		Log.debug("LoggedIn Action Validator");

		HttpSession session = requestProvider.get().getSession();

		Usuario usuario = (Usuario) session.getAttribute("login.authenticated");

		if (usuario == null) {
			throw new SessionTimeOutException();
		}
	
		return result;
	}
}