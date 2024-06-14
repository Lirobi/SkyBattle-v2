package fr.lirobi.skybattle.game;

import fr.lirobi.skybattle.utils.ActionBarMessages;
import fr.lirobi.skybattle.utils.ChatMessages;
import fr.lirobi.skybattle.world.SbWorld;
import fr.lirobi.skybattle.world.WorldType;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathKillListener implements Listener {
    @EventHandler
    public void death(PlayerDeathEvent e) {
        if (SbWorld.getSbWorldByBukkitWorld(e.getEntity().getWorld()).getType() != WorldType.GAME) {
            return;
        }

        SbPlayer victim = SbPlayer.getSbPlayerByBukkitPlayer(e.getEntity().getPlayer());
        SbPlayer killer = victim.getLastDamager();
        if (killer != null) {
            killer.kill(victim);
        } else {
            victim.die();
        }
        ChatMessages.sendDeathMessage(victim.getGame(), killer, victim);

    }

    @EventHandler
    public void damage(EntityDamageByEntityEvent e) {

        // Si le monde n'est pas un monde de jeu ou que l'entité n'est pas un joueur
        if (SbWorld.getSbWorldByBukkitWorld(e.getEntity().getWorld()).getType() != WorldType.GAME || !(e.getEntity() instanceof Player)) {
            return;
        }

        // Si la partie dans laquelle est le joueur n'est pas lancée.
        if (SbPlayer.getSbPlayerByBukkitPlayer((Player) e.getEntity()).getGame().getGameState() != GameState.PLAYING) {
            e.setCancelled(true);
            return;
        }


        if (e.getDamager() instanceof Player || e.getDamager() instanceof Arrow) {

            SbPlayer victim = SbPlayer.getSbPlayerByBukkitPlayer((Player) e.getEntity());
            SbPlayer attacker = null;

            switch (e.getDamager().getType()) {
                case ARROW:
                    attacker = SbPlayer.getSbPlayerByBukkitPlayer((Player) ((Arrow) e.getDamager()).getShooter());

                    ActionBarMessages.sendEnnemyHealth(attacker, victim);

                    break;
                case PLAYER:

                    attacker = SbPlayer.getSbPlayerByBukkitPlayer((Player) e.getDamager());

                    break;
            }

            victim.setLastDamager(attacker);
            attacker.setLastVictim(victim);
        }
    }
}
