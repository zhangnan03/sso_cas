<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->

<head>
<meta charset="utf-8" />
<title>权限管理系统</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<link href="${base}/static/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${base}/static/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${base}/static/css/style-metro.css" rel="stylesheet" type="text/css"/>
<link href="${base}/static/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${base}/static/css/light.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${base}/static/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${base}/static/css/metroStyle/metroStyle.css" />
<link rel="stylesheet" type="text/css" href="${base}/static/page/Pager.css"/>	
<link href="${base}/static/fancybox/jquery.fancybox.css" rel="stylesheet">
</head>

<body class="page-header-fixed">
<!-- 左侧菜单及头部信息 -->
  [#include "/include/header.ftl"/]
	[#include "/include/left.ftl"/]
  
  <div class="page-content"> 
    <div class="container-fluid">
      <form id="queryForm" action="list.jhtml" method="post">
      <div class="row-fluid">
        <div class="span12"> 
          <div class="color-panel hidden-phone" style="margin-top: 10px;">
          <h3 class="page-title"> 数据字典管理<small> 列表</small> </h3>
          <hr />
          
        </div>
      </div>
      <div id="dashboard">
        <div class="row-fluid form-horizontal"  style="margin-bottom: 20px;">
          <div class="span3 ">
            <div class="control-group">
              <label class="control-label">字典类别</label>
              <div class="controls">
                <input type="text" class="text1" name="dictType" id="dictType" value="${systemDictVo.dictType}" />
              </div>
            </div>
          </div>
          
          <div class="span3 ">
            <div class="control-group">
              <label class="control-label">类别名称</label>
              <div class="controls">
                <input type="text" class="text1" name="dictTypeName" id="dictTypeName" value="${systemDictVo.dictTypeName}" />
              </div>
            </div>
          </div>
          
          <div class="span3 ">
            <div class="control-group">
              <label class="control-label">字典类型</label>
              <div class="controls">
                <input type="text" class="text1" name="dictCode" id="dictCode" value="${systemDictVo.dictCode}" />
              </div>
            </div>
          </div>
          
          <div class="span3 ">
            <div class="control-group">
              <label class="control-label">类型名称</label>
              <div class="controls">
                <input type="text" class="text1" name="dictName" id="dictName" value="${systemDictVo.dictName}" />
              </div>
            </div>
          </div>
          
          <div class="span3 ">
            <div class="control-group">
              <label class="control-label">状态：</label>
              <div class="controls">
                <select class="select1" id="status" name="status">
					<option value="">请选择状态类型</option>										        
				        [@system_dict_list dictType='STATUS'] 
					         [#list systemDicts as systemDict]  
					            <option value="${systemDict.dictCode}" >${systemDict.dictName}</option>  
				          	 [/#list]  
				        [/@system_dict_list]
				</select>
              </div>
            </div>
          </div>
          
          <div class="span3">
          	<button type="submit" class="btn blue"> <i class="icon-search"></i>&nbsp;&nbsp;查询 </button>
          	<button type="button" class="btn" onclick="clearAll()"> 重置 </button>
          </div>
          
        </div>
        </form>
        <div class="row-fluid">
          <div class="span12"> 
            <div class="portlet box blue">
                [@shiro.hasPermission name = "power:datafield:add"]
                <div class="portlet-title">
                <div class="actions">
                	<a href="perAdd.jhtml" name="fancyboxClose" class="btn mini green-stripe"><i class="icon-plus"></i>&nbsp;&nbsp;添加</a>
               	</div>
               	</div>
                [/@shiro.hasPermission]
              <div class="portlet-body">
                <table class="table table-striped table-hover table-bordered check-table" id="sample_editable_1">
                  <thead>
						<tr>
							<th class="col-80">序号</th>
							<th class="col-130">字典类别</th>
							<th class="col-130">字典类别名称</th>
							<th class="col-130">字典类型</th>
							<th class="col-160">字典类型名称</th>
							<th class="col-180">状态</th>
							<th class="col-180">排序</th>
							<th class="col-80">操作</th>
						</tr>
					</thead>
                  <tbody>
					[#list page.content as systemDict]
						<tr>
							<td height="35" class="col-80">${systemDict_index+1}</td>
							<td height="35" class="col-130">${systemDict.dictType}</td>
							<td height="35" class="col-130">${systemDict.dictTypeName}</td>
							<td height="35" class="col-130">${systemDict.dictCode}</td>
							<td height="35" class="col-180">${systemDict.dictName}</td>
							<td height="35" class="col-130">
							   [#if systemDict.status=='0']
							             禁用
							   [#else]
							             启用
							   [/#if]
							</td>
							<td height="35" class="col-130">${systemDict.orderId}</td>
							<td height="35" class="col-80">
							[@shiro.hasPermission name = "boss:dict:edit"]
							<a class="btn mini green-stripe" href="${base}/dict/perEdit.jhtml?id=${systemDict.id}" name="fancyboxClose">
								<span>编辑</span>
							</a>
							[/@shiro.hasPermission]
							[@shiro.hasPermission name = "boss:dict:enable"]
					   		[#if systemDict.status == 0]
							 	<a href="javascript:void(0)" onclick="updateState(1,'${systemDict.id}')">启用</a>
						  	[#else]
							  	<a  href="javascript:void(0)" onclick="updateState(0,'${systemDict.id}')">禁用</a>
						  	[/#if]
						  	[/@shiro.hasPermission]
							</td>
						</tr>
					[/#list]
				</tbody>
                </table>
	              <div id="pager" style="float: right;"></div>
	              <div class="clearfix"><div>
              </div>
            </div>
          </div>
        </div>
        <div class="clearfix"></div>
      </div>
    </div>
  </div>
</div>

<script src="${base}/static/js/jquery.min.js" type="text/javascript"></script> 
<script src="${base}/static/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script> 
<script src="${base}/static/js/bootstrap.min.js" type="text/javascript"></script> 
<script src="${base}/static/js/jquery.uniform.min.js" type="text/javascript" ></script> 
<script src="${base}/static/js/jquery.ztree.core-3.5.min.js"></script> 
<script src="${base}/static/js/jquery.ztree.excheck-3.5.min.js"></script> 
<script src="${base}/static/js/jquery.ztree.exedit-3.5.min.js"></script> 
<script src="${base}/static/js/app.js" type="text/javascript"></script> 
<script src="${base}/static/js/jquery.validate.min.js" type="text/javascript"></script> 
<script type="text/javascript" src="${base}/static/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/static/js/common.js"></script>
<script src="${base}/static/js/table-editable.js" type="text/javascript"></script>
  
<script>
	jQuery(document).ready(function() {
	   App.init();
	   TableEditable.init();
	   //弹出层关闭时，父页面刷新
	    $("a[name='fancyboxClose']").fancybox({
	        'autoScale': false,
	        'autoSize'   : true,
	        'scrolling' : 'no',
	        'transitionIn': 'none',
	        'transitionOut': 'none',
	        'type': 'iframe',
	        'afterClose':function(){
	            window.location.reload();
	        }
	    });
	});
	
		 //重置
	   function clearAll(){
        $("#dictType").val("");
        $("#dictTypeName").val("");
        $("#dictCode").val("");
        $("#dictName").val("");
        $("#status").val("");
		}
	
</script>

<script src="${base}/static/page/jquery.pager.js" type="text/javascript"></script>
<script src="${base}/static/page/page_util.js" type="text/javascript"></script>
<script type="text/javascript" src="${base}/static/fancybox/jquery.fancybox.pack.js"></script>
<script type="text/javascript">
	$(function(){    
	   	init(${page.number+1});
   		//下拉菜单值变化时，提交form表单
	    $(".select1").change(function(){
	    	submitForm();
	    });
	   	initForm();
	});
	//初始化分页信息
	function init(pagenumber){
		$("#pager").pager({pagenumber:pagenumber, pagecount:${page.totalPages},totalcount:${page.totalElements}, buttonClickCallback: ''});
	}
	//初始化表单显示
	function initForm(){
		$("select[name='status']").val('${systemDictVo.status}');
	}
	//提交表单
	function submitForm(){
		$("#queryForm").submit();
	}
	 
 	function updateState(status,id){
	 	layer.confirm("确定要执行此操作吗？", {
		    btn: ['确定','取消']  
		}, function(){
	     	$.ajax({
	    		url: "edit.jhtml",
	    		type:'post',
	    		data: {id:id,status:status},
	    		dataType: "json",
	    		success: function(data) {
	    		var str = "";
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
</body>
</html>