package com.system.core.dao.base;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class BaseRepository<T> {
	@Autowired
	private EntityManager em;

	public EntityManager getEm() {
		return this.em;
	}

	public Long count(String queryString, Object[] params) {
		String newQueryString;
		if (queryString.toLowerCase().indexOf("count(") == -1)
			newQueryString = "select count(*) " + queryString;
		else {
			newQueryString = queryString;
		}
		Query query = this.em.createQuery(newQueryString);

		for (int i = 0; (params != null) && (i < params.length); i++) {
			query.setParameter(i + 1, params[i]);
		}
		return new Long(query.getSingleResult() + "");
	}
	@SuppressWarnings("unchecked")
	public List<T> find(String queryString, Object[] params, int startIndex,
			int num) {
		Query query = this.em.createQuery(queryString);
		for (int i = 0; (params != null) && (i < params.length); i++) {
			query.setParameter(i + 1, params[i]);
		}

		if (startIndex > 0) {
			query.setFirstResult(startIndex);
		}
		if (num > 0) {
			query.setMaxResults(num);
		}

		return query.getResultList();
	}

	public List<T> find(String queryString) {
		return find(queryString, null, -1, -1);
	}

	public List<T> find(String queryString, Object[] params) {
		return find(queryString, params, -1, -1);
	}
	@Transactional
	public int executeUpdate(String excuteSql, Object[] params) {
		Query query = this.em.createQuery(excuteSql);
		for (int i = 0; (params != null) && (i < params.length); i++) {
			query.setParameter(i + 1, params[i]);
		}
		return query.executeUpdate();
	}
}