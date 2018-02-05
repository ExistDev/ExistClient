package net.minecraft.client.gui;

import java.io.IOException;

import me.existdev.exist.gui.GuiChangeLog;
import me.existdev.exist.gui.GuiCredit;
import me.existdev.exist.gui.GuiSelectPlayMode;
import me.existdev.exist.gui.altchanger.GuiAltManager;
import me.existdev.exist.gui.button.MainMenuButton;
import me.existdev.exist.gui.particle.Particle;
import me.existdev.exist.utils.ColorUtils;
import me.existdev.exist.utils.helper.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiMainMenu extends GuiScreen {

	private ResourceLocation background = new ResourceLocation("Exist/background.png");
	Minecraft mc = Minecraft.getMinecraft();

	@Override
	public void initGui() {
		super.initGui();
		String Play = I18n.format("PLAY", new Object[0]);
		String AltChanger = "AltManager";
		String Setting = I18n.format("Setting", new Object[0]);
		String ChangeLog = "ChangeLog";
		String Credit = "Credit";
		String Exit = "Exit";
		int initHeight = GuiMainMenu.height / 2;
		int objHeight = 17;
		int objWidth = 63;
		int xMid = GuiMainMenu.width / 5;

		if (mc.gameSettings.guiScale == 0) {
			// Auto
			this.buttonList.add(new MainMenuButton(0, xMid - 100, initHeight + 80, objWidth, objHeight, Play));
			this.buttonList.add(new MainMenuButton(1, xMid - 15, initHeight + 80, objWidth, objHeight, AltChanger));
			this.buttonList.add(new MainMenuButton(2, xMid + 70, initHeight + 80, objWidth, objHeight, Setting));
			this.buttonList.add(new MainMenuButton(3, xMid + 155, initHeight + 80, objWidth, objHeight, ChangeLog));
			this.buttonList.add(new MainMenuButton(4, xMid + 240, initHeight + 80, objWidth, objHeight, Credit));
			this.buttonList.add(new MainMenuButton(5, xMid + 325, initHeight + 80, objWidth, objHeight, Exit));
		} else if (mc.gameSettings.guiScale == 1) {
			// Small
			this.buttonList.add(new MainMenuButton(0, xMid + 40, initHeight + 80, objWidth, objHeight, Play));
			this.buttonList.add(new MainMenuButton(1, xMid + 125, initHeight + 80, objWidth, objHeight, AltChanger));
			this.buttonList.add(new MainMenuButton(2, xMid + 210, initHeight + 80, objWidth, objHeight, Setting));
			this.buttonList.add(new MainMenuButton(3, xMid + 295, initHeight + 80, objWidth, objHeight, ChangeLog));
			this.buttonList.add(new MainMenuButton(4, xMid + 380, initHeight + 80, objWidth, objHeight, Credit));
			this.buttonList.add(new MainMenuButton(5, xMid + 465, initHeight + 80, objWidth, objHeight, Exit));
		} else if (mc.gameSettings.guiScale == 2) {
			// Normal
			this.buttonList.add(new MainMenuButton(0, xMid + 80, initHeight + 80, objWidth, objHeight, Play));
			this.buttonList.add(new MainMenuButton(1, xMid + 165, initHeight + 80, objWidth, objHeight, AltChanger));
			this.buttonList.add(new MainMenuButton(2, xMid + 250, initHeight + 80, objWidth, objHeight, Setting));
			this.buttonList.add(new MainMenuButton(3, xMid + 335, initHeight + 80, objWidth, objHeight, ChangeLog));
			this.buttonList.add(new MainMenuButton(4, xMid + 420, initHeight + 80, objWidth, objHeight, Credit));
			this.buttonList.add(new MainMenuButton(5, xMid + 505, initHeight + 80, objWidth, objHeight, Exit));
		} else if (mc.gameSettings.guiScale == 3) {
			// Large
			this.buttonList.add(new MainMenuButton(0, xMid - 70, initHeight + 80, objWidth, objHeight, Play));
			this.buttonList.add(new MainMenuButton(1, xMid + 10, initHeight + 80, objWidth, objHeight, AltChanger));
			this.buttonList.add(new MainMenuButton(2, xMid + 90, initHeight + 80, objWidth, objHeight, Setting));
			this.buttonList.add(new MainMenuButton(3, xMid + 170, initHeight + 80, objWidth, objHeight, ChangeLog));
			this.buttonList.add(new MainMenuButton(4, xMid + 250, initHeight + 80, objWidth, objHeight, Credit));
			this.buttonList.add(new MainMenuButton(5, xMid + 330, initHeight + 80, objWidth, objHeight, Exit));
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.id == 0) {
			this.mc.displayGuiScreen(new GuiSelectPlayMode());
		} else if (button.id == 2) {
			this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
		} else if (button.id == 1) {
			this.mc.displayGuiScreen(new GuiAltManager());
		} else if (button.id == 3) {
			this.mc.displayGuiScreen(new GuiChangeLog());
		} else if (button.id == 4) {
			this.mc.displayGuiScreen(new GuiCredit());
		}else if (button.id == 5) {
			this.mc.shutdown();
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int w = sr.getScaledWidth();
		int h = sr.getScaledHeight();
		this.mc.getTextureManager().bindTexture(background);
		GuiMainMenu.drawScaledCustomSizeModalRect(0, 0, 0.0f, 0.0f, w + 2, h, w + 2, h, w + 2, h);
		GlStateManager.enableBlend();
		GlStateManager.disableBlend();
		RenderHelper.drawRect(0, 0, w, h, ColorUtils.getColor(0, 0, 0, 150));
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
}
