package dev.epicsquid.whoosh.containers

import cofh.core.inventory.container.ContainerCoFH
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.world.MenuProvider
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.MenuType

class TransporterMenu : ContainerCoFH, MenuProvider {
	constructor(type: MenuType<*>?, id: Int, inventory: Inventory, buf: FriendlyByteBuf?) : super(
		type,
		id,
		inventory,
		inventory.player
	)

	constructor(type: MenuType<*>?, id: Int, inventory: Inventory?, player: Player?) : super(
		type,
		id,
		inventory,
		player
	)

	override fun getMergeableSlotCount(): Int {
		return 0
	}

	override fun stillValid(player: Player): Boolean {
		return true
	}

	override fun createMenu(pContainerId: Int, pPlayerInventory: Inventory, pPlayer: Player): AbstractContainerMenu? {
		createMenu(pContainerId, pPlayerInventory, pPlayer)
		return null
	}

	override fun getDisplayName(): Component {
		return Component.nullToEmpty("Transporter")
	}
}
