package com.epicsquid.whoosh.gui;

import cofh.core.client.gui.ContainerScreenCoFH;
import cofh.core.client.gui.element.ElementListBox;
import cofh.core.client.gui.element.listbox.ListBoxElementText;
import cofh.core.util.helpers.RenderHelper;
import com.epicsquid.whoosh.Whoosh;
import com.epicsquid.whoosh.containers.TransporterMenu;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class TransportScreen extends ContainerScreenCoFH<TransporterMenu> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Whoosh.MODID,  "textures/gui/transporter.png");

	private EditBox editBox;
	private boolean ignoreTextInput;

	public TransportScreen(TransporterMenu container, Inventory inv, Component component) {
		super(container, inv, component);

		texture = TEXTURE;

		drawInventory = false;
	}

	@Override
	public void init() {
		super.init();
		imageWidth = 192;
		imageHeight = 109;

		if (this.minecraft == null) {
			return;
		}

		int i = (this.width - 192) / 2;
		int j = (this.height - 109) / 2;
		editBox = new EditBox(this.minecraft.font,  i+16, j - 6, 83, 12, new TranslatableComponent("itemGroup.search"));
		editBox.setMaxLength(20);
		editBox.setBordered(true);
		editBox.setVisible(true);
		editBox.setTextColor(16777215);
		editBox.setValue("");

		addWidget(editBox);
		addElement(getList());
	}

	private ElementListBox getList() {
		ElementListBox list = new ElementListBox(this, 8, 39, 83, 12);

		list.add(new ListBoxElementText("Test"));

		return list;
	}

	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
		super.render(poseStack, mouseX, mouseY, partialTicks);
		int i = (this.width - 192) / 2;
		int j = (this.height - 109) / 2;
		if (minecraft != null && !editBox.isFocused() && editBox.getValue().isEmpty()) {
			drawString(poseStack, minecraft.font, new TranslatableComponent("itemGroup.search"), i + 18, j - 4, -1);
		} else {
			editBox.render(poseStack, mouseX, mouseY, partialTicks);
		}
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		super.renderLabels(poseStack, mouseX, mouseY);
//
//		GlStateManager._enableBlend();
//		RenderHelper.setPosTexShader();
//		RenderHelper.setShaderTexture0(SLOT_OVERLAY);
//		drawTexturedModalRect(poseStack, menu.lockedSlot.x, menu.lockedSlot.y, 0, 0, 16, 16, 16, 16);
//		GlStateManager._disableBlend();
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
		if (editBox.mouseClicked(mouseX, mouseY, mouseButton)) {
			return true;
		}
		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		editBox.tick();
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		ignoreTextInput = false;
		if (editBox.keyPressed(keyCode, scanCode, modifiers)) {
			return true;
		} else if (editBox.isFocused() && editBox.isVisible() && keyCode != 256) {
			return true;
		} else if (minecraft != null && minecraft.options.keyChat.matches(keyCode, scanCode) && !editBox.isFocused()) {
			ignoreTextInput = true;
			editBox.setFocus(true);
			return true;
		}
		return super.keyPressed(keyCode, scanCode, modifiers);
	}

	@Override
	public boolean charTyped(char codePoint, int modifiers) {
		if (this.ignoreTextInput) {
			return false;
		} else if (minecraft != null && minecraft.player != null && minecraft.player.isSpectator()) {
			if (editBox.charTyped(codePoint, modifiers)) {
				return true;
			} else {
				return super.charTyped(codePoint, modifiers);
			}
		} else {
			return false;
		}
	}
}
