package com.epicsquid.whoosh.cap;

import com.epicsquid.whoosh.utils.TransporterPoint;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.List;
import java.util.Map;

public interface ITransporterCap extends INBTSerializable<CompoundTag> {

	Map<String, TransporterPoint> getPoints();

	void addPoint(String name, TransporterPoint point);

	void removePoint(String name);

	TransporterPoint getPoint(String name);

}
