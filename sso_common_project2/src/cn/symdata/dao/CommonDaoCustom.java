package cn.symdata.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.symdata.common.exception.DatabaseException;

public interface CommonDaoCustom<T> {
	/**
	 * 根据sql语句查询
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Object[]> queryBySql(String sql, List params) throws DatabaseException;

	/**
	 * 根据sql语句查询分页数据
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Page<T> queryBySql(String sql, List params, Pageable pageable) throws DatabaseException;

	/**
	 * 根据hsql查询分页数据
	 * 
	 * @param hsql
	 * @param params
	 * @param pageable
	 * @return
	 */
	public Page<T> getListByHsql(String hsql, List params, Pageable pageable) throws DatabaseException;

	/**
	 * 
	 * @param hsql
	 * @param params
	 * @return
	 * @Description: 根据hql 无分页查询
	 * @Author:lizhen#zplay.cn
	 * @Since:2015年2月9日 下午3:36:09
	 * @Version:1.0
	 */
	public List<T> getList(String hsql, List params) throws DatabaseException;

	/**
	 * 根据sql执行更新操作
	 * 
	 * @param sql
	 * @param params
	 * @return 
	 */
	public int upadteBySql(String sql, List params) throws DatabaseException;

	/**
	 * 根据sql查询单个信息
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public String singlQueryBySql(String sql, List params) throws DatabaseException;

	/**
	 * 根据sql查询单个信息
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Object[] objQueryBySql(String sql, List params) throws DatabaseException;

}
