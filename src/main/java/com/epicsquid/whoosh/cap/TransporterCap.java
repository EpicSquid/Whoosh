package com.epicsquid.whoosh.cap;

import com.epicsquid.whoosh.utils.TransporterPoint;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

import java.util.HashMap;
import java.util.Map;

public class TransporterCap implements ITransporterCap{

	private final Map<String, TransporterPoint> points = new HashMap<>();

	@Override
	public Map<String, TransporterPoint> getPoints() {
		return Map.copyOf(points);
	}

	@Override
	public void addPoint(String name, TransporterPoint point) {
		points.put(name, point);
	}

	@Override
	public void removePoint(String name) {
		points.remove(name);
	}

	@Override
	public TransporterPoint getPoint(String name) {
		return points.get(name);
	}

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag tag = new CompoundTag();
		for (String key : points.keySet()) {
			tag.put(key, points.get(key).serializeNBT());
		}
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		for (String key : nbt.getAllKeys()) {
			Tag tag = nbt.get(key);
			if (tag instanceof CompoundTag ct) {
				points.put(key, TransporterPoint.fromNBT(ct));
			}
		}
	}
}
