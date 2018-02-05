package net.minecraft.client.gui;

import java.awt.Color;

import me.existdev.exist.Exist;
import me.existdev.exist.utils.ColorUtils;
import me.existdev.exist.utils.helper.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiButton extends Gui {

	protected static final ResourceLocation buttonTextures = new ResourceLocation("textures/gui/widgets.png");

	protected int width;

	protected int height;

	public int xPosition;

	public int yPosition;

	public String displayString;
	public int id;

	public boolean enabled;

	public boolean visible;
	protected boolean hovered;

	public float targetX = 0;
	public float onSpeedX = 3;
	public float offSpeedX;
	public int targetA = 0;
	public int speedA = 20;

	public GuiButton(int buttonId, int x, int y, String buttonText) {
		this(buttonId, x, y, 200, 20, buttonText);
	}

	public GuiButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		this.width = 200;
		this.height = 20;
		this.enabled = true;
		this.visible = true;
		this.id = buttonId;
		this.xPosition = x;
		this.yPosition = y;
		this.width = widthIn;
		this.height = heightIn;
		this.displayString = buttonText;
	}

	protected int getHoverState(boolean mouseOver) {
		int i = 1;

		if (!this.enabled) {
			i = 0;
		} else if (mouseOver) {
			i = 2;
		}

		return i;
	}

	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if (this.visible) {
			mc.getTextureManager().bindTexture(buttonTextures);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width
					&& mouseY < this.yPosition + this.height;
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
			//RenderHelper.drawRect(this.xPosition,this.yPosition - (targetX / 3),
			//		this.xPosition + this.width, this.yPosition + this.height,ColorUtils.getColor(255 - (this.targetA * 3),255 - (this.targetA * 3), 255 - (this.targetA * 3)));
			RenderHelper.drawBorderedRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 1,
					ColorUtils.getColor(255, 255, 255, (int) (255 - (this.targetA * 3.5F))) , ColorUtils.getColor(255, 255, 255,(int) (this.targetA * 3.5F)));
			Exist.fontRenderer.fontRenderer30.drawCenteredString(this.displayString, this.xPosition + this.width / 2,this.yPosition + (this.height / 3),
					ColorUtils.getColor(255-(this.targetA * 3), 255-(this.targetA * 3), 255-(this.targetA * 3)));
		}
	}

	protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
	}

	public void mouseReleased(int mouseX, int mouseY) {
	}

	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		return this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= this.yPosition
				&& mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
	}

	public boolean isMouseOver() {
		return this.hovered;
	}

	public void drawButtonForegroundLayer(int mouseX, int mouseY) {
	}

	public void playPressSound(SoundHandler soundHandlerIn) {
		soundHandlerIn.playSound(
				PositionedSoundRecord.createPositionedSoundRecord(new ResourceLocation("gui.button.press"), 1.0F));
	}

	public int getButtonWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public static int default_getWhite(int A) {
		return new Color(255, 255, 255, A).getRGB();
	}

	public static int getClientBlue(int A) {
		return new Color(66, 134, 244, A).getRGB();
	}
}
