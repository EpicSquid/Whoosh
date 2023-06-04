package com.epicsquid.whoosh.cap

import com.epicsquid.whoosh.utils.TransporterPoint
import net.minecraft.nbt.CompoundTag
import net.minecraftforge.common.util.INBTSerializable

interface ITransporterCap : INBTSerializable<CompoundTag?> {
    val points: Map<String?, TransporterPoint?>?
    fun addPoint(name: String?, point: TransporterPoint?)
    fun removePoint(name: String?)
    fun getPoint(name: String?): TransporterPoint?
}
