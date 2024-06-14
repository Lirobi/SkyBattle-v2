package fr.lirobi.skybattle.lobby;

import fr.lirobi.skybattle.WorldInstance;
import fr.lirobi.skybattle.game.SbPlayer;
import fr.lirobi.skybattle.world.SbWorld;

public class Lobby extends WorldInstance {

    private SbWorld world;

    public Lobby(SbWorld world) {
        this.world = world;
    }

    @Override
    public boolean join(SbPlayer player) {
        this.getPlayers().add(player);
        return true;
    }

    @Override
    public void leave(SbPlayer player) {
        this.getPlayers().remove(player);

    }
}
