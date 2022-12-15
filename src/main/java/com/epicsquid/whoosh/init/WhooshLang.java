package com.epicsquid.whoosh.init;

import com.epicsquid.whoosh.Whoosh;
import com.tterrag.registrate.Registrate;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

public class WhooshLang {


	private static final Registrate REGISTRATE = Whoosh.registrate();

	public static final TranslatableComponent TRANSPORTER = REGISTRATE.addLang("gui", new ResourceLocation(Whoosh.MODID, "transporter"), "Transporter");
	public static final TranslatableComponent BUTTON_WHOOSH = REGISTRATE.addLang("gui", new ResourceLocation(Whoosh.MODID, "button.whoosh"), "Whoosh");
	public static final TranslatableComponent ENTER_NAME = REGISTRATE.addLang("gui", new ResourceLocation(Whoosh.MODID, "enter_name"), "Enter Name");
	public static final TranslatableComponent MODE_BLINK = REGISTRATE.addLang("info", new ResourceLocation(Whoosh.MODID, "transporter.mode.blink"), "Blink");
	public static final TranslatableComponent MODE_TELEPORT = REGISTRATE.addLang("info", new ResourceLocation(Whoosh.MODID, "transporter.mod.teleport"), "Teleport");
	public static void init() {
	}
}
