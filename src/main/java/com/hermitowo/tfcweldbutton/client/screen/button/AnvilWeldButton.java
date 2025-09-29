package com.hermitowo.tfcweldbutton.client.screen.button;

import com.hermitowo.tfcweldbutton.TFCWeldButton;
import com.hermitowo.tfcweldbutton.network.WeldButtonPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.PacketDistributor;

import net.dries007.tfc.client.RenderHelpers;
import net.dries007.tfc.client.screen.AnvilScreen;

public class AnvilWeldButton extends Button
{
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(TFCWeldButton.MOD_ID, "textures/gui/anvil_weld_button.png");

    public AnvilWeldButton(int guiLeft, int guiTop)
    {
        super(guiLeft + 137, guiTop + 40, 18, 18, Component.translatable("tfcweldbutton.gui.weld_button"), button -> {
            PacketDistributor.sendToServer(WeldButtonPacket.PACKET);
        }, RenderHelpers.NARRATION);
        setTooltip(Tooltip.create(Component.translatable("tfcweldbutton.gui.weld_button")));
    }

    @Override
    public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick)
    {
        int x = getX();
        int y = getY();
        graphics.blit(AnvilScreen.BACKGROUND, x, y, 218, 0, width, height, 256, 256);

        graphics.blit(TEXTURE, x + 1, y + 1, 0, 0, 16, 16, 16, 16);
    }
}
