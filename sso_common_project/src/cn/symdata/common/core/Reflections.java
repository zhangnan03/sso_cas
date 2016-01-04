package cn.symdata.common.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Reflections {
	private static final String SETTER_PREFIX = "set";

	private static final String GETTER_PREFIX = "get";

	private static final String CGLIB_CLASS_SEPARATOR = "$$";

	private static Logger logger = LoggerFactory.getLogger(Reflections.class);

	/**
	 * 
	 *@param obj
	 *@param propertyName
	 *@return
	 *@Description:调用Getter方法
	 *@Author:wangdezhen#zplay.cn
	 *@Since:2015年1月20日  上午10:28:51
	 *@Version:1.0
	 */
	public static Object invokeGetter(Object obj, String propertyName) {
		String getterMethodName = GETTER_PREFIX + StringUtils.capitalize(propertyName);
		return invokeMethod(obj, getterMethodName, new Class[] {}, new Object[] {});
	}

	/**
	 * 
	 *@param obj
	 *@param propertyName
	 *@param value
	 *@return
	 *@Description:调用Setter方法, 仅匹配方法名。
	 *@Author:wangdezhen#zplay.cn
	 *@Since:2015年1月20日  上午10:28:51
	 *@Version:1.0
	 */
	public static void invokeSetter(Object obj, String propertyName, Object value) {
		String setterMethodName = SETTER_PREFIX + StringUtils.capitalize(propertyName);
		invokeMethodByName(obj, setterMethodName, new Object[] { value });
	}

	/**
	 *@param obj
	 *@param fieldName
	 *@return
	 *@Description:直接读取对象属性值, 无视private/protected修饰符, 不经过getter函数.
	 *@Author:wangdezhen#zplay.cn
	 *@Since:2015年1月20日  上午10:28:51
	 *@Version:1.0
	 */
	public static Object getFieldValue(final Object obj, final String fieldName) {
		Field field = getAccessibleField(obj, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
		}

		Object result = null;
		try {
			result = field.get(obj);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常{}", e.getMessage());
		}
		return result;
	}

	/**
	 *@param obj
	 *@param propertyName
	 *@param value
	 *@return
	 *@Description:直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数..
	 *@Author:wangdezhen#zplay.cn
	 *@Since:2015年1月20日  上午10:28:51
	 *@Version:1.0
	 */
	public static void setFieldValue(final Object obj, final String fieldName, final Object value) {
		Field field = getAccessibleField(obj, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + obj + "]");
		}

		try {
			field.set(obj, value);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}
	}

	/**
	 *@param obj
	 *@param propertyName
	 *@param parameterTypes
	 *@return
	 *@Description:直接调用对象方法, 无视private/protected修饰符
	 *用于一次性调用的情况，否则应使用getAccessibleMethod()函数获得Method后反复调用
	 *同时匹配方法名+参数类型
	 *@Author:wangdezhen#zplay.cn
	 *@Since:2015年1月20日  上午10:28:51
	 *@Version:1.0
	 */
	public static Object invokeMethod(final Object obj, final String methodName, final Class<?>[] parameterTypes,
			final Object[] args) {
		Method method = getAccessibleMethod(obj, methodName, parameterTypes);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
		}

		try {
			return method.invoke(obj, args);
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
	}

	/**
	 *@param obj
	 *@param propertyName
	 *@param value
	 *@return
	 *@Description:直接调用对象方法, 无视private/protected修饰符
	 *用于一次性调用的情况，否则应使用getAccessibleMethodByName()函数获得Method后反复调用
	 *只匹配函数名，如果有多个同名函数调用第一个。
	 *@Author:wangdezhen#zplay.cn
	 *@Since:2015年1月20日  上午10:28:51
	 *@Version:1.0
	 */
	public static Object invokeMethodByName(final Object obj, final String methodName, final Object[] args) {
		Method method = getAccessibleMethodByName(obj, methodName);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + obj + "]");
		}

		try {
			return method.invoke(obj, args);
		} catch (Exception e) {
			throw convertReflectionExceptionToUnchecked(e);
		}
	}

	/**
	 *@param obj
	 *@param fieldName
	 *@return
	 *@Description:循环向上转型, 获取对象的DeclaredField, 并强制设置为可访问.如向上转型到Object仍无法找到, 返回null.
	 *@Author:wangdezhen#zplay.cn
	 *@Since:2015年1月20日  上午10:31:11
	 *@Version:1.0
	 */
	public static Field getAccessibleField(final Object obj, final String fieldName) {
		Validate.notNull(obj, "object can't be null");
		Validate.notBlank(fieldName, "fieldName can't be blank");
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				Field field = superClass.getDeclaredField(fieldName);
				makeAccessible(field);
				return field;
			} catch (NoSuchFieldException e) {// NOSONAR
				// Field不在当前类定义,继续向上转型
			}
		}
		return null;
	}
	/**
	 *@param obj
	 *@param methodName
	 *@param parameterTypes
	 *@return
	 *@Description:循环向上转型, 获取对象的DeclaredMethod,并强制设置为可访问.
	 *如向上转型到Object仍无法找到, 返回null.用于方法需要被多次调用的情况. 先使用本函数先取得Method,然后调用Method.invoke(Object obj, Object... args)
	 *@Author:wangdezhen#zplay.cn
	 *@Since:2015年1月20日  上午10:31:28
	 *@Version:1.0
	 */
	public static Method getAccessibleMethod(final Object obj, final String methodName,
			final Class<?>... parameterTypes) {
		Validate.notNull(obj, "object can't be null");
		Validate.notBlank(methodName, "methodName can't be blank");

		for (Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass()) {
			try {
				Method method = searchType.getDeclaredMethod(methodName, parameterTypes);
				makeAccessible(method);
				return method;
			} catch (NoSuchMethodException e) {
				// Method不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	/**
	 * 
	 *@param obj
	 *@param methodName
	 *@return
	 *@Description:循环向上转型, 获取对象的DeclaredMethod,并强制设置为可访问.
	 * 如向上转型到Object仍无法找到, 返回null.
	 * 只匹配函数名。
	 *@Author:wangdezhen#zplay.cn
	 *@Since:2015年1月20日  上午10:32:01
	 *@Version:1.0
	 */
	public static Method getAccessibleMethodByName(final Object obj, final String methodName) {
		Validate.notNull(obj, "object can't be null");
		Validate.notBlank(methodName, "methodName can't be blank");

		for (Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass()) {
			Method[] methods = searchType.getDeclaredMethods();
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					makeAccessible(method);
					return method;
				}
			}
		}
		return null;
	}
	/**
	 *@param method
	 *@Description:改变private/protected的方法为public，尽量不调用实际改动的语句，避免JDK的SecurityManager抱怨。
	 *@Author:wangdezhen#zplay.cn
	 *@Since:2015年1月20日  上午10:32:19
	 *@Version:1.0
	 */
	public static void makeAccessible(Method method) {
		if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers()))
				&& !method.isAccessible()) {
			method.setAccessible(true);
		}
	}

	/**
	 * 
	 *@param field
	 *@Description:改变private/protected的成员变量为public，尽量不调用实际改动的语句，避免JDK的SecurityManager抱怨。
	 *@Author:wangdezhen#zplay.cn
	 *@Since:2015年1月20日  上午10:32:31
	 *@Version:1.0
	 */
	public static void makeAccessible(Field field) {
		if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers()) || Modifier
				.isFinal(field.getModifiers())) && !field.isAccessible()) {
			field.setAccessible(true);
		}
	}

	/**
	 * 
	 *@param clazz
	 *@return
	 *@Description:通过反射, 获得Class定义中声明的泛型参数的类型, 注意泛型必须定义在父类处
	 *如无法找到, 返回Object.class.
	 *@Author:wangdezhen#zplay.cn
	 *@Since:2015年1月20日  上午10:32:41
	 *@Version:1.0
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getClassGenricType(@SuppressWarnings("rawtypes") final Class clazz) {
		return (Class<T>) getClassGenricType(clazz, 0);
	}
	/**
	 * 
	 *@param clazz
	 *@param index
	 *@return
	 *@Description: 通过反射, 获得Class定义中声明的父类的泛型参数的类型
	 *如无法找到, 返回Object.class.
	 *@Author:wangdezhen#zplay.cn
	 *@Since:2015年1月20日  上午10:33:13
	 *@Version:1.0
	 */
	public static Class<?> getClassGenricType(final Class<?> clazz, final int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if ((index >= params.length) || (index < 0)) {
			logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
			return Object.class;
		}

		return (Class<?>) params[index];
	}

	public static Class<?> getUserClass(Object instance) {
		Validate.notNull(instance, "Instance must not be null");
		Class<?> clazz = instance.getClass();
		if ((clazz != null) && clazz.getName().contains(CGLIB_CLASS_SEPARATOR)) {
			Class<?> superClass = clazz.getSuperclass();
			if ((superClass != null) && !Object.class.equals(superClass)) {
				return superClass;
			}
		}
		return clazz;

	}

	/**
	 * 
	 *@param e
	 *@return
	 *@Description:将反射时的checked exception转换为unchecked exception.
	 *@Author:wangdezhen#zplay.cn
	 *@Since:2015年1月20日  上午10:33:30
	 *@Version:1.0
	 */
	public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
		if ((e instanceof IllegalAccessException) || (e instanceof IllegalArgumentException)
				|| (e instanceof NoSuchMethodException)) {
			return new IllegalArgumentException(e);
		} else if (e instanceof InvocationTargetException) {
			return new RuntimeException(((InvocationTargetException) e).getTargetException());
		} else if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		}
		return new RuntimeException("Unexpected Checked Exception.", e);
	}
}
