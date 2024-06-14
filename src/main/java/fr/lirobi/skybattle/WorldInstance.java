package fr.lirobi.skybattle;

import fr.lirobi.skybattle.game.SbPlayer;
import fr.lirobi.skybattle.world.SbWorld;

import java.util.List;

public abstract class WorldInstance {
    private SbWorld world;


    public abstract boolean join(SbPlayer player);

    public abstract void leave(SbPlayer player);

    public SbWorld getWorld() {
        return world;
    }

    public List<SbPlayer> getPlayers() {
        return world.getPlayers();
    }
}
