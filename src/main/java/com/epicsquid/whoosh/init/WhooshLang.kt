package com.epicsquid.whoosh.init

import com.tterrag.registrate.Registrate
import dev.epicsquid.whoosh.Whoosh
import net.minecraft.resources.ResourceLocation

object WhooshLang {
    private val REGISTRATE: Registrate = Whoosh.registrate()
    @JvmField
	val TRANSPORTER: TranslatableComponent =
        REGISTRATE.addLang("gui", ResourceLocation(Whoosh.MODID, "transporter"), "Transporter")
    @JvmField
	val BUTTON_WHOOSH: TranslatableComponent =
        REGISTRATE.addLang("gui", ResourceLocation(Whoosh.MODID, "button.whoosh"), "Whoosh")
    @JvmField
	val ENTER_NAME: TranslatableComponent =
        REGISTRATE.addLang("gui", ResourceLocation(Whoosh.MODID, "enter_name"), "Enter Name")
    @JvmField
	val MODE_BLINK: TranslatableComponent =
        REGISTRATE.addLang("info", ResourceLocation(Whoosh.MODID, "transporter.mode.blink"), "Blink")
    @JvmField
	val MODE_TELEPORT: TranslatableComponent =
        REGISTRATE.addLang("info", ResourceLocation(Whoosh.MODID, "transporter.mod.teleport"), "Teleport")

    fun init() {}
}
