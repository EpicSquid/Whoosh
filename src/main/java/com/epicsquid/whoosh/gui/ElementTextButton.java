package com.epicsquid.whoosh.gui;

import cofh.core.client.gui.IGuiAccess;
import cofh.core.client.gui.element.ElementButton;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class ElementTextButton extends ElementButton {

	private Component text;

	public ElementTextButton(IGuiAccess gui, int posX, int posY) {
		super(gui, posX, posY);
	}

	public Component getText() {
		return text;
	}

	public ElementTextButton setText(Component text) {
		this.text = text;
		return this;
	}

	@Override
	public void drawBackground(PoseStack matrixStack, int mouseX, int mouseY) {
		super.drawBackground(matrixStack, mouseX, mouseY);

		// Draw the text component
		Minecraft.getInstance().font.drawShadow(matrixStack, text, this.posX() + 18, this.posY() + 4, -1);
	}
}
