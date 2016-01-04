package cn.symdata.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.dao.CommonDaoCustom;
import cn.symdata.entity.DataField;

import com.google.common.collect.Lists;

public class DataFieldDaoImpl {
	@Autowired
	private CommonDaoCustom<DataField> commonDaoCustom;
	/**
	 * 
	 * @Title: findDataFieldByHql 
	 * @Description: 字段分页
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param sql
	 * @param @param params
	 * @param @param pageable
	 * @param @return   
	 * @return Page<T>  
	 * @throws 
	 * 2015年9月9日下午2:11:34
	 */
	public Page<DataField> findDataFieldByHql(DataField dataField, Pageable pageable) throws DatabaseException {
		List<String> params = Lists.newArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("FROM DataField p where p.isEnable=0 ");
		if(StringUtils.isNotBlank(dataField.getCode())){
			sql.append("and p.code like ?");
			params.add("%"+dataField.getCode()+"%");
		}
		if(StringUtils.isNotBlank(dataField.getName())){
			sql.append("and p.name like ?");
			params.add("%"+dataField.getName()+"%");
		}
		if(dataField.getMenu()!=null&&StringUtils.isNotBlank(dataField.getMenu().getId())){
			sql.append("and p.menu.id in ("+dataField.getMenu().getId()+")");
		}
		return commonDaoCustom.getListByHsql(sql.toString(), params,pageable);
	}
}
