[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<link rel="stylesheet" type="text/css" href="${base}/static/layer/skin/layer.css">
<link rel="stylesheet" type="text/css" href="${base}/static/layer/skin/layer.ext.css">

<link rel="stylesheet" type="text/css" href="${base}/static/css/main.css">
<link rel="stylesheet" type="text/css" href="${base}/static/css/lhgcalendar.css">
<link rel="stylesheet" type="text/css" href="${base}/static/css/blue.css">
<link rel="stylesheet" type="text/css" href="${base}/static/page/pager.css"/>
<link rel="stylesheet" type="text/css" href="${base}/static/css/validation.css"/>

<link href="${base}/static/fancybox/jquery.fancybox.css" rel="stylesheet">

<script type="text/javascript" src="${base}/static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${base}/static/layer/layer.js"></script>
<script type="text/javascript" src="${base}/static/layer/extend/layer.ext.js"></script>

<script src="${base}/static/page/jquery.pager.js" type="text/javascript"></script>
<script src="${base}/static/page/page_util.js" type="text/javascript"></script>

<script type="text/javascript" src="${base}/static/js/main.js"></script>
<script type="text/javascript" src="${base}/static/js/lhgcore.min.js"></script>
<script type="text/javascript" src="${base}/static/js/lhgcalendar.min.js"></script>
<script type="text/javascript" src="${base}/static/js/jquery.placeholder.min.js"></script>
<script type="text/javascript" src="${base}/static/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/static/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
	 window.UEDITOR_HOME_URL = "${base}/static/";	 
</script>
<script type="text/javascript" src="${base}/static/js/ueditor.config.js"></script>
<script type="text/javascript" src="${base}/static/js/ueditor.all.js"></script>
<script type="text/javascript" src="${base}/static/js/zh-cn.js"></script>
<script type="text/javascript" src="${base}/static/fancybox/jquery.fancybox.pack.js"></script>
<div class="menu-top  white-bg-color">
	<span class="menu-sym-name">
		信达账务管理中心
	</span>
	<div class="menu-login-name">
		<span class="user-power">[@shiro.principal /]，您好！</span>
	</div>
	<a href="javascript:void(0)" class="logout-btn">退出</a>
</div>
<script type="text/javascript">
	$(function(){
		$(".logout-btn").click(function(){
			layer.confirm('确定要退出吗？', {
			    btn: ['确定','取消'] 
			}, function(){
			   window.location.href='${base}/logout';
			});
		});
		//弹出层关闭时，父页面刷新,添加
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
	    $("a[name='fancyboxAutoFalse']").fancybox({
	    	'width':750,
	        'autoScale': false,
	        'autoSize'   : false,
	        'scrolling' : 'no',
	        'transitionIn': 'none',
	        'transitionOut': 'none',
	        'type': 'iframe'
	    });
	     $("a[name='fancyboxAutoTrue']").fancybox({
	        'autoScale': false,
	        'autoSize'   : true,
	        'scrolling' : 'no',
	        'transitionIn': 'none',
	        'transitionOut': 'none',
	        'type': 'iframe'
	    });
	});
</script>