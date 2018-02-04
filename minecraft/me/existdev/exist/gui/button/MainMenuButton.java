package me.existdev.exist.gui.button;

import java.awt.Color;

import me.existdev.exist.Exist;
import me.existdev.exist.utils.ColorUtils;
import me.existdev.exist.utils.helper.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class MainMenuButton extends GuiButton {

	private ResourceLocation icon;
	float targetX;
	float currentX;

	public MainMenuButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		this.icon = new ResourceLocation("Exist/Buttons/" + this.displayString + ".png");
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if (this.visible) {
			mc.getTextureManager().bindTexture(buttonTextures);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			this.hovered = mouseX >= this.xPosition - 10 && mouseY >= this.yPosition
					&& mouseX < this.xPosition + this.width + 10 && mouseY < this.yPosition + this.height;
			int i = this.getHoverState(this.hovered);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.blendFunc(770, 771);
			this.mouseDragged(mc, mouseX, mouseY);
			int j = 14737632;

			if (!this.enabled) {

			} else if (this.hovered) {
				this.offSpeedX = 3f;
				this.speedA = 10;
				this.targetA += this.speedA;
				if (this.targetA >= 70) {
					this.targetA = 70;
				}
				this.speedA -= 1f;
				if (this.speedA < 5) {
					this.speedA = 5;
				}
				this.targetX += this.onSpeedX;
				if (this.targetX >= 60) {
					this.targetX = 60;
				}
				this.onSpeedX -= 0.25f;
				if (this.onSpeedX < 0) {
					this.onSpeedX = 0f;
				}
			} else if (!this.hovered) {
				this.onSpeedX = 3f;
				this.speedA = 10;
				this.targetA -= this.speedA;
				if (this.targetA <= 0) {
					this.targetA = 0;
				}
				this.speedA -= 1f;
				if (this.speedA < 5) {
					this.speedA = 5;
				}
				this.targetX -= this.offSpeedX;
				if (this.targetX <= 0) {
					this.targetX = 0;
				}
				this.offSpeedX -= 0.25f;
				if (this.offSpeedX < 0) {
					this.offSpeedX = 0f;
				}
			}
			RenderHelper.drawRect(this.xPosition - 10,this.yPosition - (targetX / 3),
					this.xPosition + this.width + 10, this.yPosition + this.height,ColorUtils.getColor(255 - (this.targetA * 3),255 - (this.targetA * 3), 255 - (this.targetA * 3)));
			Exist.fontRenderer.fontRenderer30.drawCenteredString(this.displayString, this.xPosition + this.width / 2,
					(this.yPosition + (this.height - 8) / 2 - 2 - (targetX / 6)), ColorUtils.getColor(this.targetA * 3, this.targetA * 3, this.targetA * 3));
		}
	}

	public static int getBlack(int A) {
		return new Color(0, 0, 0, A).getRGB();
	}

	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		return this.visible && mouseX >= this.xPosition - 10 && mouseY >= this.yPosition
				&& mouseX < this.xPosition + this.width + 10 && mouseY < this.yPosition + 60;
	}

}
