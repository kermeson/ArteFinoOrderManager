package br.com.artefino.ordermanager.server.handler.despesas;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.artefino.ordermanager.server.entities.Despesa;
import br.com.artefino.ordermanager.server.util.JPAUtil;
import br.com.artefino.ordermanager.shared.action.despesas.PesquisarDespesasAction;
import br.com.artefino.ordermanager.shared.action.despesas.PesquisarDespesasResult;
import br.com.artefino.ordermanager.shared.vo.DespesaVo;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class PesquisarDespesasActionHandler implements
		ActionHandler<PesquisarDespesasAction, PesquisarDespesasResult> {

	@Inject
	public PesquisarDespesasActionHandler() {
	}

	@Override
	public PesquisarDespesasResult execute(PesquisarDespesasAction action,
			ExecutionContext context) throws ActionException {

		PesquisarDespesasResult result = null;

		try {

			EntityManager em = JPAUtil.getEntityManager();
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Despesa> criteriaQuery = criteriaBuilder
					.createQuery(Despesa.class);
			Root<Despesa> root = criteriaQuery.from(Despesa.class);
			criteriaQuery.select(root);
			TypedQuery<Despesa> typedQuery = em.createQuery(criteriaQuery);
			List<Despesa> despesas = typedQuery.getResultList();

			if (despesas != null) {
				List<DespesaVo> despesasVo = new ArrayList<DespesaVo>(despesas
						.size());

				for (Despesa cliente : despesas) {
					despesasVo.add(cliente.converterParaVo());

				}

				result = new PesquisarDespesasResult(despesasVo);
			}
		} catch (Exception e) {
			Log.warn("Erro ao pesquisar despesas", e);

			throw new ActionException(e);
		}

		return result;
	}

	@Override
	public void undo(PesquisarDespesasAction action,
			PesquisarDespesasResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<PesquisarDespesasAction> getActionType() {
		return PesquisarDespesasAction.class;
	}

}
