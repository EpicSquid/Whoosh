package com.epicsquid.whoosh.registery

import com.epicsquid.whoosh.containers.TransporterMenu
import com.epicsquid.whoosh.gui.TransportScreen
import com.tterrag.registrate.Registrate
import com.tterrag.registrate.builders.MenuBuilder.ScreenFactory
import dev.epicsquid.whoosh.Whoosh
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.inventory.MenuType

object WhooshMenuTypes {
    private val REGISTRATE: Registrate = Whoosh.registrate
    @JvmField
	val TRANSPORTER = REGISTRATE.menu<TransporterMenu, TransportScreen>(
        "transporter",
        { type: MenuType<TransporterMenu?>?, id: Int, inventory: Inventory?, buf: FriendlyByteBuf? ->
            TransporterMenu(
                type,
                id,
                inventory,
                buf
            )
        }) {
        ScreenFactory { container: TransporterMenu?, inv: Inventory?, component: Component? ->
            TransportScreen(
                container,
                inv,
                component
            )
        }
    }
        .register()

    fun init() {}
}
