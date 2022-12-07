package com.epicsquid.whoosh.containers;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

public class TransporterContainer extends AbstractContainerMenu {


	protected TransporterContainer(@Nullable MenuType<?> menuType, int windowId) {
		super(menuType, windowId);
	}

	@Override
	public boolean stillValid(@Nonnull Player player) {
		return true;
	}
}
