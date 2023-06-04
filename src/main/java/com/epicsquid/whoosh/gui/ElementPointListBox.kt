package com.epicsquid.whoosh.gui

import cofh.core.client.gui.element.ElementListBox
import cofh.core.client.gui.element.listbox.IListBoxElement
import com.epicsquid.whoosh.utils.TransporterPoint
import java.util.function.Consumer

class ElementPointListBox(
    screen: TransportScreen?,
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    private val onChange: Consumer<TransporterPoint>
) : ElementListBox(screen, x, y, width, height) {
    override fun onSelectionChanged(newIndex: Int, newElement: IListBoxElement) {
        super.onSelectionChanged(newIndex, newElement)
        if (newElement is ListBoxPointElement) {
            onChange.accept(newElement.value)
        }
    }

    override fun add(element: IListBoxElement) {
        super.add(element)
        if (_elements.size == 1) {
            selectedIndex = 0
        }
    }
}
