package com.github.yeetmanlord.reflection_api.packets.player;

import java.util.HashMap;

import com.github.yeetmanlord.reflection_api.ReflectionApi;
import com.github.yeetmanlord.reflection_api.mappings.Mappings;
import com.github.yeetmanlord.reflection_api.mappings.MappingsException;
import com.github.yeetmanlord.reflection_api.packets.NMSPacketReflection;
import com.github.yeetmanlord.reflection_api.players.NMSPlayerReflection;

public class NMSPlayerInfoPacketReflection extends NMSPacketReflection {

	private static HashMap<Class<?>, Integer> specialClasses = new HashMap<>();
	static {
		try {
			specialClasses.put(Mappings.ENUM_PLAYER_ACTION_CLASS_MAPPING.getNMSClassMapping(), 0);
		}
		catch (MappingsException e) {
			e.printStackTrace();
		}
		specialClasses.put(ReflectionApi.getNMSClassArray("EntityPlayer"), 1);
	}

	/**
	 * @param infoValue allowed values for ADD_PLAYER, UPDATE_GAME_MODE,
	 *                  UPDATE_LATENCY, UPDATE_DISPLAY_NAME, REMOVE_PLAYER;
	 * @param player    should be {@link NMSPlayerReflection#getNmsPlayer()}
	 * @throws Exception when invalid data is passed into the constructor
	 */
	public NMSPlayerInfoPacketReflection(String infoValue, Object... players) throws Exception {

		super("PacketPlayOutPlayerInfo", specialClasses, enumPlayerActionClass().getField(infoValue.toUpperCase()).get(null), ReflectionApi.castArrayToNMS("EntityPlayer", players));

	}

	private static Class<?> enumPlayerActionClass() {

		try {
			return Mappings.ENUM_PLAYER_ACTION_CLASS_MAPPING.getNMSClassMapping();
		}
		catch (MappingsException e) {
			e.printStackTrace();
		}
		
		return null;

	}

}
