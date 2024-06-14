package fr.lirobi.skybattle.utils;

import fr.lirobi.skybattle.game.Game;
import fr.lirobi.skybattle.game.SbPlayer;
import org.bukkit.entity.Player;

public class ChatMessages {


    public static void sendDeathMessage(Game game, SbPlayer killer, SbPlayer victim) {
        //TODO : Add code here
        String message = "";
        if (killer == null) {
            message = victim.getPlayer().getDisplayName() + "§7 est mort.";

        } else {
            message = killer.getPlayer().getDisplayName() + "§7 a tué " + victim.getPlayer().getDisplayName() + ".";
        }

        for (SbPlayer sbPlayer : game.getWorld().getPlayers()) {
            sbPlayer.getPlayer().sendMessage(message);
        }
    }

    public static void sendJoinMessage(Game game, SbPlayer player) {
        //TODO : Add code here
        String message = player.getPlayer().getDisplayName() + "§e a rejoint la partie (" + game.getWorld().getPlayers().size() + "/" + game.getMaxPlayers() + ")";
        for (SbPlayer sbPlayer : game.getWorld().getPlayers()) {
            sbPlayer.getPlayer().sendMessage(message);
        }
    }

    public static void sendQuitMessage(Game game, SbPlayer player) {
        //TODO : Add code here
        String message = player.getPlayer().getDisplayName() + "§e a quitté la partie.";
        for (SbPlayer sbPlayer : game.getWorld().getPlayers()) {
            sbPlayer.getPlayer().sendMessage(message);
        }
    }

    public void sendMessageWithPrefix(Player p, String m) {
        p.sendMessage("§6§lSkyBattle §8» §7" + m);
    }
}
