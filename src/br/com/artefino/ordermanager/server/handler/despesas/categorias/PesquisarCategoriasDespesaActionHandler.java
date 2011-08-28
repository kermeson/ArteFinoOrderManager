package br.com.artefino.ordermanager.server.handler.despesas.categorias;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.artefino.ordermanager.server.entities.CategoriaDespesa;
import br.com.artefino.ordermanager.server.util.JPAUtil;
import br.com.artefino.ordermanager.shared.action.despesas.categorias.PesquisarCategoriasDespesaAction;
import br.com.artefino.ordermanager.shared.action.despesas.categorias.PesquisarCategoriasDespesaResult;
import br.com.artefino.ordermanager.shared.vo.CategoriaDespesaVo;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class PesquisarCategoriasDespesaActionHandler implements
		ActionHandler<PesquisarCategoriasDespesaAction, PesquisarCategoriasDespesaResult> {

	@Inject
	public PesquisarCategoriasDespesaActionHandler() {
	}

	@Override
	public PesquisarCategoriasDespesaResult execute(PesquisarCategoriasDespesaAction action,
			ExecutionContext context) throws ActionException {

		PesquisarCategoriasDespesaResult result = null;

		try {

			EntityManager em = JPAUtil.getEntityManager();
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<CategoriaDespesa> criteriaQuery = criteriaBuilder
					.createQuery(CategoriaDespesa.class);
			Root<CategoriaDespesa> root = criteriaQuery.from(CategoriaDespesa.class);
			criteriaQuery.select(root);
			TypedQuery<CategoriaDespesa> typedQuery = em.createQuery(criteriaQuery);
			List<CategoriaDespesa> categorias = typedQuery.getResultList();

			if (categorias != null) {
				List<CategoriaDespesaVo> categoriasVo = new ArrayList<CategoriaDespesaVo>(categorias
						.size());

				for (CategoriaDespesa categoria : categorias) {
					categoriasVo.add(categoria.converterParaVo());

				}

				result = new PesquisarCategoriasDespesaResult(categoriasVo);
			}
		} catch (Exception e) {
			Log.warn("Erro ao pesquisar categorias despesa", e);

			throw new ActionException(e);
		}

		return result;
	}

	@Override
	public void undo(PesquisarCategoriasDespesaAction action,
			PesquisarCategoriasDespesaResult result, ExecutionContext context)
			throws ActionException {
	}

	@Override
	public Class<PesquisarCategoriasDespesaAction> getActionType() {
		return PesquisarCategoriasDespesaAction.class;
	}

}
