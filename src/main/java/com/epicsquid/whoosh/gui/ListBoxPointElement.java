package com.epicsquid.whoosh.gui;

import cofh.core.client.gui.element.listbox.ListBoxElementText;
import com.epicsquid.whoosh.utils.TransporterPoint;

public class ListBoxPointElement extends ListBoxElementText {

	private final TransporterPoint point;

	public ListBoxPointElement(TransporterPoint point) {
		super(point.dim());
		this.point = point;
	}


	@Override
	public TransporterPoint getValue() {
		return point;
	}
}
