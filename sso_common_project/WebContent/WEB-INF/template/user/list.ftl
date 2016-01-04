<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="renderer" content="webkit|chrome">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>信达账务管理中心</title>
	</head>
<body>
	<div class="content">
		[#include "/include/header.ftl"/]
		[#include "/include/left.ftl"/]
		<div class="right-main">
			<form action="list.jhtml" method="post" id="queryForm">
				<div id="right-container">
						<div class="curent-path">
							<span class="root-path">员工管理 <em>&gt;</em></span>								
							<span class="active-path">列表	</span>
						</div>
						<div class="search-field ">
								<div class="search-bot clearfix">
									<div class="field-div">
										<label for="name">用户名：</label>
										<input type="text" class="text1" name="username" id="username" value="${user.username}" />
									</div>
									<div class="field-div">
										<label for="name">真实姓名：</label>
										<input type="text" class="text1" name="realName" id="realName" value="${user.realName}" />
									</div>
									<div class="field-div">
										<input type="submit" class="btn search-btn" value="查询" />
									</div>
									<div class="field-div">
										<input type="reset" class="btn search-btn" value="重置" />
									</div>
								</div>
						</div>
						
						<div class="field-div">
							[@shiro.hasPermission name = "power:user:add"]
				              	<a href="${base}/user/preAdd.jhtml" name="fancyboxClose" id="userAdd" class="btn search-btn com-creat-btn" >
									<span > + </span> 新增
								</a> 
				            [/@shiro.hasPermission]
						</div>
						<table class="table" border="0" cellspacing="0" cellpadding="0">
							<thead>
								<tr>
									<th>序号</th>
				                    <th>用户名</th>
				                    <th>真实姓名</th>
				                    <th>邮箱</th>
				                    <th>状态</th>
				                    <th>创建时间</th>
				                    <th width="460">操作</th>
								</tr>
							</thead>
							<tbody>
								[#list page.content as user]
									<tr class="">
				                      <td>${user_index+1}</td>
				                      <td>${user.username}</td>
				                      <td>${user.realName}</td>
				                      <td>${user.email}</td>
				                      <td>[#if user.status == "0"]禁用[#else]启用[/#if]</td>
				                      <td>${user.createTime}</td>
				                      <td>
				                      [@shiro.hasPermission name = "power:user:role:authorize"]
				                          <a href="${base}/user/authorizeRoleList.jhtml?id=${user.id}" name="fancyboxAutoFalse" id="power"><span>角色授权</span></a>
				                      [/@shiro.hasPermission]
				                      
				                      [@shiro.hasPermission name = "power:user:button:authorize"]
				                          <a href="${base}/user/authorizePowerList.jhtml?id=${user.id}" name="fancyboxAutoFalse" id="power"><span>操作授权</span></a>
				                      [/@shiro.hasPermission]
				                      <!--
				                      [@shiro.hasPermission name = "power:user:datafield:authorize"]
				                          <a href="${base}/user/authorizeDataFieldList.jhtml?id=${user.id}" name="fancyboxClose" id="power"><span>字段授权</span></a> 
				                      [/@shiro.hasPermission]
				                      -->
				                      [@shiro.hasPermission name = "power:user:edit"]
				                          <a href="${base}/user/edit.jhtml?id=${user.id}" name="fancyboxClose" id="edit"><span>编辑</span></a> 
				                      [/@shiro.hasPermission]
				                      [@shiro.hasPermission name = "power:user:enable"]
				                        [#if user.id!='45e50b45c28e856c']
										  [#if user.status == 0]
										  <a onclick="changeStatus(1,'${user.id}')" id="userStatus${user.id}"><span>启用</span></a>
										  [#else]
										  <a id="userStatus${user.id}" onclick="changeStatus(0,'${user.id}')"><span>禁用</span></a>
										  [/#if]
										[/#if]
				                      [/@shiro.hasPermission]
 									  [@shiro.hasPermission name = "power:user:delete"]
				                      [#if user.id!='45e50b45c28e856c']
				                          <a onclick="delUser('${user.id}')" data-toggle="modal"><span>删除</span></a>
				                      [/#if]
				                      [/@shiro.hasPermission]				                      
				                      </td>
				                    </tr>
								[/#list]
							</tbody>
						</table>
					</div>
					<div id="pager" style="float: right;"></div>
				</form>
			</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){    
	   init(${page.number+1});
	});
	function init(pagenumber){
		$("#pager").pager({pagenumber:pagenumber, pagecount:${page.totalPages},totalcount:${page.totalElements}, buttonClickCallback: ''});
	}
	//删除
	function delUser(id){
		layer.confirm("确定要删除吗？", {
		    btn: ['确定','取消']  
		}, function(){
           $.ajax({
	    		url:'${base}/user/enable.jhtml',
	    		type:'post',
	    		data:'id='+id,
	    		dataType: "json",
	    		success: function(data) {
					if (data.message.type == "success") {
						window.location.reload();
					} else {
						layer.alert(data.message.content);
					}
				}
	    	});
        });
	}
	
	function changeStatus(status,id){
		layer.confirm("确定要执行此操作吗？", {
		    btn: ['确定','取消']  
		}, function(){
		     $.ajax({
	    		url:'${base}/user/updateStatus.jhtml',
	    		type:'post',
	    		data:'id='+id+"&status="+status,
	    		dataType: "json",
	    		success: function(data) {
	    			if (data.message.type == "success") {
						window.location.reload();
					} else {
						layer.alert(data.message.content);
					}
				}
	    	});
	    });
	}
</script> 
</html>