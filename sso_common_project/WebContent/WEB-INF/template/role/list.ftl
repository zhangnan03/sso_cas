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
						<span class="root-path">角色管理 <em>&gt;</em></span>								
						<span class="active-path">列表	</span>
					</div>
					<div class="search-field ">
							<div class="search-bot clearfix">
								<div class="field-div">
									<label for="name">名称：</label>
									<input type="text" class="text1" name="name" id="name" value="${role.name}" />
								</div>
								<div class="field-div">
									<label for="name">编码：</label>
									<input type="text" class="text1" name="code" id="code" value="${role.code}" />
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
						[@shiro.hasPermission name = "power:role:add"]
		                	<a href="${base}/role/add.jhtml" name="fancyboxClose"  class="btn search-btn com-creat-btn" >
								<span > + </span> 新增
							</a> 
		                [/@shiro.hasPermission]
					</div>
					<table class="table" border="0" cellspacing="0" cellpadding="0">
						<thead>
							<tr>
								 <th>序号</th>
			                     <th>名称</th>
			                     <th>编码</th>
			                     <th>操作</th>
							</tr>
						</thead>
						<tbody>
							[#list page.content as role]
								<tr class="">
			                      <td>${role_index+1}</td>
			                      <td>${role.name}</td>
			                      <td>${role.code}</td>
			                      <td>
				                      [@shiro.hasPermission name = "power:role:edit"]
					                      <a href="${base}/role/edit.jhtml?id=${role.id}" name="fancyboxClose"><span>编辑</span></a> 
					                  [/@shiro.hasPermission]
					                  [@shiro.hasPermission name = "power:role:power"]
					                      <a href="${base}/role/showMenuPowerList.jhtml?roleId=${role.id}" name="fancyboxClose"><span>菜单授权</span></a> 
					                  [/@shiro.hasPermission]
					                  [@shiro.hasPermission name = "power:role:delete"]
					                     [#if role.id!='49bfba44be9d2dd0']
					                       <a onclick="delRole('${role.id}')"><span>删除</span></a>
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
	function delRole(id){
		layer.confirm("确定要删除吗？", {
		    btn: ['确定','取消']  
		}, function(){
           $.ajax({
	    		url:'${base}/role/enable.jhtml',
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
</script> 
</html>