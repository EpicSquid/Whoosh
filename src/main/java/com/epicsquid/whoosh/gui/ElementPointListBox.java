package com.epicsquid.whoosh.gui;

import cofh.core.client.gui.element.ElementListBox;
import cofh.core.client.gui.element.listbox.IListBoxElement;
import com.epicsquid.whoosh.utils.TransporterPoint;

import java.util.function.Consumer;

public class ElementPointListBox extends ElementListBox {

	private final Consumer<TransporterPoint> onChange;

	public ElementPointListBox(TransportScreen screen, int x, int y, int width, int height, Consumer<TransporterPoint> onChange) {
		super(screen, x, y, width, height);
		this.onChange = onChange;
	}

	@Override
	protected void onSelectionChanged(int newIndex, IListBoxElement newElement) {
		super.onSelectionChanged(newIndex, newElement);
		if (newElement instanceof ListBoxPointElement listBoxPointElement) {
			onChange.accept(listBoxPointElement.getValue());
		}
	}

	@Override
	public void add(IListBoxElement element) {
		super.add(element);
		if (_elements.size() == 1) {
			setSelectedIndex(0);
		}
	}
}
