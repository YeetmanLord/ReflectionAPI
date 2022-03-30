package com.github.yeetmanlord.reflection_api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class NMSObjectReflection {

	protected Object nmsObject;

	public NMSObjectReflection(Object nmsObject) {

		this.nmsObject = nmsObject;

	}

	/**
	 * Will create a reflection based getting the nms equivalent of a Bukkit object
	 * Despite bukkitObject being an object it still must be an actual Bukkit object
	 * with the method being the method to get the nms equivalent
	 * 
	 * @param bukkitObject A Bukkit object that you can get an NMS equivalent with a
	 *                     method
	 * @param methodName   The name of the method to get an NMS equivalent of a
	 *                     Bukkit object
	 * @throws NoSuchMethodException throws when given method doesn't exist
	 */
	public NMSObjectReflection(Object bukkitObject, String methodName) {

		try {
			this.nmsObject = bukkitObject.getClass().getMethod(methodName).invoke(bukkitObject);
		}
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
			e.printStackTrace();
		}
		catch (NoSuchMethodException e) {
			System.err.println(methodName + " is not a valid method to get an nms object!");
		}

	}

	/**
	 * This creates a reflections using a classes constructor
	 */
	public NMSObjectReflection(String className, @Nullable Class<?>[] classes, Object[] args) {

		Constructor<?> constr;

		if (classes == null) {

			try {
				constr = ReflectionApi.getNMSClass(className).getConstructor();
				this.nmsObject = constr.newInstance();
			}
			catch (Exception e) {
				e.printStackTrace();
			}

		}
		else {

			if (args != null) {

				try {
					constr = ReflectionApi.getNMSClass(className).getConstructor(classes);
					this.nmsObject = constr.newInstance(args);
				}
				catch (Exception e) {
					e.printStackTrace();
				}

			}

		}

	}

	/**
	 * @param fieldName The name of a field
	 * @return Returns the <i>value</i> of a field not the field itself. To get a
	 *         {@link Field} object use {@link #getField(String)}
	 */
	public Object getFieldFromNmsObject(String fieldName) {

		try {
			return nmsObject.getClass().getField(fieldName).get(this.nmsObject);
		}
		catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Runs a method and returns the return value of that method If the method is a
	 * void method will return null
	 * 
	 * @param methodName  The method to invoke
	 * @param argsClasses The classes of the arguments in the method in order.
	 * @param args        The actual arguments for the method in order.
	 * @throws NoSuchMethodException This exception is thrown when the given method
	 *                               name does not exist
	 * @return The output of the method or null if it is a void method
	 */
	public Object invokeMethodForNmsObject(String methodName, @Nonnull Class<?>[] argsClasses, @Nonnull Object[] args) throws NoSuchMethodException {

		Method method;

		try {
			method = nmsObject.getClass().getMethod(methodName, argsClasses);

			if (method.getReturnType().equals(void.class)) {
				method.invoke(this.nmsObject, args);
				return null;
			}

			return method.invoke(this.nmsObject, args);
		}
		catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Runs a method from this reflection's associated NMS object
	 * 
	 * @param methodName methodName The method to invoke
	 * @return The output of the method or null if it is a void method
	 * @throws NoSuchMethodException This exception is thrown when the given method
	 *                               name does not exist
	 */
	public Object invokeMethodForNmsObject(String methodName) throws NoSuchMethodException {

		Method method;

		try {
			method = nmsObject.getClass().getMethod(methodName);

			if (method.getReturnType().equals(void.class)) {
				method.invoke(this.nmsObject);
				return null;
			}

			return method.invoke(this.nmsObject);
		}
		catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 
	 * @param fieldName The field you want to get
	 * @return Returns a {@link Field} object of the {@link #nmsObject}. To get the
	 *         <i>value</i> of the field use {@link #getFieldFromNmsObject(String)}
	 * @throws NoSuchFieldException This is thrown when the field doesn't exist
	 */
	public Field getField(String fieldName) throws NoSuchFieldException {

		try {
			return nmsObject.getClass().getField(fieldName);
		}
		catch (IllegalArgumentException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * @return Returns the NMS object that is associated with this reflection
	 */
	public Object getNmsObject() {

		return nmsObject;

	}

}
