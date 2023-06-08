package dev.epicsquid.whoosh.item

import cofh.core.item.IMultiModeItem
import cofh.lib.util.constants.NBTTags
import cofh.thermal.lib.common.ThermalAugmentRules
import cofh.thermal.lib.item.EnergyContainerItemAugmentable
import cofh.thermal.lib.item.IFlexibleEnergyContainerItem
import dev.epicsquid.whoosh.registery.WhooshLang
import net.minecraft.network.chat.Component
import net.minecraft.world.MenuProvider
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.energy.IEnergyStorage
import java.util.function.IntSupplier

class TransporterItem(builder: Properties?, maxEnergy: Int, maxTransfer: Int) :
	EnergyContainerItemAugmentable(builder, maxEnergy, maxTransfer), IFlexibleEnergyContainerItem, IMultiModeItem,
		MenuProvider {
	init {
		numSlots = IntSupplier { 4 }
		augValidator = ThermalAugmentRules.createAllowValidator(
			NBTTags.TAG_AUGMENT_TYPE_UPGRADE, NBTTags.TAG_AUGMENT_TYPE_RF, NBTTags.TAG_AUGMENT_TYPE_AREA_EFFECT
		)
	}

		override fun getEnergyCapability(): Capability<out IEnergyStorage> {
				TODO("Not yet implemented")
		}

		override fun createMenu(pContainerId: Int, pPlayerInventory: Inventory, pPlayer: Player): AbstractContainerMenu? {
				TODO("Not yet implemented")
		}

		override fun getDisplayName(): Component {
	return WhooshLang.TRANSPORTER
		}

}


// (builder: Properties?, maxEnergy: Int, maxTransfer: Int) :
//	EnergyContainerItemAugmentable(builder, maxEnergy, maxTransfer),
//	IFlexibleEnergyContainerItem,
//	IMultiModeItem,
//	MenuProvider {
//	init {
//		numSlots = IntSupplier { 4 }
//		augValidator = ThermalAugmentRules.createAllowValidator(
//			NBTTags.TAG_AUGMENT_TYPE_UPGRADE, NBTTags.TAG_AUGMENT_TYPE_RF, NBTTags.TAG_AUGMENT_TYPE_AREA_EFFECT
//		)
//	}
//
//	//what happens when player uses item
//	override fun use(
//		@Nonnull level: Level, @Nonnull player: Player, @Nonnull hand: InteractionHand
//	): InteractionResultHolder<ItemStack> {
//		val stack = player.getItemInHand(hand)
//		if (Utils.isFakePlayer(player) || hand == InteractionHand.OFF_HAND) {
//			return InteractionResultHolder.fail(stack)
//		}
//		if (player is ServerPlayer) {
//			if (getMode(stack) == BLINK_MODE) {
//				// Check energy can be consumed
//				if (extractEnergy(stack, 400, true) >= 400) {
//					if (TeleportUtils.shortTeleport(level, player, hand)) {
//						// Consume energy
//						extractEnergy(stack, 400, false)
//						return InteractionResultHolder(InteractionResult.SUCCESS, stack)
//					}
//				}
//			} else if (getMode(stack) == TELEPORT_MODE)
//				openScreen(player, TransporterMenu(WhooshMenuTypes.TRANSPORTER, player.inventory, player))
////			pContainerId, pPlayerInventory, pPlayer
//		}
//		return InteractionResultHolder.success(stack)
//	}
//
//	override fun getDisplayName(): Component {
//		return WhooshLang.TRANSPORTER
//	}
//
//	override fun createMenu(
//		id: Int, playerInv: @NonnullType Inventory?, player: @NonnullType Player?
//	): AbstractContainerMenu {
//		return TransporterMenu(WhooshMenuTypes.TRANSPORTER.get(), id, playerInv, player)
//	}
//
//	override fun onModeChange(player: Player, stack: ItemStack) {
//		player.level.playSound(
//			null,
//			player.blockPosition(),
//			SoundEvents.ITEM_PICKUP,
//			SoundSource.PLAYERS,
//			0.4f,
//			0.8f + 0.4f * getMode(stack)
//		)
//		if (getMode(stack) == BLINK_MODE) WhooshLang.MODE_BLINK else WhooshLang.MODE_TELEPORT
//	}
//
//	companion object {
//		private const val BLINK_MODE = 0
//		private const val TELEPORT_MODE = 1
//	}
//}
