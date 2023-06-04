package com.epicsquid.whoosh.registery

import com.epicsquid.whoosh.item.TransporterItem
import com.tterrag.registrate.Registrate
import com.tterrag.registrate.util.entry.ItemEntry
import com.tterrag.registrate.util.nullness.NonNullFunction
import com.tterrag.registrate.util.nullness.NonNullSupplier
import dev.epicsquid.whoosh.Whoosh
import dev.epicsquid.whoosh.Whoosh.Companion.MODID
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item

object WhooshItems {
    private val REGISTRATE: Registrate = Whoosh.registrate
    val TRANSPORTER: ItemEntry<TransporterItem> = REGISTRATE.item<TransporterItem>("transporter",
        NonNullFunction<Item.Properties, TransporterItem> { props: Item.Properties? ->
            TransporterItem(
                props, 10000, 1000
            )
        })
        //add item to Creative mode tab

        .tab(NonNullSupplier<CreativeModeTab> { CreativeModeTab(MODID)})
        .register()

    fun init() {}
}
