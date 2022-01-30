package com.github.yeetmanlord.reflection_api.server;

import com.github.yeetmanlord.reflection_api.NMSObjectReflection;
import com.github.yeetmanlord.reflection_api.world.NMSWorldServerReflection;

public class NMSServerReflection extends NMSObjectReflection{

	public NMSServerReflection(NMSWorldServerReflection world) 
	{
		super(world.getNMSServer());
	}

	public Object getNmsServer() {
		return nmsObject;
	}

}
