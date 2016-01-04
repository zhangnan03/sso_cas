package cn.symdata.web;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.symdata.common.DataEnum.ErrorCode;
import cn.symdata.common.Message;
import cn.symdata.entity.SystemDict;
import cn.symdata.service.SystemDictService;
import cn.symdata.vo.SystemDictVo;

import com.google.common.collect.Maps;

@Controller
@RequestMapping("/dict")
public class SystemDictController extends BaseController {
	@Autowired
	private SystemDictService systemDictService;
	@RequestMapping(value = "/list")
	public String findAllList(SystemDictVo systemDictVo,Integer page,ModelMap model){
		Page<SystemDict> list = systemDictService.findAllList(systemDictVo, page);
		model.addAttribute("page", list);
		return "dict/list";
	}
	
	/**
	 * 
	 * @Title: saveSystemDict 
	 * @Description: 添加数据字典页面
	 * @Autohr panpengxu#zymdata.cn
	 * @param @return   
	 * @return String  
	 * @throws 
	 * 2015年10月30日下午16:33:27
	 */
	@RequestMapping(value = "/perAdd")
	public String perAddSystemDict(){
		return "dict/add";
	}
	
	@RequestMapping(value = "/add")
	public @ResponseBody Map<String,Object> addSystemDict(SystemDict systemDict){
		Map<String, Object> data = Maps.newConcurrentMap();
		if(!StringUtils.isNotBlank(systemDict.getDictCode())||!StringUtils.isNotBlank(systemDict.getDictName())||
		   !StringUtils.isNotBlank(systemDict.getDictType())||!StringUtils.isNotBlank(systemDict.getDictTypeName())){
			data.put("message",Message.error(ErrorCode.ERR1001.getDescription()));
			return data;
		}
		if(systemDict.getOrderId()==null){
			systemDict.setOrderId(0);
		}
		systemDictService.saveSystemDict(systemDict);
		data.put("message",SUCCESS_MESSAGE);
		return data;
	}
	/**
	 * 
	 * @Title: updateSystemDict 
	 * @Description: 跳转修改数据字典页面
	 * @Autohr panpengxu#zymdata.cn
	 * @param @return   
	 * @return String  
	 * @throws 
	 * 2015年10月30日下午16:35:21
	 */
	@RequestMapping(value = "/perEdit")
	public String perEditSystemDict(String id,ModelMap model){
		SystemDict systemDict = systemDictService.findOne(id);
		model.addAttribute("systemDict", systemDict);
		return "dict/edit";
	}
	
	/**
	 * 
	 * @Title: editSystemDict 
	 * @Description: 修改数据字典
	 * @Autohr panpengxu#zymdata.cn
	 * @param @param systemDict
	 * @param @return   
	 * @return TransDto  
	 * @throws 
	 * 2015年10月30日下午16:45:14
	 */
	@RequestMapping(value = "/edit")
	public @ResponseBody Map<String,Object> editSystemDict(SystemDict systemDict){
		Map<String, Object> data = Maps.newConcurrentMap();
		if(systemDict==null||!StringUtils.isNotBlank(systemDict.getId())){
			data.put("message",Message.error(ErrorCode.ERR1001.getDescription()));
			return data;
		}
		SystemDict dict = systemDictService.findOne(systemDict.getId());
		if(dict==null){
			data.put("message",Message.error(ErrorCode.ERR1001.getDescription()));
			return data;
		}
		if (StringUtils.isNotBlank(systemDict.getDictCode())) {
			dict.setDictCode(systemDict.getDictCode());
		}
		if (StringUtils.isNotBlank(systemDict.getDictName())) {
			dict.setDictName(systemDict.getDictName());
		}
		if (StringUtils.isNotBlank(systemDict.getDictType())) {
			dict.setDictType(systemDict.getDictType());
		}
		if (StringUtils.isNotBlank(systemDict.getDictTypeName())) {
			dict.setDictTypeName(systemDict.getDictTypeName());
		}
		if (StringUtils.isNotBlank(systemDict.getRemark())) {
			dict.setRemark(systemDict.getRemark());
		}
		if (systemDict.getOrderId() != null) {
			dict.setOrderId(systemDict.getOrderId());
		}
		if (StringUtils.isNotBlank(systemDict.getStatus())) {
			dict.setStatus(systemDict.getStatus());
		}
		systemDictService.updateSystemDict(dict);
		data.put("message", SUCCESS_MESSAGE);
		return data;
	}
}
