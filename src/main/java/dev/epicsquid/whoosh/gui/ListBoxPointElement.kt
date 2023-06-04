package dev.epicsquid.whoosh.gui

import cofh.core.client.gui.element.listbox.ListBoxElementText
import dev.epicsquid.whoosh.utils.TransporterPoint

class ListBoxPointElement(private val point: TransporterPoint) : ListBoxElementText(point.dim()) {
    override fun getValue(): TransporterPoint {
        return point
    }
}
