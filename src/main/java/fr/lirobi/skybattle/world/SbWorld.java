package fr.lirobi.skybattle.world;

import fr.lirobi.skybattle.SkyBattle;
import fr.lirobi.skybattle.game.SbPlayer;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

public class SbWorld {
    private World world;
    private WorldType type;
    private String name;

    private List<SbPlayer> players = new ArrayList<>();

    public SbWorld(String name, WorldType worldType) {
        this.name = name;
        this.type = worldType;

        //TODO : Créer ou load le monde
    }

    public World getWorld() {
        return this.world;
    }

    public WorldType getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public List<SbPlayer> getPlayers() {
        return this.players;
    }

    public static SbWorld getSbWorldByBukkitWorld(World world) {
        for (SbWorld w : SkyBattle.getWorlds()) {
            if (w.getWorld() == world) {
                return w;
            }
        }
        return null;
    }

    public static SbWorld getSbWorldBySbPlayer(SbPlayer player) {
        for (SbWorld w : SkyBattle.getWorlds()) {
            for (SbPlayer p : w.getPlayers()) {
                if (p == player) {
                    return w;
                }
            }
        }
        return null;
    }

}
