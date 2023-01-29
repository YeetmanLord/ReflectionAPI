package com.github.yeetmanlord.reflection_api.entity;

import com.github.yeetmanlord.reflection_api.NMSObjectReflection;
import com.github.yeetmanlord.reflection_api.ReflectionApi;
import com.github.yeetmanlord.reflection_api.mappings.Mappings;
import org.bukkit.Location;


/**
 * This class is a wrapper for the NMS AxisAlignedBB class. It is used to create a bounding box for
 * entities, or just as a general bounding box, since it also has methods for checking if a point is
 * within the bounding box, or if an entity is within the bounding box.
 */
public class NMSAxisAlignedBBReflection extends NMSObjectReflection {

    private double x1;
    private double y1;
    private double z1;

    private double x2;
    private double y2;
    private double z2;


    public NMSAxisAlignedBBReflection(double x1, double y1, double z1, double x2, double y2, double z2) {
        super(Mappings.WORLD_PHYSICS_PACKAGE_MAPPING, "AxisAlignedBB", new Class[]{double.class, double.class, double.class, double.class, double.class, double.class}, new Object[]{x1, y1, z1, x2, y2, z2});
        this.x1 = Math.min(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.z1 = Math.min(z1, z2);
        this.x2 = Math.max(x1, x2);
        this.y2 = Math.max(y1, y2);
        this.z2 = Math.max(z1, z2);
    }

    public NMSAxisAlignedBBReflection(Location loc1, Location loc2) {
        this(loc1.getX(), loc1.getY(), loc1.getZ(), loc2.getX(), loc2.getY(), loc2.getZ());
    }

    public NMSAxisAlignedBBReflection(Object nmsObject) {
        super(nmsObject);
        try {
            if (ReflectionApi.version.isNewer(ReflectionApi.v1_13) && ReflectionApi.version.isOlder(ReflectionApi.v1_17)) {
                this.x1 = (double) this.getFieldFromNmsObject("minX");
                this.y1 = (double) this.getFieldFromNmsObject("minY");
                this.z1 = (double) this.getFieldFromNmsObject("minZ");

                this.x2 = (double) this.getFieldFromNmsObject("maxX");
                this.y2 = (double) this.getFieldFromNmsObject("maxY");
                this.z2 = (double) this.getFieldFromNmsObject("maxZ");
            } else {
                this.x1 = (double) this.getFieldFromNmsObject("a");
                this.y1 = (double) this.getFieldFromNmsObject("b");
                this.z1 = (double) this.getFieldFromNmsObject("c");

                this.x2 = (double) this.getFieldFromNmsObject("d");
                this.y2 = (double) this.getFieldFromNmsObject("e");
                this.z2 = (double) this.getFieldFromNmsObject("f");
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "NMSAxisAlignedBBReflection{" +
                "x1: " + x1 +
                ", y1: " + y1 +
                ", z1: " + z1 +
                ", x2: " + x2 +
                ", y2: " + y2 +
                ", z2: " + z2 +
                '}';
    }

    /**
     * Checks if a point is within the bounding box.
     * @param x The x coordinate of the point.
     * @param y The y coordinate of the point.
     * @param z The z coordinate of the point.
     * @return True if the point is within the bounding box, false otherwise.
     */
    public boolean isWithinBoundingBox(double x, double y, double z) {
        return x >= this.x1 && x <= this.x2 && y >= this.y1 && y <= this.y2 && z >= this.z1 && z <= this.z2;
    }

    /**
     * Checks if an entity is within the bounding box.
     * @param boundingBox The bounding box to check.
     * @return True if the entity is within the bounding box, false otherwise.
     */
    public boolean doesCollide(NMSAxisAlignedBBReflection boundingBox) {
        return this.x1 <= boundingBox.x2 && this.x2 >= boundingBox.x1 && this.y1 <= boundingBox.y2 && this.y2 >= boundingBox.y1 && this.z1 <= boundingBox.z2 && this.z2 >= boundingBox.z1;
    }

    /**
     * Checks if a location is within the bounding box.
     * @param location The location to check.
     * @return True if the location is within the bounding box, false otherwise.
     */
    public boolean isWithinBoundingBox(Location location) {
        return this.isWithinBoundingBox(location.getX(), location.getY(), location.getZ());
    }

    public Location getStarting() {
        return new Location(null, this.x1, this.y1, this.z1);
    }

    public Location getEnding() {
        return new Location(null, this.x2, this.y2, this.z2);
    }

    public static final Class<?> staticClass = ReflectionApi.getNMSClass(Mappings.WORLD_PHYSICS_PACKAGE_MAPPING, "AxisAlignedBB");

}
