package com.songoda.epicspawners.spawners.condition;

import com.songoda.epicspawners.EpicSpawners;
import com.songoda.epicspawners.spawners.spawner.Spawner;
import com.songoda.epicspawners.utils.settings.Setting;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class SpawnConditionNearbyEntities implements SpawnCondition {

    private final int max;

    public SpawnConditionNearbyEntities(int max) {
        this.max = max;
    }

    @Override
    public String getName() {
        return "nearby_entities";
    }

    @Override
    public String getDescription() {
        return EpicSpawners.getInstance().getLocale().getMessage("interface.spawner.conditionNearbyEntities", max);
    }

    @Override
    public boolean isMet(Spawner spawner) {

        Location location = spawner.getLocation().add(0.5, 0.5, 0.5);

        String[] arr = Setting.SEARCH_RADIUS.getString().split("x");


        int size = Math.toIntExact(location.getWorld().getNearbyEntities(location, Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]))
                .stream().filter(e -> e instanceof LivingEntity && e.getType() != EntityType.PLAYER && e.getType() != EntityType.ARMOR_STAND).count());

        return size < max;
    }

    public int getMax() {
        return max;
    }
}