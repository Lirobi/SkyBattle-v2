package fr.lirobi.skybattle.listeners;

import fr.lirobi.skybattle.SkyBattle;
import fr.lirobi.skybattle.WorldInstance;
import fr.lirobi.skybattle.game.SbPlayer;
import fr.lirobi.skybattle.world.SbWorld;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class WorldChange implements Listener {

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        SbPlayer player = SbPlayer.getSbPlayerByBukkitPlayer(e.getPlayer());
        SbWorld world = SbWorld.getSbWorldByBukkitWorld(e.getPlayer().getWorld());

        if (player.getWorld() != null) {

            SbWorld.getSbWorldByBukkitWorld(e.getFrom()).getPlayers().remove(player);
            world.getPlayers().add(player);
            player.setWorld(world);

            for (WorldInstance g : SkyBattle.getWorldInstances()) {
                if (g.getWorld() == world) {
                    g.join(player);
                }
            }
        }


    }


}
