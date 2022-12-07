package com.epicsquid.whoosh.containers;

import cofh.core.inventory.container.ContainerCoFH;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TransporterMenu extends ContainerCoFH {

	public TransporterMenu(@Nullable MenuType<?> type, int id, Inventory inventory, FriendlyByteBuf buf) {
		super(type, id, inventory, inventory.player);
	}

	public TransporterMenu(@Nullable MenuType<?> type, int id, Inventory inventory, Player player) {
		super(type, id, inventory, player);
	}

	@Override
	protected int getMergeableSlotCount() {
		return 0;
	}

	@Override
	public boolean stillValid(@NotNull Player player) {
		return true;
	}
}
