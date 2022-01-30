package com.github.yeetmanlord.reflection_api;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class ReflectionApi extends JavaPlugin 
{
	
	protected class IllegalVersionException extends Exception
	{
		private static final long serialVersionUID = 6794071921978043326L;

		public IllegalVersionException() {
			
		}
		
		public IllegalVersionException(String string) {
			super(string);
		}
	}
	
	
	private static Version getVersion()
	{
		try {
			Field console = Bukkit.getServer().getClass().getDeclaredField("console");
			console.setAccessible(true);
			NMSObjectReflection object = new NMSObjectReflection(console.get(Bukkit.getServer()));
			console.setAccessible(false);
			return new Version((String) object.invokeMethodForNmsObject("getVersion"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Version version;
	
	@Override
	public void onEnable() {
		version = getVersion();
		try
		{
			if(version == null)
			{
				throw(new IllegalVersionException("Cannot find the version for this server. Likely means this plugin is broken!!"));
			}
		}
		catch(IllegalVersionException versionException)
		{
			versionException.printStackTrace();
			this.getServer().getPluginManager().disablePlugin(this);
		}
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[ReflectionAPI] Running on server version &2" + version));
	}
	
	public static Class<?> getNMSClass(String className)
	{
		String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
        String name = "net.minecraft.server." + version + className;
        Class<?> nmsClass;
        try {
            nmsClass = Class.forName(name);
            return nmsClass;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	public static Class<?> getNMSInnerClass(String innerClassName, String parenetClassName)
	{
		String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
        String name = "net.minecraft.server." + version + parenetClassName + "$" + innerClassName;
        Class<?> nmsClass;
        try {
            nmsClass = Class.forName(name);
            return nmsClass;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	/**
	 * Gets the class of an array of a given NMS class
	 * @param className The NMS class name
	 * @return The array class version of an NMS class
	 */
	public static Class<?> getNMSClassArray(String className)
	{
		Class<?> type = getNMSClass(className);
		return Array.newInstance(type, 1).getClass();
	}
	

	public static Class<?> getNMSInnerClassArray(String innerClassName, String parenetClassName)
	{
		Class<?> type = getNMSInnerClass(innerClassName, parenetClassName);
		return Array.newInstance(type, 1).getClass();
	}
	
	public static Object getHandle(Object bukkitObject)
	{
		try {
			Method getHandle = bukkitObject.getClass().getMethod("getHandle");
			return getHandle.invoke(bukkitObject);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Casts a given array to the an nms class by the given name
	 * @param nmsClassName The name of the NMS class to cast to
	 * @param arrayToCast The array you are casting
	 * @return Returns an object array casted to the given nmsClassName
	 * @throws IllegalArgumentException when any value in the arrayToCast are not castable to the given class
	 */
	public static Object[] castArrayToNMS(String nmsClassName, Object[] arrayToCast) throws IllegalArgumentException
	{
		Class<?> type = getNMSClass(nmsClassName);
		Class<?> arrayType = getNMSClassArray(nmsClassName);
		Object[] castedArray = Arrays.copyOf((Object[])arrayType.cast(Array.newInstance(type, arrayToCast.length)), arrayToCast.length);
		for(int x = 0; x < arrayToCast.length; x++)
		{
			if(type.isInstance(arrayToCast[x]))
			{
				castedArray[x] = type.cast(arrayToCast[x]);
			}
			else
			{
				System.err.println(arrayToCast[x]);
				System.err.println(nmsClassName);
				throw(new IllegalArgumentException("The provided array contains objects that are not instances of the given class"));
			}
		}
		return castedArray;
	}
}
