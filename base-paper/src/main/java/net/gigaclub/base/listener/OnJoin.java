package net.gigaclub.base.listener;

import net.gigaclub.base.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerUUID = player.getUniqueId().toString();
        String playerName = player.getName();
        if (Main.getOdoo().checkIfPlayerExists(playerUUID)) {
            if (Main.getOdoo().checkName(playerName, playerUUID)) {
                Main.getOdoo().updateName(playerName, playerUUID);
            }
        } else {
            Main.getOdoo().createPlayer(playerName, playerUUID);
        }
        Main.getOdoo().updateStatus(playerUUID, "online");
    }

}
