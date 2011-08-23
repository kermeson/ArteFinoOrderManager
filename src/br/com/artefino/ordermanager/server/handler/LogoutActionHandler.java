package br.com.artefino.ordermanager.server.handler;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.artefino.ordermanager.shared.action.LogoutAction;
import br.com.artefino.ordermanager.shared.action.LogoutResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

// don't forget to update bindHandler() in ServerModule

public class LogoutActionHandler implements
		ActionHandler<LogoutAction, LogoutResult> {

	private final Provider<HttpServletRequest> requestProvider;

	// private final ServletContext servletContext;

	@Inject
	LogoutActionHandler(final ServletContext servletContext,
			final Provider<HttpServletRequest> requestProvider) {
		this.requestProvider = requestProvider;
		// this.servletContext = servletContext;
	}

	@Override
	public LogoutResult execute(final LogoutAction action,
			final ExecutionContext context) throws ActionException {

		LogoutResult result = null;

		try {

			HttpSession session = requestProvider.get().getSession();
			session.invalidate();

		} catch (Exception e) {
			throw new ActionException(e);
		}

		return result;
	}

	@Override
	public Class<LogoutAction> getActionType() {
		return LogoutAction.class;
	}

	@Override
	public void undo(LogoutAction action, LogoutResult result,
			ExecutionContext context) throws ActionException {
	}
}
