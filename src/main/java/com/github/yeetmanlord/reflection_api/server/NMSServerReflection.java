package com.github.yeetmanlord.reflection_api.server;

import org.bukkit.Server;

import com.github.yeetmanlord.reflection_api.NMSObjectReflection;
import com.github.yeetmanlord.reflection_api.world.NMSWorldServerReflection;

public class NMSServerReflection extends NMSObjectReflection {

	public NMSServerReflection(NMSWorldServerReflection world) {

		super(world.getNMSServer());

	}
	
	public NMSServerReflection(Object nmsObject) {

		super(nmsObject);

	}

	public NMSServerReflection(Server server) {

		super(server, "getServer");

	}

	public void stop() {

		try {
			this.invokeMethodForNmsObject("stop");
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

	}

	public Object getNmsServer() {

		return nmsObject;

	}

}
