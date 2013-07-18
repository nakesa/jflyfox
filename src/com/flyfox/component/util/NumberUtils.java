package com.flyfox.component.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 在此类中添加与日期操作有关的工具函数 . 本类不可实例化 ,所有方法为静态 ,所有属性为静态常量 
 * @author 王平 2008.09.24
 */
public class NumberUtils {
	private NumberUtils(){
		
	}
	/**
	 * 将传入的字符串转成int型数据 . 遇到任何错误返回0
	 * @param str 待解析的字符串
	 * @return 解析结果 
	 */
	public static int parseInt0(String str){
		return parseInt(str ,0);
	}
	/**
	 * 将传入的字符串转成int型数据 . 遇到任何错误返回replaceWith
	 * @param str 待解析的字符串
	 * @param defaultValue 遇到错误时的替换数字 . 
	 * @return 解析结果 
	 */
	public static int parseInt(String str ,int defaultValue){
		try{
			defaultValue = Integer.parseInt(str);
		} catch(Exception e){}
		return defaultValue ;
	}
	/**
	 * 将传入的BigDecimal转成int型数据 . 遇到任何错误返回0
	 * @param dec
	 * @return 解析结果 
	 */
	public static int parseInt(BigDecimal dec){
		if(dec==null)
			return 0;
		else 
			return dec.intValue();
	}
	public static int parseInt(Object dec){
		if(dec==null)
			return 0;
		else 
			return parseInt(dec.toString());
	}
	/**
	 * 将传入的BigDecimal转成double型数据 . 遇到任何错误返回0
	 * @param dec
	 * @return 解析结果 
	 */
	public static double parseDbl(BigDecimal dec){
		if(dec==null)
			return 0;
		else 
			return dec.doubleValue();
	}
	
	public static String formatDoubleToString(double dec){
			String number ="0";
			try{
				DecimalFormat myformat1 = new DecimalFormat("0.00");// 使用系统默认的格式
				number = myformat1.format(dec);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			return number;
		}
	
	/**
	 * 将传入的字符串转成int型数据 . 遇到任何错误返回0
	 * @param str 待解析的字符串
	 * @return 解析结果 
	 */
	public static int parseInt(String str){
		return parseInt(str ,0);
	}
	/**
	 * 将传入的字符串转成double型数据 . 遇到任何错误返回0
	 * @param str 待解析的字符串
	 * @return 解析结果 
	 */
	public static double parseDbl0(String str){
		return parseDbl(str ,0);
	}
	/**
	 * 将传入的字符串转成double型数据 . 遇到任何错误返回0
	 * @param str 待解析的字符串
	 * @return 解析结果 
	 */
	public static double parseDbl(String str){
		return parseDbl(str ,0);
	}
	/**
	 * 将传入的字符串转成double型数据 . 遇到任何错误返回replaceWith
	 * @param str 待解析的字符串
	 * @param defaultValue 遇到错误时的替换数字 . 
	 * @return 解析结果 
	 */
	public static double parseDbl(String str ,double defaultValue){
		try{
			defaultValue = Double.parseDouble(str);
		} catch(Exception e){}
		return defaultValue ;
	}
	/**
	 * 获取传入数字的字符串形式 , 如果是0则返回&nbsp;
	 * @param dbl 待转化数字
	 * @return 转化结果 
	 */
	public static String toStr0Nbsp(double dbl){
		return toStr(dbl ,0 ,"&nbsp;");
	}
	/**
	 * 将参数dbl转成字符串 , 当dbl==dbl2时 ,返回 replaceWith
	 * @param dbl 待转化的数字
	 * @param dbl2 需要过滤的数字
	 * @param defaultValue 用于替换被过滤数字的字符串
	 * @return 转化结果 
	 */
	public static String toStr(double dbl ,double dbl2 ,String defaultValue){
		return dbl == dbl2 ? defaultValue : String.valueOf(dbl) ;
	}
	/**
	 * 返回保留n位小数的形式 ,不足补0
	 * 四舍五入;
	 * @param a
	 * @param n
	 * @return
	 */
	public static String cut(double a ,int n){
		if(n<0)
			n=0;
		java.math.BigDecimal dbl_num = new java.math.BigDecimal(a).setScale(n,java.math.BigDecimal.ROUND_HALF_UP);
		return String.valueOf(dbl_num) ; 
	}
	/**
	 * 返回保留n位小数的形式 ,不足补0
	 * 四舍五入;
	 * @param a
	 * @param n
	 * @return
	 */
	public static String cut(java.math.BigDecimal a ,int n){
		if(n<0)
			n=0;
		if(a == null)
			a = java.math.BigDecimal.ZERO;
		java.math.BigDecimal dbl_num = a.setScale(n,java.math.BigDecimal.ROUND_HALF_UP);
		return String.valueOf(dbl_num) ; 
	}
	/**
	 * 返回保留n位小数的形式 ,不足补0
	 * 四舍五入;
	 * @param a
	 * @param n
	 * @return
	 */
	public static String cut(String a ,int n){
		if(n<0)
			n=0;
		double tem = parseDbl(a);
		java.math.BigDecimal dbl_num = new java.math.BigDecimal(tem).setScale(n,java.math.BigDecimal.ROUND_HALF_UP);
		return String.valueOf(dbl_num) ; 
	}
	/**
	 * 判断传入的字符串是不是个数字;是就返回true
	 * @author wangp
	 * @since 2008.12.16
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str){
		try {
			Double.parseDouble(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static float parseFloat(String str) {
		return parseFloat(str, 0);
	}
	public static float parseFloat(String str ,float b) {
		try{
			return Float.parseFloat(str);
		}catch(Exception e){
			return b;
		}
	}
	/**
	 * 将传入的BigDecimal转成float型数据 . 遇到任何错误返回0
	 * @param dec
	 * @return 解析结果 
	 */
	public static float parseFloat(BigDecimal dec){
		if(dec==null)
			return 0F;
		else 
			return dec.floatValue();
	}
	/**
	 * 取得值等于传入数字的Bigdecimal对象 ,传入null或""返回null
	 * @author wangp
	 * @since 2009.04.30
	 * @param parameter
	 * @return
	 */
	public static BigDecimal getBigDecimal(String parameter) {
		BigDecimal res = null;
		if(!StrUtils.isEmpty(parameter))
			res = new BigDecimal(parameter);
		return res;
	}
	public static BigDecimal getBigDecimal(int parameter) {
		return new BigDecimal(parameter);
	}
	/**
	 * 取得值等于传入数字的Bigdecimal对象 ,传入null或""返回null
	 * @author wangp
	 * @since 2009.04.30
	 * @param parameter
	 * @return
	 */
	public static BigDecimal getBigDecimalNull(BigDecimal dec) {
		if(dec==null)
			return new BigDecimal(0);
		else
			return dec;
	}
	
	/**
	 * 取得值等于传入数字的Bigdecimal对象 ,传入null或""返回值==a的Bigdecimal对象
	 * @author wangp
	 * @since 2009.04.30
	 * @param parameter
	 * @return
	 */
	public static BigDecimal getBigDecimal(String parameter ,double a) {
		BigDecimal res = null;
		if(!StrUtils.isEmpty(parameter))
			res = new BigDecimal(parameter);
		return res != null?res : BigDecimal.valueOf(a);
	}
	/**
	 * 遇到错误返回0L 
	 * @author 王平
	 * @since 2009.04.30
	 * @param str
	 * @return
	 */
	public static long parseLong(String str) {
		return parseLong(str, 0l);
	}
	/**
	 * 遇到错误返回defaultValue
	 * @author 王平
	 * @since 2009.04.30
	 * @param str
	 * @return
	 */
	public static long parseLong(String str ,long defaultValue){
		try{
			defaultValue = Long.parseLong(str);
		} catch(Exception e){}
		return defaultValue ;
	}
	/**
	 * 将传入的BigDecimal转成long型数据 . 遇到任何错误返回0
	 * @param dec
	 * @return 解析结果 
	 */
	public static long parseLong(BigDecimal dec){
		if(dec==null)
			return 0L;
		else 
			return dec.longValue();
	}
	public static BigDecimal add(BigDecimal big1, BigDecimal big2) {
		if(big1==null)
			big1 = BigDecimal.ZERO;
		if(big2==null)
			big2 = BigDecimal.ZERO;
		return big1.add(big2);
	}
	public static  String parseNumber0(BigDecimal dec){
		int a = 0 ;
		double b = 0.0 ;
		dec = dec == null ? BigDecimal.ZERO : dec;
		try {
			a = dec.intValueExact();
		} catch (RuntimeException e) {
			b = dec.doubleValue();
		}
		if(b != 0.0)
			return String.valueOf(b);
		else 
			return String.valueOf(a);
	}
	public static  String parseNumber0(int dec){
		return String.valueOf(dec);
	}
	/**
	 * 取得值等于传入数字的Bigdecimal对象 ,传入null或""返回null
	 * @author wangp
	 * @since 2009.04.30
	 * @param parameter
	 * @return
	 */
	public static BigDecimal getBigDecimalZero(String parameter) {
		BigDecimal res = new BigDecimal(0);
		if(!StrUtils.isEmpty(parameter))
			res = new BigDecimal(parameter);
		return res;
	}
	
	public static BigDecimal getBigNull(BigDecimal dec,BigDecimal anthor) {
		if(dec==null)
			return anthor;
		else
			return dec;
	}
	/**
	 * 根据既定的规则保留小数
	 * @author xiachagnsong
	 * @param v 需要按规则保留的数字
	 * @param scale 小数点后保留几位
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(v);
		return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}