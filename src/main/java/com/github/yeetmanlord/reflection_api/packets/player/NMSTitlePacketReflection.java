package com.github.yeetmanlord.reflection_api.packets.player;

import java.util.HashMap;

import com.github.yeetmanlord.reflection_api.ReflectionApi;
import com.github.yeetmanlord.reflection_api.chat_components.ChatComponentTextReflection;
import com.github.yeetmanlord.reflection_api.chat_components.NMSChatSerializerReflection;
import com.github.yeetmanlord.reflection_api.packets.NMSPacketReflection;

public class NMSTitlePacketReflection extends NMSPacketReflection {
	private static HashMap<Class<?>, Integer> classes = new HashMap<>();
	static {
		classes.put(ReflectionApi.getNMSClass("IChatBaseComponent"), 1);
	}

	/**
	 * For adding timings to titles
	 * 
	 * @param fadeIn    Time in ticks it takes for the title to fade in.
	 * @param timeShown Time in ticks before the title starts to fade out
	 * @param fadeOut   Time in ticks it takes for the title to fade out and
	 *                  disappear
	 */
	public NMSTitlePacketReflection(int fadeIn, int timeShown, int fadeOut) {
		this("TIMES", ReflectionApi.getNMSClass("IChatBaseComponent").cast(null), fadeIn, timeShown, fadeOut);
	}

	/**
	 * For creating an actual title packet without timings.
	 * 
	 * @param enumTitleAction Based on EnumTitleAction allowed values are TITLE,
	 *                        SUBTITLE, TIMES, CLEAR, and RESET
	 * @param chatComponent   An NMS IChatBaseComponent gotten either by
	 *                        {@link NMSChatSerializerReflection#createChatComponentFromString(String)},
	 *                        {@link NMSChatSerializerReflection#createChatComponentFromRawText(String)},
	 *                        or {@link ChatComponentTextReflection#getComponent()}
	 */
	public NMSTitlePacketReflection(String enumTitleAction, Object chatComponent) {
		this(enumTitleAction, chatComponent, -1, -1, -1);
	}

	/**
	 * To create a title packet with timings or to update an existing packet before
	 * sending it (like add timings)
	 * 
	 * @param enumTitleAction Based on EnumTitleAction allowed values are TITLE,
	 *                        SUBTITLE, TIMES, CLEAR, and RESET
	 * @param chatComponent   An NMS IChatBaseComponent gotten either by
	 *                        {@link NMSChatSerializerReflection#createChatComponentFromString(String)},
	 *                        {@link NMSChatSerializerReflection#createChatComponentFromRawText(String)},
	 *                        or {@link ChatComponentTextReflection#getComponent()}
	 * @param fadeIn          Time in ticks it takes for the title to fade in.
	 * @param timeShown       Time in ticks before the title starts to fade out
	 * @param fadeOut         Time in ticks it takes for the title to fade out and
	 *                        disappear
	 */
	public NMSTitlePacketReflection(String enumTitleAction, Object chatComponent, int fadeIn, int timeShow,
			int fadeOut) {
		super("PacketPlayOutTitle", classes, getTitleAction(enumTitleAction), chatComponent, fadeIn, timeShow, fadeOut);
	}

	private static Object getTitleAction(String enumField) {
		try {
			if(ReflectionApi.version.equals("1.8"))
			{
				return ReflectionApi.getNMSClass("EnumTitleAction").getField(enumField.toUpperCase()).get(null);
			}
			return ReflectionApi.getNMSInnerClass("EnumTitleAction", "PacketPlayOutTitle").getField(enumField.toUpperCase()).get(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
