package com.github.yeetmanlord.reflection_api.world;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;

import com.github.yeetmanlord.reflection_api.ReflectionApi;
import com.github.yeetmanlord.reflection_api.NMSObjectReflection;
import com.github.yeetmanlord.reflection_api.entity.NMSEntityReflection;
import com.github.yeetmanlord.reflection_api.players.NMSPlayerReflection;
import com.github.yeetmanlord.reflection_api.server.NMSServerReflection;

public class NMSWorldServerReflection extends NMSObjectReflection {
	private NMSServerReflection server;

	public NMSWorldServerReflection(World bukkitWorld) {
		super(bukkitWorld, "getHandle");
		this.server = new NMSServerReflection(this);
	}

	public Object getNmsWorldServer() {
		return nmsObject;
	}

	public Object getNMSServer() {
		Method getServer;
		try {
			getServer = nmsObject.getClass().getMethod("getMinecraftServer");
			return getServer.invoke(nmsObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public NMSServerReflection getServer() {
		return server;
	}

	/**
	 * Gets the list of entities for a server world
	 * 
	 * @return The entity list of a server but as reflected entities
	 */
	public List<NMSEntityReflection> entityList() {
		List<NMSEntityReflection> reflectedEntityList = new ArrayList<>();
		List<Object> entityList;
		try {
			entityList = (List<Object>) nmsObject.getClass().getField("entityList").get(nmsObject);
			for (int x = 0; x < entityList.size(); x++) {
				NMSEntityReflection entity = new NMSEntityReflection(entityList.get(x));
				if (NMSPlayerReflection.isInstance(entity)) {
					reflectedEntityList.add(new NMSPlayerReflection(entityList.get(x)));
				} else
					reflectedEntityList.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reflectedEntityList;
	}

	public void addEntity(NMSEntityReflection entity) {
		try {
			nmsObject.getClass().getMethod("addEntity", ReflectionApi.getNMSClass("Entity")).invoke(nmsObject,
					entity.getNmsEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeEntity(NMSEntityReflection entity) {
		try {
			nmsObject.getClass().getMethod("removeEntity", ReflectionApi.getNMSClass("Entity")).invoke(nmsObject,
					entity.getNmsEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}