package cn.junit.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cn.symdata.entity.Admin;
import cn.symdata.service.AdminService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext*.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback = false)
@Transactional
public class UserTest {
	@Autowired
	private AdminService userService;
	/**
	 *
	 *@Description:测试用户查询功能
	 *@Author:zhangnan#symdata
	 *@Since:2015年9月1日  下午5:48:25
	 *@Version:1.0
	 */
	@Test
	public void testUserQuery(){
		Admin user = userService.findByUsername("admin");
		System.out.println(user.getUsername());
	}
}
