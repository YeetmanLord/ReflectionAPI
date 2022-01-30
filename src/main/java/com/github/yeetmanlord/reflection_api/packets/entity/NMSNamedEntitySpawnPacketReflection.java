package com.github.yeetmanlord.reflection_api.packets.entity;

import java.util.HashMap;

import com.github.yeetmanlord.reflection_api.ReflectionApi;
import com.github.yeetmanlord.reflection_api.packets.NMSPacketReflection;
import com.github.yeetmanlord.reflection_api.players.NMSPlayerReflection;

public class NMSNamedEntitySpawnPacketReflection extends NMSPacketReflection {
	private static HashMap<Class<?>, Integer> classObjectMap = new HashMap<>();
	static
	{
		classObjectMap.put(ReflectionApi.getNMSClass("EntityHuman"), 0);
	}

	public NMSNamedEntitySpawnPacketReflection(NMSPlayerReflection entity) {
		super("PacketPlayOutNamedEntitySpawn", classObjectMap, entity.getNmsPlayer());
	}

}
