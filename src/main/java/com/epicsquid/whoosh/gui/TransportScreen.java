package com.epicsquid.whoosh.gui;

import cofh.core.client.gui.ContainerScreenCoFH;
import cofh.core.client.gui.element.ElementListBox;
import cofh.core.client.gui.element.listbox.ListBoxElementText;
import cofh.core.util.helpers.RenderHelper;
import com.epicsquid.whoosh.Whoosh;
import com.epicsquid.whoosh.containers.TransporterMenu;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.font.TextFieldHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class TransportScreen extends ContainerScreenCoFH<TransporterMenu> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Whoosh.MODID,  "textures/gui/transporter.png");

	private EditBox editBox;

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

		String s = editBox != null ? editBox.getValue() : "";
		editBox = new EditBox(this.minecraft.font,  10, 24, 83, 12, new TranslatableComponent("itemGroup.search"));
		editBox.setMaxLength(50);
		editBox.setBordered(false);
		editBox.setVisible(true);
		editBox.setTextColor(16777215);
		editBox.setValue("Test");

		addWidget(editBox);
		addElement(getList());
	}

	private ElementListBox getList() {
		ElementListBox list = new ElementListBox(this, 8, 39, 83, 12);

		list.add(new ListBoxElementText("Test"));

		return list;
	}

	@Override
	protected void renderBg(PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
		RenderHelper.resetShaderColor();
		RenderHelper.setPosTexShader();
		RenderHelper.setShaderTexture0(texture);

		drawTexturedModalRect(poseStack, leftPos, topPos, 0, 0, imageWidth, imageHeight);
		poseStack.pushPose();
		poseStack.translate(leftPos, topPos, 0.0F);

		if (minecraft != null && !editBox.isFocused() && editBox.getValue().isEmpty()) {
			drawString(poseStack, minecraft.font, new TranslatableComponent("itemGroup.search"), 10, 24, -1);
		} else {
			editBox.render(poseStack, mouseX, mouseY, partialTicks);
		}

		drawPanels(poseStack, false);
		drawElements(poseStack, false);

		poseStack.popPose();
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
}
