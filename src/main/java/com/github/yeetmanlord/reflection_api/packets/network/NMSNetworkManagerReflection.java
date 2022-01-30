package com.github.yeetmanlord.reflection_api.packets.network;

import java.lang.reflect.Constructor;

import com.github.yeetmanlord.reflection_api.ReflectionApi;
import com.github.yeetmanlord.reflection_api.NMSObjectReflection;

public class NMSNetworkManagerReflection extends NMSObjectReflection
{

	/**
	 * @param value Allowed values are SERVERBOUND and CLIENTBOUND although case doesn't matter
	 */
	public NMSNetworkManagerReflection(String value) {
		super(init(value));
	}
	
	private static Object init(String value) {
		try {
			Class<?> enumProtocolDirection = ReflectionApi.getNMSClass("EnumProtocolDirection");
			Object direction = enumProtocolDirection.getField(value.toUpperCase()).get(null);
			Constructor<?> managerConstructor = ReflectionApi.getNMSClass("NetworkManager")
					.getConstructor(enumProtocolDirection);
			return managerConstructor.newInstance(direction);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object getNmsNetworkManager() {
		return nmsObject;
	}

}
