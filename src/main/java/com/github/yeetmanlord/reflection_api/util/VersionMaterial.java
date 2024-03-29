package com.github.yeetmanlord.reflection_api.util;

import com.github.yeetmanlord.reflection_api.ReflectionApi;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.UnsafeValues;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class VersionMaterial {
    public static final HashMap<String, VersionMaterial> stringMaterialMap = new HashMap<>();

    public static final HashMap<String, VersionMaterial> legacyStringMaterialMap = new HashMap<>();
    public static final boolean IS_FLAT = ReflectionApi.version.isNewer(ReflectionApi.v1_13);
    private final String legacyMaterial, flatMaterial;
    private final byte data;

    public VersionMaterial(String legacyMaterial, String flatMaterial, byte data) {
        this.legacyMaterial = legacyMaterial.toUpperCase();
        this.flatMaterial = flatMaterial.toUpperCase();
        this.data = data;
        stringMaterialMap.put(flatMaterial.toUpperCase(), this);
        legacyStringMaterialMap.put(legacyMaterial.toUpperCase(), this);
    }

    public String getLegacyMaterial() {
        return legacyMaterial;
    }

    public String getFlatMaterial() {
        return flatMaterial;
    }

    public byte getData() {
        return data;
    }

    public Material getMaterial() {
        if (IS_FLAT) {
            return Material.matchMaterial(this.flatMaterial.toUpperCase());
        } else {
            return Material.matchMaterial(this.legacyMaterial.toUpperCase());
        }
    }

    public ItemStack getItem() {
        if (IS_FLAT) {
            return new ItemStack(getMaterial());
        } else {
            return new ItemStack(getMaterial(), 1, this.data);
        }
    }

    public static VersionMaterial valueOf(String material) {
        return IS_FLAT ? stringMaterialMap.get(material) : legacyStringMaterialMap.get(material);
    }

    public static final VersionMaterial WHITE_WOOL = new VersionMaterial("wool", "white_wool", (byte) 0);
    public static final VersionMaterial ORANGE_WOOL = new VersionMaterial("wool", "orange_wool", (byte) 1);
    public static final VersionMaterial MAGENTA_WOOL = new VersionMaterial("wool", "magenta_wool", (byte) 2);
    public static final VersionMaterial LIGHT_BLUE_WOOL = new VersionMaterial("wool", "light_blue_wool", (byte) 3);
    public static final VersionMaterial YELLOW_WOOL = new VersionMaterial("wool", "yellow_wool", (byte) 4);
    public static final VersionMaterial LIME_WOOL = new VersionMaterial("wool", "lime_wool", (byte) 5);
    public static final VersionMaterial PINK_WOOL = new VersionMaterial("wool", "pink_wool", (byte) 6);
    public static final VersionMaterial GRAY_WOOL = new VersionMaterial("wool", "gray_wool", (byte) 7);
    public static final VersionMaterial LIGHT_GRAY_WOOL = new VersionMaterial("wool", "light_gray_wool", (byte) 8);
    public static final VersionMaterial CYAN_WOOL = new VersionMaterial("wool", "cyan_wool", (byte) 9);
    public static final VersionMaterial PURPLE_WOOL = new VersionMaterial("wool", "purple_wool", (byte) 10);
    public static final VersionMaterial BLUE_WOOL = new VersionMaterial("wool", "blue_wool", (byte) 11);
    public static final VersionMaterial BROWN_WOOL = new VersionMaterial("wool", "brown_wool", (byte) 12);
    public static final VersionMaterial GREEN_WOOL = new VersionMaterial("wool", "green_wool", (byte) 13);
    public static final VersionMaterial RED_WOOL = new VersionMaterial("wool", "red_wool", (byte) 14);
    public static final VersionMaterial BLACK_WOOL = new VersionMaterial("wool", "black_wool", (byte) 15);

    public static final VersionMaterial WHITE_BED = new VersionMaterial("bed", "white_bed", (byte) 0);
    public static final VersionMaterial ORANGE_BED = new VersionMaterial("bed", "orange_bed", (byte) 1);
    public static final VersionMaterial MAGENTA_BED = new VersionMaterial("bed", "magenta_bed", (byte) 2);
    public static final VersionMaterial LIGHT_BLUE_BED = new VersionMaterial("bed", "light_blue_bed", (byte) 3);
    public static final VersionMaterial YELLOW_BED = new VersionMaterial("bed", "yellow_bed", (byte) 4);
    public static final VersionMaterial LIME_BED = new VersionMaterial("bed", "lime_bed", (byte) 5);
    public static final VersionMaterial PINK_BED = new VersionMaterial("bed", "pink_bed", (byte) 6);
    public static final VersionMaterial GRAY_BED = new VersionMaterial("bed", "gray_bed", (byte) 7);
    public static final VersionMaterial LIGHT_GRAY_BED = new VersionMaterial("bed", "light_gray_bed", (byte) 8);
    public static final VersionMaterial CYAN_BED = new VersionMaterial("bed", "cyan_bed", (byte) 9);
    public static final VersionMaterial PURPLE_BED = new VersionMaterial("bed", "purple_bed", (byte) 10);
    public static final VersionMaterial BLUE_BED = new VersionMaterial("bed", "blue_bed", (byte) 11);
    public static final VersionMaterial BROWN_BED = new VersionMaterial("bed", "brown_bed", (byte) 12);
    public static final VersionMaterial GREEN_BED = new VersionMaterial("bed", "green_bed", (byte) 13);
    public static final VersionMaterial RED_BED = new VersionMaterial("bed", "red_bed", (byte) 14);
    public static final VersionMaterial BLACK_BED = new VersionMaterial("bed", "black_bed", (byte) 15);

    public static final VersionMaterial WHITE_STAINED_GLASS_PANE = new VersionMaterial("stained_glass_pane", "white_stained_glass_pane", (byte) 0);
    public static final VersionMaterial ORANGE_STAINED_GLASS_PANE = new VersionMaterial("stained_glass_pane", "orange_stained_glass_pane", (byte) 1);
    public static final VersionMaterial MAGENTA_STAINED_GLASS_PANE = new VersionMaterial("stained_glass_pane", "magenta_stained_glass_pane", (byte) 2);
    public static final VersionMaterial LIGHT_BLUE_STAINED_GLASS_PANE = new VersionMaterial("stained_glass_pane", "light_blue_stained_glass_pane", (byte) 3);
    public static final VersionMaterial YELLOW_STAINED_GLASS_PANE = new VersionMaterial("stained_glass_pane", "yellow_stained_glass_pane", (byte) 4);
    public static final VersionMaterial LIME_STAINED_GLASS_PANE = new VersionMaterial("stained_glass_pane", "lime_stained_glass_pane", (byte) 5);
    public static final VersionMaterial PINK_STAINED_GLASS_PANE = new VersionMaterial("stained_glass_pane", "pink_stained_glass_pane", (byte) 6);
    public static final VersionMaterial GRAY_STAINED_GLASS_PANE = new VersionMaterial("stained_glass_pane", "gray_stained_glass_pane", (byte) 7);
    public static final VersionMaterial LIGHT_GRAY_STAINED_GLASS_PANE = new VersionMaterial("stained_glass_pane", "light_gray_stained_glass_pane", (byte) 8);
    public static final VersionMaterial CYAN_STAINED_GLASS_PANE = new VersionMaterial("stained_glass_pane", "cyan_stained_glass_pane", (byte) 9);
    public static final VersionMaterial PURPLE_STAINED_GLASS_PANE = new VersionMaterial("stained_glass_pane", "purple_stained_glass_pane", (byte) 10);
    public static final VersionMaterial BLUE_STAINED_GLASS_PANE = new VersionMaterial("stained_glass_pane", "blue_stained_glass_pane", (byte) 11);
    public static final VersionMaterial BROWN_STAINED_GLASS_PANE = new VersionMaterial("stained_glass_pane", "brown_stained_glass_pane", (byte) 12);
    public static final VersionMaterial GREEN_STAINED_GLASS_PANE = new VersionMaterial("stained_glass_pane", "green_stained_glass_pane", (byte) 13);
    public static final VersionMaterial RED_STAINED_GLASS_PANE = new VersionMaterial("stained_glass_pane", "red_stained_glass_pane", (byte) 14);
    public static final VersionMaterial BLACK_STAINED_GLASS_PANE = new VersionMaterial("stained_glass_pane", "black_stained_glass_pane", (byte) 15);

    public static final VersionMaterial SKELETON_HEAD = new VersionMaterial("skull_item", "skeleton_skull", (byte) 0);
    public static final VersionMaterial WITHER_SKELETON_HEAD = new VersionMaterial("skull_item", "wither_skeleton_skull", (byte) 1);
    public static final VersionMaterial ZOMBIE_HEAD = new VersionMaterial("skull_item", "zombie_head", (byte) 2);
    public static final VersionMaterial PLAYER_HEAD = new VersionMaterial("skull_item", "player_head", (byte) 3);
    public static final VersionMaterial CREEPER_HEAD = new VersionMaterial("skull_item", "creeper_head", (byte) 4);
    public static final VersionMaterial DRAGON_HEAD = new VersionMaterial("skull_item", "dragon_head", (byte) 5);


    public static final VersionMaterial WHITE_STAINED_GLASS = new VersionMaterial("stained_glass", "white_stained_glass", (byte) 0);
    public static final VersionMaterial ORANGE_STAINED_GLASS = new VersionMaterial("stained_glass", "orange_stained_glass", (byte) 1);
    public static final VersionMaterial MAGENTA_STAINED_GLASS = new VersionMaterial("stained_glass", "magenta_stained_glass", (byte) 2);
    public static final VersionMaterial LIGHT_BLUE_STAINED_GLASS = new VersionMaterial("stained_glass", "light_blue_stained_glass", (byte) 3);
    public static final VersionMaterial YELLOW_STAINED_GLASS = new VersionMaterial("stained_glass", "yellow_stained_glass", (byte) 4);
    public static final VersionMaterial LIME_STAINED_GLASS = new VersionMaterial("stained_glass", "lime_stained_glass", (byte) 5);
    public static final VersionMaterial PINK_STAINED_GLASS = new VersionMaterial("stained_glass", "pink_stained_glass", (byte) 6);
    public static final VersionMaterial GRAY_STAINED_GLASS = new VersionMaterial("stained_glass", "gray_stained_glass", (byte) 7);
    public static final VersionMaterial LIGHT_GRAY_STAINED_GLASS = new VersionMaterial("stained_glass", "light_gray_stained_glass", (byte) 8);
    public static final VersionMaterial CYAN_STAINED_GLASS = new VersionMaterial("stained_glass", "cyan_stained_glass", (byte) 9);
    public static final VersionMaterial PURPLE_STAINED_GLASS = new VersionMaterial("stained_glass", "purple_stained_glass", (byte) 10);
    public static final VersionMaterial BLUE_STAINED_GLASS = new VersionMaterial("stained_glass", "blue_stained_glass", (byte) 11);
    public static final VersionMaterial BROWN_STAINED_GLASS = new VersionMaterial("stained_glass", "brown_stained_glass", (byte) 12);
    public static final VersionMaterial GREEN_STAINED_GLASS = new VersionMaterial("stained_glass", "green_stained_glass", (byte) 13);
    public static final VersionMaterial RED_STAINED_GLASS = new VersionMaterial("stained_glass", "red_stained_glass", (byte) 14);
    public static final VersionMaterial BLACK_STAINED_GLASS = new VersionMaterial("stained_glass", "black_stained_glass", (byte) 15);

    public static final VersionMaterial NETHERITE_HELMET = new VersionMaterial("netherite_helmet", "netherite_helmet", (byte) 0);
    public static final VersionMaterial NETHERITE_CHESTPLATE = new VersionMaterial("netherite_chestplate", "netherite_chestplate", (byte) 0);
    public static final VersionMaterial NETHERITE_LEGGINGS = new VersionMaterial("netherite_leggings", "netherite_leggings", (byte) 0);
    public static final VersionMaterial NETHERITE_BOOTS = new VersionMaterial("netherite_boots", "netherite_boots", (byte) 0);
    public static final VersionMaterial NETHERITE_SWORD = new VersionMaterial("netherite_sword", "netherite_sword", (byte) 0);
    public static final VersionMaterial NETHERITE_PICKAXE = new VersionMaterial("netherite_pickaxe", "netherite_pickaxe", (byte) 0);
    public static final VersionMaterial NETHERITE_AXE = new VersionMaterial("netherite_axe", "netherite_axe", (byte) 0);
    public static final VersionMaterial NETHERITE_SHOVEL = new VersionMaterial("netherite_shovel", "netherite_shovel", (byte) 0);
    public static final VersionMaterial NETHERITE_HOE = new VersionMaterial("netherite_hoe", "netherite_hoe", (byte) 0);
    public static final VersionMaterial NETHERITE_INGOT = new VersionMaterial("netherite_ingot", "netherite_ingot", (byte) 0);
    public static final VersionMaterial NETHERITE_SCRAP = new VersionMaterial("netherite_scrap", "netherite_scrap", (byte) 0);
    public static final VersionMaterial ANCIENT_DEBRIS = new VersionMaterial("ancient_debris", "ancient_debris", (byte) 0);
    public static final VersionMaterial NETHERITE_BLOCK = new VersionMaterial("netherite_block", "netherite_block", (byte) 0);

    public static final VersionMaterial WOODEN_SWORD = new VersionMaterial("wood_sword", "wooden_sword", (byte) 0);
    public static final VersionMaterial WOODEN_SHOVEL = new VersionMaterial("wood_spade", "wooden_shovel", (byte) 0);
    public static final VersionMaterial WOODEN_PICKAXE = new VersionMaterial("wood_pickaxe", "wooden_pickaxe", (byte) 0);
    public static final VersionMaterial WOODEN_AXE = new VersionMaterial("wood_axe", "wooden_axe", (byte) 0);
    public static final VersionMaterial WOODEN_HOE = new VersionMaterial("wood_hoe", "wooden_hoe", (byte) 0);

    public static final VersionMaterial GOLDEN_SWORD = new VersionMaterial("gold_sword", "golden_sword", (byte) 0);
    public static final VersionMaterial GOLDEN_SHOVEL = new VersionMaterial("gold_spade", "golden_shovel", (byte) 0);
    public static final VersionMaterial GOLDEN_PICKAXE = new VersionMaterial("gold_pickaxe", "golden_pickaxe", (byte) 0);
    public static final VersionMaterial GOLDEN_AXE = new VersionMaterial("gold_axe", "golden_axe", (byte) 0);
    public static final VersionMaterial GOLDEN_HOE = new VersionMaterial("gold_hoe", "golden_hoe", (byte) 0);
    public static final VersionMaterial GOLDEN_HELMET = new VersionMaterial("gold_helmet", "golden_helmet", (byte) 0);
    public static final VersionMaterial GOLDEN_CHESTPLATE = new VersionMaterial("gold_chestplate", "golden_chestplate", (byte) 0);
    public static final VersionMaterial GOLDEN_LEGGINGS = new VersionMaterial("gold_leggings", "golden_leggings", (byte) 0);
    public static final VersionMaterial GOLDEN_BOOTS = new VersionMaterial("gold_boots", "golden_boots", (byte) 0);

    public static final VersionMaterial IRON_SHOVEL = new VersionMaterial("iron_spade", "iron_shovel", (byte) 0);
    public static final VersionMaterial DIAMOND_SHOVEL = new VersionMaterial("diamond_spade", "diamond_shovel", (byte) 0);
    public static final VersionMaterial STONE_SHOVEL = new VersionMaterial("stone_spade", "stone_shovel", (byte) 0);

    public static final VersionMaterial GRASS_BLOCK = new VersionMaterial("grass", "grass_block", (byte) 0);
    public static final SignMaterial OAK_SIGN = new SignMaterial("sign", "oak_sign", (byte) 0);
    public static final SignMaterial SPRUCE_SIGN = new SignMaterial("sign", "spruce_sign", (byte) 0);
    public static final SignMaterial BIRCH_SIGN = new SignMaterial("sign", "birch_sign", (byte) 0);
    public static final SignMaterial JUNGLE_SIGN = new SignMaterial("sign", "jungle_sign", (byte) 0);
    public static final SignMaterial ACACIA_SIGN = new SignMaterial("sign", "acacia_sign", (byte) 0);
    public static final SignMaterial DARK_OAK_SIGN = new SignMaterial("sign", "dark_oak_sign", (byte) 0);
    public static final SignMaterial CRIMSON_SIGN = new SignMaterial("sign", "crimson_sign", (byte) 0);
    public static final SignMaterial WARPED_SIGN = new SignMaterial("sign", "warped_sign", (byte) 0);

    public static final VersionMaterial COMMAND_BLOCK = new VersionMaterial("command", "command_block", (byte) 0);
    public static final VersionMaterial REPEATING_COMMAND_BLOCK = new VersionMaterial("command", "repeating_command_block", (byte) 0);
    public static final VersionMaterial CHAIN_COMMAND_BLOCK = new VersionMaterial("command", "chain_command_block", (byte) 0);
    public static final VersionMaterial WHITE_TERRACOTTA = new VersionMaterial("stained_clay", "white_terracotta", (byte) 0);
    public static final VersionMaterial ORANGE_TERRACOTTA = new VersionMaterial("stained_clay", "orange_terracotta", (byte) 1);
    public static final VersionMaterial MAGENTA_TERRACOTTA = new VersionMaterial("stained_clay", "magenta_terracotta", (byte) 2);
    public static final VersionMaterial LIGHT_BLUE_TERRACOTTA = new VersionMaterial("stained_clay", "light_blue_terracotta", (byte) 3);
    public static final VersionMaterial YELLOW_TERRACOTTA = new VersionMaterial("stained_clay", "yellow_terracotta", (byte) 4);
    public static final VersionMaterial LIME_TERRACOTTA = new VersionMaterial("stained_clay", "lime_terracotta", (byte) 5);
    public static final VersionMaterial PINK_TERRACOTTA = new VersionMaterial("stained_clay", "pink_terracotta", (byte) 6);
    public static final VersionMaterial GRAY_TERRACOTTA = new VersionMaterial("stained_clay", "gray_terracotta", (byte) 7);
    public static final VersionMaterial LIGHT_GRAY_TERRACOTTA = new VersionMaterial("stained_clay", "light_gray_terracotta", (byte) 8);
    public static final VersionMaterial CYAN_TERRACOTTA = new VersionMaterial("stained_clay", "cyan_terracotta", (byte) 9);
    public static final VersionMaterial PURPLE_TERRACOTTA = new VersionMaterial("stained_clay", "purple_terracotta", (byte) 10);
    public static final VersionMaterial BLUE_TERRACOTTA = new VersionMaterial("stained_clay", "blue_terracotta", (byte) 11);
    public static final VersionMaterial BROWN_TERRACOTTA = new VersionMaterial("stained_clay", "brown_terracotta", (byte) 12);
    public static final VersionMaterial GREEN_TERRACOTTA = new VersionMaterial("stained_clay", "green_terracotta", (byte) 13);
    public static final VersionMaterial RED_TERRACOTTA = new VersionMaterial("stained_clay", "red_terracotta", (byte) 14);
    public static final VersionMaterial BLACK_TERRACOTTA = new VersionMaterial("stained_clay", "black_terracotta", (byte) 15);
    public static final VersionMaterial TERRACOTTA = new VersionMaterial("hard_clay", "terracotta", (byte) 0);

    public static final VersionMaterial WHITE_GLAZED_TERRACOTTA = new VersionMaterial("stained_clay", "white_glazed_terracotta", (byte) 0);
    public static final VersionMaterial ORANGE_GLAZED_TERRACOTTA = new VersionMaterial("stained_clay", "orange_glazed_terracotta", (byte) 1);
    public static final VersionMaterial MAGENTA_GLAZED_TERRACOTTA = new VersionMaterial("stained_clay", "magenta_glazed_terracotta", (byte) 2);
    public static final VersionMaterial LIGHT_BLUE_GLAZED_TERRACOTTA = new VersionMaterial("stained_clay", "light_blue_glazed_terracotta", (byte) 3);
    public static final VersionMaterial YELLOW_GLAZED_TERRACOTTA = new VersionMaterial("stained_clay", "yellow_glazed_terracotta", (byte) 4);
    public static final VersionMaterial LIME_GLAZED_TERRACOTTA = new VersionMaterial("stained_clay", "lime_glazed_terracotta", (byte) 5);
    public static final VersionMaterial PINK_GLAZED_TERRACOTTA = new VersionMaterial("stained_clay", "pink_glazed_terracotta", (byte) 6);
    public static final VersionMaterial GRAY_GLAZED_TERRACOTTA = new VersionMaterial("stained_clay", "gray_glazed_terracotta", (byte) 7);
    public static final VersionMaterial LIGHT_GRAY_GLAZED_TERRACOTTA = new VersionMaterial("stained_clay", "light_gray_glazed_terracotta", (byte) 8);
    public static final VersionMaterial CYAN_GLAZED_TERRACOTTA = new VersionMaterial("stained_clay", "cyan_glazed_terracotta", (byte) 9);
    public static final VersionMaterial PURPLE_GLAZED_TERRACOTTA = new VersionMaterial("stained_clay", "purple_glazed_terracotta", (byte) 10);
    public static final VersionMaterial BLUE_GLAZED_TERRACOTTA = new VersionMaterial("stained_clay", "blue_glazed_terracotta", (byte) 11);
    public static final VersionMaterial BROWN_GLAZED_TERRACOTTA = new VersionMaterial("stained_clay", "brown_glazed_terracotta", (byte) 12);
    public static final VersionMaterial GREEN_GLAZED_TERRACOTTA = new VersionMaterial("stained_clay", "green_glazed_terracotta", (byte) 13);
    public static final VersionMaterial RED_GLAZED_TERRACOTTA = new VersionMaterial("stained_clay", "red_glazed_terracotta", (byte) 14);
    public static final VersionMaterial BLACK_GLAZED_TERRACOTTA = new VersionMaterial("stained_clay", "black_glazed_terracotta", (byte) 15);


    public static final VersionMaterial CLOCK = new VersionMaterial("watch", "clock", (byte) 0);
    public static final VersionMaterial ENCHANTING_TABLE = new VersionMaterial("enchantment_table", "enchanting_table", (byte) 0);

    public static final MonsterEggVersionMaterial MONSTER_EGG = new MonsterEggVersionMaterial();

    public static final VersionMaterial WHITE_BANNER = new VersionMaterial("banner", "white_banner", (byte) 15);
    public static final VersionMaterial ORANGE_BANNER = new VersionMaterial("banner", "orange_banner", (byte) 14);
    public static final VersionMaterial MAGENTA_BANNER = new VersionMaterial("banner", "magenta_banner", (byte) 13);
    public static final VersionMaterial LIGHT_BLUE_BANNER = new VersionMaterial("banner", "light_blue_banner", (byte) 12);
    public static final VersionMaterial YELLOW_BANNER = new VersionMaterial("banner", "yellow_banner", (byte) 11);
    public static final VersionMaterial LIME_BANNER = new VersionMaterial("banner", "lime_banner", (byte) 10);
    public static final VersionMaterial PINK_BANNER = new VersionMaterial("banner", "pink_banner", (byte) 9);
    public static final VersionMaterial GRAY_BANNER = new VersionMaterial("banner", "gray_banner", (byte) 8);
    public static final VersionMaterial LIGHT_GRAY_BANNER = new VersionMaterial("banner", "light_gray_banner", (byte) 7);
    public static final VersionMaterial CYAN_BANNER = new VersionMaterial("banner", "cyan_banner", (byte) 6);
    public static final VersionMaterial PURPLE_BANNER = new VersionMaterial("banner", "purple_banner", (byte) 5);
    public static final VersionMaterial BLUE_BANNER = new VersionMaterial("banner", "blue_banner", (byte) 4);
    public static final VersionMaterial BROWN_BANNER = new VersionMaterial("banner", "brown_banner", (byte) 3);
    public static final VersionMaterial GREEN_BANNER = new VersionMaterial("banner", "green_banner", (byte) 2);
    public static final VersionMaterial RED_BANNER = new VersionMaterial("banner", "red_banner", (byte) 1);
    public static final VersionMaterial BLACK_BANNER = new VersionMaterial("banner", "black_banner", (byte) 0);

    public static final VersionMaterial REDSTONE_TORCH = new VersionMaterial("redstone_torch_on", "redstone_torch", (byte) 0);

    public static final VersionMaterial FIRE_CHARGE = new VersionMaterial("fireball", "fire_charge", (byte) 0);

    public static  final VersionMaterial BED_BLOCK = new VersionMaterial("bed_block", "white_bed", (byte) 0);

    public static final VersionMaterial EXPERIENCE_BOTTLE = new VersionMaterial("exp_bottle", "experience_bottle", (byte) 0);


    public static final VersionMaterial WHITE_CONCRETE = new VersionMaterial("concrete", "white_concrete", (byte) 0);
    public static final VersionMaterial ORANGE_CONCRETE = new VersionMaterial("concrete", "orange_concrete", (byte) 1);
    public static final VersionMaterial MAGENTA_CONCRETE = new VersionMaterial("concrete", "magenta_concrete", (byte) 2);
    public static final VersionMaterial LIGHT_BLUE_CONCRETE = new VersionMaterial("concrete", "light_blue_concrete", (byte) 3);
    public static final VersionMaterial YELLOW_CONCRETE = new VersionMaterial("concrete", "yellow_concrete", (byte) 4);
    public static final VersionMaterial LIME_CONCRETE = new VersionMaterial("concrete", "lime_concrete", (byte) 5);
    public static final VersionMaterial PINK_CONCRETE = new VersionMaterial("concrete", "pink_concrete", (byte) 6);
    public static final VersionMaterial GRAY_CONCRETE = new VersionMaterial("concrete", "gray_concrete", (byte) 7);
    public static final VersionMaterial LIGHT_GRAY_CONCRETE = new VersionMaterial("concrete", "light_gray_concrete", (byte) 8);
    public static final VersionMaterial CYAN_CONCRETE = new VersionMaterial("concrete", "cyan_concrete", (byte) 9);
    public static final VersionMaterial PURPLE_CONCRETE = new VersionMaterial("concrete", "purple_concrete", (byte) 10);
    public static final VersionMaterial BLUE_CONCRETE = new VersionMaterial("concrete", "blue_concrete", (byte) 11);
    public static final VersionMaterial BROWN_CONCRETE = new VersionMaterial("concrete", "brown_concrete", (byte) 12);
    public static final VersionMaterial GREEN_CONCRETE = new VersionMaterial("concrete", "green_concrete", (byte) 13);
    public static final VersionMaterial RED_CONCRETE = new VersionMaterial("concrete", "red_concrete", (byte) 14);
    public static final VersionMaterial BLACK_CONCRETE = new VersionMaterial("concrete", "black_concrete", (byte) 15);

    public static final VersionMaterial WHITE_CONCRETE_POWDER = new VersionMaterial("concrete_powder", "white_concrete_powder", (byte) 0);
    public static final VersionMaterial ORANGE_CONCRETE_POWDER = new VersionMaterial("concrete_powder", "orange_concrete_powder", (byte) 1);
    public static final VersionMaterial MAGENTA_CONCRETE_POWDER = new VersionMaterial("concrete_powder", "magenta_concrete_powder", (byte) 2);
    public static final VersionMaterial LIGHT_BLUE_CONCRETE_POWDER = new VersionMaterial("concrete_powder", "light_blue_concrete_powder", (byte) 3);
    public static final VersionMaterial YELLOW_CONCRETE_POWDER = new VersionMaterial("concrete_powder", "yellow_concrete_powder", (byte) 4);
    public static final VersionMaterial LIME_CONCRETE_POWDER = new VersionMaterial("concrete_powder", "lime_concrete_powder", (byte) 5);
    public static final VersionMaterial PINK_CONCRETE_POWDER = new VersionMaterial("concrete_powder", "pink_concrete_powder", (byte) 6);
    public static final VersionMaterial GRAY_CONCRETE_POWDER = new VersionMaterial("concrete_powder", "gray_concrete_powder", (byte) 7);
    public static final VersionMaterial LIGHT_GRAY_CONCRETE_POWDER = new VersionMaterial("concrete_powder", "light_gray_concrete_powder", (byte) 8);
    public static final VersionMaterial CYAN_CONCRETE_POWDER = new VersionMaterial("concrete_powder", "cyan_concrete_powder", (byte) 9);
    public static final VersionMaterial PURPLE_CONCRETE_POWDER = new VersionMaterial("concrete_powder", "purple_concrete_powder", (byte) 10);
    public static final VersionMaterial BLUE_CONCRETE_POWDER = new VersionMaterial("concrete_powder", "blue_concrete_powder", (byte) 11);
    public static final VersionMaterial BROWN_CONCRETE_POWDER = new VersionMaterial("concrete_powder", "brown_concrete_powder", (byte) 12);
    public static final VersionMaterial GREEN_CONCRETE_POWDER = new VersionMaterial("concrete_powder", "green_concrete_powder", (byte) 13);
    public static final VersionMaterial RED_CONCRETE_POWDER = new VersionMaterial("concrete_powder", "red_concrete_powder", (byte) 14);
    public static final VersionMaterial BLACK_CONCRETE_POWDER = new VersionMaterial("concrete_powder", "black_concrete_powder", (byte) 15);

    public static final VersionMaterial WHITE_CARPET = new VersionMaterial("carpet", "white_carpet", (byte) 0);
    public static final VersionMaterial ORANGE_CARPET = new VersionMaterial("carpet", "orange_carpet", (byte) 1);
    public static final VersionMaterial MAGENTA_CARPET = new VersionMaterial("carpet", "magenta_carpet", (byte) 2);
    public static final VersionMaterial LIGHT_BLUE_CARPET = new VersionMaterial("carpet", "light_blue_carpet", (byte) 3);
    public static final VersionMaterial YELLOW_CARPET = new VersionMaterial("carpet", "yellow_carpet", (byte) 4);
    public static final VersionMaterial LIME_CARPET = new VersionMaterial("carpet", "lime_carpet", (byte) 5);
    public static final VersionMaterial PINK_CARPET = new VersionMaterial("carpet", "pink_carpet", (byte) 6);
    public static final VersionMaterial GRAY_CARPET = new VersionMaterial("carpet", "gray_carpet", (byte) 7);
    public static final VersionMaterial LIGHT_GRAY_CARPET = new VersionMaterial("carpet", "light_gray_carpet", (byte) 8);
    public static final VersionMaterial CYAN_CARPET = new VersionMaterial("carpet", "cyan_carpet", (byte) 9);
    public static final VersionMaterial PURPLE_CARPET = new VersionMaterial("carpet", "purple_carpet", (byte) 10);
    public static final VersionMaterial BLUE_CARPET = new VersionMaterial("carpet", "blue_carpet", (byte) 11);
    public static final VersionMaterial BROWN_CARPET = new VersionMaterial("carpet", "brown_carpet", (byte) 12);
    public static final VersionMaterial GREEN_CARPET = new VersionMaterial("carpet", "green_carpet", (byte) 13);
    public static final VersionMaterial RED_CARPET = new VersionMaterial("carpet", "red_carpet", (byte) 14);
    public static final VersionMaterial BLACK_CARPET = new VersionMaterial("carpet", "black_carpet", (byte) 15);

    public static VersionMaterial getWool(DyeColor dyeColor) {
        CrossVersionDyeColor color = CrossVersionDyeColor.fromBukkit(dyeColor);
        switch (color) {
            case WHITE:
                return WHITE_WOOL;
            case ORANGE:
                return ORANGE_WOOL;
            case MAGENTA:
                return MAGENTA_WOOL;
            case LIGHT_BLUE:
                return LIGHT_BLUE_WOOL;
            case YELLOW:
                return YELLOW_WOOL;
            case LIME:
                return LIME_WOOL;
            case PINK:
                return PINK_WOOL;
            case GRAY:
                return GRAY_WOOL;
            case CYAN:
                return CYAN_WOOL;
            case PURPLE:
                return PURPLE_WOOL;
            case BLUE:
                return BLUE_WOOL;
            case BROWN:
                return BROWN_WOOL;
            case GREEN:
                return GREEN_WOOL;
            case RED:
                return RED_WOOL;
            case BLACK:
                return BLACK_WOOL;
            case LIGHT_GRAY:
                return LIGHT_GRAY_WOOL;
            default:
                throw new IllegalArgumentException("Unknown color " + color);
        }
    }

    public static VersionMaterial getBedBlock(DyeColor color) {
        if (ReflectionApi.version.isNewer("1.12")) {
            return getBed(color);
        }
        return BED_BLOCK;
    }

    public static VersionMaterial getBed(DyeColor dyeColor) {
        CrossVersionDyeColor color = CrossVersionDyeColor.fromBukkit(dyeColor);
        if (ReflectionApi.version.isNewer("1.12")) {
            switch (color) {
                case WHITE:
                    return WHITE_BED;
                case ORANGE:
                    return ORANGE_BED;
                case MAGENTA:
                    return MAGENTA_BED;
                case LIGHT_BLUE:
                    return LIGHT_BLUE_BED;
                case YELLOW:
                    return YELLOW_BED;
                case LIME:
                    return LIME_BED;
                case PINK:
                    return PINK_BED;
                case GRAY:
                    return GRAY_BED;
                case CYAN:
                    return CYAN_BED;
                case PURPLE:
                    return PURPLE_BED;
                case BLUE:
                    return BLUE_BED;
                case BROWN:
                    return BROWN_BED;
                case GREEN:
                    return GREEN_BED;
                case RED:
                    return RED_BED;
                case BLACK:
                    return BLACK_BED;
                case LIGHT_GRAY:
                    return LIGHT_GRAY_BED;
                default:
                    throw new IllegalArgumentException("Unknown color " + color);
            }
        } else {
            return WHITE_BED;
        }
    }

    public static Material getFromString(String material) {
        if (valueOf(material) != null) {
            return valueOf(material).getMaterial();
        }
        if (material.equalsIgnoreCase("AIR")) {
            return Material.AIR;
        }
        if (ReflectionApi.version.isNewer(ReflectionApi.v1_13)) {
            try {
                Material mat = Material.getMaterial(material.toUpperCase());
                if (mat == null || mat == Material.AIR) {
                    mat = (Material) Material.class.getMethod("getMaterial", String.class, boolean.class).invoke(null, material.toUpperCase(), true);
                }
                if (mat == null || mat == Material.AIR) {
                    mat = Material.valueOf(material.toUpperCase());
                }
                return mat;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            UnsafeValues unsafe = Bukkit.getUnsafe();
            try {
                Material mat = (Material) unsafe.getClass().getMethod("getMaterialFromInternalName", String.class).invoke(unsafe, material);
                if (mat == null || mat == Material.AIR) {
                    mat = Material.getMaterial(material.toUpperCase());
                }
                return mat;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static class SignMaterial extends VersionMaterial {
        public SignMaterial(String material, String material1_14, byte data) {
            super(material, material1_14, data);
        }

        @Override
        public Material getMaterial() {
            if (ReflectionApi.version.isOlder(ReflectionApi.v1_14)) {
                return Material.matchMaterial(this.getLegacyMaterial().toUpperCase());
            } else {
                return super.getMaterial();
            }
        }
    }


}