package io.github.frqnny.mostructures.util;

import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHelper {
    public static void spawnStack(World world, double x, double y, double z, ItemStack stack) {
        ItemEntity item = new ItemEntity(world, x, y, z, stack);
        item.setToDefaultPickupDelay();
        world.spawnEntity(item);
    }

}
