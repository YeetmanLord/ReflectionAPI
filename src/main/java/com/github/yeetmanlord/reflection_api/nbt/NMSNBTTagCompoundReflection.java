package com.github.yeetmanlord.reflection_api.nbt;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.github.yeetmanlord.reflection_api.NMSObjectReflection;
import com.github.yeetmanlord.reflection_api.ReflectionApi;

public class NMSNBTTagCompoundReflection extends NMSObjectReflection {
	
	public NMSNBTTagCompoundReflection(Object nmsObject) {

		super(nmsObject);

	}

	public NMSNBTTagCompoundReflection() {

		super(init());

	}

	private static Object init() {

		Constructor<?> constr;

		try {
			constr = ReflectionApi.getNMSClass("NBTTagCompound").getConstructor();
			return constr.newInstance();
		}
		catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void setByte(String key, byte value) {

		try {
			this.invokeMethodForNmsObject("setByte", new Class<?>[] { String.class, byte.class }, new Object[] { key, value });
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

	}

	public void setShort(String key, short value) {

		try {
			this.invokeMethodForNmsObject("setShort", new Class<?>[] { String.class, short.class }, new Object[] { key, value });
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

	}

	public void setInt(String key, int value) {

		try {
			this.invokeMethodForNmsObject("setInt", new Class<?>[] { String.class, int.class }, new Object[] { key, value });
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

	}

	public void setLong(String key, long value) {

		try {
			this.invokeMethodForNmsObject("setLong", new Class<?>[] { String.class, long.class }, new Object[] { key, value });
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

	}

	public void setFloat(String key, float value) {

		try {
			this.invokeMethodForNmsObject("setFloat", new Class<?>[] { String.class, float.class }, new Object[] { key, value });
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

	}

	public void setDouble(String key, double value) {

		try {
			this.invokeMethodForNmsObject("setDouble", new Class<?>[] { String.class, double.class }, new Object[] { key, value });
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

	}

	public void setString(String key, String value) {

		try {
			this.invokeMethodForNmsObject("setString", new Class<?>[] { String.class, String.class }, new Object[] { key, value });
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

	}

	public void setByteArray(String key, byte[] value) {

		try {
			this.invokeMethodForNmsObject("setByteArray", new Class<?>[] { String.class, byte[].class }, new Object[] { key, value });
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

	}

	public void setIntArray(String key, int[] value) {

		try {
			this.invokeMethodForNmsObject("setIntArray", new Class<?>[] { String.class, int[].class }, new Object[] { key, value });
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

	}

	public void setBoolean(String key, boolean value) {

		try {
			this.invokeMethodForNmsObject("setBoolean", new Class<?>[] { String.class, boolean.class }, new Object[] { key, value });
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

	}

	public static final Class<?> staticClass = ReflectionApi.getNMSClass("NBTTagCompound");

	public static NMSNBTTagCompoundReflection cast(NMSObjectReflection refl) {

		if (staticClass.isInstance(refl.getNmsObject())) {
			return new NMSNBTTagCompoundReflection(refl.getNmsObject());
		}

		throw new ClassCastException("Cannot cast " + refl.toString() + " to NMSNBTTagCompoundReflection");

	}

}
