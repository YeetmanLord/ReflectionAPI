package com.github.yeetmanlord.reflection_api.entity;

import com.github.yeetmanlord.reflection_api.NMSObjectReflection;

public class NMSDataWatcherReflection extends NMSObjectReflection {

	public NMSDataWatcherReflection(NMSEntityReflection entity) {
		super(init(entity));
	}
	
	private static Object init(NMSEntityReflection entity)
	{
		try {
			return entity.getNmsEntity().getClass().getMethod("getDataWatcher").invoke(entity.getNmsEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object getNmsDataWatcher() {
		return nmsObject;
	}

	public void watch(int i, Object data) {
		try {
			nmsObject.getClass().getMethod("watch", int.class, Object.class).invoke(nmsObject, i, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
