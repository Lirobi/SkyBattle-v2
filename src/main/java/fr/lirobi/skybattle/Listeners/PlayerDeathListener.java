package fr.lirobi.sykbattle.Listeners;

import net.minecraft.server.v1_8_R3.PacketPlayOutCombatEvent;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerDeathListener implements Listener {


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        new BukkitRunnable() {
            @Override
            public void run() {
                // Restaurer la santé et téléporter le joueur
                player.setHealth(player.getMaxHealth());
                player.setFoodLevel(20);
                player.getActivePotionEffects().clear();
                player.getInventory().clear();
                player.setExp(0);
                // Intercepter et modifier le packet de combat
                interceptDeathScreen(player);
            }
        }.runTaskLater(this, 1L); // Exécuter après 1 tick pour éviter les conflits avec d'autres plugins
    }

    private void interceptDeathScreen(Player player) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        PacketPlayOutCombatEvent packet = new PacketPlayOutCombatEvent();

        try {
            packet.a = PacketPlayOutCombatEvent.EnumCombatEventType.END_COMBAT; // Indique la fin du combat
            packet.b = player.getEntityId(); // ID de l'entité
            packet.c = 0; // Durée du combat

            craftPlayer.getHandle().playerConnection.sendPacket(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
