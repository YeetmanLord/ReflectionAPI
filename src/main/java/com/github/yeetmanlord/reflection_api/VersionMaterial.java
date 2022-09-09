package com.github.yeetmanlord.reflection_api;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class VersionMaterial {

    public static final boolean IS_FLAT = ReflectionApi.version.isNewer("1.13");
    private String legacyMaterial, flatMaterial;
    private byte data;

    public VersionMaterial(String legacyMaterial, String flatMaterial, byte data) {
        this.legacyMaterial = legacyMaterial;
        this.flatMaterial = flatMaterial;
        this.data = data;
    }

    public Material get(){
        if (IS_FLAT) {
            return Material.valueOf(this.flatMaterial.toLowerCase());
        } else {
            return Material.valueOf(this.legacyMaterial.toUpperCase());
        }
    }

    public ItemStack getItem() {
        if (IS_FLAT) {
            return new ItemStack(get());
        }
        else {
            return new ItemStack(get(), this.data);
        }
    }

    public static final VersionMaterial WHITE_WOOL = new VersionMaterial("wool", "white_wool", (byte)0);
    public static final VersionMaterial ORANGE_WOOL = new VersionMaterial("wool", "orange_wool", (byte)1);
    public static final VersionMaterial MAGENTA_WOOL = new VersionMaterial("wool", "magenta_wool", (byte)2);
    public static final VersionMaterial LIGHT_BLUE_WOOL = new VersionMaterial("wool", "light_blue_wool", (byte)3);
    public static final VersionMaterial YELLOW_WOOL = new VersionMaterial("wool", "yellow_wool", (byte)4);
    public static final VersionMaterial LIME_WOOL = new VersionMaterial("wool", "lime_wool", (byte)5);
    public static final VersionMaterial PINK_WOOL = new VersionMaterial("wool", "pink_wool", (byte)6);
    public static final VersionMaterial GRAY_WOOL = new VersionMaterial("wool", "gray_wool", (byte)7);
    public static final VersionMaterial LIGHT_GRAY_WOOL = new VersionMaterial("wool", "light_gray_wool", (byte)8);
    public static final VersionMaterial CYAN_WOOL = new VersionMaterial("wool", "cyan_wool", (byte)9);
    public static final VersionMaterial PURPLE_WOOL = new VersionMaterial("wool", "purple_wool", (byte)10);
    public static final VersionMaterial BLUE_WOOL = new VersionMaterial("wool", "blue_wool", (byte)11);
    public static final VersionMaterial BROWN_WOOL = new VersionMaterial("wool", "brown_wool", (byte)12);
    public static final VersionMaterial GREEN_WOOL = new VersionMaterial("wool", "green_wool", (byte)13);
    public static final VersionMaterial RED_WOOL = new VersionMaterial("wool", "red_wool", (byte)14);
    public static final VersionMaterial BLACK_WOOL = new VersionMaterial("wool", "black_wool", (byte)15);

    public static final VersionMaterial WHITE_BED = new VersionMaterial("bed", "white_bed", (byte)0);
    public static final VersionMaterial ORANGE_BED = new VersionMaterial("bed", "orange_bed", (byte)1);
    public static final VersionMaterial MAGENTA_BED = new VersionMaterial("bed", "magenta_bed", (byte)2);
    public static final VersionMaterial LIGHT_BLUE_BED = new VersionMaterial("bed", "light_blue_bed", (byte)3);
    public static final VersionMaterial YELLOW_BED = new VersionMaterial("bed", "yellow_bed", (byte)4);
    public static final VersionMaterial LIME_BED = new VersionMaterial("bed", "lime_bed", (byte)5);
    public static final VersionMaterial PINK_BED = new VersionMaterial("bed", "pink_bed", (byte)6);
    public static final VersionMaterial GRAY_BED = new VersionMaterial("bed", "gray_bed", (byte)7);
    public static final VersionMaterial LIGHT_GRAY_BED = new VersionMaterial("bed", "light_gray_bed", (byte)8);
    public static final VersionMaterial CYAN_BED = new VersionMaterial("bed", "cyan_bed", (byte)9);
    public static final VersionMaterial PURPLE_BED = new VersionMaterial("bed", "purple_bed", (byte)10);
    public static final VersionMaterial BLUE_BED = new VersionMaterial("bed", "blue_bed", (byte)11);
    public static final VersionMaterial BROWN_BED = new VersionMaterial("bed", "brown_bed", (byte)12);
    public static final VersionMaterial GREEN_BED = new VersionMaterial("bed", "green_bed", (byte)13);
    public static final VersionMaterial RED_BED = new VersionMaterial("bed", "red_bed", (byte)14);
    public static final VersionMaterial BLACK_BED = new VersionMaterial("bed", "black_bed", (byte)15);

    public static final VersionMaterial WHITE_STAINED_GLASS_PANEL = new VersionMaterial("stained_glass_pane", "white_stained_glass_pane", (byte)0);
    public static final VersionMaterial ORANGE_STAINED_GLASS_PANEL = new VersionMaterial("stained_glass_pane", "orange_stained_glass_pane", (byte)1);
    public static final VersionMaterial MAGENTA_STAINED_GLASS_PANEL = new VersionMaterial("stained_glass_pane", "magenta_stained_glass_pane", (byte)2);
    public static final VersionMaterial LIGHT_BLUE_STAINED_GLASS_PANEL = new VersionMaterial("stained_glass_pane", "light_blue_stained_glass_pane", (byte)3);
    public static final VersionMaterial YELLOW_STAINED_GLASS_PANEL = new VersionMaterial("stained_glass_pane", "yellow_stained_glass_pane", (byte)4);
    public static final VersionMaterial LIME_STAINED_GLASS_PANEL = new VersionMaterial("stained_glass_pane", "lime_stained_glass_pane", (byte)5);
    public static final VersionMaterial PINK_STAINED_GLASS_PANEL = new VersionMaterial("stained_glass_pane", "pink_stained_glass_pane", (byte)6);
    public static final VersionMaterial GRAY_STAINED_GLASS_PANEL = new VersionMaterial("stained_glass_pane", "gray_stained_glass_pane", (byte)7);
    public static final VersionMaterial LIGHT_GRAY_STAINED_GLASS_PANEL = new VersionMaterial("stained_glass_pane", "light_gray_stained_glass_pane", (byte)8);
    public static final VersionMaterial CYAN_STAINED_GLASS_PANEL = new VersionMaterial("stained_glass_pane", "cyan_stained_glass_pane", (byte)9);
    public static final VersionMaterial PURPLE_STAINED_GLASS_PANEL = new VersionMaterial("stained_glass_pane", "purple_stained_glass_pane", (byte)10);
    public static final VersionMaterial BLUE_STAINED_GLASS_PANEL = new VersionMaterial("stained_glass_pane", "blue_stained_glass_pane", (byte)11);
    public static final VersionMaterial BROWN_STAINED_GLASS_PANEL = new VersionMaterial("stained_glass_pane", "brown_stained_glass_pane", (byte)12);
    public static final VersionMaterial GREEN_STAINED_GLASS_PANEL = new VersionMaterial("stained_glass_pane", "green_stained_glass_pane", (byte)13);
    public static final VersionMaterial RED_STAINED_GLASS_PANEL = new VersionMaterial("stained_glass_pane", "red_stained_glass_pane", (byte)14);
    public static final VersionMaterial BLACK_STAINED_GLASS_PANEL = new VersionMaterial("stained_glass_pane", "black_stained_glass_pane", (byte)15);



}