package dev.crasher508.forms.utils;

import dev.waterdog.waterdogpe.network.protocol.updaters.ProtocolCodecUpdater;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.BedrockPacketDefinition;
import org.cloudburstmc.protocol.bedrock.packet.ModalFormRequestPacket;
import org.cloudburstmc.protocol.bedrock.packet.ModalFormResponsePacket;

public class CodecUpdater implements ProtocolCodecUpdater {

    @Override
    public BedrockCodec.Builder updateCodec(BedrockCodec.Builder builder, BedrockCodec baseCodec) {
        BedrockPacketDefinition<ModalFormRequestPacket> requestDefinition = baseCodec.getPacketDefinition(ModalFormRequestPacket.class);
        builder.registerPacket(ModalFormRequestPacket::new, requestDefinition.getSerializer(), requestDefinition.getId());
        BedrockPacketDefinition<ModalFormResponsePacket> responseDefinition = baseCodec.getPacketDefinition(ModalFormResponsePacket.class);
        builder.registerPacket(ModalFormResponsePacket::new, responseDefinition.getSerializer(), responseDefinition.getId());
        return builder;
    }

    @Override
    public int getRequiredVersion() {
        return -1;
    }
}