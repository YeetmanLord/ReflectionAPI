package com.github.yeetmanlord.reflection_api.block;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import com.github.yeetmanlord.reflection_api.NMSObjectReflection;
import com.github.yeetmanlord.reflection_api.ReflectionApi;
import com.google.common.collect.ImmutableMap;

public class NMSBlockPositionReflection extends NMSObjectReflection {

	public double x;

	public double y;

	public double z;

	public NMSBlockPositionReflection(double x, double y, double z) {

		super(init(x, y, z));
		this.x = x;
		this.y = y;
		this.z = z;

	}

	private NMSBlockPositionReflection(Object blockPosistion) {

		super(blockPosistion);

	}

	private static Object init(double x, double y, double z) {

		try {
			Constructor<?> constr = ReflectionApi.getNMSClass("BlockPosition").getConstructor(double.class, double.class, double.class);
			return constr.newInstance(x, y, z);
		}
		catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}

	}

	public NMSBlockPositionReflection(Entity ent) {

		this(ent.getLocation());

	}

	public NMSBlockPositionReflection(Location loc) {

		this(loc.getX(), loc.getY(), loc.getZ());

	}

	public NMSBlockPositionReflection add(double x, double y, double z) {

		try {
			return new NMSBlockPositionReflection(this.invokeMethodForNmsObject("a", new Class<?>[] { double.class, double.class, double.class }, new Object[] { x, y, z }));
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
			return this;
		}

	}

	public NMSBlockPositionReflection add(NMSBlockPositionReflection blockPosition) {

		return add(this.x, this.y, this.z);

	}

	@Override
	public String toString() {

		HashMap<String, Object> values = new HashMap<>();
		values.put("type", nmsObject.getClass());
		values.put("object", this.nmsObject);
		values.put("location", ImmutableMap.of("x", x, "y", y, "z", z));
		return "BlockPosReflection" + values.toString();

	}

}
