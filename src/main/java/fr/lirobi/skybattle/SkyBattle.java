package fr.lirobi.skybattle;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import fr.lirobi.skybattle.register.Registers;
import fr.lirobi.skybattle.world.SbWorld;
import fr.lirobi.skybattle.world.WorldType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class SkyBattle extends JavaPlugin {

    private static SkyBattle INSTANCE;
    private ProtocolManager protocolManager;

    private static List<SbWorld> worlds = new ArrayList<>();

    private static List<WorldInstance> worldInstances = new ArrayList<>();


    @Override
    public void onEnable() {
        // Plugin startup logic
        INSTANCE = this;
        Registers.register();

        protocolManager = ProtocolLibrary.getProtocolManager();

        SbWorld lobby = new SbWorld("world", WorldType.LOBBY);
        worlds.add(lobby);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static SkyBattle getInstance() {
        return INSTANCE;
    }

    public static List<SbWorld> getWorlds() {
        return worlds;
    }

    public static List<WorldInstance> getWorldInstances() {
        return worldInstances;
    }

    public static ProtocolManager getProtocolManager() {
        return INSTANCE.protocolManager;
    }
}
