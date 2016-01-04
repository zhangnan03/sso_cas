package cn.symdata.log;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import cn.symdata.common.DataEnum.OperatorType;
import cn.symdata.common.exception.DatabaseException;
import cn.symdata.common.utils.JsonMapper;
import cn.symdata.entity.OperatorLog;
import cn.symdata.entity.User;
import cn.symdata.service.OperatorLogService;
import cn.symdata.service.UserService;

//@Component
//@Aspect
public class LogAspect {
	private final JsonMapper binder = JsonMapper.nonDefaultMapper();
	@Autowired
	private OperatorLogService operatorLogService;
	@Autowired
	private UserService userService;
	/**
	 *  
	 *@Description:保存切入点
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月7日  下午3:19:09
	 *@Version:1.0
	 */
    @Pointcut("execution(* cn.symdata.service.*.save*(..))")  
    public void saveServiceCall() { }  
      
    /**
     *@Description:修改切入点
     *@Author:zhangnan#symdata
     *@Since:2015年9月7日  下午3:19:05
     *@Version:1.0
     */
    @Pointcut("execution(* cn.symdata.service.*.update*(..))")  
    public void updateServiceCall() { }  
      
 
    /**
     *
     *@Description:删除切入点
     *@Author:zhangnan#symdata
     *@Since:2015年9月7日  下午3:18:59
     *@Version:1.0
     */
    @Pointcut("execution(* cn.symdata.service.*.delete*(..))")  
    public void deleteServiceCall() { }
    @Pointcut("execution(* cn.symdata.service.*.find*(..))")  
    public void findServiceCall() { }
	/**
	 *  
	 *@param joinPoint
	 *@param rtv
	 *@throws Throwable
	 *@Description:添加操作日志(后置通知) 
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月7日  下午3:22:41
	 *@Version:1.0
	 */
    @AfterReturning(value="saveServiceCall()", argNames="obj",returning="obj")  
    public void saveServiceCallCalls(JoinPoint joinPoint, Object obj) throws Throwable{  
    	OperatorLog log = initLog(joinPoint,obj,OperatorType.SAVE.getStatusCode());
    	operatorLogService.storeOperatorLog(log);
    }  
      
     /**
      *  
      *@param joinPoint
      *@param obj
      *@throws Throwable
      *@Description:修改操作日志(后置通知) 
      *@Author:zhangnan#symdata
      *@Since:2015年9月7日  下午3:22:59
      *@Version:1.0
      */
    @AfterReturning(value="updateServiceCall()", argNames="obj",returning="obj")  
    public void updateServiceCallCalls(JoinPoint joinPoint, Object obj) throws Throwable{  
    	OperatorLog log = initLog(joinPoint,obj,OperatorType.UPDATE.getStatusCode());
    	operatorLogService.storeOperatorLog(log);
    } 
    @Before(value="deleteServiceCall()")  
    public void deleteServiceCall(JoinPoint joinPoint) throws Throwable{  
    	OperatorLog log = initLog(joinPoint,null,OperatorType.DELETE.getStatusCode());
    	operatorLogService.storeOperatorLog(log);
    } 
    
    private OperatorLog initLog(JoinPoint joinPoint,Object obj,Integer operatorType) throws DatabaseException{
    	OperatorLog log = new OperatorLog();
    	log.setCreateTime(DateTime.now().toDate());
    	Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			String username = (String) subject.getPrincipal();
			if(username!=null){
				User user = userService.findByUsername(username);
				log.setCreator(user!=null?user.getId():"-1");
			}else{
				log.setCreator("-1");
			}
		}
		if(operatorType==OperatorType.DELETE.getStatusCode()){
			log.setOperatorData(binder.toJson(joinPoint.getArgs()));
		}else{
			log.setOperatorData(binder.toJson(obj));
		}
    	log.setOperatorModule(joinPoint.getTarget().getClass().getName());
    	log.setOperatorMethod(joinPoint.getSignature().getName());
    	log.setOperatorType(operatorType);
    	return log;
    }
}
