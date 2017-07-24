package com.ars.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface CommonDao<T> {

	void save(T entity);
	void update(T entity);
	T findObjectByID(Serializable id);
	public T findObjectBySno(Serializable sno);
	void deleteObjectByIds(Serializable... ids);
	void deleteObjectByCollection(List<T> list);
	List<T> findCollectionByConditionNoPage(String condition,Object[] params, Map<String, String> orderby);
	List<T> findListWithPage(String condition, Object[] params, Map<String,String> map, int page, int rows);

	int getMemberCounts();

	List findCollectionByConditionNoPageWithSelectCondition(String condition, Object[] params,
			Map<String, String> orderby, String selectCondition);
	}
