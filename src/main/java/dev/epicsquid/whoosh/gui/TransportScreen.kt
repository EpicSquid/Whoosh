package dev.epicsquid.whoosh.gui

import cofh.core.client.gui.ContainerScreenCoFH
import cofh.core.client.gui.element.ElementButton
import cofh.core.client.gui.element.ElementListBox
import cofh.lib.util.helpers.SoundHelper
<<<<<<< Updated upstream:src/main/java/dev/epicsquid/whoosh/gui/TransportScreen.kt
import dev.epicsquid.whoosh.containers.TransporterMenu
import dev.epicsquid.whoosh.registery.WhooshLang
import dev.epicsquid.whoosh.utils.TransporterPoint
=======
import com.epicsquid.whoosh.containers.TransporterMenu
import com.epicsquid.whoosh.registery.WhooshLang
import com.epicsquid.whoosh.utils.TransporterPoint
>>>>>>> Stashed changes:src/main/java/com/epicsquid/whoosh/gui/TransportScreen.kt
import com.mojang.blaze3d.vertex.PoseStack
import dev.epicsquid.whoosh.Whoosh
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.components.EditBox
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.StringUtil
import net.minecraft.world.entity.player.Inventory
import java.awt.TextComponent

class TransportScreen(container: TransporterMenu?, inv: Inventory?, component: Component?) :
    ContainerScreenCoFH<TransporterMenu?>(container, inv, component) {
    private var editBox: EditBox? = null
    private var pointsList: ElementListBox? = null
    private var ignoreTextInput = false
    private var currentPoint: TransporterPoint? = null

    init {
        texture = TEXTURE
        imageWidth = 176
        imageHeight = 142
        drawInventory = false
    }

    override fun init() {
        super.init()
        if (minecraft == null) {
            return
        }
        val i = (width - 192) / 2
        val j = (height - 109) / 2
        editBox = EditBox(minecraft!!.font, i + 16, j + 6, 83, 12, WhooshLang.ENTER_NAME)
        editBox!!.setMaxLength(20)
        editBox!!.setBordered(true)
        editBox!!.isVisible = true
        editBox!!.setTextColor(16777215)
        editBox!!.value = ""
        addWidget(editBox)
        addButtons()
        addList()
    }

    private fun addButtons() {
        val addPoint = ElementButton(this, 95, 20)
            .setName("AddPoint")
            .setSize(16, 16)
            .setTexture(Whoosh.MODID + ":textures/gui/button_add.png", 48, 16)
            .setEnabled { !StringUtil.isNullOrEmpty(editBox!!.value) }
        val removePoint = ElementButton(this, 113, 20)
            .setName("RemovePoint")
            .setSize(16, 16)
            .setTexture(Whoosh.MODID + ":textures/gui/button_remove.png", 48, 16)
        val whooshButton = ElementTextButton(this, 95, 108)
            .setText(WhooshLang.BUTTON_WHOOSH)
            .setName("Whoosh")
            .setSize(74, 16)
            .setTexture(Whoosh.MODID + ":textures/gui/button_whoosh.png", 222, 16)
        addElement<Any>(addPoint)
        addElement<Any>(removePoint)
        addElement<Any>(whooshButton)
    }

    fun handleElementButtonClick(buttonName: String?, mouseButton: Int): Boolean {
        var pitch = 0.7f
        when (buttonName) {
            "AddPoint" -> {
                pointsList!!.add(ListBoxPointElement(point))
                pitch += 0.1f
            }

            "RemovePoint" -> {
                pointsList!!.removeAll()
                pitch -= 0.1f
            }

            "Whoosh" -> {
                pitch += 0.3f
                //				player.teleportTo();
            }
        }
        SoundHelper.playClickSound(pitch)
        return true
    }

    private val point: TransporterPoint
        private get() {
            val pos = player.blockPosition()
            return TransporterPoint(pos.x, pos.y, pos.z, player.level.dimension().location().path, editBox!!.value)
        }

    private fun addList() {
        pointsList = ElementPointListBox(this, 8, 43, 83, 90) { p: TransporterPoint? -> currentPoint = p }
        addElement<Any>(pointsList)
    }

    override fun render(poseStack: PoseStack, mouseX: Int, mouseY: Int, partialTicks: Float) {
        super.render(poseStack, mouseX, mouseY, partialTicks)
        val i = (width - 192) / 2
        val j = (height - 109) / 2
        if (minecraft != null && !editBox!!.isFocused && editBox!!.value.isEmpty()) {
            drawString(poseStack, minecraft!!.font, WhooshLang.ENTER_NAME, i + 18, j + 8, -1)
        } else {
            editBox!!.render(poseStack, mouseX, mouseY, partialTicks)
        }

        // Draw the current point info
        if (currentPoint != null) {
//            drawString(poseStack, minecraft!!.font, TextComponent(currentPoint!!.dim()), i + 106, j + 29, -1)
//            drawString(poseStack, minecraft!!.font, TextComponent("X: " + currentPoint!!.x()), i + 106, j + 39, -1)
//            drawString(poseStack, minecraft!!.font, TextComponent("Y: " + currentPoint!!.y()), i + 106, j + 49, -1)
//            drawString(poseStack, minecraft!!.font, TextComponent("Z: " + currentPoint!!.z()), i + 106, j + 59, -1)
//        }
        }
    }
//    fun drawString(pPoseStack: PoseStack?, pFont: Font, pText: Component?, pX: Int, pY: Int, pColor: Int) {
//        pFont.drawShadow(pPoseStack, pText, pX.toFloat(), pY.toFloat(), pColor)
//    }
    override fun renderLabels(poseStack: PoseStack, mouseX: Int, mouseY: Int) {
        super.renderLabels(poseStack, mouseX, mouseY)
//
//		GlStateManager._enableBlend();
//		RenderHelper.setPosTexShader();
//		RenderHelper.setShaderTexture0(SLOT_OVERLAY);
//		drawTexturedModalRect(poseStack, menu.lockedSlot.x, menu.lockedSlot.y, 0, 0, 16, 16, 16, 16);
//		GlStateManager._disableBlend();
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, mouseButton: Int): Boolean {
        return if (editBox!!.mouseClicked(mouseX, mouseY, mouseButton)) {
            true
        } else super.mouseClicked(
            mouseX,
            mouseY,
            mouseButton
        )
    }

    override fun containerTick() {
        super.containerTick()
        editBox!!.tick()
    }

    override fun keyPressed(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
        ignoreTextInput = false
        if (editBox!!.keyPressed(keyCode, scanCode, modifiers)) {
            return true
        } else if (editBox!!.isFocused && editBox!!.isVisible && keyCode != 256) {
            return true
        } else if (minecraft != null && minecraft!!.options.keyChat.matches(
                keyCode,
                scanCode
            ) && !editBox!!.isFocused
        ) {
            ignoreTextInput = true
            editBox!!.setFocus(true)
            return true
        }
        return super.keyPressed(keyCode, scanCode, modifiers)
    }

    override fun keyReleased(pKeyCode: Int, pScanCode: Int, pModifiers: Int): Boolean {
        return false
    }

    override fun charTyped(codePoint: Char, modifiers: Int): Boolean {
        return if (ignoreTextInput) {
            false
        } else if (minecraft != null && minecraft!!.player != null && !minecraft!!.player!!.isSpectator) {
            editBox!!.charTyped(codePoint, modifiers)
        } else {
            false
        }
    }

    companion object {
        val TEXTURE = ResourceLocation(Whoosh.MODID, "textures/gui/transporter.png")
    }
}
