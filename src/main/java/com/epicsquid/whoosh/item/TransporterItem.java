package com.epicsquid.whoosh.item;

import cofh.core.item.IMultiModeItem;
import cofh.core.util.helpers.ChatHelper;
import cofh.lib.util.Utils;
import cofh.thermal.lib.item.EnergyContainerItemAugmentable;
import cofh.thermal.lib.item.IFlexibleEnergyContainerItem;
import com.epicsquid.whoosh.containers.TransporterMenu;
import com.epicsquid.whoosh.init.WhooshItems;
import com.epicsquid.whoosh.init.WhooshLang;
import com.epicsquid.whoosh.init.WhooshMenuTypes;
import com.epicsquid.whoosh.utils.TeleportUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

import static cofh.lib.util.constants.NBTTags.*;
import static cofh.thermal.lib.common.ThermalAugmentRules.createAllowValidator;

public class TransporterItem extends EnergyContainerItemAugmentable implements IFlexibleEnergyContainerItem, IMultiModeItem, MenuProvider {

	private static final int BLINK_MODE = 0;
	private static final int TELEPORT_MODE = 1;

	public TransporterItem(Item.Properties builder, int maxEnergy, int maxTransfer) {
		super(builder, maxEnergy, maxTransfer);

		numSlots = () -> 4;
		augValidator = createAllowValidator(TAG_AUGMENT_TYPE_UPGRADE, TAG_AUGMENT_TYPE_RF, TAG_AUGMENT_TYPE_AREA_EFFECT);
	}

	@Override
	@Nonnull
	public InteractionResultHolder<ItemStack> use(@Nonnull Level level, @Nonnull Player player, @Nonnull InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (Utils.isFakePlayer(player) || hand == InteractionHand.OFF_HAND) {
			return InteractionResultHolder.fail(stack);
		}
		if (player instanceof ServerPlayer serverPlayer) {
			if (getMode(stack) == BLINK_MODE) {
				// Check energy can be consumed
				if (extractEnergy(stack, 400, true) >= 400) {
					if (TeleportUtils.shortTeleport(level, player, hand)) {
						// Consume energy
						extractEnergy(stack, 400, false);
						return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
					}
				}
			} else if (getMode(stack) == TELEPORT_MODE) {
				NetworkHooks.openGui(serverPlayer, this);
			}
		}
		return InteractionResultHolder.success(stack);
	}

	@Override
	@NotNull
	public Component getDisplayName() {
		return WhooshLang.TRANSPORTER;
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int id, @NotNull Inventory playerInv, @NotNull Player player) {
		return new TransporterMenu(WhooshMenuTypes.TRANSPORTER.get(), id, playerInv, player);
	}

	@Override
	public void onModeChange(Player player, ItemStack stack) {
		player.level.playSound(null, player.blockPosition(), SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 0.4F, 0.8F + 0.4F * getMode(stack));
		ChatHelper.sendIndexedChatMessageToPlayer(player, getMode(stack) == BLINK_MODE ? WhooshLang.MODE_BLINK : WhooshLang.MODE_TELEPORT);
	}
}
