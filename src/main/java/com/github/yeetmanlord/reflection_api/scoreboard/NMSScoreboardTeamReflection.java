package com.github.yeetmanlord.reflection_api.scoreboard;

import java.lang.reflect.Constructor;

import org.bukkit.scoreboard.Scoreboard;

import com.github.yeetmanlord.reflection_api.NMSObjectReflection;
import com.github.yeetmanlord.reflection_api.ReflectionApi;

import net.md_5.bungee.api.ChatColor;

public class NMSScoreboardTeamReflection extends NMSObjectReflection {

	public NMSScoreboardTeamReflection(Scoreboard scoreboard, String name) {
		super(init(scoreboard, name));
	}

	private static Object init(Scoreboard scoreboard, String name) {
		Object nmsScoreboard = ReflectionApi.getHandle(scoreboard);
		try {
			Constructor<?> constr = ReflectionApi.getNMSClass("ScoreboardTeam")
					.getConstructor(ReflectionApi.getNMSClass("Scoreboard"), String.class);
			return constr.newInstance(nmsScoreboard, name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object getTeam() {
		return nmsObject;
	}

	/**
	 * @param visibility Allowed values are ALWAYS, NEVER, HIDE_FOR_OTHER_TEAMS,
	 *                   HIDE_FOR_OWN_TEAM
	 */
	public void setNametagVisibility(String visibility) {
		Class<?> clazz;
		if(ReflectionApi.version.equals("1.8"))
		{
			clazz = ReflectionApi.getNMSClass("EnumNameTagVisibility");
		}
		else {
			clazz = ReflectionApi.getNMSInnerClass("EnumNameTagVisibility", "ScoreboardTeamBase");
		}
		try {
			Object value = clazz.getField(visibility.toUpperCase()).get(null);
			if(ReflectionApi.version.equals("1.8"))
			{
				invokeMethodForNmsObject("a", new Class<?>[] { clazz }, new Object[] { value });
				invokeMethodForNmsObject("b", new Class<?>[] { clazz }, new Object[] { value });
			}
			else 
			{
				invokeMethodForNmsObject("setNameTagVisibility", new Class<?>[] { clazz }, new Object[] { value }); 
				invokeMethodForNmsObject("b", new Class<?>[] { clazz }, new Object[] { value }); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Takes in a color
	 * 
	 * @param color
	 */
	public void setChatFormat(String color) {
		Class<?> clazz = ReflectionApi.getNMSClass("EnumChatFormat");
		try {
			Object value = clazz.getField(color.toUpperCase()).get(null);
			invokeMethodForNmsObject("a", new Class<?>[] { clazz }, new Object[] { value });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setSuffix(String suffix) {
		suffix = ChatColor.translateAlternateColorCodes('&', suffix);
		try {
			invokeMethodForNmsObject("setSuffix", new Class<?>[] { String.class }, new Object[] { suffix });
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	public void setPrefix(String prefix) {
		prefix = ChatColor.translateAlternateColorCodes('&', prefix);
		try {
			invokeMethodForNmsObject("setPrefix", new Class<?>[] { String.class }, new Object[] { prefix });
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	public void setDisplayName(String name) {
		name = ChatColor.translateAlternateColorCodes('&', name);
		try {
			invokeMethodForNmsObject("setDisplayName", new Class<?>[] { String.class }, new Object[] { name });
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	public void setAllowFriendlyFire(boolean allow) {
		try {
			invokeMethodForNmsObject("setAllowFriendlyFire", new Class<?>[] { boolean.class }, new Object[] { allow });
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	public String getSuffix() {
		try {
			return (String) invokeMethodForNmsObject("getSuffix");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getPrefix() {
		try {
			return (String) invokeMethodForNmsObject("getPrefix");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getDisplayName() {
		try {
			return (String) invokeMethodForNmsObject("getDisplayName");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean allowFriendlyFire() {
		try {
			return (boolean) invokeMethodForNmsObject("allowFriendlyFire");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public Object getNametagVisibility()
	{
		try {
			if(ReflectionApi.version.equals("1.8"))
			{
				return invokeMethodForNmsObject("i");
			}
			return invokeMethodForNmsObject("getNameTagVisibility");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object getChatFormat()
	{
		try {
			if(ReflectionApi.version.equals("1.8"))
			{
				return invokeMethodForNmsObject("l");
			}
			return invokeMethodForNmsObject("l");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
}