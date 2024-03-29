package com.github.yeetmanlord.reflection_api.packets;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import javax.annotation.Nullable;

import com.github.yeetmanlord.reflection_api.NMSObjectReflection;
import com.github.yeetmanlord.reflection_api.ReflectionApi;
import com.github.yeetmanlord.reflection_api.exceptions.MappingsException;
import com.github.yeetmanlord.reflection_api.mappings.types.ClassNameMapping;
import com.github.yeetmanlord.reflection_api.mappings.types.PackageMapping;
import com.github.yeetmanlord.reflection_api.packets.entity.NMSEntityDestroyPacketReflection;
import com.github.yeetmanlord.reflection_api.packets.entity.NMSEntityPacketReflection;
import com.github.yeetmanlord.reflection_api.packets.entity.NMSNamedEntitySpawnPacketReflection;
import com.github.yeetmanlord.reflection_api.packets.player.NMSPlayerInfoPacketReflection;
import com.github.yeetmanlord.reflection_api.packets.player.NMSScoreboardTeamPacketReflection;

/**
 * This class is for reflecting NMS packets. It is a wrapper for the
 * Packet class. It is used for sending packets to players in
 * {@link com.github.yeetmanlord.reflection_api.entity.players.player_connection.NMSPlayerConnectionReflection}
 */
public class NMSPacketReflection extends NMSObjectReflection {

    private Object nmsPacket;

    private String name;

    public NMSPacketReflection(Object nmsPacket) {

        super(nmsPacket);
        this.name = nmsPacket.getClass().getName();
        this.nmsPacket = nmsPacket;

    }

    private static HashMap<Class<?>, Class<?>> natives = new HashMap<>();

    static {
        natives.put(Integer.class, int.class);
        natives.put(Long.class, long.class);
        natives.put(Boolean.class, boolean.class);
        natives.put(Double.class, double.class);
        natives.put(Float.class, float.class);
        natives.put(Byte.class, byte.class);
        natives.put(Short.class, short.class);
        natives.put(Integer[].class, int[].class);
        natives.put(Boolean[].class, boolean[].class);
        natives.put(Double[].class, double[].class);
        natives.put(Float[].class, float[].class);
        natives.put(Byte[].class, byte[].class);
        natives.put(Long[].class, long[].class);
        natives.put(Short[].class, short[].class);
    }

    /**
     * For reflecting NMS packets
     *
     * @param mapping    The package mapping of the packet (Most of the time it's {@link com.github.yeetmanlord.reflection_api.mappings.Mappings#PACKET_PLAY_PACKAGE_MAPPING})
     * @param packetName is the name of the packet you are reflecting although
     *                   special packets have separate classes These classes are
     *                   {@link NMSScoreboardTeamPacketReflection},
     *                   {@link NMSEntityDestroyPacketReflection},
     *                   {@link NMSPlayerInfoPacketReflection}
     *                   {@link NMSEntityPacketReflection}
     *                   {@link NMSNamedEntitySpawnPacketReflection}
     * @param args       these are the arguments that the packet would normally
     *                   take. If you use the wrong class there will be an error and
     *                   the server will crash (For example you use a float instead
     *                   of a byte) MAKE SURE TO CAST!! <b>IMPORTANT NOTE: Passing nulls into this will not work
     *                   if you have something null you wish to pass in to the constructor please use a different
     *                   NMS Packet Reflection constructor.</b>
     */
    public NMSPacketReflection(PackageMapping mapping, String packetName, @Nullable Object... args) {

        super(getClassName(mapping, packetName), init(args), args);
        nmsPacket = nmsObject;
        this.name = packetName;

    }

    public NMSPacketReflection(ClassNameMapping packetName, @Nullable Object... args) {

        super(packetName, init(args), args);
        nmsPacket = nmsObject;

        try {
            this.name = packetName.getName() + ": " + packetName.getNMSClassMapping().getName().replaceFirst(packetName.getNMSClassMapping().getPackage().getName() + ".", "");
        } catch (MappingsException e) {
            e.printStackTrace();
            this.name = packetName.name;
        }

    }

    private static Class<?>[] init(@Nullable Object[] args) {

        int length = 0;

        if (args != null && args[0] != null) {
            length = args.length;
        }

        Class<?>[] classes = new Class<?>[length];

        if (args != null && args[0] != null) {

            for (int x = 0; x < args.length; x++) {

                if (natives.keySet().contains(args[x].getClass())) {
                    classes[x] = natives.get(args[x].getClass());
                } else {
                    classes[x] = args[x].getClass();
                }

            }

        }

        return classes;

    }

    /**
     * @param packetName            The name of the packet to reflect
     * @param specialClassForObject A hashmap of a Class and object where the class
     *                              is the class type for a constructor and the
     *                              object being the value.
     * @see NMSPlayerInfoPacketReflection to see how this would work
     * @see NMSScoreboardTeamPacketReflection as well for another example
     */
    public NMSPacketReflection(PackageMapping mapping, String packetName, HashMap<Class<?>, Integer> specialClassForObject, Object... args) {

        super(init(getClassName(mapping, packetName), specialClassForObject, args));
        nmsPacket = nmsObject;
        this.name = packetName;

    }

    public NMSPacketReflection(ClassNameMapping packetMapping, HashMap<Class<?>, Integer> specialConstructorClasses, Object... args) {
        this(packetMapping.getPackageMapping(), getPacketClass(packetMapping), specialConstructorClasses, args);
    }

    private static String getPacketClass(ClassNameMapping mapping) {
        try {
            return mapping.get();
        } catch (MappingsException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getClassName(PackageMapping mapping, String packetName) {
        try {
            return mapping.getNMSSubPackage() + packetName;
        } catch (MappingsException e) {
            e.printStackTrace();
            return packetName;
        }
    }

    private static Object init(String packetName, HashMap<Class<?>, Integer> specialClassForObject, Object[] args) {

        int length = args.length;

        if (args.length < specialClassForObject.size()) {
            throw (new IllegalArgumentException("The parameter specialClassForObject must be the same length or less than the given arguements length"));
        }

        Class<?>[] classes = new Class<?>[length];

        for (Class<?> clazz : specialClassForObject.keySet()) {

            if (natives.keySet().contains(clazz)) {
                classes[specialClassForObject.get(clazz)] = natives.get(clazz);
            } else classes[specialClassForObject.get(clazz)] = clazz;

        }

        for (int x = 0; x < length; x++) {

            if (classes[x] == null) {

                if (natives.keySet().contains(args[x].getClass())) {
                    classes[x] = natives.get(args[x].getClass());
                } else {
                    classes[x] = args[x].getClass();
                }

            }

        }

        try {
            Constructor<?> packetConstructor;

            if (classes.length != 0) {
                packetConstructor = ReflectionApi.getNMSClass(packetName).getConstructor(classes);
            } else {
                packetConstructor = ReflectionApi.getNMSClass(packetName).getConstructor();
            }

            return packetConstructor.newInstance(args);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * @param packetName The name of the packet to reflect
     * @param classes    List of all classes that this packet's constructor uses.
     *                   They are in the order that they appear in the wanted constructor.
     * @param args       List of arguments to fill into the constructor. (In the order they appear in the constructor)
     */
    public NMSPacketReflection(PackageMapping mapping, String packetName, Class<?>[] classes, Object... args) {
        super(getClassName(mapping, packetName), classes, args);
        nmsPacket = nmsObject;
        this.name = packetName;
    }

    /**
     * @return Returns the nmsPacket equivalent
     */
    public Object getNmsPacket() {

        return nmsPacket;

    }

    public String getPacketName() {

        return name;

    }

    @Override
    public String toString() {

        HashMap<String, Object> values = new HashMap<>();
        values.put("type", nmsObject.getClass().getName());
        values.put("packet", nmsPacket);
        values.put("packetName", nmsObject.getClass().getSimpleName());
        return "PacketReflection" + values;

    }

    public static NMSPacketReflection cast(NMSObjectReflection refl, String packetName) {

        Class<?> clazz = ReflectionApi.getNMSClass(packetName);

        if (clazz.isInstance(refl.getNMSObject())) {
            return new NMSPacketReflection(refl.getNMSObject());
        }

        throw new ClassCastException("Cannot cast " + refl.toString() + " to NMSPacketReflection(" + packetName + ")");

    }

}
