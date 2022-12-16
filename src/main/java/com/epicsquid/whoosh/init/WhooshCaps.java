package com.epicsquid.whoosh.init;

import com.epicsquid.whoosh.Whoosh;
import com.epicsquid.whoosh.cap.ITransporterCap;
import com.epicsquid.whoosh.cap.TransporterCapProvider;
import com.epicsquid.whoosh.item.TransporterItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Whoosh.MODID)
public class WhooshCaps {

	public static final ResourceLocation TRANSPORTER_CAP_ID = new ResourceLocation(Whoosh.MODID, "transporter");
	public static final Capability<ITransporterCap> TRANSPORTER = CapabilityManager.get(new CapabilityToken<>() {
	});

	@SubscribeEvent
	public void registerCaps(RegisterCapabilitiesEvent event) {
		event.register(ITransporterCap.class);
	}

	@SubscribeEvent
	public static void attachCapability(AttachCapabilitiesEvent<Item> event) {
		if (event.getObject() instanceof TransporterItem) {
			event.addCapability(TRANSPORTER_CAP_ID, new TransporterCapProvider());
		}
	}
}
