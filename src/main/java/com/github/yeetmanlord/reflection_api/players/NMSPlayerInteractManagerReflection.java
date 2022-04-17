package com.github.yeetmanlord.reflection_api.players;

import java.lang.reflect.Constructor;

import com.github.yeetmanlord.reflection_api.ReflectionApi;
import com.github.yeetmanlord.reflection_api.NMSObjectReflection;
import com.github.yeetmanlord.reflection_api.world.NMSWorldServerReflection;

public class NMSPlayerInteractManagerReflection extends NMSObjectReflection {

	public NMSPlayerInteractManagerReflection(NMSWorldServerReflection worldServer) {

		super(instance(worldServer));

	}

	public NMSPlayerInteractManagerReflection(Object nmsObject) {

		super(nmsObject);

	}

	public Object getNmsManager() {

		return nmsObject;

	}

	public static Object instance(NMSWorldServerReflection worldServer) {

		try {
			Constructor<?> managerConstructor = ReflectionApi.getNMSClass("PlayerInteractManager").getConstructor(worldServer.getNmsWorldServer().getClass().getSuperclass());
			return managerConstructor.newInstance(ReflectionApi.getNMSClass("World").cast(worldServer.getNmsWorldServer()));
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

}
