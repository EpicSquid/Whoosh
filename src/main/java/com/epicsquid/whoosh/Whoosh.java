package com.epicsquid.whoosh;

import com.epicsquid.whoosh.init.WhooshItems;
import com.epicsquid.whoosh.init.WhooshLang;
import com.epicsquid.whoosh.init.WhooshMenuTypes;
import com.tterrag.registrate.Registrate;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.annotation.Nonnull;

@Mod(Whoosh.MODID)
public class Whoosh {
	public static final String MODID = "whoosh";

	private static final Lazy<Registrate> REGISTRATE = Lazy.of(() -> Registrate.create(MODID));

	public static final CreativeModeTab CREATIVE_TAB = new CreativeModeTab(Whoosh.MODID) {
		@Override
		@Nonnull
		public ItemStack makeIcon() {
			return new ItemStack(WhooshItems.TRANSPORTER.get());
		}
	};

	public Whoosh() {
		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

		// Register all the things
		WhooshLang.init();
		WhooshMenuTypes.init();
		WhooshItems.init();

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {

	}

	public static Registrate registrate() {
		return REGISTRATE.get();
	}
}
