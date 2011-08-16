package br.com.artefino.ordermanager.server.businessobject;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.artefino.ordermanager.server.entities.Usuario;
import br.com.artefino.ordermanager.server.util.JPAUtil;

import com.gwtplatform.dispatch.shared.ActionException;

public class UsuarioBO {

	public static Usuario retornarUsuario(String login) throws ActionException {

		try {
			EntityManager em = JPAUtil.getEntityManager();
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder
					.createQuery(Usuario.class);
			Root<Usuario> root = criteriaQuery.from(Usuario.class);
			criteriaQuery.select(root);
			criteriaQuery.where(criteriaBuilder.equal(
					root.get("login").as(String.class), login));

			TypedQuery<Usuario> typedQuery = em.createQuery(criteriaQuery);
			return typedQuery.getSingleResult();

		} catch (Exception e) {
			throw new ActionException(e);
		}
	}

	public static Long criarUsuario(Usuario usuario) throws ActionException {
		try {
			JPAUtil.save(usuario);
			return usuario.getId();
		} catch (Exception e) {
			throw new ActionException(e);
		}

	}

}
