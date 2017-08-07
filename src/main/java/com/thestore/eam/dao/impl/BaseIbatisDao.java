/**
 * Copyright (C), 2011-2016
 * File Name: BaseDao.java
 * Encoding: UTF-8
 * Date: Sep 7, 2011
 * History: 
 */
package com.thestore.eam.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.thestore.eam.common.PageController;
import com.thestore.eam.common.ThreadLocalObject;
import com.thestore.eam.common.ThreadLocalVar;
import com.thestore.eam.dao.support.SqlSessionDaoSupport;
import com.thestore.eam.domain.BaseEntity;
/**
 * <p>Ibatis base dao</p>
 * <p>Notes: We can nearly pay no attention on paging function.<br>
 * cause we had encapsulated it by binding it to current thread.<br>
 * 
 * @author WayneWan(waynewan.com)
 * @version Revision: 1.00 Date: Sep 7, 2011
 */
public class BaseIbatisDao<T extends BaseEntity, PK extends Serializable> extends SqlSessionDaoSupport {

	static final Log log = LogFactory.getLog(BaseIbatisDao.class);

	/**
	 * default constructor
	 */
	public BaseIbatisDao() {

	}

	/**
	 * do save or update(add/delete/update) database job
	 * 
	 * @param entity
	 * @return return entity id
	 */
	public int saveOrUpdate(String statementId, T entity) {
		return getSqlSession().update(statementId, entity);
	}

	public void deleteByPk(String statementId, PK pk){
		getSqlSession().update(statementId, pk);
	}
	
	/**
	 * query entities
	 * @param statementId
	 * @param entity
	 * @return result list
	 */
	@SuppressWarnings("rawtypes")
	public List queryList(String statementId, Object entity) {
		PageController pageCtrl = getPageController();
		RowBounds rowBounds = null;
		if (pageCtrl != null && pageCtrl.isEnabled()) {
			rowBounds = new RowBounds((pageCtrl.getCurrentPage() - 1) * pageCtrl.getPageSize(), pageCtrl.getPageSize());
		} else {
			rowBounds = new RowBounds();
		}
		List results = getSqlSession().selectList(statementId, entity, rowBounds);
		return results;
	}

	/**
	 * get an entity
	 * 
	 * @param statement
	 * @param parameter
	 * @return entity
	 */
	@SuppressWarnings("unchecked")
	public T getEntity(String statement, Object parameter) {
		return (T) getSqlSession().selectOne(statement, parameter);
	}

	/**
	 * get a unique integer result
	 * 
	 * @param statement
	 * @param paramObj
	 * @return the unique int result
	 */
	protected int uniqueIntResult(String statementId, Object parameter) {
		return (Integer) this.getSqlSession().selectOne(statementId, parameter);
	}

	/**
	 * do update batch job
	 * <p>Notes: In mybatis, A SqlSession can not change executorType at runtime.
	 * So in SqlSessionDaoSupport, we create a new SqlSession when invoke getSqlSession, then 
	 * we can change the executorType when getSqlSession is invoked;<br>
	 * But when we use spring to manage our transaction ,mybatis-spring library will throw exception
	 * when we change  executorType in a transaction
	 * 
	 * @param statementId
	 * @param parameterArray
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected void batchUpdate(final String statementId, List parameterArray) {
		SqlSession sqlSession = getSqlSession();
		for(Object obj : parameterArray){
			sqlSession.insert(statementId, obj);
		}
	}
	
	/**
	 * <p>do customer code by passing a SqlSessionCallback implementation<br>
	 * notes: if use this method, you should process paging by yourself.<br>
	 * please refer to queryList method
	 * @param callback
	 * @return
	 */
	protected Object execute(SqlSessionCallback callback){
		SqlSession session = getSqlSession();
		return callback.doInSqlSession(session);
	}
	
	/**
	 * get a PageController component from current thread
	 * @return
	 */
	protected PageController getPageController() {
		ThreadLocalObject tlObj = ThreadLocalVar.get();
		if (tlObj != null){
			return tlObj.getPageContrller();
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public void insertTreeCateBatch(final String statementId,final List parameterArray){ 
		SqlSession sqlSession=getSqlSessionForBatch();//getSqlSession(ExecutorType.BATCH);
		for(Object obj : parameterArray){
			sqlSession.insert(statementId, obj);
		}
		
		//sqlSession.commit();
		sqlSession.close();
		 }

	}
/**
 * callback interface used to execute customer logic
 *
 * @author Wayne Wan(bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Sep 13, 2011
 */
interface SqlSessionCallback{
	public Object doInSqlSession(SqlSession session);
}





