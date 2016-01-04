package cn.symdata.dao.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.dao.CommonDaoCustom;
import cn.symdata.dao.DaoFactory;

@Repository
public class CommonDaoCustomImpl<T> extends DaoFactory<T> implements
		CommonDaoCustom<T> {

	/**
	 * 根据sql语句查询
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Object[]> queryBySql(String sql, List params) throws DatabaseException {
		return (List<Object[]>) queryObjectsListBySql(sql, params);
	} 

	/**
	 * 根据hsql查询博客分页数据
	 * 
	 * @param hsql
	 * @param params
	 * @param pageable
	 * @return
	 */
	public Page<T> getListByHsql(String hsql, List params, Pageable pageable) throws DatabaseException {
		return hqlQueryForPage(hsql, params, pageable);
	}

	public List<T> getList(String hsql, List params) throws DatabaseException{
		return hqlQuery(hsql, params);
	}

	public int upadteBySql(String sql, List params) throws DatabaseException {
		return updateBySql(sql, params);
	}

	public Page<T> queryBySql(String sql, List params, Pageable pageable) throws DatabaseException {
		return queryObjectsPageBySql(sql, params, pageable);
	}

	@Override
	public String singlQueryBySql(String sql, List params) throws DatabaseException {
		return getSingleQuery(sql, params);
	}

	@Override
	public Object[] objQueryBySql(String sql, List params) throws DatabaseException {
		return getObjQuery(sql, params);
	}
}
