package cn.symdata.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.symdata.common.Function;
import cn.symdata.common.exception.DatabaseException;
import cn.symdata.dao.CommonDaoCustom;
import cn.symdata.entity.SystemDict;
import cn.symdata.vo.SystemDictVo;

import com.google.common.collect.Lists;

public class SystemDictDaoImpl {
	@Autowired
	private CommonDaoCustom<SystemDict> commonDaoCustom;
	
	public Page<SystemDict> findAllList(SystemDictVo systemDictVo, Pageable pageable) throws DatabaseException {
		List<Object> params = Lists.newArrayList();
		StringBuffer sql = new StringBuffer();
		boolean hasWhere = false;
		sql.append("FROM SystemDict s ");
		if(StringUtils.isNotBlank(systemDictVo.getDictType())){
			hasWhere = Function.appendWhereIfNeed(sql, hasWhere); 
			sql.append("s.dictType like ? ");
			params.add("%"+systemDictVo.getDictType()+"%");
		}
		if(StringUtils.isNotBlank(systemDictVo.getDictTypeName())){
			hasWhere = Function.appendWhereIfNeed(sql, hasWhere);
			sql.append("s.dictTypeName like ? ");
			params.add("%"+systemDictVo.getDictTypeName()+"%");
		}
		if(StringUtils.isNotBlank(systemDictVo.getDictCode())){
			hasWhere = Function.appendWhereIfNeed(sql, hasWhere);
			sql.append("s.dictCode like ?");
			params.add("%"+systemDictVo.getDictCode()+"%");
		}
		if(StringUtils.isNotBlank(systemDictVo.getDictName())){
			hasWhere = Function.appendWhereIfNeed(sql, hasWhere);
			sql.append("s.dictName like ? ");
			params.add("%"+systemDictVo.getDictName()+"%");
		}
		if(StringUtils.isNotBlank(systemDictVo.getStatus())){
			hasWhere = Function.appendWhereIfNeed(sql, hasWhere);
			sql.append("s.status = ? ");
			params.add(systemDictVo.getStatus());
		}
		sql.append(" order by createTime desc");
		return commonDaoCustom.getListByHsql(sql.toString(), params,pageable);
	}
}
