# ReflectionAPI
ReflectionAPI is powerful toolset for working with cross-version compatible NMS. It has many features including generic and specific object wrappers, mappings, packet handling, version detection, and more!

## Contents 
[Object Wrappers](#object-wrappers)
- [Generic Object Wrapper](#generic-object-wrapper)
- [Specialized Wrappers](#specialized-wrappers)
- [Note on Packets](#special-note-on-packets)

[Mappings](#mappings)

[Version Detection](#version-detection)

[Extras](#extras)

# Object Wrappers
Object wrappers are a way to quickly and easily interface with NMS code without the overhead of having to actually use reflection. There are many different types of wrappers, starting with the basic generic wrapper going down to wrappers for specific network packets.

## Generic Object Wrapper
ReflectionAPI provides a generic wrapper object called `NMSObjectReflection`. I find that in most cases, I use this to wrap the result of invoking a method that returns an NMS object. This is a simple wrapper that can easily access fields and run methods.
#### For example:
```java
NMSObjectReflection nmsObj = <something or other>;
try {
 
    nmsObj.invokeMethodForNmsObject("kill"); // Invokes the kill method within this object.
 
} catch (NoSuchMethodException exc) {
 
    Bukkit.getConsoleSender().sendMessage("Method not found")
 
// Or some other error handling
 
}
```

## Specialized Wrappers
ReflectionAPI comes with a suit of specialized wrappers for various different objects including WorldServer, Minecraft Server, Entity, EntityPlayer, Packets, and more. Unlike the generic wrapper, these specialized wrappers come with extra methods that allow you to easily access methods within the class. Additionally, these methods include mappings where applicable to ensure the right method is being called throughout all versions. There is by far not a complete set of wrappers for every NMS class, so if it becomes necessary, you can easily implement your own wrapper class by extending NMSObjectReflection or any other existing wrappers. I create new wrappers based off of what I need although I feel like the existing ones will definitely be helpful for many developers.
#### For Example:
```java
NMSPlayerReflection nmsPlayer = new NMSPlayerReflection(bukkitPlayer); // Can instantiate a reflection using a Bukkit equivalent
NMSTitlePacketReflection titlePacket = new NMSTitlePacketReflection(NMSEnumTitleAction.TITLE,
    NMSChatSerializerReflection.createChatComponentFromText("&6Title!"); // Supports color codes
nmsPlayer.getPlayerConnection().sendPacket(titlePacket) // To send a packet it must be an instance of NMSPacketReflection
```
That is only a small example of what can be done with these specialized wrappers. You could easily use this to create NPCs. I've even used it to create custom ai. 
(On that, it is a little difficult and I haven't done the research on pathfinder goals. I more or less just used in-built methods to create custom ai rather than pathfinder goals. I imagine I will implement some sort of pathfinder goal system in the future)

## Special Note On Packets
ReflectionAPI provides a bunch of different packet wrappers making it easier to send and interpret received packets. However, the api's library of packets is nowhere near complete and, additionally, there is no safety baked into these wrappers. If you somehow screw up and cause a crash it is likely not an implementation issue on the API's part. For example, providing the wrong id for a DataWatcher will result in a server crash. Incorrectly handling incoming packets will cause the client to be disconnected. And other things. I try to make the packet creation process easier but what you do with those packets cannot be 100% safe.
In most cases, you can expect packets to be just fine, especially if it's just creating a new packet and then sending it. (Like titles and such.) Packet wrappers are also a little more specific since they create a new instance of a packet (Unless you provide it with an existing packet.)

# Mappings
ReflectionAPI also includes mappings. These mappings are found in the Mappings class. There are a few mappings that you can use. There are mappings for methods (With arguments and without) that also support a return type. There are class name mappings. There are field mappings which map the name of a field and allow you to easily access that field. There are also package mappings, but those are used after 1.17 when NMS was put into a different structure. Then there is NMSFieldMapping which specifically maps a field of a NMS class. I haven't really used this one but it may be useful. Then there is ValueMapping which has a changing value based on the version. I have used this for mapping the index of a DataWatcher entry.
Most mappings are already implemented into existing code (In specialized wrappers.) But you can define your own mappings.
#### For Example:
```java
public class MyMappings {
    public static final FieldMapping<Integer, NMSEntityReflection> MAPPING_EXAMPLE = new FieldMapping<>("MyMapping", NMSEntityReflection.class, Maps.newHashMap());
    // First generic argument is the field type, second generic type is associated reflection wrapper.

    // Should be called in plugin's onEnable
    public static void init() {
         // If you did not per-define your mappings add them here
         MAPPING_EXAMPLE.addMapping(new VersionRange(ReflectionApi.v1_8,
            ReflectionApi.v1_17), "exampleField");
         MAPPING_EXAMPLE.addMapping(new VersionRange(ReflectionApi.v1_17,
            Version.MAX), "a");
    }
}
```

# Version Detection
Version detection is very easy, in fact it's accessible right from the main API class. You can use ReflectionApi.version to access the detected version and compare it to other versions to see if you should run code.
#### For Example:
```java
if (ReflectionApi.version.isOlder(ReflectionApi.v1_16)) {
    // isOlder is a non-inclusive operation, so it will not include the version you are checking
    // This is checking for all version lower than but not including 1.16
    player.getInventory().addItem(new ItemStack(Material.NETHERITE_INGOT))
} else if (ReflectionApi.version.isNewer(ReflectionApi.v1_18)) {
    // This can also be done using a string of "1.16"
    // isNewer is an inclusive operation, so it will include the version you are checking
    // This is checking for all version higher than and including 1.18
    // This can also be done using a string of "1.18"
    player.getInventory().addItem(new ItemStack(Material.DEEPSLATE))
}
```

# Extras

## Initialization
To initialize ReflectionAPI, you must call `ReflectionApi.init()` in your `onEnable` method. This is required because this API is not meant to be a plugin and instead should be compiled into your resulting jar. There is no real need for it to be a full-fledged plugin so it is simple a library.

## Javadoc
Javadoc can be found can be found on my [github.io page](https://yeetmanlord.github.io/docs/ReflectionAPI). Additionally, you can find more API examples there as well. (These examples also have stuff for another API I'm developing.) I am still in the process of creating more of these examples however.

## Adding to project via maven
You can add ReflectionAPI to your project via jitpack.
```html
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
<dependency>
    <groupId>com.github.YeetManLord</groupId>
    <artifactId>ReflectionAPI</artifactId>
    <version>Tag</version>
</dependency>
```
