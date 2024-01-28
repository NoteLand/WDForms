package dev.crasher508.forms.listeners;

import dev.crasher508.forms.handlers.PacketHandler;
import dev.waterdog.waterdogpe.event.defaults.PlayerLoginEvent;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

public class PlayerLoginListener {

    public void onPlayerLogin(PlayerLoginEvent event) {
        ProxiedPlayer proxiedPlayer = event.getPlayer();
        proxiedPlayer.getPluginPacketHandlers().add(new PacketHandler(proxiedPlayer));
    }
}
