/////////////////////////////////////////////////////////
//页面跳转，session失效Jsp页面跳转
HB = {};

/* 跳转页面 */
HB.gotoLoginPage = function(page){
	var parent = this;
	while(  this.parent != null ){
		parent = this.parent;
	}	 
	parent.location = page;
}

// 如果form为空，那么默认为form1
function getFormObj(form){
	// 如果form为空，那么默认为form1
	form = form || '';
	if(form==''){
		form = document.form1;	
	}
	return form;
}
/*
方法含义：页面跳转
用法：
 1.如果不写参数form，那么默认form名称为form1
 2.如果不写参数message 为直接提交action
 3.如果填写参数message 填出提示框 内容是message 确认操作
*/
function submitAction(url,message,form){
	var form = getFormObj(form);
	message = message || '';
	if(message!=''){
		if(confirm(message)){
			// 调用通用的方法
			queryByCondition(url,form);
		}
	}else{
		// 直接调用就ok
		queryByCondition(url,form);
	}
	return;
}
/**
*列表页面的查询js
*如果form为空，那么默认为form1
*/
function queryByCondition(url,form){
	
	form = getFormObj(form);
	form.action=url;
	form.submit();
}
/*
方法含义：重置文本框内容
用法：
 1.如果不写参数form，那么默认form名称为form1
 2.要求要重置的字段必须加上标示 search="true"
 3.下拉框重置的默认值为-1
*/
function resetQuery(form){
	// 如果form为空，那么默认为form1
	form = getFormObj(form);
	
	jQuery(form).find("table").each(function(){
		if($(".tableSearch")){
			jQuery(this).find("input").each(function(){
				//文本框
				var type = $(this).attr("type");
				
				if(type=="text"||type=="hidden"){
					$(this).val("");
				}else if(type=="checkbox"){
					$(this).attr("checked",false);
				}
			})
			// 下拉框
			jQuery(this).find("select").each(function(){
				$(this).val("-1");
			});
		}
	});
	
	/*
	// 文本框
	jQuery(form).find("input").each(function(){
		// 文本域
		var search = jQuery(this).attr("search");
		var type = jQuery(this).attr("type");
		//不是这种类型的不用处理
		if(search!="true") return true;
		
		if(type=="text"||type=="hidden"){
			jQuery(this).val("");
		}else if(type=="checkbox"){
			jQuery(this).attr("checked",false);
		}
	});
	// 下拉框
	jQuery(form).find("select").each(function(){
		if(jQuery(this).attr("search")=="true"){
			jQuery(this).val("-1");
		}
	});
	*/
}

/*
方法含义：全选
用法：selAll( this.form,this.checked )
*/
function selAll ( form,isSelAll ) {
	if ( typeof( form.mid ) == "object" ) {
	   	if ( typeof( form.mid.length ) == "number" ) {
	       	for ( var i = 0 ; i < form.mid.length ; i++ ) {
	           	form.mid[ i ].checked = isSelAll;
	        }
	   } else {
	       	form.mid.checked = isSelAll;
       }
    }
}
// 对选中的复选框进行主键拼接
function selCheckbox(form){
	var mids="";
 	if ( typeof( form.mid ) == "object" ) {
            if ( typeof( form.mid.length ) == "number" ) {
                for ( var i = 0 ; i < form.mid.length ; i++ ) {
                    if(form.mid[ i ].checked){
                    	mids+=form.mid[ i ].value;
                    	mids+=",";
                    }
                }
            }else {
            	if(form.mid.checked){
            		mids = form.mid.value;
            		mids += ",";
            	}
            }
    }
    mids = mids.substring(0,mids.lastIndexOf(','));
	form.mids.value=mids;
	if(isEmpty(mids)){
		return false;
	}
}

//获取当前时间，避免去后台获取java时间了，
function getDate(){
	var datetime = new Date();
	var year = datetime.getFullYear();
	var month = datetime.getMonth()+1;//js从0开始取 
	var date = datetime.getDate(); 
	
	if(month<10){
		month = "0" + month;
	}
	if(date<10){
		date = "0" + date;
	}
	var time = year+"-"+month+"-"+date; //2009-06-12
	return time;
}

	/*
	*获取时间，传入时间，
	*Update Laozhong 2013-04-11 支持类型传入匹配
	*/
function getDateWithIN(datetime,patternReg){
	var year = datetime.getFullYear();
	var month = datetime.getMonth()+1;//js从0开始取 
	var date = datetime.getDate(); 
	if(month<10){
		month = "0" + month;
	}
	if(date<10){
		date = "0" + date;
	}
	var time = year+"-"+month+"-"+date; //2009-06-12
	
	//添加方法,内容,支持自己定义自己加入
	if(patternReg){
		switch(patternReg){
			case 'yyyyMM':time=year+""+month;break;
			case 'yyyyMMdd':time=year+""+month+""+date;break;
		}
	}
	
	return time;
}

//在一个时间基础上增加时间，年,月,日,明确格式，返回yyyy-MM-dd格式
function AddYMD(oldDate,inYear,inMonth,inDate){
	inYear = inYear||0;
	inMonth = inMonth||0;
	inDate = inDate||0;
	
	var str = oldDate.split('-'); 
	var mydate = new Date(); 
    mydate.setUTCFullYear(str[0], str[1] - 1, str[2]); 
    mydate.setUTCHours(0, 0, 0, 0); 
	
	mydate.setYear(parseInt(mydate.getFullYear())+parseInt(inYear));
	mydate.setMonth(parseInt(mydate.getMonth())+parseInt(inMonth));
	mydate.setDate(parseInt(mydate.getDate())+parseInt(inDate));
	return getDateWithIN(mydate);
}

//实列化一个时间对象，兼容
function instanceMyDate(oldDate){
	var str = oldDate.split('-'); 
	var mydate = new Date(); 
    mydate.setUTCFullYear(str[0], str[1] - 1, str[2]); 
    mydate.setUTCHours(0, 0, 0, 0); 
    return mydate;
}
//获取某年某月有多少天
function getDaysInMonth(year,month){
      month = parseInt(month,10)+1;
      var temp = instanceMyDate(year+"-"+month+"-00");
      return temp.getDate();
}
/**
功能：取RADIO控件的值
返回：如RADIO控件有被选中的项，返回被选中值
      如RADIO控件无被选中的项，返回null
*/
function getRadioValue( obj ) {
   var arrObj=document.getElementsByName(obj);
   for(var i=0;i<arrObj.length;i++)
   {
     if(arrObj.item(i).checked)
         return strNew=arrObj.item(i).getAttribute("value");  
	}
	return null;
}
/*********************************************/
/* Trim the both side blank of the string    */
/*********************************************/
function trim(s)
{
	return trimRight(trimLeft(s))
}
/****************************************/
/* Trim the left blank of the string    */
/****************************************/
function trimLeft(s) {
	while (s.charAt(0) ==" " ||s.charAt(0) =="" ){
		s = s.substr(1,s.length-1)
	}
	return s;
}
/*****************************************/
/* Trim the right blank of the string    */
/*****************************************/
function trimRight(s) {
	while (s.charAt(s.length-1) == " " || s.charAt(s.length-1) == "")	{
		s = s.substr(0,s.length-1)
	}
	return s;
}

////////////////////////jQuery//////////////////////////////
//由于这其中使用了jquery对象，所以需要把jquery引用到此js前面来方能正常使用
/**
 * 使用ajax提交数据
 */
function ajax_post(the_url,the_param,succ_callback){
	jQuery.ajax({
		type:'POST',
		url:the_url,
		data:the_param,
		success:succ_callback,
		error:function(html){
			alert("服务器忙，提交数据失败，代码:" +html.status+ "，请联系管理员！");
		}
	});
}
//将表单元素设置为diabled
function disabled_all(){
	jQuery('input').filter('[type!="button"]').each(function(key,val){
		jQuery(this).attr("disabled","disabled");
	});
	jQuery('select').each(function(key,val){
		jQuery(this).attr("disabled","disabled");
	});
	jQuery('textarea').each(function(key,val){
		jQuery(this).attr("disabled","disabled");
	});
}

//update laozhong 20130702 避免传递空""值过来value时判断为是取值操作而不是赋值操作
function jqName(name,value){
	if(value === undefined) {
		return jQuery(":input[name='"+name+"']").val()||'';
	}else{
		return jQuery(":input[name='"+name+"']").val(value);
	}
}

function jqAlert(msg,name){
	Alert(msg,function(){
		jQuery(":input[name='"+name+"']").focus();
	});
	return;
}

//select 获取值text的方法
function jqSelect(name){
	return jQuery(":input[name='"+name+"'] option:selected").text();
}

/* 列表页面提醒方法 by LCY
function myhover(obj,color,cls){
	obj.onmouseout=function(c,c2){
		return function(){
			obj.style.backgroundColor = c;
			obj.className=c2;
		}
	}(obj.style.backgroundColor,obj.className);
	obj.style.backgroundColor=color;
	obj.className=obj.className.replace(cls,"");
}
*/

function date(format,timestamp){
		var a,jsdate=((timestamp)?new Date(timestamp*1000):new Date());
		var pad=function(n,c){
			if((n=n+"").length<c){
				return new Array(++c-n.length).join("0")+n;
			}else{
				return n;
			}
		};
		var txt_weekdays=["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
		var txt_ordin={1:"st",2:"nd",3:"rd",21:"st",22:"nd",23:"rd",31:"st"};
		var txt_months=["","January","February","March","April","May","June","July","August","September","October","November","December"];
		var f={
			//日期两位02
			d:function(){
				return pad(f.j(),2)
			},
			//星期的前三位如 Tue
			D:function(){
				return f.l().substr(0,3)
			},
			//日期一位02->2
			j:function(){
				return jsdate.getDate()
			},
			//星期的全部txt_weekdays这里得
			l:function(){
				return txt_weekdays[f.w()]
			},
			//下一天一位
			N:function(){
				return f.w()+1
			},
			//txt_ordin的数据
			S:function(){
				return txt_ordin[f.j()]?txt_ordin[f.j()]:'th'
			},
			//日期一位天
			w:function(){
				return jsdate.getDay()
			},
			//距离1月1日有多少天
			z:function(){
				return(jsdate-new Date(jsdate.getFullYear()+"/1/1"))/864e5>>0
			},
			//当前是一年中的第几周?
			W:function(){
				var a=f.z(),b=364+f.L()-a;
				var nd2,nd=(new Date(jsdate.getFullYear()+"/1/1").getDay()||7)-1;
				if(b<=2&&((jsdate.getDay()||7)-1)<=2-b){
					return 1;
				}else{
					if(a<=2&&nd>=4&&a>=(6-nd)){
						nd2=new Date(jsdate.getFullYear()-1+"/12/31");
						return date("W",Math.round(nd2.getTime()/1000));
					}else{
						return(1+(nd<=3?((a+nd)/7):(a-(7-nd))/7)>>0);
					}
				}
			},
			//月份英文
			F:function(){
				return txt_months[f.n()]
			},
			//月份两位
			m:function(){
				return pad(f.n(),2)
			},
			//月份英文两位July-Jul
			M:function(){
				return f.F().substr(0,3)
			},
			//下一月
			n:function(){
				return jsdate.getMonth()+1
			},
			//最后一天是几号
			t:function(){
				var n;
				if((n=jsdate.getMonth()+1)==2){
					return 28+f.L();
				}else{
					if(n&1&&n<8||!(n&1)&&n>7){
						return 31;
					}else{
						return 30;
					}
				}
			},
			//是否是闰年
			L:function(){
				var y=f.Y();
				return(!(y&3)&&(y%1e2||!(y%4e2)))?1:0
			},
			//年份
			Y:function(){
				return jsdate.getFullYear()
			},
			//两位的年份
			y:function(){
				return(jsdate.getFullYear()+"").slice(2)
			},
			//上午还是下午
			a:function(){
				return jsdate.getHours()>11?"pm":"am"
			},
			//大写的上午还是下午
			A:function(){
				return f.a().toUpperCase()
			},
			//不清楚,与时区有关的东西,86.4是平均寿命
			B:function(){
				var off=(jsdate.getTimezoneOffset()+60)*60;
				var theSeconds=(jsdate.getHours()*3600)+(jsdate.getMinutes()*60)+jsdate.getSeconds()+off;
				var beat=Math.floor(theSeconds/86.4);
				if(beat>1000) beat-=1000;
				if(beat<0) beat+=1000;
				if((String(beat)).length==1) beat="00"+beat;
				if((String(beat)).length==2) beat="0"+beat;
				return beat;
			},
			//时间点数
			g:function(){
				return jsdate.getHours()%12||12
			},
			//24小时制
			G:function(){
				return jsdate.getHours()
			},
			//两位的
			h:function(){
				return pad(f.g(),2)
			},
			//两位的时间
			H:function(){
				return pad(jsdate.getHours(),2)
			},
			//分钟的两位
			i:function(){
				return pad(jsdate.getMinutes(),2)
			},
			//秒钟的两位
			s:function(){
				return pad(jsdate.getSeconds(),2)
			},
			//时区+0800
			O:function(){
				var t=pad(Math.abs(jsdate.getTimezoneOffset()/60*100),4);
				if(jsdate.getTimezoneOffset()>0) t="-"+t;
				else t="+"+t;
				return t;
			},
			//时区的+08:00
			P:function(){
				var O=f.O();
				return(O.substr(0,3)+":"+O.substr(3,2))
			},
			//完整的时间
			c:function(){
				return f.Y()+"-"+f.m()+"-"+f.d()+"T"+f.h()+":"+f.i()+":"+f.s()+f.P()
			},
			//长时间/1000
			U:function(){
				return Math.round(jsdate.getTime()/1000)
			}
		};
		return format.replace(/[\\]?([a-zA-Z])/g,function(t,s){
			if(t!=s){
				ret=s;
			}else if(f[s]){
				ret=f[s]();
			}else{
				ret=s;
			} 
			return ret;
	});
}
//按照格式设置哪个name的时间,也可以指定时间;
function setDate(name,format,timestamp){
	if(jqName(name)!=''){
		return;
	}else{
		jqName(name,date(format,timestamp))
	}
}