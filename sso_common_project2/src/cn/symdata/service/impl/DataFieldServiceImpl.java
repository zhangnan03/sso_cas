package cn.symdata.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.dao.DataFieldDao;
import cn.symdata.dao.impl.DataFieldDaoImpl;
import cn.symdata.entity.DataField;
import cn.symdata.entity.User;
import cn.symdata.service.DataFieldService;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
@Service
@Transactional(readOnly = true)
public class DataFieldServiceImpl implements DataFieldService{
	@Autowired
	private DataFieldDaoImpl dataFieldDaoImpl;
	@Autowired
	private DataFieldDao dataFieldDao;
	
	@Override
	public List<DataField> findAll() throws DatabaseException{
		return (List<DataField>) dataFieldDao.findAll(new Sort(Direction.ASC, "id"));
	}
	
	@Override
	public List<DataField> findDataFieldByEnable(User user,String menuIds) throws DatabaseException {
		List<DataField> resultList = Lists.newArrayList();
		List<DataField> dataFieldList = dataFieldDao.findDataFieldByEnable();
		for (DataField dataField : dataFieldList) {
			String users = dataField.getUsers();
			if(users.contains(user.getId())){
				dataField.setIsChecked(1);
			}
			
			if(StringUtils.isNotBlank(menuIds)&&dataField.getMenu()!=null&&StringUtils.isNotBlank(dataField.getMenu().getId())){
				if(!menuIds.contains(dataField.getMenu().getId())){//过滤所有包含该菜单ID的按钮
					resultList.add(dataField);
				}
			}
		}
		
		if(resultList!=null&&resultList.size()>0){
			dataFieldList.removeAll(resultList);
		}
		return dataFieldList;
	}
	
	public DataField findOne(String id){
		return dataFieldDao.findOne(id);
	}
	
	public DataField findByCode(String code) throws DatabaseException{
		return dataFieldDao.findByCode(code);
	}
	
	@Transactional(readOnly = false)
	public DataField save(DataField dataField){
		return dataFieldDao.save(dataField);
	}

	@Override
	public Page<DataField> findDataFieldByHql(DataField dataField,Integer page) throws DatabaseException {
		Pageable pageable = new PageRequest(page != null ? page.intValue() - 1 : 0, 10);
		return dataFieldDaoImpl.findDataFieldByHql(dataField,pageable);
	}

	@Override
	@Transactional(readOnly = false)
	public DataField update(DataField dataField) throws DatabaseException {
		return dataFieldDao.save(dataField);
	}

	@Override
	@Transactional(readOnly = false)
	public Integer updateDataFieldIsEnable(String id) throws DatabaseException {
		return dataFieldDao.updateDataFieldIsEnable(id);
	}

	@Override
	public boolean findByCodeAndId(String code, String id) throws DatabaseException {
		DataField dataField = null;
		code  = StringUtils.trimToEmpty(code);
		if(Strings.isNullOrEmpty(code)){
			return false;
		}
		if(StringUtils.isNotBlank(id)){
			dataField = dataFieldDao.findByCodeAndId(code,id);
		}else{
			dataField = dataFieldDao.findByCode(code);
		}
		if(dataField!=null){
			return false;
		}
		return true;
	}

}
