package com.github.yeetmanlord.reflection_api.packets.entity;

import com.github.yeetmanlord.reflection_api.ReflectionApi;
import com.github.yeetmanlord.reflection_api.mappings.Mappings;
import com.github.yeetmanlord.reflection_api.packets.NMSPacketReflection;

public class NMSPacketPlayOutRelEntityMove extends NMSPacketReflection {

	public NMSPacketPlayOutRelEntityMove(int entityId, long x, long y, long z, byte pitch, byte yaw, boolean bool) {

		super(Mappings.PACKET_PLAY_OUT_REL_ENTITY_MOVE_LOOK_CLASS_MAPPING, getVersionDependentArgs(entityId, x, y, z, pitch, yaw, bool));

	}

	private static Object[] getVersionDependentArgs(int entityId, long x, long y, long z, byte pitch, byte yaw, boolean bool) {

		Object[] args = new Object[7];
		args[0] = entityId;

		if (ReflectionApi.version.isOlder("1.9")) {
			byte bX = x > Byte.MAX_VALUE ? Byte.MAX_VALUE : x < Byte.MIN_VALUE ? Byte.MIN_VALUE : (byte) x;
			byte bY = y > Byte.MAX_VALUE ? Byte.MAX_VALUE : y < Byte.MIN_VALUE ? Byte.MIN_VALUE : (byte) y;
			byte bZ = z > Byte.MAX_VALUE ? Byte.MAX_VALUE : z < Byte.MIN_VALUE ? Byte.MIN_VALUE : (byte) z;

			args[1] = bX;
			args[2] = bY;
			args[3] = bZ;
		}
		else {
			args[1] = x;
			args[2] = y;
			args[3] = z;
		}

		args[4] = pitch;
		args[5] = yaw;
		args[6] = bool;

		return args;

	}

}
