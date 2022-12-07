package com.epicsquid.whoosh.item;

import cofh.core.item.IMultiModeItem;
import cofh.lib.util.Utils;
import cofh.thermal.lib.item.EnergyContainerItemAugmentable;
import cofh.thermal.lib.item.IFlexibleEnergyContainerItem;
import com.epicsquid.whoosh.utils.TeleportUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

import static cofh.lib.util.constants.NBTTags.*;
import static cofh.thermal.lib.common.ThermalAugmentRules.createAllowValidator;

public class TransporterItem extends EnergyContainerItemAugmentable implements IFlexibleEnergyContainerItem, IMultiModeItem {

	private static final int BLINK_MODE = 0;
	private static final int TELEPORT_MODE = 1;

	public TransporterItem(Item.Properties builder, int maxEnergy, int maxTransfer) {
		super(builder, maxEnergy, maxTransfer);

		numSlots = () -> 4; // TODO: Add config
		augValidator = createAllowValidator(TAG_AUGMENT_TYPE_UPGRADE, TAG_AUGMENT_TYPE_RF, TAG_AUGMENT_TYPE_AREA_EFFECT);
	}

	@Override
	@Nonnull
	public InteractionResultHolder<ItemStack> use(@Nonnull Level level, @Nonnull Player player, @Nonnull InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (getMode(stack) == BLINK_MODE) {
			// Check for fake players
			if (!Utils.isFakePlayer(player) && hand == InteractionHand.MAIN_HAND) {
				// Check energy can be consumed
				if (extractEnergy(stack, 400, true) >= 400) {
					if (TeleportUtils.shortTeleport(level, player, hand)) {
						// Consume energy
						extractEnergy(stack, 400, false);
						return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
					}
				}
			}
		} else if (getMode(stack) == TELEPORT_MODE) {

		}
		return new InteractionResultHolder<>(InteractionResult.FAIL, stack);
	}
}
