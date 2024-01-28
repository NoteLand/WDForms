package dev.crasher508.forms;

import dev.crasher508.forms.listeners.PlayerLoginListener;
import dev.crasher508.forms.utils.CodecUpdater;
import dev.crasher508.forms.utils.Form;
import dev.waterdog.waterdogpe.event.defaults.*;
import dev.waterdog.waterdogpe.network.protocol.ProtocolCodecs;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import dev.waterdog.waterdogpe.plugin.Plugin;
import org.cloudburstmc.protocol.bedrock.packet.ModalFormRequestPacket;
import java.util.*;

public class Forms extends Plugin {

    private static Forms instance;

    public static Forms getInstance() {
        return instance;
    }

    //Forms
    private int formIdCounter = 0;
    private final HashMap<Integer, Form> formStore = new HashMap<>();

    public HashMap<Integer, Form> getFormStore() {
        return formStore;
    }

    public void sendForm(ProxiedPlayer proxiedPlayer, Form form) {
        int id = this.formIdCounter++;
        ModalFormRequestPacket packet = new ModalFormRequestPacket();
        packet.setFormId(id);
        packet.setFormData(form.getData().toString());
        proxiedPlayer.sendPacket(packet);
        this.formStore.put(id, form);
    }

    @Override
    public void onEnable() {
        instance = this;
        ProtocolCodecs.addUpdater(new CodecUpdater());
        this.getProxy().getEventManager().subscribe(PlayerLoginEvent.class, new PlayerLoginListener()::onPlayerLogin);
    }
}
