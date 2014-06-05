$(function() {
	// 回顶部
	$(window).scroll( function() { 
		if($(this).scrollTop() >= 200 ){
			$("#scrollTop").show();
		} else {
			$("#scrollTop").hide();
		}
	} );
	
	
	
	totalMoney();

	projectMoney();
	
	trendMoney();
});

/**
 * 查询
 */
function oper_list(){
	$('#tableMoney tr').show();
	var project_id = form_money.project_id.value;
	var type = form_money.type.value;
	var pay_time_start = form_money.pay_time_start.value;
	var pay_time_end = form_money.pay_time_end.value;
	if(project_id!=''){
		$('#tableMoney tr:not(.project'+project_id+")").hide();
	}
	if(type!=''){
		$('#tableMoney tr:not(.type'+type+")").hide();
	}
	if(pay_time_start==''){
		pay_time_start = "1000-01-01";
	}
	if(pay_time_end==''){
		pay_time_end = "9999-99-99";
	}
	$('#tableMoney tr[class^="time"]').each(function(index,data){
		var time = $(data).find('td').html();
		if(time < pay_time_start || time > pay_time_end)
			$(data).hide();
	});
	
	$('#tableMoney thead tr').show();
}

/**
 * 重置表单
 */
function oper_reset(){
	$(".tableSearch input:not(.btn1)").val("");
	$(".tableSearch select").val("-1");
}

function oper_add(){
	var url = 'money/add';
	Iframe(url,350,450,'金额添加',false,false,false,function(){
		form_money.action = 'web#money';
		form_money.submit();
	});
}

/**
 * 总金额
 */
function totalMoney(){
	/**
	 * echarts
	 */
	var echarts1 = echarts.init(document.getElementById('echarts1'));
	echarts1.showLoading({
		text : '正在努力的读取数据中...' // loading话术
	});

	// ajax return
	echarts1.hideLoading();

	// 图表使用-------------------
	option = {
		tooltip : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		legend : {
			data : [ '金额' ]
		},
		xAxis : [ {
			type : 'value'
		} ],
		yAxis : [ {
			type : 'category',
			data : [ "预算", "支出", "收入" ]
		} ],
		series : [ {
			name : '金额',
			type : 'bar',
			stack : '总量',
			itemStyle : {
				normal : {
					label : {
						show : true,
						position : 'inside'
					}
				}
			},
			data : totalMoneyData
			//data : [ 10000, 40000, 50000, 100000 ]
		} ]
	};
	echarts1.setOption(option);
}

/**
 * 项目总金额
 */
function projectMoney(){
	/**
	 * echarts
	 */
	var echarts1 = echarts.init(document.getElementById('echarts2'));
	echarts1.showLoading({
		text : '正在努力的读取数据中...' // loading话术
	});

	// ajax return
	echarts1.hideLoading();

	// 图表使用-------------------
	option = {
		tooltip : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		legend : {
			data : ['预算','支出',"收入"]
		},
		yAxis : [ {
			type : 'value'
		} ],
		xAxis : [ {
			type : 'category',
			data : projectMoneyData.name
			//data : [ "买车", "买房", "装修", "结婚" ]
		} ],
		series : [ {
			name : '预算',
			type : 'line',
			// stack : '总量',
			data : projectMoneyData.ys
			//data : [ 10000, 40000, 50000, 100000 ]
		},{
			name : '支出',
			type : 'line',
			// stack : '总量',
			data : projectMoneyData.zc
			//data : [ -20000, -30000, -70000, -70000 ]
		},{
			name : '收入',
			type : 'line',
			// stack : '总量',
			data : projectMoneyData.sr
			//data : [ -20000, -30000, -70000, -70000 ]
		} ]
	};
	echarts1.setOption(option);
}

/**
 * 趋势图
 */
function trendMoney(){
	/**
	 * echarts
	 */
	var echarts1 = echarts.init(document.getElementById('echarts3'));
	echarts1.showLoading({
		text : '正在努力的读取数据中...' // loading话术
	});

	// ajax return
	echarts1.hideLoading();

	// 图表使用-------------------
	option = {
		tooltip : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		legend : {
			data : [ "买车", "买房", "装修", "结婚"]
		},
		yAxis : [ {
			type : 'value'
		} ],
		xAxis : [ {
			type : 'category',
			data : [ '预算-支出','预算','支出' ]
		} ],
		series : [ {
			name : '买车',
			type : 'line',
			stack : '总量',
			data : [ -10000, 40000, 50000]
		},{
			name : '买房',
			type : 'line',
			stack : '总量',
			data : [ -20000, 40000, 60000]
		},{
			name : '装修',
			type : 'line',
			stack : '总量',
			data : [ 10000, 50000, 40000]
		} ,{
			name : '结婚',
			type : 'line',
			stack : '总量',
			data : [ 50000, 60000, 10000 ]
		} ]
	};
	echarts1.setOption(option);
}