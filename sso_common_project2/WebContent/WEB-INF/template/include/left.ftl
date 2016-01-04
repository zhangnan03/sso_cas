<div class="left-menu">
	<div class="menus-a">
		<div data-url="${base}/common/main.jhtml"  class="fir-menu home-menu active-a select-a">
			<span></span>
			<p>首页</p>
		</div>
		[@menu_list] 
			[#list menus as menu]
				<div href="javascript:void(0)" class="${menu.menuFlag}">
					<span></span>
					<p>${menu.name}</p>
					<i class="more-child-menu"></i>
					<em class="tri-icon"></em>
					<div class="sen-menu">
						[#if menu.childrenTmp?exists]
							[#list menu.childrenTmp as menu2]
								<a href="${base}/${menu2.url}">${menu2.name}</a>
							[/#list]
						[/#if]
					</div>
				</div>
			[/#list]
		[/@menu_list]
	</div>
</div>
