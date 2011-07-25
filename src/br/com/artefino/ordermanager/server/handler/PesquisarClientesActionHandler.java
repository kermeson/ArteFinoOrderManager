package br.com.artefino.ordermanager.server.handler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.artefino.ordermanager.server.entities.Cliente;
import br.com.artefino.ordermanager.server.util.JPAUtil;
import br.com.artefino.ordermanager.shared.action.clientes.PesquisarClientesAction;
import br.com.artefino.ordermanager.shared.action.clientes.PesquisarClientesResult;
import br.com.artefino.ordermanager.shared.vo.ClienteVo;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class PesquisarClientesActionHandler implements
		ActionHandler<PesquisarClientesAction, PesquisarClientesResult> {

	@Inject
	public PesquisarClientesActionHandler() {
	}

	@Override
	public PesquisarClientesResult execute(PesquisarClientesAction action,
			ExecutionContext context) throws ActionException {

		PesquisarClientesResult result = null;

		try {

			EntityManager em = JPAUtil.getEntityManager();
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Cliente> criteriaQuery = criteriaBuilder
					.createQuery(Cliente.class);
			Root<Cliente> root = criteriaQuery.from(Cliente.class);
			criteriaQuery.select(root);
			TypedQuery<Cliente> typedQuery = em.createQuery(criteriaQuery);
			List<Cliente> clientes = typedQuery.getResultList();

			if (clientes != null) {
				List<ClienteVo> clienteVos = new ArrayList<ClienteVo>(clientes
						.size());

				for (Cliente cliente : clientes) {
					clienteVos.add(createClienteVo(cliente));
				}

				result = new PesquisarClientesResult(clienteVos);
			}
		} catch (Exception e) {
			Log.warn("Erro ao pesquisar clientes", e);

			throw new ActionException(e);
		}

		return result;
	}

	@Override
	public void undo(PesquisarClientesAction action,
			PesquisarClientesResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<PesquisarClientesAction> getActionType() {
		return PesquisarClientesAction.class;
	}

	private ClienteVo createClienteVo(Cliente cliente) {
		ClienteVo clienteVo = new ClienteVo();
		clienteVo.setId(cliente.getId());
		clienteVo.setNome(cliente.getNome());
		clienteVo.setEndereco(cliente.getEndereco());
		clienteVo.setCnpjf(cliente.getCnpjf());
		clienteVo.setTipoPessoa(cliente.getTipoPessoa());
		return clienteVo;
		
	}
}
