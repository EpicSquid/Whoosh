package dev.epicsquid.whoosh.cap

import dev.epicsquid.whoosh.registery.WhooshCaps
import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilitySerializable
import net.minecraftforge.common.util.LazyOptional

object TransporterCapProvider : ICapabilitySerializable<CompoundTag?> {
    private val transporter: ITransporterCap
    private val op: LazyOptional<ITransporterCap>

    init {
        transporter = TransporterCap()
        op = LazyOptional.of { transporter }
    }

    override fun <T> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
        return WhooshCaps.TRANSPORTER.orEmpty(cap, op)
    }

    override fun serializeNBT(): CompoundTag {
        return transporter.serializeNBT()!!
    }


    override fun deserializeNBT(nbt: CompoundTag?) {
        transporter.deserializeNBT(nbt)
    }
}
