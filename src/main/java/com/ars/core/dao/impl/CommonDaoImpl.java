package com.ars.core.dao.impl;



import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.ars.core.dao.CommonDao;
import com.ars.core.utils.TUtil;
import com.ars.core.utils.TabelNameUtil;



public class CommonDaoImpl<T> extends HibernateDaoSupport implements CommonDao<T> {
	
	/**T型转换*/
	Class entityClass = TUtil.getActualType(this.getClass());
	String tableName=TabelNameUtil.getTableName(entityClass);
	@Autowired
	public void setSessionFactory0(SessionFactory sessionFactory){  
	super.setSessionFactory(sessionFactory);  
	}
	/**保存*/
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}
	
	/**更新*/
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}
	
	/**使用主键ID，查询对象*/
	public T findObjectByID(Serializable id) {
		return (T) this.getHibernateTemplate().get(entityClass, id);
	}
	
	public T findObjectBySno(Serializable sno) {
		return (T) this.getHibernateTemplate().get(entityClass, sno);
	}
	
	/**删除（使用主键ID删除）*/
	public void deleteObjectByIds(Serializable... ids) {
		if(ids!=null && ids.length>0){
			for(Serializable id:ids){
				//先查询
				Object entity = this.findObjectBySno(id);
				//再删除
				this.getHibernateTemplate().delete(entity);
			}
		}
	}
	
	/**删除（使用集合List进行删除）*/
	public void deleteObjectByCollection(List<T> list) {
		this.getHibernateTemplate().deleteAll(list);
	}
	
	/**指定查询条件，查询列表*/
	/**
	 * SELECT * FROM elec_text o WHERE 1=1     #Dao层
		AND o.textName LIKE '%张%'   #Service层
		AND 
		ORDER BY o.textDate ASC,o.textName DESC  #Service层
	 */
	public List<T> findCollectionByConditionNoPage(String condition,
			final Object[] params, Map<String, String> orderby) {
		//hql语句
		String hql = "from "+tableName+" o where 1=1 ";
		//将Map集合中存放的字段排序，组织成ORDER BY o.textDate ASC,o.textName DESC
		String orderbyCondition = this.orderbyHql(orderby);
		//添加查询条件
		final String finalHql = hql + condition + orderbyCondition;
		//查询，执行hql语句  方案一和方案三一样，底层都是同样的做的做法
		/**方案一*/
		//List<T> list = (List<T>) this.getHibernateTemplate().find(finalHql, params);
		/**方案二*/
//		SessionFactory sf = this.getHibernateTemplate().getSessionFactory();
//		Session s = sf.getCurrentSession();//Session与线程绑定
//		Query query = s.createQuery(finalHql);
//		if(params!=null && params.length>0){
//			for(int i=0;i<params.length;i++){
//				query.setParameter(i, params[i]);
//			}
//		}
//		List<T> list = query.list();
		/**方案三*/
		List list = this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(finalHql);
				if(params!=null && params.length>0){
					for(int i=0;i<params.length;i++){
						query.setParameter(i, params[i]);
					}
				}
				return query.list();
			}
			
		});
		return list;
	}

	/**将Map集合中存放的字段排序，组织成
	 * ORDER BY o.textDate ASC,o.textName DESC*/
	private String orderbyHql(Map<String, String> orderby) {
		StringBuffer buffer = new StringBuffer("");
		if(orderby!=null && orderby.size()>0){
			buffer.append(" ORDER BY ");
			for(Map.Entry<String, String> map:orderby.entrySet()){
				buffer.append(map.getKey()+" "+map.getValue()+",");
			}
			//在循环后，删除最后一个逗号
			buffer.deleteCharAt(buffer.length()-1);
		}
		return buffer.toString();
	}

	@Override
	public List<T> findListWithPage(String condition, final Object[] params, Map<String,String>orderby, final int page, final int rows) {
		String hql = "from "+tableName+" o where 1=1 ";
		//将Map集合中存放的字段排序，组织成ORDER BY o.textDate ASC,o.textName DESC
		String orderbyCondition = this.orderbyHql(orderby);
		//添加查询条件
		final String finalHql = hql + condition + orderbyCondition;
		//查询，执行hql语句
		/**方案一*/
		List<T> list = (List<T>) this.getHibernateTemplate().find(finalHql, params);
		/**方案二*/
//		SessionFactory sf = this.getHibernateTemplate().getSessionFactory();
//		Session s = sf.getCurrentSession();//Session与线程绑定
//		Query query = s.createQuery(finalHql);
//		if(params!=null && params.length>0){
//			for(int i=0;i<params.length;i++){
//				query.setParameter(i, params[i]);
//			}
//		}
//		List<T> list = query.list();
		/**方案三*/
	/*	List<T> list = this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(finalHql);
				if(params!=null && params.length>0){
					for(int i=0;i<params.length;i++){
						query.setParameter(i, params[i]);
					}
				}
			//	pageInfo.setTotalResult(query.list().size());
				query.setFirstResult((page-1)*rows);
				query.setMaxResults(rows);
				
				return query.list();
			}
			
		});*/
		return list;
	}

	@Override
	public int getMemberCounts() {
		 int i=this.getHibernateTemplate().find("from " +tableName).size();
		 return i;
	}

	@Override
	public List findCollectionByConditionNoPageWithSelectCondition(String condition, final Object[] params,
			Map<String, String> orderby, String selectCondition) {
		String hql = "select "+selectCondition+" from "+tableName+" o where 1=1 ";
		//将Map集合中存放的字段排序，组织成ORDER BY o.textDate ASC,o.textName DESC
		String orderbyCondition = this.orderbyHql(orderby);
		//添加查询条件
		final String finalHql = hql + condition + orderbyCondition;
		//查询，执行hql语句
		List list = this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(finalHql);
				if(params!=null && params.length>0){
					for(int i=0;i<params.length;i++){
						query.setParameter(i, params[i]);
					}
				}
				return query.list();
			}
			
		});
		return list;
	}
	/**
	 * 采用sql进行批量更新和
	 */
	public void updateList(final String sql){
		int num=this.getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				int num = query.executeUpdate();
				return num;
			}
		});
	}

}
