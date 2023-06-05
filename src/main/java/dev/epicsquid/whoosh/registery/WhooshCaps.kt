package dev.epicsquid.whoosh.registery

import dev.epicsquid.whoosh.cap.ITransporterCap
import dev.epicsquid.whoosh.cap.TransporterCapProvider
import dev.epicsquid.whoosh.item.TransporterItem
import dev.epicsquid.whoosh.Whoosh
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.common.capabilities.CapabilityToken
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = Whoosh.MODID)
class WhooshCaps {
    @SubscribeEvent
    fun registerCaps(event: RegisterCapabilitiesEvent) {
        event.register<ITransporterCap>(ITransporterCap::class.java)
    }

    companion object {
        val TRANSPORTER_CAP_ID = ResourceLocation(Whoosh.MODID, "transporter")
        @JvmField
		val TRANSPORTER: Capability<ITransporterCap> =
            CapabilityManager.get<ITransporterCap>(object : CapabilityToken<ITransporterCap?>() {})

        @SubscribeEvent
        fun attachCapability(event: AttachCapabilitiesEvent<Item?>) {
            if (event.getObject() is TransporterItem) {
                event.addCapability(TRANSPORTER_CAP_ID, TransporterCapProvider)
            }
        }
    }
}
