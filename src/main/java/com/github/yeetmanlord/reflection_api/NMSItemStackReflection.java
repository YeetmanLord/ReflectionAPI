package com.github.yeetmanlord.reflection_api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.github.yeetmanlord.reflection_api.nbt.NMSNBTTagCompoundReflection;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NMSItemStackReflection extends NMSObjectReflection {

    public NMSItemStackReflection(Object nmsObject) {

        super(nmsObject);

    }

    public NMSItemStackReflection(ItemStack stack) {

        super(init(stack));

    }

    public NMSItemStackReflection(Material mat, int amount) {

        this(new ItemStack(mat, amount));

    }

    public NMSItemStackReflection(Material mat, int amount, short damage) {

        this(new ItemStack(mat, amount, damage));

    }

    public NMSItemStackReflection(Material mat) {

        this(new ItemStack(mat));

    }

    public NMSItemStackReflection(Material mat, int amount, short damage, Byte data) {

        this(new ItemStack(mat, amount, damage, data));

    }

    private static Object init(ItemStack stack) {

        try {
            Method asNMSCopy = ReflectionApi.getCraftBukkitClass("CraftItemStack", "inventory").getMethod("asNMSCopy", ItemStack.class);
            return asNMSCopy.invoke(null, stack);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static final Class<?> staticClass = ReflectionApi.getNMSClass("ItemStack");

    public static NMSItemStackReflection cast(NMSObjectReflection refl) {

        if (staticClass.isInstance(refl.getNmsObject())) {
            return new NMSItemStackReflection(refl.getNmsObject());
        }

        throw new ClassCastException("Cannot cast " + refl.toString() + " to NMSItemStackReflection");

    }

    public NMSNBTTagCompoundReflection getTag() {
        try {
            return new NMSNBTTagCompoundReflection(invokeMethodForNmsObject("getTag"));
        } catch (NoSuchMethodException exc) {
            exc.printStackTrace();
        }
        return null;
    }

    public ItemStack asBukkit() {
        Class<?> craftItemStack = ReflectionApi.getCraftBukkitClass("CraftItemStack", "inventory");
        try {
            Method asCraftMirror = craftItemStack.getMethod("asCraftMirror", staticClass);
            return (ItemStack) asCraftMirror.invoke(null, this.getNmsObject());
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setTag(NMSNBTTagCompoundReflection tag) {
        try {
            this.invokeMethodForNmsObject("setTag", new Class<?>[]{NMSNBTTagCompoundReflection.staticClass}, new Object[]{tag.getNmsObject()});
        } catch (NoSuchMethodException exc) {
            exc.printStackTrace();
        }
    }

}
