package com.github.yeetmanlord.reflection_api;

import com.github.yeetmanlord.reflection_api.block.NMSBlockPositionReflection;
import com.github.yeetmanlord.reflection_api.chat_components.NMSChatSerializerReflection;
import com.github.yeetmanlord.reflection_api.entity.NMSAxisAlignedBBReflection;
import com.github.yeetmanlord.reflection_api.entity.players.NMSPlayerInteractManagerReflection;
import com.github.yeetmanlord.reflection_api.entity.players.NMSPlayerReflection;
import com.github.yeetmanlord.reflection_api.entity.players.player_connection.NMSPlayerConnectionReflection;
import com.github.yeetmanlord.reflection_api.exceptions.IllegalVersionException;
import com.github.yeetmanlord.reflection_api.exceptions.MappingsException;
import com.github.yeetmanlord.reflection_api.inventory.NMSItemStackReflection;
import com.github.yeetmanlord.reflection_api.mappings.IMapping;
import com.github.yeetmanlord.reflection_api.mappings.Mappings;
import com.github.yeetmanlord.reflection_api.mappings.VersionRange;
import com.github.yeetmanlord.reflection_api.mappings.types.PackageMapping;
import com.github.yeetmanlord.reflection_api.nbt.NMSNBTTagCompoundReflection;
import com.github.yeetmanlord.reflection_api.packets.chat.NMSChatPacketReflection;
import com.github.yeetmanlord.reflection_api.packets.entity.NMSEntityDestroyPacketReflection;
import com.github.yeetmanlord.reflection_api.packets.entity.NMSEntityEquipmentPacketReflection;
import com.github.yeetmanlord.reflection_api.packets.entity.NMSNamedEntitySpawnPacketReflection;
import com.github.yeetmanlord.reflection_api.packets.entity.NMSPacketPlayOutRelEntityMove;
import com.github.yeetmanlord.reflection_api.packets.network.NMSNetworkManagerReflection;
import com.github.yeetmanlord.reflection_api.packets.player.NMSPlayerInfoPacketReflection;
import com.github.yeetmanlord.reflection_api.packets.player.NMSScoreboardTeamPacketReflection;
import com.github.yeetmanlord.reflection_api.packets.player.NMSTitlePacketReflection;
import com.github.yeetmanlord.reflection_api.scoreboard.NMSScoreboardTeamReflection;
import com.github.yeetmanlord.reflection_api.server.NMSServerReflection;
import com.github.yeetmanlord.reflection_api.util.EnumEquipmentSlot;
import com.github.yeetmanlord.reflection_api.util.ParticleUtility;
import com.github.yeetmanlord.reflection_api.util.VersionMaterial;
import com.github.yeetmanlord.reflection_api.world.NMSVec3DReflection;
import com.github.yeetmanlord.reflection_api.world.NMSWorldServerReflection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.NameTagVisibility;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * ReflectionAPI main class. ReflectionAPI is a library that allows you to access NMS classes and methods without having to
 * worry about version differences. It is designed to be as easy to use as possible, and to be as flexible as possible.
 * It supports custom mappings, to allow you to access fields, run methods, get classes, etc. independently of the version.
 * <p>
 * It provides a suit of utilities to make things easier for you, such as a {@link VersionMaterial} class, which is similar to
 * XMaterial, but isn't as cool. It also provides a {@link ParticleUtility} class, which allows you to send particles to players
 * without having to worry about version differences. There a bunch of other utility classes. However, the bulk of classes are wrappers
 * for NMS classes. These wrappers allow you to access NMS classes without having to do any reflection. They also allow you to access
 * NMS methods without having to worry about version differences. There is a small amount of built-in mappings, but you can add your own.
 * <p>
 * This class is the main class of ReflectionAPI. It is used to get the current version of Minecraft, as well as perform utility functions.
 * <p>
 * If it seems like there's a bunch of random things in this api, it's because there is. I develop this API as I develop my plugins, so I
 * add things as I need them. I try to keep it organized, but it's kind of random. Packets are useful though.
 */
public class ReflectionApi {

    /**
     * Range of supported versions
     */
    public static final VersionRange SUPPORTED_VERSIONS = new VersionRange(Version.UNSUPPORTED_MIN, Version.MAX);

    /**
     * Range of maintained versions
     */
    public static final VersionRange MAINTAINED_VERSIONS = new VersionRange(Version.SUPPORTED_MIN, Version.MAX);

    /*
        List of major versions API-wise. These versions are for mappings and mark points of major change.
     */
    /**
     * Minecraft version 1.8. I originally started building ReflectionAPI on 1.8 but then moved to 1.8.8.
     * Version 1.8 is not supported anymore, since I didn't <em>specifically</em> develop for it. However,
     * it is still possible that it will work by chance.
     */
    public static final Version v1_8 = new Version(8, 0);

    /**
     * Minecraft version 1.8.1. There isn't much of a change, I think, between 1.8.1 and 1.8.8. It is likely that this API
     * will function on version 1.8.1.
     */
    public static final Version v1_8_1 = new Version(8, 1);

    /**
     * Minecraft version 1.9. This is the combat update. There are major inventory and ender dragon changes in this version.
     */
    public static final Version v1_9 = new Version(9, 0);

    /**
     * Minecraft version 1.10. This is the frostburn update. There are some entity metadata changes in this version and data watcher updates.
     */
    public static final Version v1_10 = new Version(10, 0);

    /**
     * Minecraft version 1.13. This is update aquatic. There are major changes to world generation and some packet changes (Entity use packet).
     * The biggest change, however, is the massive changes to materials, commands, blockstates, block data, and much, much, more.
     */
    public static final Version v1_13 = new Version(13, 0);

    /**
     * Minecraft version 1.14. This is the village and pillage update. There are major changes to villages and some packet changes.
     */
    public static final Version v1_14 = new Version(14, 0);

    /**
     * Minecraft version 1.15. This is the busy bees update. There are some packet changes and some entity changes (Entity metadata).
     */
    public static final Version v1_15 = new Version(15, 0);

    /**
     * Minecraft version 1.16. This is the nether update. There were some entity storage changes.
     */
    public static final Version v1_16 = new Version(16, 0);

    /**
     * Minecraft version 1.17. This is the caves and cliffs update pt. 1. There are major changes to packets and the entire setup of the Minecraft server.
     * Past this version, you must use a {@link PackageMapping} to more easily get classes. Sadly, however, it seems that many nms classes have lost a lot
     * of their field and method names just reverting to generic letters.
     */
    public static final Version v1_17 = new Version(17, 0);

    /**
     * Minecraft version 1.18. This is the caves and cliffs update pt. 2. There are major changes to packets and the entire setup of the Minecraft server.
     * To an even greater degree than 1.17, many nms classes have lost a lot of their field and method names just reverted to generic letters. This means that
     * there are plenty more mappings to be added.
     */
    public static final Version v1_18 = new Version(18, 0);

    /**
     * Minecraft version 1.19. This is the wild update. There are major changes to packets and the entire setup of the Minecraft server. Once again, many nms
     * classes have lost a lot of their field and method names that were just reverted to generic letters. More mappings here as well. Additionally, Mojang began implementation
     * of what would become the chat reporting update.
     */
    public static final Version v1_19 = new Version(19, 0);

    /**
     * Doesn't exist yet, but I have it here because 1.20 will eventually be added.
     */
    public static final Version v1_20 = new Version(20, 0);

    /**
     * Minecraft version 1.19.1. This is the chat reporting update. There are major changes to packets mostly in the form of signed chat packets. Chat packets had an overhaul.
     */
    public static Version v1_19_1 = new Version(19, 1);

    private static Version getVersion() {

        try {
            NMSObjectReflection object = new NMSObjectReflection(Bukkit.getServer(), "getServer");
            try {
                return new Version((String) object.invokeMethodForNmsObject("getVersion"));
            } catch (Exception e) {
                // Version is higher than 1.18, so we need to use a different method (It was renamed)
                try {
                     return new Version((String) object.invokeMethodForNmsObject("G"));
                } catch (ClassCastException exc) {
                    return new Version((String) object.invokeMethodForNmsObject("F"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * Runs a series of tests to ensure that ReflectionAPI is working properly. These are by no means exhaustive, but they should
     * give you a good idea of whether ReflectionAPI is working properly. Most classes catch errors and print stack traces, so you
     * will also need to check console logs. This will test any registered mappings as well.
     *
     * @param executor The player to run the tests on
     * @return Whether the tests passed
     */
    public static boolean runReflectionTests(Player executor) {
        try {
            // Chat packets
            NMSChatPacketReflection packet = new NMSChatPacketReflection(ChatColor.RED + "Hello World!", NMSChatPacketReflection.EnumChatPosition.CHAT);

            // Entity packets
            NMSEntityDestroyPacketReflection entityPacket = new NMSEntityDestroyPacketReflection(1);
            NMSEntityEquipmentPacketReflection equipmentPacketReflection = new NMSEntityEquipmentPacketReflection(1, EnumEquipmentSlot.HEAD, new ItemStack(Material.AIR));
            NMSNamedEntitySpawnPacketReflection spawnPacket = new NMSNamedEntitySpawnPacketReflection(new NMSPlayerReflection(executor));
            NMSPacketPlayOutRelEntityMove movePacket = new NMSPacketPlayOutRelEntityMove(1, 0L, 0L, 0L, (byte) 127, (byte) 0, false);

            // Player connection
            NMSPlayerConnectionReflection connection = new NMSPlayerConnectionReflection(new NMSPlayerReflection(executor));

            // Network manager
            NMSNetworkManagerReflection networkManager = new NMSNetworkManagerReflection(NMSNetworkManagerReflection.EnumNetworkDirection.SERVERBOUND);

            // Send packets
            connection.sendPacket(packet);

            // Player tests
            NMSPlayerReflection player = new NMSPlayerReflection(executor);
            player.setLocation(executor.getLocation().add(0, 1, 0));
            NMSAxisAlignedBBReflection b = player.getBoundingBox();
            b = new NMSAxisAlignedBBReflection(0, 0, 0, 1, 1, 1);

            // Player packets
            NMSPlayerInfoPacketReflection infoPacket = new NMSPlayerInfoPacketReflection(NMSPlayerInfoPacketReflection.EnumPlayerInfoPacketAction.ADD_PLAYER, player);

            /// Teams
            NMSScoreboardTeamReflection team = new NMSScoreboardTeamReflection(Bukkit.getScoreboardManager().getMainScoreboard(), "test");
            team.setPrefix("test");
            team.setSuffix("test");
            team.setDisplayName("test");
            team.setAllowFriendlyFire(true);
            team.setCanSeeFriendlyInvisibles(true);
            team.setNametagVisibility(NameTagVisibility.ALWAYS);
            team.setCanSeeFriendlyInvisibles(true);
            team.setChatFormat(ChatColor.AQUA);
            NMSScoreboardTeamPacketReflection removePacket = new NMSScoreboardTeamPacketReflection(team, NMSScoreboardTeamPacketReflection.TeamPacketAction.REMOVE);
            NMSScoreboardTeamPacketReflection addPacket = new NMSScoreboardTeamPacketReflection(team, "123", NMSScoreboardTeamPacketReflection.TeamPacketAction.ADD_PLAYERS);

            /// Title
            NMSTitlePacketReflection titlePacket = new NMSTitlePacketReflection(NMSTitlePacketReflection.NMSEnumTitleAction.TITLE, NMSChatSerializerReflection.createChatComponentFromText(ChatColor.RED + "Hello World!"));
            NMSTitlePacketReflection subtitlePacket = new NMSTitlePacketReflection(NMSTitlePacketReflection.NMSEnumTitleAction.SUBTITLE, NMSChatSerializerReflection.createChatComponentFromText(ChatColor.RED + "Hello World!"));
            NMSTitlePacketReflection timesPacket = new NMSTitlePacketReflection(10, 20, 10);

            /// Actionbar
            NMSChatPacketReflection actionbarPacket = new NMSChatPacketReflection(ChatColor.RED + "Hello World!", NMSChatPacketReflection.EnumChatPosition.GAME_INFO);

            connection.sendPacket(actionbarPacket);

            /// NBT tests
            NMSItemStackReflection item = new NMSItemStackReflection(new ItemStack(Material.DIAMOND));
            NMSNBTTagCompoundReflection tag = new NMSNBTTagCompoundReflection();
            tag.setString("test", "test");
            item.setTag(tag);
            executor.getInventory().addItem(item.asBukkit());

            // Interact manager
            if (ReflectionApi.version.isOlder(v1_17)) {
                NMSPlayerInteractManagerReflection interactManager = new NMSPlayerInteractManagerReflection(player);
            }

            // Block position
            NMSBlockPositionReflection blockPosition = new NMSBlockPositionReflection(executor.getLocation());

            // World & server
            NMSWorldServerReflection world = new NMSWorldServerReflection(executor.getWorld());
            NMSServerReflection server = new NMSServerReflection(Bukkit.getServer());

            // Particle
            ParticleUtility.spawnParticle(executor.getLocation(), ParticleUtility.ParticleTypes.CRIT_MAGIC, 1, 1, 1, 1, 1, 30);

            // Vec 3D
            NMSVec3DReflection vec = new NMSVec3DReflection(0, 0, 0);


            boolean testsPassed = true;
            // Material tests
            for (VersionMaterial material : VersionMaterial.stringMaterialMap.values()) {
                Material mat = material.getMaterial();
                if (mat != null) {
                    System.out.println(material.getFlatMaterial() + " " + material.getMaterial());
                } else {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + material.getFlatMaterial() + " failed to load! (NULL)");
                }
            }

            // Mappings tests
            for (IMapping<?> mappings : Mappings.mappings) {
                boolean passed = mappings.testMapping();
                if (passed) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Mapping `" + mappings.getName() + "` tests passed");
                } else {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Mapping `" + mappings.getName() + "` tests failed");
                    testsPassed = false;
                }
            }

            return testsPassed;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Version version;

    /**
     * Initializes the Reflection API. This should be called in the onEnable method of your plugin.
     * This will also disable the plugin if the version is not supported. This also loads the mappings and server version.
     * @param plugin The plugin to disable if the version is not supported.
     */
    public static void init(JavaPlugin plugin) {

        version = getVersion();

        try {

            if (version == null) {
                throw (new IllegalVersionException("Cannot find the version for this server. Likely means this plugin is broken!!"));
            }

        } catch (IllegalVersionException versionException) {
            versionException.printStackTrace();
            Bukkit.getConsoleSender().sendMessage((ChatColor.DARK_RED + "FATAL ERROR: Cannot find the version for this server. Likely means this plugin is broken!!"));
            Bukkit.getPluginManager().disablePlugin(plugin);
        }

        try {
            Mappings.loadMappings();
        } catch (MappingsException exc) {

            exc.printStackTrace();
            Bukkit.getConsoleSender().sendMessage((ChatColor.DARK_RED + "ERROR: " + ChatColor.RED + "Could not properly load mappings. Because of this I do not recommend using this plugin on your current version because it could easily lead to errors."));

        }
        if (!SUPPORTED_VERSIONS.isWithinRange(version)) {
            Bukkit.getConsoleSender().sendMessage((ChatColor.YELLOW + "[WARNING] The server version you are using is not supported! This means that this plugin may not work properly."));
        } else if (!MAINTAINED_VERSIONS.isWithinRange(version)) {
            Bukkit.getConsoleSender().sendMessage((ChatColor.YELLOW + "[WARNING] The server version you are using is not maintained! This means that this plugin some features may not work properly. Please use at least version " + MAINTAINED_VERSIONS.start + "."));
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[ReflectionAPI] Running on server version &2" + version));

    }

    public static String getBasePackage() {
        if (version.isOlder(v1_17)) {
            String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
            return "net.minecraft.server." + version;
        } else {
            return "net.minecraft";
        }
    }

    public static Class<?> getNMSClass(String className) {
        if (version.isOlder(v1_17)) {
            String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
            String name = "net.minecraft.server." + version + className;
            Class<?> nmsClass;

            try {
                nmsClass = Class.forName(name);
                return nmsClass;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                return Class.forName("net.minecraft." + className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


        return null;

    }

    public static Class<?> getNMSClass(PackageMapping mapping, String className) {
        try {
            return getNMSClass(mapping.getNMSSubPackage() + className);
        } catch (MappingsException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> getCraftBukkitClass(String className, String subpackage) {

        String pack = Bukkit.getServer().getClass().getPackage().getName();

        if (!subpackage.isEmpty() && subpackage.charAt(subpackage.length() - 1) != '.') {
            subpackage += '.';
        }

        String name = pack + "." + subpackage + className;
        Class<?> bukkitClass;

        try {
            bukkitClass = Class.forName(name);
            return bukkitClass;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static Class<?> getNMSInnerClass(String innerClassName, String parenetClassName) {

        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
        String className = parenetClassName + "$" + innerClassName;

        return getNMSClass(className);

    }

    public static Class<?> getNMSInnerClass(PackageMapping mapping, String innerClassName, String parenetClassName) {
        try {
            return getNMSInnerClass(innerClassName, mapping.getNMSSubPackage() + parenetClassName);
        } catch (MappingsException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the class of an array of a given NMS class
     *
     * @param className The NMS class name
     * @return The array class version of an NMS class
     */
    public static Class<?> getNMSClassArray(PackageMapping mapping, String className) {

        Class<?> type = getNMSClass(mapping, className);
        return Array.newInstance(type, 1).getClass();

    }

    public static Class<?> getNMSInnerClassArray(PackageMapping mapping, String innerClassName, String parenetClassName) {

        Class<?> type = getNMSInnerClass(mapping, innerClassName, parenetClassName);
        return Array.newInstance(type, 1).getClass();

    }

    public static Object getHandle(Object bukkitObject) {

        try {
            Method getHandle = bukkitObject.getClass().getMethod("getHandle");
            return getHandle.invoke(bukkitObject);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * Casts a given array to the an nms class by the given name
     *
     * @param nmsClassName The name of the NMS class to cast to
     * @param arrayToCast  The array you are casting
     * @return Returns an object array casted to the given nmsClassName
     * @throws IllegalArgumentException when any value in the arrayToCast are not
     *                                  castable to the given class
     */
    public static Object[] castArrayToNMS(PackageMapping mapping, String nmsClassName, Object[] arrayToCast) throws IllegalArgumentException {

        Class<?> type = getNMSClass(mapping, nmsClassName);
        Class<?> arrayType = getNMSClassArray(mapping, nmsClassName);
        Object[] castedArray = Arrays.copyOf((Object[]) arrayType.cast(Array.newInstance(type, arrayToCast.length)), arrayToCast.length);

        for (int x = 0; x < arrayToCast.length; x++) {

            if (type.isInstance(arrayToCast[x])) {
                castedArray[x] = type.cast(arrayToCast[x]);
            } else {
                System.err.println(arrayToCast[x]);
                System.err.println(nmsClassName);
                throw (new IllegalArgumentException("The provided array contains objects that are not instances of the given class"));
            }

        }

        return castedArray;

    }

}
