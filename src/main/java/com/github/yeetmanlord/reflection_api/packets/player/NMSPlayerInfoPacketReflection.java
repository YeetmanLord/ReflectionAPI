package com.github.yeetmanlord.reflection_api.packets.player;

import java.util.HashMap;

import com.github.yeetmanlord.reflection_api.ReflectionApi;
import com.github.yeetmanlord.reflection_api.packets.NMSPacketReflection;
import com.github.yeetmanlord.reflection_api.players.NMSPlayerReflection;

public class NMSPlayerInfoPacketReflection extends NMSPacketReflection {
	private static HashMap<Class<?>, Integer> specialClasses = new HashMap<>();
	static {
		if (ReflectionApi.version.equals("1.8")) {
			specialClasses.put(ReflectionApi.getNMSClass("EnumPlayerInfoAction"), 0);
		} else
			specialClasses.put(ReflectionApi.getNMSInnerClass("EnumPlayerInfoAction", "PacketPlayOutPlayerInfo"), 0);
		specialClasses.put(ReflectionApi.getNMSClassArray("EntityPlayer"), 1);
	}

	/**
	 * @param infoValue allowed values for ADD_PLAYER, UPDATE_GAME_MODE,
	 *                  UPDATE_LATENCY, UPDATE_DISPLAY_NAME, REMOVE_PLAYER;
	 * @param player    should be {@link NMSPlayerReflection#getNmsPlayer()}
	 * @throws Exception when invalid data is passed into the constructor
	 */
	public NMSPlayerInfoPacketReflection(String infoValue, Object... players) throws Exception {
		super("PacketPlayOutPlayerInfo", specialClasses,
				enumPlayerActionClass().getField(infoValue.toUpperCase()).get(null),
				ReflectionApi.castArrayToNMS("EntityPlayer", players));
	}

	private static Class<?> enumPlayerActionClass() {
		if(ReflectionApi.version.equals("1.8"))
		{
			return ReflectionApi.getNMSClass("EnumPlayerInfoAction");
		}
		else return (ReflectionApi.getNMSInnerClass("EnumPlayerInfoAction", "PacketPlayOutPlayerInfo"));
	}

}
