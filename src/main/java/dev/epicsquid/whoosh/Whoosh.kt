package dev.epicsquid.whoosh

import com.mojang.logging.LogUtils
import com.tterrag.registrate.Registrate
import com.tterrag.registrate.util.nullness.NonnullType
import dev.epicsquid.whoosh.registery.WhooshCaps
import dev.epicsquid.whoosh.registery.WhooshItems
import dev.epicsquid.whoosh.registery.WhooshLang
import dev.epicsquid.whoosh.registery.WhooshMenuTypes
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.resolver.ServerAddressResolver.LOGGER
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.Blocks
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.server.ServerStartingEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

@Mod(Whoosh.MODID)
class Whoosh {


	init {
		val modEventBus = FMLJavaModLoadingContext.get().modEventBus

		// Register the commonSetup method for modloading
		modEventBus.addListener { event: FMLCommonSetupEvent ->
			commonSetup(
				event
			)
		}

		// Register the Deferred Register to the mod event bus so items get registered
		WhooshItems.init()//Regsiters Items
		WhooshCaps.init()//Registers Caps
		WhooshLang.init()//Registers Lang
		WhooshMenuTypes.init()//Registers MenuTypes

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this)
	}

	private fun commonSetup(event: FMLCommonSetupEvent) {
		// Some common setup code
		LOGGER.info("HELLO FROM COMMON SETUP")
		LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT))
	}

	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	fun onServerStarting(event: ServerStartingEvent?) {
		// Do something when the server starts
		LOGGER.info("HELLO from server starting")
	}

	// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
	@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = [Dist.CLIENT])
	object ClientModEvents {
		@SubscribeEvent
		fun onClientSetup(event: FMLClientSetupEvent?) {
			// Some client setup code
			LOGGER.info("HELLO FROM CLIENT SETUP")
			LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().user.name)
		}
	}

	companion object {
		const val MODID = "whoosh"

		// Directly reference a log4j logger.
		//Makes creative tab to see mods excludded from thermaal
		val registrate by lazy { Registrate.create(MODID).creativeModeTab { tab } }

		val tab: CreativeModeTab = object : CreativeModeTab(MODID) {
			@NonnullType
			override fun makeIcon(): ItemStack {
				return ItemStack(WhooshItems.TRANSPORTER.get())
			}
		}
	}

}



