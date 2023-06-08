package dev.epicsquid.whoosh.containers

import cofh.core.inventory.container.ContainerCoFH
import dev.epicsquid.whoosh.registery.WhooshLang
import dev.epicsquid.whoosh.registery.WhooshMenuTypes
import net.minecraft.network.chat.Component
import net.minecraft.world.MenuProvider
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.MenuType

//
//import cofh.core.inventory.container.ContainerCoFH
//import dev.epicsquid.whoosh.registery.WhooshLang
//import dev.epicsquid.whoosh.registery.WhooshMenuTypes
//import net.minecraft.network.chat.Component
//import net.minecraft.world.MenuProvider
//import net.minecraft.world.entity.player.Inventory
//import net.minecraft.world.entity.player.Player
//import net.minecraft.world.inventory.AbstractContainerMenu
//import net.minecraft.world.inventory.MenuType
//
class TransporterMenu(type: MenuType<TransporterMenu>, id: Int, inventory: Inventory?, player: Player?) :
	ContainerCoFH(type, id, inventory, player), MenuProvider {


	//
//
//
	override fun stillValid(pPlayer: Player): Boolean {
		return true
	}
//
	override fun getMergeableSlotCount(): Int {
		return 0
	}

	override fun createMenu(pContainerId: Int, pPlayerInventory: Inventory, pPlayer: Player): AbstractContainerMenu? {
		return TransporterMenu(WhooshMenuTypes.TRANSPORTER, pContainerId, pPlayerInventory, pPlayer)

	}

	override fun getDisplayName(): Component {
		return WhooshLang.TRANSPORTER


}
