package dev.epicsquid.whoosh

import com.mojang.logging.LogUtils
import dev.epicsquid.whoosh.registery.WhooshCaps
import dev.epicsquid.whoosh.registery.WhooshItems
import dev.epicsquid.whoosh.registery.WhooshLang
import dev.epicsquid.whoosh.registery.WhooshMenuTypes
import net.minecraft.client.Minecraft
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
		ITEMS.register(modEventBus)
			WhooshItems.init()
			WhooshCaps.init()
			WhooshLang.init()
			WhooshMenuTypes.init()

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
		// Define mod id in a common place for everything to reference
		const val MODID = "whoosh"

		// Directly reference a slf4j logger
		private val LOGGER = LogUtils.getLogger()


		// Create a Deferred Register to hold Items which will all be registered under the "whoosh" namespace
		val ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID)

	}
}


