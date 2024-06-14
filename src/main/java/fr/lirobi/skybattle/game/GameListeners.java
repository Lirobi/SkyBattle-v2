package fr.lirobi.skybattle.game;

import fr.lirobi.skybattle.world.SbWorld;
import fr.lirobi.skybattle.world.WorldType;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class GameListeners implements Listener {

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        if (e.getFoodLevel() == 20) return;

        e.setFoodLevel(20);
    }


    public void onBlockPlace(BlockPlaceEvent e) {
        SbWorld world = SbWorld.getSbWorldByBukkitWorld(e.getBlock().getWorld());
        SbPlayer player = SbPlayer.getSbPlayerByBukkitPlayer(e.getPlayer());

        if (player.getPlayer().getGameMode() != GameMode.CREATIVE) {
            return;
        }

        if (world.getType() != WorldType.GAME || player.getGame().getGameState() != GameState.PLAYING) {
            e.setCancelled(true);
            return;
        }

        player.getGame().getPlacedBlocks().add(e.getBlock());
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        SbWorld world = SbWorld.getSbWorldByBukkitWorld(e.getBlock().getWorld());
        SbPlayer player = SbPlayer.getSbPlayerByBukkitPlayer(e.getPlayer());

        if (player.getPlayer().getGameMode() != GameMode.CREATIVE) {
            return;
        }
        if (world.getType() != WorldType.GAME || player.getGame().getGameState() != GameState.PLAYING) {
            e.setCancelled(true);
            return;
        }

        if (player.getGame().getPlacedBlocks().contains(e.getBlock())) {
            player.getGame().getPlacedBlocks().remove(e.getBlock());
        } else {
            e.setCancelled(true);
        }
    }


}
