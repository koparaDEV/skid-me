package net.zell7z.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.resources.I18n;
import net.zell7z.managers.WingsManager;
import net.zell7z.mods.statuseffect.StatusEffect;
import net.zell7z.modules.BackgroundChatModule;
import net.zell7z.modules.CompassMoudle;
import net.zell7z.modules.DesignModule;
import net.zell7z.modules.ItemPhysicModule;
import net.zell7z.modules.SprintModule;
import net.zell7z.modules.StatusEffectModule;
import net.zell7z.modules.WingsModule;
import net.zell7z.utils.ChatUtils;

import java.io.IOException;

public class GuiClientOptions extends GuiScreen implements GuiYesNoCallback {

    private final GuiScreen field_146441_g;
    private String field_146442_a;

    public GuiClientOptions(final GuiScreen p_i1046_1_) {
        this.field_146442_a = "Ustawienia DropGuard...";
        this.field_146441_g = p_i1046_1_;
    }

    @Override
    public void initGui() {
        this.field_146442_a = I18n.format("Ustawienia DropGuard...");
        this.buttonList.add(new GuiButton(500, this.width / 2 - 110, this.height / 6 + 168, 106, 20, I18n.format("Gotowe")));
        this.buttonList.add(new GuiButton(501, this.width / 2 - 155, this.height / 6 + 96 - 6, 150, 20, I18n.format(ChatUtils.repairColors("Nowy wyglad: " + (DesignModule.isEnabled() ? "&2TAK" : "&cNIE")))));
        this.buttonList.add(new GuiButton(502, this.width / 2 - 155, this.height / 6 + 72 - 6, 150, 20, I18n.format(ChatUtils.repairColors("Przezroczysty chat: " + (!BackgroundChatModule.isEnabled() ? "&2TAK" : "&cNIE")))));
        this.buttonList.add(new GuiButton(503, this.width / 2 - 155, this.height / 6 + 48 - 6, 150, 20, I18n.format(ChatUtils.repairColors("Fizyka przedmiotow: " + (ItemPhysicModule.isEnabled() ? "&2TAK" : "&cNIE")))));
        this.buttonList.add(new GuiButton(504, this.width / 2 - 155, this.height / 6 + 24 - 6, 150, 20, I18n.format(ChatUtils.repairColors("Pokazuj skrzydla: " + (WingsModule.isEnabled() ? "&2TAK" : "&cNIE")))));
        this.buttonList.add(new GuiButton(505, this.width / 2 - 155, this.height / 6 - 6, 150, 20, I18n.format(ChatUtils.repairColors("Pokazuj kompas: " + (CompassMoudle.isEnabled() ? "&2TAK" : "&cNIE")))));
        this.buttonList.add(new GuiButton(506, this.width / 2 - 155, this.height / 6 + 120 - 6, 150, 20, I18n.format(ChatUtils.repairColors("Automatyczny sprint: " + (SprintModule.isEnabled() ? "&2TAK" : "&cNIE")))));
        this.buttonList.add(new GuiButton(507, this.width / 2 - 155, this.height / 6 + 144 - 6, 150, 20, I18n.format(ChatUtils.repairColors("Efekty potek: " + (StatusEffectModule.isEnabled() ? "&2TAK" : "&cNIE")))));
        this.buttonList.add(new GuiButton(508, this.width / 2, this.height / 6 + 168, 106, 20, I18n.format("Przeladuj skrzydla")));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.enabled) {
            if (button.id == 500) {
                this.mc.displayGuiScreen(this.field_146441_g);
            } else if (button.id == 501) {
                DesignModule.setEnabled(!DesignModule.isEnabled());
                button.displayString = ChatUtils.repairColors("Nowy wyglad: " + (DesignModule.isEnabled() ? "&2TAK" : "&cNIE"));
            } else if (button.id == 502) {
                BackgroundChatModule.setEnabled(!BackgroundChatModule.isEnabled());
                button.displayString = ChatUtils.repairColors("Przezroczysty chat: " + (!BackgroundChatModule.isEnabled() ? "&2TAK" : "&cNIE"));
            } else if (button.id == 503) {
                ItemPhysicModule.setEnabled(!ItemPhysicModule.isEnabled());
                button.displayString = ChatUtils.repairColors("Fizyka przedmiotow: " + (ItemPhysicModule.isEnabled() ? "&2TAK" : "&cNIE"));
            } else if (button.id == 504) {
                WingsModule.setEnabled(!WingsModule.isEnabled());
                button.displayString = ChatUtils.repairColors("Pokazuj skrzydla: " + (WingsModule.isEnabled() ? "&2TAK" : "&cNIE"));
            } else if (button.id == 505) {
                CompassMoudle.setEnabled(!CompassMoudle.isEnabled());
                button.displayString = ChatUtils.repairColors("Pokazuj kompas: " + (CompassMoudle.isEnabled() ? "&2TAK" : "&cNIE"));
            } else if (button.id == 506) {
                SprintModule.setEnabled(!SprintModule.isEnabled());
                button.displayString = ChatUtils.repairColors("Automatyczny sprint: " + (SprintModule.isEnabled() ? "&2TAK" : "&cNIE"));
            } else if (button.id == 507) {
                StatusEffectModule.setEnabled(!StatusEffectModule.isEnabled());
                button.displayString = ChatUtils.repairColors("Efekty potek: " + (StatusEffectModule.isEnabled() ? "&2TAK" : "&cNIE"));
            } else if (button.id == 508) {
                WingsManager.loadWings();
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.field_146442_a, this.width / 2, 15, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
