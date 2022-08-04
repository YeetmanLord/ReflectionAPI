package com.github.yeetmanlord.reflection_api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class NMSItemStackReflection extends NMSObjectReflection {

	public NMSItemStackReflection(Object nmsObject) {

		super(nmsObject);

	}

	public NMSItemStackReflection(ItemStack stack) {

		super(init(stack));

	}

	public NMSItemStackReflection(Material mat, int amount) {

		this(new ItemStack(mat, amount));

	}

	public NMSItemStackReflection(Material mat, int amount, short damage) {

		this(new ItemStack(mat, amount, damage));

	}

	public NMSItemStackReflection(Material mat) {

		this(new ItemStack(mat));

	}

	public NMSItemStackReflection(Material mat, int amount, short damage, Byte data) {

		this(new ItemStack(mat, amount, damage, data));

	}

	private static Object init(ItemStack stack) {

		try {
			Method asNMSCopy = ReflectionApi.getCraftBukkitClass("CraftItemStack", "inventory").getMethod("asNMSCopy", ItemStack.class);
			return asNMSCopy.invoke(null, stack);
		}
		catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;

	}

	public static final Class<?> staticClass = ReflectionApi.getNMSClass("ItemStack");

	public static NMSItemStackReflection cast(NMSObjectReflection refl) {

		if (staticClass.isInstance(refl.getNmsObject())) {
			return new NMSItemStackReflection(refl.getNmsObject());
		}

		throw new ClassCastException("Cannot cast " + refl.toString() + " to NMSItemStackReflection");

	}

}
