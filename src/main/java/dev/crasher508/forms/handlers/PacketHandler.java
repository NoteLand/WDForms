package dev.crasher508.forms.handlers;

import dev.crasher508.forms.utils.Form;
import dev.crasher508.forms.Forms;
import dev.waterdog.waterdogpe.network.protocol.Signals;
import dev.waterdog.waterdogpe.network.protocol.handler.PluginPacketHandler;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import org.cloudburstmc.protocol.bedrock.PacketDirection;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.ModalFormResponsePacket;
import org.cloudburstmc.protocol.common.PacketSignal;

public class PacketHandler implements PluginPacketHandler {

    private final ProxiedPlayer proxiedPlayer;

    public PacketHandler(ProxiedPlayer proxiedPlayer) {
        this.proxiedPlayer = proxiedPlayer;
    }

    @Override
    public PacketSignal handle(ModalFormResponsePacket packet) {
        int formID = packet.getFormId();
        Form form = Forms.getInstance().getFormStore().get(formID);
        Forms.getInstance().getFormStore().remove(formID);
        if (form == null)
            return PacketSignal.UNHANDLED;
        String formData = packet.getFormData();
        if (formData == null) {
            if (form.getCloseCallback() != null) {
                form.getCloseCallback().onRun(this.proxiedPlayer);
            }
            return Signals.CANCEL;
        }
        if (formData.equals("null")) {
            formData = null;
        }
        form.handle(this.proxiedPlayer, formData);
        return Signals.CANCEL;
    }

    @Override
    public PacketSignal handlePacket(BedrockPacket bedrockPacket, PacketDirection packetDirection) {
        return bedrockPacket.handle(this);
    }
}
