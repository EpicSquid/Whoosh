package com.epicsquid.whoosh.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;

public class TransporterPoint implements INBTSerializable<CompoundTag> {

	private int x;
	private int y;
	private int z;
	@NotNull
	private String dim;

	public TransporterPoint(int x, int y, int z, @NotNull String dim) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.dim = dim;
	}

	private TransporterPoint() {

	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public int z() {
		return z;
	}

	public String dim() {
		return dim;
	}

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag tag = new CompoundTag();
		tag.putInt("x", x);
		tag.putInt("y", y);
		tag.putInt("z", z);
		tag.putString("dim", dim);
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		x = nbt.getInt("x");
		y = nbt.getInt("y");
		z = nbt.getInt("z");
		dim = nbt.getString("dim");
	}

	public static TransporterPoint fromNBT(CompoundTag tag) {
		TransporterPoint point = new TransporterPoint();
		point.deserializeNBT(tag);
		return point;
	}
}
