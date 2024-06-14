package fr.lirobi.skybattle.utils.packetsenders;

import fr.lirobi.skybattle.SkyBattle;
import net.minecraft.server.v1_8_R3.PacketPlayOutCombatEvent;
import net.minecraft.server.v1_8_R3.PacketPlayOutRespawn;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;


public class NoDeathScreen implements Listener {


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        // Planifie une tâche pour restaurer la santé et téléporter le joueur après la mort
        new BukkitRunnable() {
            @Override
            public void run() {
                CraftPlayer craftPlayer = (CraftPlayer) player;
                PlayerConnection connection = craftPlayer.getHandle().playerConnection;

                // Envoyer le packet de respawn
                connection.sendPacket(new PacketPlayOutRespawn(
                        craftPlayer.getHandle().dimension,
                        craftPlayer.getHandle().getWorld().getDifficulty(),
                        craftPlayer.getHandle().getWorld().worldData.getType(),
                        craftPlayer.getHandle().playerInteractManager.getGameMode()
                ));

                // Restaurer la santé
                player.setHealth(player.getMaxHealth());
                player.setFoodLevel(20);

                // Envoyer un message au joueur
                event.setDeathMessage(null);

                // Supprimer l'écran de mort
                interceptDeathScreen(player);
            }
        }.runTaskLater(SkyBattle.getInstance(), 1L); // Exécuter après 1 tick pour éviter les conflits avec d'autres plugins
    }

    private void interceptDeathScreen(Player player) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        PlayerConnection connection = craftPlayer.getHandle().playerConnection;

        try {
            PacketPlayOutCombatEvent packet = new PacketPlayOutCombatEvent();
            Field a = packet.getClass().getDeclaredField("a");
            Field b = packet.getClass().getDeclaredField("b");
            Field c = packet.getClass().getDeclaredField("c");
            a.setAccessible(true);
            b.setAccessible(true);
            c.setAccessible(true);

            a.set(packet, PacketPlayOutCombatEvent.EnumCombatEventType.END_COMBAT);
            b.set(packet, craftPlayer.getHandle().getId());
            c.set(packet, 0);

            connection.sendPacket(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
