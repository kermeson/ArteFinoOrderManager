package br.com.artefino.ordermanager.server.handler;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.artefino.ordermanager.server.businessobject.UsuarioBO;
import br.com.artefino.ordermanager.server.entities.Usuario;
import br.com.artefino.ordermanager.server.util.Security;
import br.com.artefino.ordermanager.shared.action.LoginAction;
import br.com.artefino.ordermanager.shared.action.LoginResult;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

// don't forget to update bindHandler() in ServerModule

public class LoginActionHandler implements ActionHandler<LoginAction, LoginResult> {

  private final Provider<HttpServletRequest> requestProvider;
  // private final ServletContext servletContext;

  @Inject
  LoginActionHandler(final ServletContext servletContext,
      final Provider<HttpServletRequest> requestProvider) {
    this.requestProvider = requestProvider;
    // this.servletContext = servletContext;
  }

  @Override
  public LoginResult execute(final LoginAction action,
      final ExecutionContext context) throws ActionException {

    LoginResult result = null;

    // Log.debug("LoginHandler - login: " + action.getLogin() + ", password: " + action.getPassword());

    try {
      Usuario user = UsuarioBO.retornarUsuario(action.getLogin());

      if (user != null && isValidLogin(action, user)) {
        Log.debug(action.getLogin() + " has logged in");

        HttpSession session = requestProvider.get().getSession();
        session.setAttribute("login.authenticated", action.getLogin());

        result = new LoginResult(session.getId());
      }
      else {
        // GWTP only includes the exception type and it's message?
        // Looks like it needs only a small change on the DispatchServiceImpl class,
        // on the execute() method:
        // Instead of calling logger.warning(message), use logger.log(level, message, throwable).
        throw new LoginException("Invalid User name or Password.");
      }
    }
    catch (Exception e) {
      throw new ActionException(e);
    }

    return result;
  }

  private Boolean isValidLogin(LoginAction action, Usuario user) {
    String hash = Security.sha256(user.getSalt() + action.getPassword());

    return hash.equals(user.getSenha());
  }

  @Override
  public Class<LoginAction> getActionType() {
    return LoginAction.class;
  }

  @Override
  public void undo(LoginAction action, LoginResult result,
      ExecutionContext context) throws ActionException {
  }
}

