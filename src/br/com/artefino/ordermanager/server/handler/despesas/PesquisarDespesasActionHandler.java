package br.com.artefino.ordermanager.server.handler.despesas;

import java.util.ArrayList;
import java.util.List;

import br.com.artefino.ordermanager.server.businessobject.DespesaBO;
import br.com.artefino.ordermanager.server.entities.Despesa;
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

			List<Despesa> despesas = DespesaBO.pesquisarDespesas(action
					.getParametros());
			if (despesas != null) {
				List<DespesaVo> despesasVo = new ArrayList<DespesaVo>(
						despesas.size());

				for (Despesa despesa : despesas) {
					despesasVo.add(despesa.converterParaVo());

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
