package fr.lirobi.skybattle.utils.packetsenders;

import fr.lirobi.skybattle.SkyBattle;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PerWorldTablist implements Listener {


    /**
     * Call the 'showAndHidePlayers(...)' method when a player joins the server.
     */
    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent e) {
        showAndHidePlayers(e.getPlayer());
    }

    /**
     * Call the 'showAndHidePlayers(...)' method when a player changes the world
     */
    @EventHandler
    public void onPlayerChangedWorld(final PlayerChangedWorldEvent e) {
        showAndHidePlayers(e.getPlayer());
    }

    /**
     * This method goes through every player on the server and checks if they are in the same world.
     * If not, both will be hidden from each other. Otherwise both will be shown each other.
     * Using the 'hidePlayer(...)' method because sending Packets would break custom/colored names in the TabList.
     */
    private void showAndHidePlayers(final Player player) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(SkyBattle.getInstance(), () -> {
            for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                if (player.getWorld().equals(players.getWorld())) {
                    player.showPlayer(players);
                    players.showPlayer(player);
                } else {
                    player.hidePlayer(players);
                    players.hidePlayer(player);
                }
            }
        }, 1);
    }
}
