package com.epicsquid.whoosh.cap;

import com.epicsquid.whoosh.registery.WhooshCaps;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TransporterCapProvider implements ICapabilitySerializable<CompoundTag> {

	private final ITransporterCap transporter;
	private final LazyOptional<ITransporterCap> op;

	public TransporterCapProvider() {
		this.transporter = new TransporterCap();
		this.op = LazyOptional.of(() -> transporter);
	}

	@Override
	@NotNull
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		return WhooshCaps.TRANSPORTER.orEmpty(cap, op);
	}

	@Override
	public CompoundTag serializeNBT() {
		return transporter.serializeNBT();
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		transporter.deserializeNBT(nbt);
	}
}
