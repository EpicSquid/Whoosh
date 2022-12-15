package com.epicsquid.whoosh.utils;

import org.jetbrains.annotations.NotNull;

public record TransporterPoint(int x, int y, int z, String dim, @NotNull String name) {
}
