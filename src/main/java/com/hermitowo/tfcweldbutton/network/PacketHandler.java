package com.hermitowo.tfcweldbutton.network;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import com.hermitowo.tfcweldbutton.TFCWeldButton;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class PacketHandler
{
    public static <T extends CustomPacketPayload> CustomPacketPayload.Type<T> type(String id)
    {
        return new CustomPacketPayload.Type<T>(ResourceLocation.fromNamespaceAndPath(TFCWeldButton.MOD_ID, id));
    }

    public static void setup(RegisterPayloadHandlersEvent event)
    {
        final PayloadRegistrar register = event.registrar(ModList.get().getModFileById(TFCWeldButton.MOD_ID).versionString());

        register.playToServer(WeldButtonPacket.TYPE, WeldButtonPacket.CODEC, onServer(WeldButtonPacket::handle));
    }

    private static <T extends CustomPacketPayload> IPayloadHandler<T> onClient(Consumer<T> handler)
    {
        return (payload, context) -> context.enqueueWork(() -> handler.accept(payload));
    }

    private static <T extends CustomPacketPayload> IPayloadHandler<T> onServer(BiConsumer<T, ServerPlayer> handler)
    {
        return (payload, context) -> context.enqueueWork(() -> handler.accept(payload, (ServerPlayer) context.player()));
    }
}
