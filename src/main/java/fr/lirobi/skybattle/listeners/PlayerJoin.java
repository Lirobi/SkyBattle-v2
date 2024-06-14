package fr.lirobi.skybattle.listeners;

import fr.lirobi.skybattle.game.SbPlayer;
import fr.lirobi.skybattle.utils.packetsenders.PacketTablist;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        PacketTablist.sendHeaderFooter(e.getPlayer(), "    §bVous jouez actuellement sur §6§lMinatao    ", "§emc.minatao.fr");


        SbPlayer player = new SbPlayer(e.getPlayer(), null);
    }
}
