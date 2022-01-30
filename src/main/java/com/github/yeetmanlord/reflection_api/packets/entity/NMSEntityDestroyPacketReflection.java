package com.github.yeetmanlord.reflection_api.packets.entity;

import com.github.yeetmanlord.reflection_api.packets.NMSPacketReflection;

public class NMSEntityDestroyPacketReflection extends NMSPacketReflection {

	public NMSEntityDestroyPacketReflection(int... entityIds) {
		super("PacketPlayOutEntityDestroy", entityIds);
	}

}
