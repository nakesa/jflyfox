var oper ;
jQuery(function($) {
	oper = new flyfox("dict", "数据字典");
	
	paginator = function(page) {
		oper.list();
	};
});
	

