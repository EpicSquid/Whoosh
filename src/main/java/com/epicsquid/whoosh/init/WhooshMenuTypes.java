package com.epicsquid.whoosh.init;

import com.epicsquid.whoosh.Whoosh;
import com.epicsquid.whoosh.containers.TransporterMenu;
import com.epicsquid.whoosh.gui.TransportScreen;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.MenuEntry;

public class WhooshMenuTypes {

	private static final Registrate REGISTRATE = Whoosh.registrate();

	public static final MenuEntry<TransporterMenu> TRANSPORTER = REGISTRATE.menu("transporter", TransporterMenu::new, () -> TransportScreen::new).register();

	public static void init() {
	}
}
