package br.com.artefino.ordermanager.server.guice;

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
	}
}
