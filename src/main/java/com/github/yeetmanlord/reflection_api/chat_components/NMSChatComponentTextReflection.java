package com.github.yeetmanlord.reflection_api.chat_components;

import java.lang.reflect.Constructor;

import com.github.yeetmanlord.reflection_api.ReflectionApi;
import com.github.yeetmanlord.reflection_api.NMSObjectReflection;

public class NMSChatComponentTextReflection extends NMSObjectReflection {
	
	public NMSChatComponentTextReflection(String text) 
	{
		super(init(text));
	}

	public NMSChatComponentTextReflection(Object nmsObject) {

		super(nmsObject);

	}
	
	private static Object init(String text)
	{
		try
		{
			Constructor<?> constr = ReflectionApi.getNMSClass("ChatComponentText").getConstructor(String.class);
			return constr.newInstance(text);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Object getComponent()
	{
		return nmsObject;
	}
}
