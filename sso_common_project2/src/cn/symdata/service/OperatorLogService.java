package cn.symdata.service;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.entity.OperatorLog;

public interface OperatorLogService {
	public abstract void storeOperatorLog(OperatorLog log) throws DatabaseException;
}
