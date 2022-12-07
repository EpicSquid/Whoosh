package com.epicsquid.whoosh.init;

import com.epicsquid.whoosh.Whoosh;
import com.epicsquid.whoosh.item.TransporterItem;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.ItemEntry;

public class WhooshItems {

	private static final Registrate REGISTRATE = Whoosh.registrate();

	public static final ItemEntry<TransporterItem> TRANSPORTER = REGISTRATE.item("transporter", props -> new TransporterItem(props, 10000, 1000))
			.tab(() -> Whoosh.CREATIVE_TAB)
			.register();

	public static void classload() {
	}
}
