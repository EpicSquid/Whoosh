package dev.epicsquid.whoosh

import com.epicsquid.whoosh.init.WhooshItems
import com.epicsquid.whoosh.init.WhooshLang
import com.epicsquid.whoosh.init.WhooshMenuTypes
import com.tterrag.registrate.Registrate
import com.tterrag.registrate.util.nullness.NonnullType
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraftforge.common.data.ForgeBlockTagsProvider
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext

@Mod(whoosh.MODID)
class whoosh {
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