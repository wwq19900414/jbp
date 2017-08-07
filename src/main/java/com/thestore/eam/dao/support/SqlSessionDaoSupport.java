/**
 * Copyright (C), 2011-2016 The Store
 * File Name: SqlSessionDaoSupport.java
 * Encoding: UTF-8
 * Date: Sep 13, 2011
 * History: 
 */
package com.thestore.eam.dao.support;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.util.Assert;

/**
 * <p>convenient way to cooperate with spring using SqlSession</p>
 * <p>notes: this SqlSessionDaoSupport is different from the mybatis-spring's official one.
 * @author Wayne Wan(bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Sep 13, 2011
 */
public abstract class SqlSessionDaoSupport extends DaoSupport {

    private SqlSessionFactory sqlSessionFactory;
    
    @Autowired(required = false)
    public final void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
    	this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * get a sqlsession use default ExecutorType Type
     * Users should use this method to get a SqlSession to call its statement methods
     * This is SqlSession is managed by spring. Users should not commit/rollback/close it
     * because it will be automatically done.
     * 
     * @return Spring managed thread safe SqlSession 
     */
    public final SqlSession getSqlSession() {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    /**
     * get a sqlsession use the specified ExecutorType
     * Users should use this method to get a SqlSession to call its statement methods
     * This is SqlSession is managed by spring. Users should not commit/rollback/close it
     * because it will be automatically done.
     * @param executorType
     * @return Spring managed thread safe SqlSession 
     */
    public final SqlSession getSqlSession(ExecutorType executorType){
    	return new SqlSessionTemplate(sqlSessionFactory, executorType);
    }

    /**
     * {@inheritDoc}
     */
    protected void checkDaoConfig() {
        Assert.notNull(this.sqlSessionFactory, "Property 'sqlSessionFactory' is required");
    }
    public final SqlSession getSqlSessionForBatch() {
    	SqlSession sqlSession=this.sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        return sqlSession;
    }
}
