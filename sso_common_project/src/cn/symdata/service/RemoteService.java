package cn.symdata.service;

import java.util.List;

import cn.symdata.common.exception.DatabaseException;
import cn.symdata.entity.Menu;
import cn.symdata.entity.User;

/**
 * 
 * @ClassName: RemoteInterface 
 * @Description: 外部系统接口信息处理 
 * @author guoxuelian@symdata.cn
 * @date 2015年9月14日 下午1:48:33 
 *
 */
public interface RemoteService {
	/**
	 * 
	 * @Title: permission 
	 * @Description: 外部系统，根据用户名及系统标识获取权限
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param username
	 * @param @param systemId 系统标识
	 * @param @return
	 * @param @throws DatabaseException   
	 * @return TransDto  
	 * @throws 
	 * 2015年9月14日下午1:59:19
	 */
	public List<String> permission(User user,String systemId) throws DatabaseException;
	
	/**
	 * 
	 * @Title: getMenu 
	 * @Description: 获取该系统的菜单
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param username
	 * @param @param systemId
	 * @param @return
	 * @param @throws DatabaseException   
	 * @return TransDto  
	 * @throws 
	 * 2015年9月14日下午3:49:34
	 */
	public List<Menu> getMenu(User user,String systemId) throws DatabaseException;
	
	/**
	 * 
	 * @Title: updatePassword 
	 * @Description: 修改密码
	 * @Autohr guoxuelian#symdata.cn
	 * @param @param username 用户名
	 * @param @param newPwd  新密码
	 * @param @param oldPwd  老密码
	 * @param @return
	 * @param @throws DatabaseException   
	 * @return TransDto  
	 * @throws 
	 * 2015年9月16日下午4:47:18
	 */
	public void updatePassword(String username,String newPwd) throws DatabaseException;
}
