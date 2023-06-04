package dev.epicsquid.whoosh.gui

import cofh.core.client.gui.IGuiAccess
import cofh.core.client.gui.element.ElementButton
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.Minecraft
import net.minecraft.network.chat.Component

class ElementTextButton(gui: IGuiAccess?, posX: Int, posY: Int) : ElementButton(gui, posX, posY) {
    var text: Component? = null
        private set

    fun setText(text: Component?): ElementTextButton {
        this.text = text
        return this
    }

    override fun drawBackground(matrixStack: PoseStack, mouseX: Int, mouseY: Int) {
        super.drawBackground(matrixStack, mouseX, mouseY)

        // Draw the text component
        Minecraft.getInstance().font.drawShadow(matrixStack, text, (posX() + 18).toFloat(), (posY() + 4).toFloat(), -1)
    }
}
