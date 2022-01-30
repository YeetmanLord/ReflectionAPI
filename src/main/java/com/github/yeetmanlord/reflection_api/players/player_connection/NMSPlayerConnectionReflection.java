package com.github.yeetmanlord.reflection_api.players.player_connection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.entity.Player;

import com.github.yeetmanlord.reflection_api.ReflectionApi;
import com.github.yeetmanlord.reflection_api.NMSObjectReflection;
import com.github.yeetmanlord.reflection_api.packets.NMSPacketReflection;
import com.github.yeetmanlord.reflection_api.packets.network.NMSNetworkManagerReflection;
import com.github.yeetmanlord.reflection_api.players.NMSPlayerReflection;
import com.github.yeetmanlord.reflection_api.server.NMSServerReflection;

public class NMSPlayerConnectionReflection extends NMSObjectReflection {
	private Object nmsPlayerConnection;

	
	/**
	 * {@link NMSPlayerConnectionReflection#NMSPlayerConnectionReflection(NMSPlayerReflection)} is for getting a player connection for a normal player
	 * While this is for creating a new connection for fake players
	 */
	public NMSPlayerConnectionReflection(NMSServerReflection server, NMSNetworkManagerReflection netManager,
			NMSPlayerReflection player) {
		super(instance(server, netManager, player));
		nmsPlayerConnection = nmsObject;
	}
	
	/**
	 * Gets a player connection for a bukkit player
	 * @param player A Bukkit {@link Player}
	 */
	public NMSPlayerConnectionReflection(Player player) {
		super(instance(player));
		nmsPlayerConnection = nmsObject;
	}
	
	/**
	 * Gets a player connection for an EntityPlayer reflection
	 * 
	 * @param player An actual player not a fake player/npc
	 */
	public NMSPlayerConnectionReflection(NMSPlayerReflection player)
	{
		super(getConnection(player));
		nmsPlayerConnection = nmsObject;
	}

	public Object getNmsPlayerConnection() {
		return nmsPlayerConnection;
	}

	public Object nmsNetworkManager() {
		try {
			Field network = nmsPlayerConnection.getClass().getField("networkManager");
			return network.get(nmsPlayerConnection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void sendPacket(NMSPacketReflection packet) {
		try {
			Method sendPacket = nmsPlayerConnection.getClass().getMethod("sendPacket", ReflectionApi.getNMSClass("Packet"));
			sendPacket.invoke(nmsPlayerConnection, packet.getNmsPacket());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Object instance(NMSServerReflection server, NMSNetworkManagerReflection netManager,
			NMSPlayerReflection player) {
		try {
			Constructor<?> playerConnectionConstructor = ReflectionApi.getNMSClass("PlayerConnection").getConstructor(
					server.getNmsServer().getClass().getSuperclass(), netManager.getNmsNetworkManager().getClass(),
					player.getNmsPlayer().getClass());
			return playerConnectionConstructor.newInstance(server.getNmsServer(),
					netManager.getNmsNetworkManager(), player.getNmsPlayer());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Object instance(Player player)
	{
		try {
			return ReflectionApi.getNMSClass("EntityPlayer").getField("playerConnection").get(ReflectionApi.getHandle(player));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object getConnection(NMSPlayerReflection player)
	{
		try {
			return player.getNmsPlayer().getClass().getField("playerConnection").get(player.getNmsPlayer());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
