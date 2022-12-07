package com.epicsquid.whoosh.gui;

import cofh.core.client.gui.ContainerScreenCoFH;
import cofh.core.client.gui.element.ElementListBox;
import cofh.core.client.gui.element.listbox.ListBoxElementText;
import cofh.core.util.helpers.RenderHelper;
import com.epicsquid.whoosh.Whoosh;
import com.epicsquid.whoosh.containers.TransporterMenu;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class TransportScreen extends ContainerScreenCoFH<TransporterMenu> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Whoosh.MODID,  "textures/gui/transporter.png");

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
}
