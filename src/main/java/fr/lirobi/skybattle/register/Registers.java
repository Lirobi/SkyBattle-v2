package fr.lirobi.skybattle.register;

import fr.lirobi.skybattle.SkyBattle;
import fr.lirobi.skybattle.game.DeathKillListener;
import fr.lirobi.skybattle.listeners.PlayerJoin;
import fr.lirobi.skybattle.listeners.PlayerQuit;
import fr.lirobi.skybattle.lobby.MenuHandler;
import fr.lirobi.skybattle.utils.packetsenders.NoDeathScreen;
import fr.lirobi.skybattle.utils.packetsenders.PerWorldTablist;
import org.bukkit.Bukkit;

public class Registers {

    private static SkyBattle INSTANCE = SkyBattle.getInstance();

    public static void register() {
        Bukkit.getServer().getPluginManager().registerEvents(new NoDeathScreen(), INSTANCE);
        Bukkit.getServer().getPluginManager().registerEvents(new PerWorldTablist(), INSTANCE);

        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin(), INSTANCE);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerQuit(), INSTANCE);
        Bukkit.getServer().getPluginManager().registerEvents(new MenuHandler(), INSTANCE);

        Bukkit.getServer().getPluginManager().registerEvents(new DeathKillListener(), INSTANCE);

    }
}
