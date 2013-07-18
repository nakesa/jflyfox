package com.flyfox.component.util;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class Reflection {

	/**
	 * 取得无参数方法返回值类型
	 * 
	 * @param obj
	 * @param fieldname
	 * @return
	 * @throws Exception
	 */
	public static Class<?> getReturnType(Class<?> cls, String methodName) throws Exception {
		Method method = cls.getDeclaredMethod(methodName);
		// System.out.println(method.getGenericReturnType());
		return method.getReturnType();
	}

	/**
	 * 获取对象属性
	 * 
	 * @param owner
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	public static Object getProperty(Object owner, String fieldName) throws Exception {
		Class<?> ownerClass = owner.getClass();
		Field field = ownerClass.getField(fieldName);
		Object property = field.get(owner);
		return property;
	}

	/**
	 * 获取对象静态属性
	 * 
	 * @param className
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	public static Object getStaticProperty(Object owner, String fieldName) throws Exception {
		Class<?> ownerClass = owner.getClass();
		Field field = ownerClass.getField(fieldName);
		Object property = field.get(owner);
		return property;
	}

	/**
	 * 获取对象静态属性
	 * 
	 * @param className
	 * @param fieldName
	 * @return
	 * @throws Exception
	 */
	public static Object getStaticProperty(String className, String fieldName) throws Exception {
		Class<?> ownerClass = Class.forName(className);
		Field field = ownerClass.getField(fieldName);
		Object property = field.get(className);
		return property;
	}

	/**
	 * 执行某对象的方法
	 * 
	 * @param owner
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object invokeMethodGet(Object owner, String methodName, Object[] args) throws Exception {
		Class<?> ownerClass = owner.getClass();
		Class<?>[] argsClass = new Class[args.length];
		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}
		Method method = ownerClass.getMethod(methodName, argsClass);
		return method.invoke(owner, args);
	}

	/**
	 * 执行某对象的方法
	 * 
	 * @param owner
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static void invokeMethodSet(Object owner, String methodName, Object[] args) throws Exception {
		Method method = null;
		// 取出所有的属性的定义的方法，不包括其他方法
		Method[] DeclaredM = owner.getClass().getDeclaredMethods();
		for (int i = 0; i < DeclaredM.length; i++) {
			if ((DeclaredM[i].getName()).equals(methodName)) {
				method = DeclaredM[i];
				break;
			}
		}
		for (int i = 0; i < args.length; i++) {
			// 根据方法定义自动转换类型，只支持一个参数的写法
			Class<?> _class = method.getParameterTypes()[0];
			if ((_class == Float.class || _class == Float.TYPE)) {
				method.invoke(owner, new Object[] { NumberUtils.parseFloat(String.valueOf(args[0])) });
			} else if (_class == String.class) {
				method.invoke(owner, new Object[] { String.valueOf(args[0]) });
			} else if (_class == Long.class || _class == Long.TYPE) {
				method.invoke(owner, new Object[] { NumberUtils.parseLong(String.valueOf(args[0])) });
			} else if (_class == Double.class || _class == Double.TYPE) {
				method.invoke(owner, new Object[] { NumberUtils.parseDbl(String.valueOf(args[0])) });
			} else if (_class == BigDecimal.class) {
				method.invoke(owner, new Object[] { NumberUtils.getBigDecimal(String.valueOf(args[0]), 0) });
			} else if (_class == Integer.class || _class == Integer.TYPE) {
				method.invoke(owner, new Object[] { NumberUtils.parseInt(String.valueOf(args[0])) });
			} else if (_class == Date.class) {
				method.invoke(owner, new Object[] { DateUtils.parse(String.valueOf(args[0])) });
			} else if (_class == Timestamp.class) {
				method.invoke(owner, new Object[] { DateUtils.parseTimestamp(String.valueOf(args[0])) });
			} else {
				System.err.println("不支持的参数类型: " + _class + " | " + methodName + " | " + String.valueOf(args[0]));
			}
		}
	}

	/**
	 * 执行某对象的方法
	 * 
	 * @param owner
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object invokeMethod(Object owner, String methodName, Object[] args, Class<?> clx) throws Exception {
		Class<?>[] clxs = new Class<?>[1];
		clxs[0] = clx;
		Class<? extends Object> cls = owner.getClass();
		Method method = cls.getMethod(methodName, clxs);
		return method.invoke(owner, args);
	}

	/**
	 * 执行某对象的静态方法
	 * 
	 * @param className
	 * @param methodName
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object invokeStaticMethod(String className, String methodName, Object[] args) throws Exception {
		Class<?> ownerClass = Class.forName(className);
		Class<?>[] argsClass = new Class[args.length];
		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}

		Method method = ownerClass.getMethod(methodName, argsClass);
		return method.invoke(null, args);

	}

	/**
	 * 新建实例
	 * 
	 * @param className
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static Object newInstance(String className, Object[] args) throws Exception {

		Class<?> newoneClass = Class.forName(className);
		Class<?>[] argsClass = new Class[args.length];
		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}

		Constructor<?> cons = newoneClass.getConstructor(argsClass);
		return cons.newInstance(args);
	}

	/**
	 * 判断是否为某个类的实例
	 * 
	 * @param obj
	 * @param cls
	 * @return
	 */
	public static boolean isInstance(Object obj, Class<?> cls) {
		return cls.isInstance(obj);
	}

	/**
	 * 得到数组中的某个元素
	 * 
	 * @param array
	 * @param index
	 * @return
	 */
	public static Object getByArray(Object array, int index) {
		return Array.get(array, index);
	}
}