package br.com.washcarprime.model.repository.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.washcarprime.model.entities.AbstractEntityBean;
import br.com.washcarprime.model.enums.TipoOrdemEnum;

@SuppressWarnings({ "unchecked", "serial" })
public class EntitiesRepository<T extends AbstractEntityBean> implements Serializable {

	public EntityManager entityManager;
	
	private Class<T> classe;
	

	public EntitiesRepository() {
		this.classe = getClassType();
	}

	private Class<T> getClassType() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	public void salvar(T t) { 
		this.entityManager.persist(t);
	}

	public void alterar(T t) {
		this.entityManager.merge(t);
	}

	public void excluir(T t) {
		this.entityManager.remove(entityManager.merge(t));
	}

	public T consultar(Long id) {
		return (T) entityManager.getReference(classe, id);
	}

	public List<T> listar() {
		return entityManager.createQuery(("From " + classe.getName())).getResultList();
	}
	
	public void excluirTodos(){
		for(T t: listar()){
			excluir(t);
		}
	}

	/**
	 * Metodo que retorna uma lista paginada
	 * 
	 * @param inicio
	 *            - primeiro elemento da lista
	 * @param maxPorPagina
	 *            - total de registros da lista
	 * @param campoOrdenar
	 *            - campo base da ordenacao
	 * @param tipoOrdem
	 *            - crescente (ASC) ou decrescente (DESC)
	 * @param filtros
	 *            - criterios para filtro de registros
	 * @return lista paginada
	 */

	public List<T> listar(int inicio, int maxPorPagina, String campoOrdenar, TipoOrdemEnum tipoOrdem, String campoFiltro, String valorFiltro ) {
		String sql = "select c from " + classe.getName() + " c ";

		if (campoOrdenar == null || campoOrdenar.equals("")) {
			campoOrdenar = campoFiltro;
		}

		sql += " where UPPER(c." + campoFiltro + ") like UPPER(:" + campoFiltro
				+ ")" + " order by c." + campoOrdenar + " "
				+ tipoOrdem.getNome();

		Query query = entityManager.createQuery(sql);
		query.setParameter(campoFiltro, valorFiltro);
		query.setFirstResult(inicio);
		query.setMaxResults(maxPorPagina);
		return query.getResultList();
	}

	public EntityManager getEm() {
		return entityManager;
	}

	public Long getTotalRegistros(String campoFiltro, String valorFiltro) {
		String sql = "select COUNT(c) from " + classe.getName() + " c ";
		sql += " where UPPER(c." + campoFiltro + ") like UPPER(:" + campoFiltro+ ")";
		Query query = entityManager.createQuery(sql);
		query.setParameter(campoFiltro, valorFiltro);
		Number result = (Number) query.getSingleResult();
		return result.longValue();
	}

	
	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	

}
