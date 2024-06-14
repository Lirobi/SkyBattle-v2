package fr.lirobi.skybattle.utils;

import fr.lirobi.skybattle.game.SbPlayer;
import fr.lirobi.skybattle.utils.packetsenders.PacketTitle;

public class TitleMessages {
    public static void sendRespawnTimer(SbPlayer p, int time) {
        PacketTitle.sendTitle(p.getPlayer(), "", "§7Réapparition dans §c" + time + "§7s", 0, 21, 0);
    }

    public static void sendRepawn(SbPlayer p) {
        PacketTitle.sendTitle(p.getPlayer(), "§aRéapparition", "", 0, 21, 0);
    }

    public static void sendGameStartTimer(SbPlayer p, int time) {
        PacketTitle.sendTitle(p.getPlayer(), "", "§7La partie commence dans §6" + time + "§7s", 0, 21, 0);
    }

    public static void sendGameStart(SbPlayer p) {
        PacketTitle.sendTitle(p.getPlayer(), "§6§lSkyBattle", "§7La partie commence !", 5, 30, 5);
    }
}
