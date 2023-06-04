package dev.epicsquid.whoosh.registery

import com.tterrag.registrate.Registrate
import dev.epicsquid.whoosh.Whoosh
import net.minecraft.network.chat.MutableComponent
import net.minecraft.resources.ResourceLocation

object WhooshLang {
    private val REGISTRATE: Registrate = Whoosh.registrate
    @JvmField
	val TRANSPORTER: MutableComponent =
        REGISTRATE.addLang("gui", ResourceLocation(Whoosh.MODID, "transporter"), "Transporter")
    @JvmField
	val BUTTON_WHOOSH: MutableComponent =
        REGISTRATE.addLang("gui", ResourceLocation(Whoosh.MODID, "button.whoosh"), "Whoosh")
    @JvmField
	val ENTER_NAME: MutableComponent =
        REGISTRATE.addLang("gui", ResourceLocation(Whoosh.MODID, "enter_name"), "Enter Name")
    @JvmField
	val MODE_BLINK: MutableComponent =
        REGISTRATE.addLang("info", ResourceLocation(Whoosh.MODID, "transporter.mode.blink"), "Blink")
    @JvmField
	val MODE_TELEPORT: MutableComponent =
        REGISTRATE.addLang("info", ResourceLocation(Whoosh.MODID, "transporter.mod.teleport"), "Teleport")

    fun init() {}
}
