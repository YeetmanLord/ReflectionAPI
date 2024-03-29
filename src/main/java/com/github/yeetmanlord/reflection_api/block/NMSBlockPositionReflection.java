package com.github.yeetmanlord.reflection_api.block;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import com.github.yeetmanlord.reflection_api.mappings.Mappings;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import com.github.yeetmanlord.reflection_api.NMSObjectReflection;
import com.github.yeetmanlord.reflection_api.util.ReflectedField;
import com.github.yeetmanlord.reflection_api.ReflectionApi;
import com.github.yeetmanlord.reflection_api.exceptions.FieldReflectionExcpetion;
import com.google.common.collect.ImmutableMap;

/**
 * This class is a wrapper for the NMS BlockPosition class.
 *
 * @author YeetManLord
 * @version 1.0
 * @since 1.0
 *
 */
public class NMSBlockPositionReflection extends NMSObjectReflection {

	/**
	 * The underlying x coordinate of the block position.
	 */
	public ReflectedField<Integer> x;

	/**
	 * The underlying y coordinate of the block position.
	 */
	public ReflectedField<Integer> y;

	/**
	 * The underlying z coordinate of the block position.
	 */
	public ReflectedField<Integer> z;

	public NMSBlockPositionReflection(double x, double y, double z) {

		super(init(x, y, z));

		try {
			this.x = new ReflectedField<>(getField("a"), true, false, this, "getX", "");
			this.y = new ReflectedField<>(getField(ReflectionApi.version.isOlder(ReflectionApi.v1_10) ? "c" : "b"), true, false, this, "getY", "");
			this.z = new ReflectedField<>(getField(ReflectionApi.version.isOlder(ReflectionApi.v1_10) ? "d" : "c"), true, false, this, "getZ", "");
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}

	}

	private NMSBlockPositionReflection(Object blockPosistion) {

		super(blockPosistion);

		try {
			this.x = new ReflectedField<>(getField("a"), true, false, this, "getX", "");
			this.y = new ReflectedField<>(getField(ReflectionApi.version.isOlder(ReflectionApi.v1_10) ? "c" : "b"), true, false, this, "getY", "");
			this.z = new ReflectedField<>(getField(ReflectionApi.version.isOlder(ReflectionApi.v1_10) ? "d" : "c"), true, false, this, "getZ", "");
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}

	}

	private static Object init(double x, double y, double z) {

		try {
			Constructor<?> constr = staticClass.getConstructor(double.class, double.class, double.class);
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

	/**
	 * Adds the given coordinates to the block position.
	 * @param x The x coordinate to add.
	 * @param y The y coordinate to add.
	 * @param z The z coordinate to add.
	 * @return The new block position.
	 */
	public NMSBlockPositionReflection add(double x, double y, double z) {

		try {
			return new NMSBlockPositionReflection(this.invokeMethodForNmsObject("a", new Class<?>[] { double.class, double.class, double.class }, new Object[] { x, y, z }));
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
			return this;
		}

	}

	/**
	 * Adds the given coordinates to the block position.
	 * @param blockPosition The block position to add.
	 * @return The new block position.
	 */
	public NMSBlockPositionReflection add(NMSBlockPositionReflection blockPosition) {

		try {
			return add(blockPosition.x.get(), blockPosition.y.get(), blockPosition.z.get());
		}
		catch (FieldReflectionExcpetion e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public String toString() {

		HashMap<String, Object> values = new HashMap<>();
		values.put("type", nmsObject.getClass());
		values.put("object", this.nmsObject);
		values.put("location", ImmutableMap.of("x", x, "y", y, "z", z));
		return "BlockPosReflection" + values.toString();

	}

	public static final Class<?> staticClass = ReflectionApi.getNMSClass(Mappings.CORE_PACKAGE_MAPPING, "BaseBlockPosition");

	public static NMSBlockPositionReflection cast(NMSObjectReflection refl) {

		if (staticClass.isInstance(refl.getNMSObject())) {
			return new NMSBlockPositionReflection(refl.getNMSObject());
		}

		throw new ClassCastException("Cannot cast " + refl.toString() + " to NMSBlockPositionReflection");

	}

}
