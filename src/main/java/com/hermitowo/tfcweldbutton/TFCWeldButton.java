package com.hermitowo.tfcweldbutton;

import com.hermitowo.tfcweldbutton.network.PacketHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(TFCWeldButton.MOD_ID)
public class TFCWeldButton
{
    public static final String MOD_ID = "tfcweldbutton";

    public TFCWeldButton(IEventBus bus)
    {
        bus.addListener(PacketHandler::setup);
    }
}
