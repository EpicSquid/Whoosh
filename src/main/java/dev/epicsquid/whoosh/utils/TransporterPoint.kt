//package dev.epicsquid.whoosh.utils
//
//import net.minecraft.nbt.CompoundTag
//import net.minecraftforge.common.util.INBTSerializable
//
//class TransporterPoint : INBTSerializable<CompoundTag> {
//    private var x = 0
//    private var y = 0
//    private var z = 0
//    private var dim: String? = null
//
//    constructor(x: Int, y: Int, z: Int, dim: String) {
//        this.x = x
//        this.y = y
//        this.z = z
//        this.dim = dim
//    }
//
//    private constructor()
//    constructor(x: Int, y: Int, z: Int, path: String?, value: String?)
//
//    fun x(): Int {
//        return x
//    }
//
//    fun y(): Int {
//        return y
//    }
//
//    fun z(): Int {
//        return z
//    }
//
//    fun dim(): String? {
//        return dim
//    }
//
//    override fun serializeNBT(): CompoundTag {
//        val tag = CompoundTag()
//        tag.putInt("x", x)
//        tag.putInt("y", y)
//        tag.putInt("z", z)
//        tag.putString("dim", dim)
//        return tag
//    }
//
//    override fun deserializeNBT(nbt: CompoundTag) {
//        x = nbt.getInt("x")
//        y = nbt.getInt("y")
//        z = nbt.getInt("z")
//        dim = nbt.getString("dim")
//    }
//
//    companion object {
//        fun fromNBT(tag: CompoundTag): TransporterPoint {
//            val point = TransporterPoint()
//            point.deserializeNBT(tag)
//            return point
//        }
//    }
//}
