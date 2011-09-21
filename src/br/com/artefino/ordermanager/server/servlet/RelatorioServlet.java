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
import br.com.artefino.ordermanager.server.businessobject.DespesaBO;
import br.com.artefino.ordermanager.server.businessobject.PedidoBO;
import br.com.artefino.ordermanager.server.entities.Despesa;
import br.com.artefino.ordermanager.server.entities.Pedido;
import br.com.artefino.ordermanager.server.entities.Pedidos;
import br.com.artefino.ordermanager.server.util.JPAUtil;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Singleton;

@Singleton
@SuppressWarnings("serial")
public class RelatorioServlet extends HttpServlet {

	// as per ReportsRecord
	private static final String REPORT_NAME = "report";
	private static final String DEFAULT_REPORTS_SERVICE_PATH = "/reports/";
	private static final String REPORT_PEDIDO = "pedido";
	private static final String REPORT_PEDIDOS = "pedidos";
	private static final String REPORT_DESPESAS = "despesas";

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
		} else if (reportName.equalsIgnoreCase(REPORT_DESPESAS)) {
			relatorioDespesas(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}

	}

	private void relatorioPedido(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("application/pdf");
		OutputStream servletOutputStream = response.getOutputStream();
		try {
			String resourceName = DEFAULT_REPORTS_SERVICE_PATH
					+ "ReciboPedido.jasper";
			InputStream reportStream = getServletConfig().getServletContext()
					.getResourceAsStream(resourceName);

			String pathImagens = getServletConfig().getServletContext()
					.getRealPath("/images") + "/";

			String pathSubreport = getServletConfig().getServletContext()
			.getRealPath("/reports") + "/";

			Long idPedido = Long.parseLong(request.getParameter("id"));
			Pedido pedido = (Pedido) JPAUtil.findByID(Pedido.class, idPedido);
			List<Pedido> pedidos = new ArrayList<Pedido>();
			pedidos.add(pedido);

			List<Pedidos>  pedidosList = new ArrayList<Pedidos>();
			Pedidos pedidosBean = new Pedidos();
			pedidosBean.setPedidos(pedidos);
			
			pedidosList.add(pedidosBean);

			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(
					pedidosList);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("IMAGES_DIR", pathImagens);
			map.put("SUBREPORT_DIR", pathSubreport);

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
		response.setContentType("application/pdf");
		OutputStream servletOutputStream = response.getOutputStream();
		try {
			String resourceName = DEFAULT_REPORTS_SERVICE_PATH
					+ "PedidosReport.jasper";
			InputStream reportStream = getServletConfig().getServletContext()
					.getResourceAsStream(resourceName);

			String pathImagens = getServletConfig().getServletContext()
					.getRealPath("/images/") + "/";

			String pathSubreport = getServletConfig().getServletContext()
			.getRealPath("/reports/") + "/";

			Map<String, Object> parametros = new HashMap<String, Object>();

			if (request.getParameter("idCliente") != null) {
				parametros.put("idCliente", Long.valueOf(request
						.getParameter("idCliente")));
			}

			if (request.getParameter("dataInicial") != null) {
				parametros.put("dataInicial", Long.valueOf(request
						.getParameter("dataInicial")));
			}
			if (request.getParameter("dataFinal") != null) {
				parametros.put("dataFinal", Long.valueOf(request
						.getParameter("dataFinal")));
			}
			if (request.getParameter("situacao") != null) {
				parametros.put("situacao", Long.valueOf(request
						.getParameter("situacao")));
			}

			List<Pedido> pedidos = PedidoBO.pesquisarPedidos(parametros);

			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(
					pedidos);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("IMAGES_DIR", pathImagens);
			map.put("SUBREPORT_DIR", pathSubreport);

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

	private void relatorioDespesas(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("application/pdf");
		OutputStream servletOutputStream = response.getOutputStream();
		try {
			String resourceName = DEFAULT_REPORTS_SERVICE_PATH
					+ "DespesasReport.jasper";
			InputStream reportStream = getServletConfig().getServletContext()
					.getResourceAsStream(resourceName);

			String pathImagens = getServletConfig().getServletContext()
					.getRealPath("/images/") + "/";

			String pathSubreport = getServletConfig().getServletContext()
			.getRealPath("/reports/") + "/";

			Map<String, Object> parametros = new HashMap<String, Object>();

			if (request.getParameter("idCategoria") != null) {
				parametros.put("idCategoria", Long.valueOf(request
						.getParameter("idCategoria")));
			}

			if (request.getParameter("dataInicial") != null) {
				parametros.put("dataInicial", Long.valueOf(request
						.getParameter("dataInicial")));
			}
			if (request.getParameter("dataFinal") != null) {
				parametros.put("dataFinal", Long.valueOf(request
						.getParameter("dataFinal")));
			}

			List<Despesa> pedidos = DespesaBO.pesquisarDespesas(parametros);

			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(
					pedidos);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("IMAGES_DIR", pathImagens);
			map.put("SUBREPORT_DIR", pathSubreport);



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
