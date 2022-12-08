package com.epicsquid.whoosh.gui;

import cofh.core.client.gui.ContainerScreenCoFH;
import cofh.core.client.gui.element.ElementBase;
import cofh.core.client.gui.element.ElementButton;
import cofh.core.client.gui.element.ElementListBox;
import cofh.core.client.gui.element.listbox.ListBoxElementText;
import com.epicsquid.whoosh.Whoosh;
import com.epicsquid.whoosh.containers.TransporterMenu;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringUtil;
import net.minecraft.world.entity.player.Inventory;

import static cofh.lib.util.helpers.SoundHelper.playClickSound;

public class TransportScreen extends ContainerScreenCoFH<TransporterMenu> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Whoosh.MODID, "textures/gui/transporter.png");

	private EditBox editBox;
	private ElementListBox pointsList;
	private boolean ignoreTextInput;

	public TransportScreen(TransporterMenu container, Inventory inv, Component component) {
		super(container, inv, component);

		texture = TEXTURE;
		imageWidth = 176;
		imageHeight = 142;

		drawInventory = false;
	}

	@Override
	public void init() {
		super.init();

		if (this.minecraft == null) {
			return;
		}

		int i = (this.width - 192) / 2;
		int j = (this.height - 109) / 2;
		editBox = new EditBox(this.minecraft.font, i + 16, j + 6, 83, 12, new TranslatableComponent("itemGroup.search"));
		editBox.setMaxLength(20);
		editBox.setBordered(true);
		editBox.setVisible(true);
		editBox.setTextColor(16777215);
		editBox.setValue("");

		addWidget(editBox);
		addButtons();
		addList();
	}

	private void addButtons() {
		ElementBase addPoint = new ElementButton(this, 95, 20)
				.setName("AddPoint")
				.setSize(16, 16)
				.setTexture(Whoosh.MODID + ":textures/gui/button_add.png", 48, 16)
				.setEnabled(() -> !StringUtil.isNullOrEmpty(editBox.getValue()));
		ElementBase removePoint = new ElementButton(this, 111, 20)
				.setName("RemovePoint")
				.setSize(16, 16)
				.setTexture(Whoosh.MODID + ":textures/gui/button_remove.png", 48, 16);

		addElement(addPoint);
		addElement(removePoint);
	}

	@Override
	public boolean handleElementButtonClick(String buttonName, int mouseButton) {
		float pitch = 0.7f;
		switch (buttonName) {
			case "AddPoint" -> {
				pointsList.add(new ListBoxElementText(editBox.getValue()));
				pitch += 0.1F;
			}
			case "RemovePoint" -> {
				pointsList.removeAll();
				pitch -= 0.1F;
			}
		}
		playClickSound(pitch);
		return true;
	}

	private void addList() {
		pointsList = new ElementListBox(this, 8, 43, 83, 91);
		addElement(pointsList);
	}

	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
		super.render(poseStack, mouseX, mouseY, partialTicks);
		int i = (this.width - 192) / 2;
		int j = (this.height - 109) / 2;
		if (minecraft != null && !editBox.isFocused() && editBox.getValue().isEmpty()) {
			drawString(poseStack, minecraft.font, new TranslatableComponent("itemGroup.search"), i + 18, j + 8, -1);
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
	public boolean keyReleased(int pKeyCode, int pScanCode, int pModifiers) {
		return false;
	}

	@Override
	public boolean charTyped(char codePoint, int modifiers) {
		if (this.ignoreTextInput) {
			return false;
		} else if (minecraft != null && minecraft.player != null && !minecraft.player.isSpectator()) {
			return editBox.charTyped(codePoint, modifiers);
		} else {
			return false;
		}
	}
}
