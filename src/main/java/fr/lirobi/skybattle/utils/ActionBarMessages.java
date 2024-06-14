package fr.lirobi.skybattle.utils;

import fr.lirobi.skybattle.game.SbPlayer;
import fr.lirobi.skybattle.utils.packetsenders.PacketTitle;

public class ActionBarMessages {
    public static void sendEnnemyHealth(SbPlayer p, SbPlayer ennemy) {
        PacketTitle.sendActionBarTitle(p.getPlayer(), ennemy.getPlayer().getCustomName() + " §c" + ennemy.getPlayer().getHealth() + "❤");
    }

    public static void sendEarnCoins(SbPlayer p, int coins) {
        String message = "§6+" + coins + " coins";
        if (coins > 1) {
            message += "s";
        }
        PacketTitle.sendActionBarTitle(p.getPlayer(), message);
    }
}
