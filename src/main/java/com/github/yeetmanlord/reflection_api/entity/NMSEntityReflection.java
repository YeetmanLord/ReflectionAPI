package com.github.yeetmanlord.reflection_api.entity;

import java.lang.reflect.Method;

import org.bukkit.entity.Entity;

import com.github.yeetmanlord.reflection_api.NMSObjectReflection;
import com.github.yeetmanlord.reflection_api.ReflectionApi;

public class NMSEntityReflection extends NMSObjectReflection {
	private NMSDataWatcherReflection dataWatcher;
	
	public double locX;
	public double locY;
	public double locZ;

	public NMSEntityReflection(Entity entity) {
		super(entity, "getHandle");
		this.dataWatcher = new NMSDataWatcherReflection(this);
		locX = entity.getLocation().getX();
		locY = entity.getLocation().getY();
		locZ = entity.getLocation().getZ();
	}

	public NMSEntityReflection(Object nmsEntity)
	{
		super(nmsEntity);
		if(ReflectionApi.getNMSClass("Entity").isInstance(nmsEntity))
		{
			this.dataWatcher = new NMSDataWatcherReflection(this);
			try {
				locX = nmsEntity.getClass().getField("locX").getDouble(nmsEntity);
				locY = nmsEntity.getClass().getField("locY").getDouble(nmsEntity);
				locZ = nmsEntity.getClass().getField("locZ").getDouble(nmsEntity);
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				throw(new IllegalArgumentException(nmsEntity.toString() + " does not the correct data"));
			}
		}
		else
		{
			throw(new IllegalArgumentException(nmsEntity.toString() + " is not an instance of net.minecraft.server.Entity"));
		}
	}

	public void setLocation(double x, double y, double z, float yaw, float pitch) {
		try {
			nmsObject.getClass()
					.getMethod("setLocation", double.class, double.class, double.class, float.class, float.class)
					.invoke(nmsObject, x, y, z, yaw, pitch);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public NMSDataWatcherReflection getDataWatcher() {
		return this.dataWatcher;
	}

	public Object getNmsEntity() {
		return this.nmsObject;
	}
	
	public int getId() {
		try {
			Method getId = nmsObject.getClass().getMethod("getId");
			return (int) getId.invoke(nmsObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String getName() {
		try {
			Method getName = nmsObject.getClass().getMethod("getName");
			return (String) getName.invoke(nmsObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
