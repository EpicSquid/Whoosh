package dev.epicsquid.whoosh.registery

import com.tterrag.registrate.Registrate
import dev.epicsquid.whoosh.Whoosh
import dev.epicsquid.whoosh.item.TransporterItem
import net.minecraft.world.item.Item


object WhooshItems {


	private val REGISTRATE: Registrate = Whoosh.registrate

	val TRANSPORTER = REGISTRATE.item<TransporterItem>(
		"transporter"
	) { props: Item.Properties? ->
		TransporterItem(
			props,
			10000,
			1000
		)
	}
		.tab {Whoosh.tab}
		.register()
		fun init() {}
}

