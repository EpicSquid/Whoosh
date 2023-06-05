package dev.epicsquid.whoosh.item

import cofh.core.item.IMultiModeItem
import cofh.lib.util.Utils
import cofh.lib.util.constants.NBTTags
import cofh.thermal.lib.common.ThermalAugmentRules
import cofh.thermal.lib.item.EnergyContainerItemAugmentable
import cofh.thermal.lib.item.IFlexibleEnergyContainerItem
import dev.epicsquid.whoosh.containers.TransporterMenu
import dev.epicsquid.whoosh.registery.WhooshLang
import dev.epicsquid.whoosh.registery.WhooshMenuTypes
import dev.epicsquid.whoosh.utils.TeleportUtils
import com.tterrag.registrate.util.nullness.NonnullType
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.MenuProvider
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraftforge.network.NetworkHooks
import java.util.function.IntSupplier
import javax.annotation.Nonnull

class TransporterItem(builder: Properties?, maxEnergy: Int, maxTransfer: Int) :
    EnergyContainerItemAugmentable(builder, maxEnergy, maxTransfer), IFlexibleEnergyContainerItem, IMultiModeItem,
    MenuProvider {
    init {
        numSlots = IntSupplier { 4 }
        augValidator = ThermalAugmentRules.createAllowValidator(
            NBTTags.TAG_AUGMENT_TYPE_UPGRADE,
            NBTTags.TAG_AUGMENT_TYPE_RF,
            NBTTags.TAG_AUGMENT_TYPE_AREA_EFFECT
        )
    }

    @Nonnull
    override fun use(
        @Nonnull level: Level,
        @Nonnull player: Player,
        @Nonnull hand: InteractionHand
    ): InteractionResultHolder<ItemStack> {
        val stack = player.getItemInHand(hand)
        if (Utils.isFakePlayer(player) || hand == InteractionHand.OFF_HAND) {
            return InteractionResultHolder.fail(stack)
        }
        if (player is ServerPlayer) {
            if (getMode(stack) == BLINK_MODE) {
                // Check energy can be consumed
                if (extractEnergy(stack, 400, true) >= 400) {
                    if (TeleportUtils.shortTeleport(level, player, hand)) {
                        // Consume energy
                        extractEnergy(stack, 400, false)
                        return InteractionResultHolder(InteractionResult.SUCCESS, stack)
                    }
                }
            } else if (getMode(stack) == TELEPORT_MODE) {
                NetworkHooks.openScreen(player, this)
            }
        }
        return InteractionResultHolder.success(stack)
    }

    override fun getDisplayName(): Component {
        return WhooshLang.TRANSPORTER
    }

    override fun createMenu(
        id: Int,
        playerInv: @NonnullType Inventory?,
        player: @NonnullType Player?
    ): AbstractContainerMenu? {
        return TransporterMenu(WhooshMenuTypes.TRANSPORTER.get(), id, playerInv, player)
    }

    override fun onModeChange(player: Player, stack: ItemStack) {
        player.level.playSound(
            null,
            player.blockPosition(),
            SoundEvents.ITEM_PICKUP,
            SoundSource.PLAYERS,
            0.4f,
            0.8f + 0.4f * getMode(stack)
        )
//        ChatHelper.sendIndexedChatMessageToPlayer(
//            player,
//            if (getMode(stack) == BLINK_MODE) WhooshLang.MODE_BLINK else WhooshLang.MODE_TELEPORT
//        )
    }

    companion object {
        private const val BLINK_MODE = 0
        private const val TELEPORT_MODE = 1
    }
}
