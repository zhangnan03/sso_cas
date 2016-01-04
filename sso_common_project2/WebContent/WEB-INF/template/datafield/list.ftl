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
      <form id="queryForm" action="${base}/dataField/list.jhtml" method="post">
      <div class="row-fluid">
        <div class="span12"> 
          <div class="color-panel hidden-phone" style="margin-top: 10px;">
          <h3 class="page-title"> 字段管理<small> 列表</small> </h3>
          <hr />
          
        </div>
      </div>
      <div id="dashboard">
        <div class="row-fluid form-horizontal"  style="margin-bottom: 20px;">
          <div class="span3 ">
            <div class="control-group">
              <label class="control-label">名称</label>
              <div class="controls">
                <input type="text" class="m-wrap span11" name="name" id="name" value="${dataField.name}" />
              </div>
            </div>
          </div>
          
          <div class="span3 ">
            <div class="control-group">
              <label class="control-label">编码</label>
              <div class="controls">
                <input type="text" class="m-wrap span11" name="code" id="code" value="${dataField.code}" />
              </div>
            </div>
          </div>
          
          <div class="span3">
	          <div class="control-group">
		           <label class="control-label">所属菜单</label>
				   <div class="controls text-cont-box">
				       <div class="text-cont" onMouseLeave="javascript:$(this).hide();">
				           <ul id="auth-tree" class="ztree"></ul>
				       </div>
				       <input type="hidden" name="menu.id" id="menuId" value="${dataField.menu.id}" />
				       <input type="text" id="menuName" name="menu.name" value="${dataField.menu.name}"  class="m-wrap span11 text-tree-inp"readonly onclick="focusMenu()" placeholder="请选择菜单">
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
                	<a href="${base}/dataField/dataFieldAdd.jhtml" name="fancyboxClose" class="btn black"><i class="icon-plus"></i>&nbsp;&nbsp;添加</a>
               	</div>
               	</div>
                [/@shiro.hasPermission]
              <div class="portlet-body">
                <table class="table table-striped table-hover table-bordered check-table" id="sample_editable_1">
                  <thead>
                    <tr>
                      <th class="check" width="20"><input type="checkbox" class="group-checkable" data-set=".check-table .checkboxes" /></th>
                      <th>名称</th>
                      <th>编码</th>
                      <th>所属菜单</th>
                      <th width="200">操作</th>
                    </tr>
                  </thead>
                  <tbody>
                  [#list page.content as dataField]
                     <tr class="">
                      <td><input type="checkbox" class="checkboxes" value="1" /></td>
                      <td>${dataField.name}</td>
                      <td>${dataField.code}</td>
                      <td>
                         [#if dataField.menu.parent.parent.name?exists]${dataField.menu.parent.parent.name}--[/#if]
	                      [#if dataField.menu.parent.name?exists]${dataField.menu.parent.name}--[/#if]
	                      [#if dataField.menu.name?exists]${dataField.menu.name}--[/#if]
                      </td>
                      <td>
                      [@shiro.hasPermission name = "power:datafield:edit"]
	                      <a class="btn mini green-stripe" href="${base}/dataField/edit.jhtml?id=${dataField.id}" name="fancyboxClose"><i class="icon-pencil"></i>&nbsp;&nbsp;&nbsp;编辑</a> 
	                  [/@shiro.hasPermission]
	                  [@shiro.hasPermission name = "power:datafield:delete"]
	                      <a class="btn mini red-stripe"  onclick="delDataField('${dataField.id}')"><i class="icon-trash"></i>&nbsp;&nbsp;&nbsp;删除</a>
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
		});
</script>

<script src="${base}/static/page/jquery.pager.js" type="text/javascript"></script>
<script src="${base}/static/page/page_util.js" type="text/javascript"></script>
<script type="text/javascript" src="${base}/static/fancybox/jquery.fancybox.pack.js"></script>
<script type="text/javascript">
	$(function(){    
	   init(${page.number+1});
	   
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
	function init(pagenumber){
		$("#pager").pager({pagenumber:pagenumber, pagecount:${page.totalPages},totalcount:${page.totalElements}, buttonClickCallback: ''});
	}
	
	//删除
	function delDataField(id){
		layer.confirm("确定要删除吗？", {
		    btn: ['确定','取消']  
		}, function(){
           $.ajax({
	    		url:'${base}/dataField/enable.jhtml',
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
	
	var zTreeObj;
		var setting = {
			view: {
				dblClickExpand: false
			},data : {
				key : {
				    name : 'name',
				    url:''
				},
				simpleData : {
					enable : true,
					idKey : 'id',
					pIdKey : 'parentId',
					rootPId : '0'
				}
			},callback: {
				onClick: onClick
			}
        };
        
        //触发菜单操作
        function onClick(event, treeId, treeNode){
            var ids="";
			ids = getChildNodes(treeNode);
			
        	$("#menuId").val(ids);
        	$("#menuName").val(treeNode.name);
        	$(".text-cont").hide();
        }
        
        function focusMenu(){
           $(".text-cont").show();
        }
        //清空内容
        function clearAll(){
           $("#code").val("");
           $("#name").val("");
           $("#menuId").val("");
           $("#menuName").val("");
        }
        
        function getChildNodes(treeNode) {
		     var childNodes = zTreeObj.transformToArray(treeNode);
		     var nodes = new Array();
		     for(i = 0; i < childNodes.length; i++) {
		          nodes[i] = "'"+childNodes[i].id+"'";
		     }
		     return nodes.join(",");
		}
		
		jQuery(document).ready(function() {
		    zTreeObj = $.fn.zTree.init($("#auth-tree"), setting, ${menuList});
		});
</script> 
</body>
</html>