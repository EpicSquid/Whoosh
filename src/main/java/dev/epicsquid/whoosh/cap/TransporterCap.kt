package dev.epicsquid.whoosh.cap

import dev.epicsquid.whoosh.utils.TransporterPoint
import net.minecraft.nbt.CompoundTag

object TransporterCap : ITransporterCap {
    override val points: MutableMap<String?, TransporterPoint?> = HashMap()
    fun getPoints(): Map<String?, TransporterPoint?> {
        return java.util.Map.copyOf(points)
    }

    override fun addPoint(name: String?, point: TransporterPoint?) {
        points[name] = point
    }

    override fun removePoint(name: String?) {
        points.remove(name)
    }

    override fun getPoint(name: String?): TransporterPoint? {
        return points[name]
    }

    override fun serializeNBT(): CompoundTag? {
        val tag = CompoundTag()
        for (key in points.keys) {
            tag.put(key, points[key]!!.serializeNBT())
        }
        return tag
    }

    override fun deserializeNBT(nbt: CompoundTag?) {
//        for (key in nbt.allKeys) {
//            val tag = nbt[key]
//            if (tag is CompoundTag) {
//                points[key] = TransporterPoint.fromNBT(tag)
//            }
//        }
    }
}
