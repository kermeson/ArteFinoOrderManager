package br.com.artefino.ordermanager.server.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.artefino.ordermanager.server.bussinessobject.PedidoBO;
import br.com.artefino.ordermanager.server.entities.Pedido;
import br.com.artefino.ordermanager.server.util.JPAUtil;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Singleton;

@Singleton
@SuppressWarnings("serial")
public class ReportServlet extends HttpServlet {

	// as per ReportsRecord
	private static final String REPORT_NAME = "report";
	private static final String DEFAULT_REPORTS_SERVICE_PATH = "/reports/";
	private static final String REPORT_PEDIDO = "pedido";
	private static final String REPORT_PEDIDOS = "pedidos";

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.processRequest(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Log.info("Report Servlet");
		String reportName = request.getParameter(REPORT_NAME);

		if (reportName.equalsIgnoreCase(REPORT_PEDIDO)) {
			relatorioPedido(request, response);

		} else if (reportName.equalsIgnoreCase(REPORT_PEDIDOS)) {
			relatorioPedidos(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}

	}

	private void relatorioPedido(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String resourceName = DEFAULT_REPORTS_SERVICE_PATH
				+ "PedidoReport.jasper";
		InputStream reportStream = getServletConfig().getServletContext()
				.getResourceAsStream(resourceName);

		String pathImagens = getServletConfig().getServletContext()
		.getRealPath("/images");

		response.setContentType("application/pdf");
		OutputStream servletOutputStream = response.getOutputStream();
		try {
			Long idPedido = Long.parseLong(request.getParameter("id"));
			Pedido pedido = (Pedido) JPAUtil.findByID(Pedido.class, idPedido);
			List<Pedido> pedidos = new ArrayList<Pedido>();
			pedidos.add(pedido);

			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(
					pedidos);

			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("IMAGES_DIR", pathImagens);
			map.put("SUBREPORT_DIR", "reports/");

			JasperRunManager.runReportToPdfStream(reportStream,
					servletOutputStream, map, ds);
		} catch (Exception e) {
			// display stack trace in the browser
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			e.printStackTrace(printWriter);
			response.setContentType("text/plain");
			response.getOutputStream().print(stringWriter.toString());
		} finally {
			servletOutputStream.flush();
			servletOutputStream.close();
		}

	}

	private void relatorioPedidos(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String resourceName = DEFAULT_REPORTS_SERVICE_PATH
				+ "PedidosReport.jasper";
		InputStream reportStream = getServletConfig().getServletContext()
				.getResourceAsStream(resourceName);

		String pathImagens = getServletConfig().getServletContext()
		.getRealPath("/images");

		response.setContentType("application/pdf");
		OutputStream servletOutputStream = response.getOutputStream();
		try {
			List<Pedido> pedidos = PedidoBO.pesquisarPedido(null);

			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(
					pedidos);

			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("IMAGES_DIR", pathImagens);
			map.put("SUBREPORT_DIR", "reports/");

			JasperRunManager.runReportToPdfStream(reportStream,
					servletOutputStream, map, ds);
		} catch (Exception e) {
			// display stack trace in the browser
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			e.printStackTrace(printWriter);
			response.setContentType("text/plain");
			response.getOutputStream().print(stringWriter.toString());
		} finally {
			servletOutputStream.flush();
			servletOutputStream.close();
		}

	}
}

