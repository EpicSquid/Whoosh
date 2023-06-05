package dev.epicsquid.whoosh

import dev.epicsquid.whoosh.registery.WhooshItems
import dev.epicsquid.whoosh.registery.WhooshLang
import dev.epicsquid.whoosh.registery.WhooshMenuTypes
import com.tterrag.registrate.Registrate
import com.tterrag.registrate.util.nullness.NonnullType
import net.minecraft.data.DataGenerator
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraftforge.common.data.ForgeBlockTagsProvider
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext

@Mod(Whoosh.MODID)
class Whoosh {
    companion object {
        const val MODID = "whoosh"

        val registrate by lazy { Registrate.create(MODID).creativeModeTab { tab } }

        val tab: CreativeModeTab = object : CreativeModeTab(MODID) {
            @NonnullType
            override fun makeIcon(): ItemStack {
                return ItemStack(WhooshItems.TRANSPORTER.get())
            }
        }
    }

    init {
        FMLJavaModLoadingContext.get().modEventBus.addListener { event: GatherDataEvent -> gatherData(event) }

        WhooshItems.init()
        WhooshMenuTypes.init()
        WhooshLang.init()
    }

    private fun gatherData(event: GatherDataEvent) {
        val generator = event.generator
        val blockTagsProvider = ForgeBlockTagsProvider(generator, event.existingFileHelper)
        generator.addProvider(event.includeServer())
    }
}

private fun DataGenerator.addProvider(includeServer: Boolean) {
    TODO("Not yet implemented")
}
