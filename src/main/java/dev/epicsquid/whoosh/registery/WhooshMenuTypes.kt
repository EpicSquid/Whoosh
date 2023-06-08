package dev.epicsquid.whoosh.registery

import com.tterrag.registrate.Registrate
import dev.epicsquid.whoosh.Whoosh
import dev.epicsquid.whoosh.containers.TransporterMenu
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.inventory.MenuType

object WhooshMenuTypes {
	private val REGISTRATE: Registrate = Whoosh.registrate
	fun init() {}
	val TRANSPORTER = REGISTRATE.menu()<
					TransporterMenu, //TransportScreen>
					>(
		"transporter",
		{ type: MenuType<TransporterMenu?>, id: Int, inventory: Inventory?, buf: FriendlyByteBuf? ->
			TransporterMenu(
				type,
				id,
				inventory,
				player = null
			)
		})
		//		{
//		ScreenFactory { container: TransporterMenu?, inv: Inventory?, component: Component? ->
//			TransportScreen(
//				container,
//				inv,
//				component
//			)
//		}
//	}
		.register()
}
