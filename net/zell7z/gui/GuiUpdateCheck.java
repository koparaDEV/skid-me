package net.zell7z.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.zell7z.utils.ChatUtils;
import net.zell7z.utils.UpdaterUtils;

public class GuiUpdateCheck extends GuiScreen {
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, I18n.format(ChatUtils.repairColors(UpdaterUtils.getMessage())), this.width / 2, 100, 16777215);
        UpdaterUtils.checkVersion();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
