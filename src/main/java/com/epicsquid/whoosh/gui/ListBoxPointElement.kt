package com.epicsquid.whoosh.gui

import cofh.core.client.gui.element.listbox.ListBoxElementText
import com.epicsquid.whoosh.utils.TransporterPoint

class ListBoxPointElement(private val point: TransporterPoint) : ListBoxElementText(point.dim()) {
    override fun getValue(): TransporterPoint {
        return point
    }
}
