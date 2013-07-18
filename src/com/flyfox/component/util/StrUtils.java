package com.flyfox.component.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 提供与字符串操作有关的通用方法 ,全部方法都是静态方法 ,全部变量都是静态常量 .
 * 
 * @author 王平 . 2008.11.07
 * 
 */

public class StrUtils {

	private static final String COLOR_RED = "#df0000";

	private StrUtils() {
		// TOINIT
	}

	public static String toUpperCase(String instr) {
		return instr == null ? instr : instr.toUpperCase();
	}

	/**
	 * 过滤 ;当instr==null时返回长度为0的""; <br>
	 * 与 nvl(...)系的区别在于只处理null ,不处理长度为0的"";
	 * 
	 * @param instr
	 * @return
	 */
	public static String nvl2(String instr) {
		return instr == null ? "" : instr;
	}

	/**
	 * 过滤 ;当instr==null时返回长度为0的""; <br>
	 * 与 nvl(...)系的区别在于只处理null ,不处理长度为0的"";
	 * 
	 * @param instr
	 * @return
	 */
	public static Object nvl2(Object instr) {
		return instr == null ? "" : instr;
	}

	/**
	 * 过滤 ,把null和长度为0的""当成同一种情况处理; <br>
	 * 当instr==null||"".equals(instr)时返回defaultValue ;其它情况返回 instr
	 * 
	 * @param instr
	 * @param defaultValue
	 * @return
	 */
	public static String nvl(String instr, String defaultValue) {
		return instr == null || "".equals(instr) ? defaultValue : instr;
	}

	/**
	 * 过滤 ; 当instr==null||instr.equals(tagstr)时返回defaultValue ;其它情况返回 instr <br>
	 * 与 nvl(...)系的区别在于只处理null ,不处理长度为0的"";
	 * 
	 * @param instr
	 * @param tagstr
	 * @param defaultValue
	 * @return
	 */
	public static String nvl2(String instr, String tagstr, String defaultValue) {
		return instr == null || instr.equals(tagstr) ? defaultValue : instr;
	}

	/**
	 * 过滤 ,把null和长度为0的""当成同一种情况处理; <br>
	 * 当instr==null||"".equals(instr)时返回defaultValue ;其它情况返回 instr
	 * 
	 * @param instr
	 * @param defaultValue
	 * @return
	 */
	public static String nvl(Object instr, String defaultValue) {
		return instr == null || "".equals(instr.toString()) ? defaultValue
				: instr.toString();
	}

	/**
	 * 过滤 ,instr==null时返回defaultvalue; <br>
	 * 把null和长度为0的""当成同一种情况处理;
	 * 
	 * @param instr
	 * @param defaultValue
	 * @return
	 */
	public static Object nvl(Object instr, Object defaultValue) {
		return instr == null || "".equals(instr.toString()) ? defaultValue
				: instr;
	}

	/**
	 * 不会抛NullPointerException 的trim() <br>
	 * 传入null会返回null
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		if (str == null)
			return str;
		return str.trim();
	}

	/**
	 * backColorForChange 为合同变更页面提供的提示背景色 ; <br>
	 * 提供给HTML页面 ,如果obj==null&&obj1==null或者是obj.equals(obj1)那么会返回一段
	 * style='background-color:color;' 代码 ;否则返回''; <br>
	 * 如果传入的color==null那么返回红色背景 ;
	 * 
	 * @param obj
	 * @param obj1
	 * @return
	 */
	public static String backColor4C(String obj, String obj1, String color) {
		if (obj == null)
			return back4CPro(obj1 == null, color == null ? COLOR_RED : color);
		return back4CPro(obj.equals(obj1), color == null ? COLOR_RED : color);
	}

	/**
	 * backColorForChange 为合同变更页面提供的提示背景色 ; <br>
	 * 提供给HTML页面 ,如果obj==null&&obj1==null或者是obj.equals(obj1)那么会返回一段
	 * style='background-color:color;' 代码 ;否则返回''; <br>
	 * 如果传入的color==null那么返回红色背景 ;
	 * 
	 * @param obj
	 * @param obj1
	 * @return
	 */
	public static String backColor4C(Date obj, Date obj1, String color) {
		if (obj == null)
			return back4CPro(obj1 == null, color == null ? COLOR_RED : color);
		if (obj1 == null)
			return back4CPro(obj == null, color == null ? COLOR_RED : color);
		return back4CPro(DateUtils.format(obj).equals(DateUtils.format(obj1)),
				color == null ? COLOR_RED : color);
	}

	/**
	 * backColorForChange 为合同变更页面提供的提示背景色 ; <br>
	 * 提供给HTML页面 ,如果obj==null&&obj1==null或者是obj.equals(obj1)那么会返回一段
	 * style='background-color:color;' 代码 ;否则返回''; <br>
	 * 如果传入的color==null那么返回红色背景 ;
	 * 
	 * @param obj
	 * @param obj1
	 * @return
	 */
	public static String backColor4C(BigDecimal obj, BigDecimal obj1,
			String color) {
		if (obj == null)
			return back4CPro(obj1 == null, color == null ? COLOR_RED : color);
		if (obj1 == null)
			return back4CPro(obj == null, color == null ? COLOR_RED : color);
		return backColor4C(obj.doubleValue(), obj1.doubleValue(), color);
	}

	/**
	 * backColorForChange 为合同变更页面提供的提示背景色 ; <br>
	 * 提供给HTML页面 ,如果obj==null&&obj1==null或者是obj.equals(obj1)那么会返回一段
	 * style='background-color:color;' 代码 ;否则返回''; <br>
	 * 如果传入的color==null那么返回红色背景 ;
	 * 
	 * @param obj
	 * @param obj1
	 * @return
	 */
	public static String backColor4C(double obj, double obj1, String color) {
		return back4CPro(obj == obj1, color == null ? COLOR_RED : color);
	}

	public static String back4CPro(boolean flag, String color) {
		return flag == true ? "" : " style='color:" + color + "' ";
	}

	/**
	 * 是否 null 或者长度为0的字符串
	 * 
	 * @author wangp
	 * @since 2008.12.02
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str);
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	public static String apadLeft(double a, int b, int len) {
		return apadLeft(String.valueOf(a), String.valueOf(b), len);
	}

	public static String apadRight(double a, int b, int len) {
		return apadRight(String.valueOf(a), String.valueOf(b), len);
	}

	public static String apadLeft(String str, String str2, int len) {
		if (str == null || str.length() == len || str2 == null)
			return str;
		if (str.length() > len)
			return str.substring(str.length() - len, len);
		return apadpro(str, str2, len, true);
	}

	public static String apadRight(String str, String str2, int len) {
		if (str == null || str.length() == len || str2 == null)
			return str;
		if (str.length() > len)
			return str.substring(0, len);
		return apadpro(str, str2, len, false);
	}

	private static String apadpro(String a, String b, int len,
			boolean appendleft) {
		int f = len - a.length();
		for (int i = 0; i < f; i++) {
			a = appendleft == true ? b + a : a + b;
		}
		return a;
	}

	/**
	 * 把数组里的元素用tg连接后返回 ,如果tg==null会被替换成""
	 * 
	 * @author 王平
	 * @since 2009.01.08
	 * @param res
	 *            9,2,2,2 ,,,,,
	 * @param tg
	 * @return
	 */
	public static String concat(String[] res, String tg) {
		StringBuffer sb = new StringBuffer();
		if (res == null)
			return null;
		tg = nvl2(tg);
		for (int i = 0; i < res.length; i++) {
			sb.append(res[i]).append(tg);
		}
		if (sb.length() > 0)
			sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * 把数组里的元素用tg连接后返回，遇到数组中null元素时忽略 ,如果tg==null会被替换成"" ，
	 * 
	 * @author 王平
	 * @since 2009.01.08
	 * @param res
	 *            9,2,2,2 ,,,,,
	 * @param tg
	 * @return
	 */
	public static String concatMaskNull(String[] res, String tg) {
		StringBuffer sb = new StringBuffer();
		if (res == null)
			return null;
		for (int i = 0; i < res.length; i++) {
			if (res[i] == null)
				continue;
			sb.append(res[i]).append(nvl2(tg));
		}
		if (sb.length() > 0)
			sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * 比较 str1 和 str2 如果都是 null 或者 str1.equals(str2) 返回 true 表示一样 ;
	 * 
	 * @author wangp
	 * @since 2009.01.10
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean compStr(String str1, String str2) {
		if (str1 == null && str2 == null)
			return true;
		if (str1 != null && str1.equals(str2))
			return true;
		return false;
	}

	/**
	 * 比较obj1 和 obj2 如果都是null或者obj1的数值内容==obj2的数值内容 ,或者obj1代表的时间==obj2代表的时间
	 * 返回true ;表示逻辑"一样"
	 * 
	 * @author 王平
	 * @since 2009.01.10
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static boolean compStr(Object obj1, Object obj2) {
		if (obj1 == obj2)
			return true;
		if (obj1 == null && obj2 == null)
			return true;
		if (obj1 != null && obj1.equals(obj2))
			return true;
		if (obj1 instanceof java.math.BigDecimal
				&& obj2 instanceof java.math.BigDecimal)
			return ((java.math.BigDecimal) obj1)
					.compareTo((java.math.BigDecimal) obj2) == 0;
		return false;
	}

	/**
	 * 取内容的toString
	 * 
	 * @author wangp
	 * @since 2009.01.10
	 * @param obj1
	 * @return
	 */
	public static String toString(Object obj1) {
		if (obj1 == null)
			return null;
		if (obj1 instanceof java.util.Date)
			return DateUtils.format((java.util.Date) obj1);
		if (obj1 instanceof java.math.BigDecimal)
			return String.valueOf(((java.math.BigDecimal) obj1).doubleValue());
		return obj1.toString();
	}

	/**
	 * 转小写
	 * 
	 * @author wangp
	 * @since 2009.01.10
	 * @param str
	 * @return
	 */
	public static String toLowerCase(String str) {
		return str == null ? null : str.toLowerCase();
	}

	/**
	 * 首字母大写 ,其余不变
	 * 
	 * @author wangp
	 * @since 2009.01.10
	 * @param string
	 * @return
	 */
	public static String toUpperCaseFirst(String str) {
		if (str == null)
			return null;
		if (str.length() == 0)
			return str;
		String pre = String.valueOf(str.charAt(0));
		return str.replaceFirst(pre, pre.toUpperCase());
	}

	/**
	 * 首字母小写 ,其余不变
	 * 
	 * @author wangp
	 * @since 2009.04.30
	 * @param string
	 * @return
	 */
	public static String toLowerCaseFirst(String str) {
		if (str == null)
			return null;
		if (str.length() == 0)
			return str;
		String pre = String.valueOf(str.charAt(0));
		return str.replaceFirst(pre, pre.toLowerCase());
	}

	/**
	 * 清除字符串中所有的空格 ,传入null返回null
	 * 
	 * @author wangp
	 * @since 2009.02.06
	 * @param str
	 * @return
	 */
	public static String clear(String str) {
		return clear(str, " ");
	}

	/**
	 * 清除str中出现的所有str2字符序列 直到结果中再也找不出str2为止 str2 == null时 返回str
	 * 
	 * @author wangp
	 * @since 2009.02.06
	 * @param str
	 *            原始字符串
	 * @param str2
	 *            清除的目标
	 * @return
	 */
	public static String clear(String str, String str2) {
		if (str == null)
			return str;
		if (str2 == null)
			return str;
		String reg = "(" + str2 + ")+";
		Pattern p = Pattern.compile(reg);
		while (p.matcher(str).find()) {
			str = str.replaceAll(reg, "");
		}
		return str;
	}

	/**
	 * 如果str的长度超过了c则取c-sub.length长度,然后拼上sub结尾
	 * 
	 * @author wangp
	 * @since 2009.06.11
	 * @param str
	 * @param c
	 * @param sub
	 * @return
	 */
	public static String suojin(String str, int c, String sub) {
		if (isEmpty(str))
			return str;
		if (str.length() <= c)
			return str;
		sub = nvl2(sub);
		c = c - sub.length();
		c = c > str.length() ? 0 : c;
		str = str.substring(0, c);
		return str + sub;
	}

	/**
	 * 如果str的长度超过了length,取前length位然后拼上...
	 * 
	 * @author yimian
	 * @since 2009.06.11
	 * @param str
	 * @param length
	 * @return
	 */
	public static String suojin(String str, int length) {
		return suojin(str, length, "…");
	}

	public static String nvl(String a, String b, String c) {
		return isEmpty(a) ? b : c;
	}

	/**
	 * 类似于String.substr ；不同在于传入的长度参数为oracle中的长度定义数
	 * 
	 * @param data
	 * @param len
	 * @return
	 */
	public static String substr(String str, int len) {
		if (isEmpty(str))
			return str;
		int res = 0;
		int l = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) <= 255)
				res += 1;
			else
				res += 2;
			if (res > len)
				break;
			l++;
		}
		return str.substring(0, l);
	}

	public static String replaceOnce(String text, String searchString, String replacement)
    {
        return replace(text, searchString, replacement, 1);
    }

    public static String replace(String text, String searchString, String replacement)
    {
        return replace(text, searchString, replacement, -1);
    }

    public static String replace(String text, String searchString, String replacement, int max)
    {
        if(isEmpty(text) || isEmpty(searchString) || replacement == null || max == 0)
            return text;
        int start = 0;
        int end = text.indexOf(searchString, start);
        if(end == -1)
            return text;
        int replLength = searchString.length();
        int increase = replacement.length() - replLength;
        increase = increase >= 0 ? increase : 0;
        increase *= max >= 0 ? max <= 64 ? max : 64 : 16;
        StringBuffer buf = new StringBuffer(text.length() + increase);
        do
        {
            if(end == -1)
                break;
            buf.append(text.substring(start, end)).append(replacement);
            start = end + replLength;
            if(--max == 0)
                break;
            end = text.indexOf(searchString, start);
        } while(true);
        buf.append(text.substring(start));
        return buf.toString();
    }

	public static String getGUID() {
		return UUID.randomUUID().toString().toUpperCase();
	}

	/**
	 * 判断一个字符串是否是GUID
	 * 
	 * @param guid
	 * @return 
	 */
	public static boolean isNotGUID(String guid) {
		return !isGUID(guid);
	}
	
	/**
	 * 判断一个字符串是否是GUID
	 * 
	 * @param guid
	 * @return 
	 */
	public static boolean isGUID(String guid) {
		if (guid != null && guid.trim().length() == 36
				&& guid.indexOf('_') == -1) {
			String regex = "\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(guid);
			return matcher.find();
		}
		return false;
	}
	
}
