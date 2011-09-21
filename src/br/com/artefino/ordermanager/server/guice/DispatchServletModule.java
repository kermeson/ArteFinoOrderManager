package br.com.artefino.ordermanager.server.guice;

import br.com.artefino.ordermanager.server.servlet.RelatorioServlet;

import com.google.inject.servlet.ServletModule;
import com.gwtplatform.dispatch.shared.ActionImpl;
import com.gwtplatform.dispatch.shared.SecurityCookie;
import com.gwtplatform.dispatch.server.guice.DispatchServiceImpl;
import com.gwtplatform.dispatch.server.guice.HttpSessionSecurityCookieFilter;

public class DispatchServletModule extends ServletModule {
	public static final String DEFAULT_SERVICE_PATH = "/";
	public static final String DEFAULT_REPORTS_SERVICE_PATH = "/reports/*";

	@Override
	public void configureServlets() {
		bindConstants();
		bindFilters();
		bindServlets();
	}

	protected void bindConstants() {
		// Protect against XSRF attacks
		bindConstant().annotatedWith(SecurityCookie.class).to("gwtSessionId");
	}

	protected void bindFilters() {
		// Protect against XSRF attacks
		filter("*").through( HttpSessionSecurityCookieFilter.class );
	}

	protected void bindServlets() {
		serve(DEFAULT_SERVICE_PATH + ActionImpl.DEFAULT_SERVICE_NAME).with(
				DispatchServiceImpl.class);


	    // This registers a servlet (subclass of HttpServlet) called ReportServlet
	    // to serve any web requests using a path-style syntax (as you would in web.xml).
	    serve(DEFAULT_REPORTS_SERVICE_PATH).with(RelatorioServlet.class);
	}
}
