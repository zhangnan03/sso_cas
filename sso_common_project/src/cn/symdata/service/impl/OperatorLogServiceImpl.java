package cn.symdata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.dao.OperatorLogDao;
import cn.symdata.entity.OperatorLog;
import cn.symdata.service.OperatorLogService;
@Service
public class OperatorLogServiceImpl implements OperatorLogService {
	@Autowired
	private OperatorLogDao operatorLogDao;
	@Override
	@Transactional(readOnly = false)
	public void storeOperatorLog(OperatorLog log) throws DatabaseException {
		operatorLogDao.save(log);
	}

}
