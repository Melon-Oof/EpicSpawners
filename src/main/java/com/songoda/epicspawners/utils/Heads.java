package com.songoda.epicspawners.utils;

import com.songoda.arconix.plugin.Arconix;
import com.songoda.epicspawners.EpicSpawners;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by songoda on 3/19/2017.
 */
public class Heads {

    private EpicSpawners instance;

    private Map<String, String> textureURL = new HashMap<>();

    public Heads(EpicSpawners instance) {
        this.instance = instance;
        try {
            for (HeadType type : HeadType.values()) {
                textureURL.put(type.name(), type.getUrl());
            }
        } catch (Exception e) {
            Debugger.runReport(e);
        }
    }

    public ItemStack addTexture(ItemStack item, String name) {
        try {
            String headURL = textureURL.get(name.toUpperCase().replace(" ", "_"));
            if (headURL == null) {
                headURL = textureURL.get("UNKNOWN");
            }
            if (instance.v1_7) {
                item = new ItemStack(Material.MOB_SPAWNER, 1);
                return item;
            } else {
                return Arconix.pl().getApi().getGUI().addTexture(item, headURL);
            }
        } catch (Exception e) {
            Debugger.runReport(e);
        }
        return null;
    }
}