package dev.epicsquid.whoosh.registery

import dev.epicsquid.whoosh.Whoosh
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = Whoosh.MODID)
object WhooshCaps {
    @SubscribeEvent
    fun registerCaps(event: RegisterCapabilitiesEvent) {
//        event.register<ITransporterCap>(ITransporterCap::class.java)
    }



		fun init() {}

}
