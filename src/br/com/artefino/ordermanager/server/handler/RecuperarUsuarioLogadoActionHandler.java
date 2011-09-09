package br.com.artefino.ordermanager.server.handler;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.artefino.ordermanager.server.entities.Usuario;
import br.com.artefino.ordermanager.shared.action.RecuperarUsuarioLogadoAction;
import br.com.artefino.ordermanager.shared.action.RecuperarUsuarioLogadoResult;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

// don't forget to update bindHandler() in ServerModule

public class RecuperarUsuarioLogadoActionHandler
		implements
		ActionHandler<RecuperarUsuarioLogadoAction, RecuperarUsuarioLogadoResult> {

	private final Provider<HttpServletRequest> requestProvider;

	// private final ServletContext servletContext;

	@Inject
	RecuperarUsuarioLogadoActionHandler(final ServletContext servletContext,
			final Provider<HttpServletRequest> requestProvider) {
		this.requestProvider = requestProvider;
	}

	@Override
	public RecuperarUsuarioLogadoResult execute(
			final RecuperarUsuarioLogadoAction action,
			final ExecutionContext context) throws ActionException {

		RecuperarUsuarioLogadoResult result = null;

		try {

			HttpSession session = requestProvider.get().getSession();
			if (session.getAttribute("login.authenticated") != null) {
				Usuario usuario = (Usuario) session
						.getAttribute("login.authenticated");
				result = new RecuperarUsuarioLogadoResult(usuario
						.converterParaVo());

			}

		} catch (Exception e) {
			throw new ActionException(e);
		}

		return result;
	}

	@Override
	public Class<RecuperarUsuarioLogadoAction> getActionType() {
		return RecuperarUsuarioLogadoAction.class;
	}

	@Override
	public void undo(RecuperarUsuarioLogadoAction action,
			RecuperarUsuarioLogadoResult result, ExecutionContext context)
			throws ActionException {
	}
}
