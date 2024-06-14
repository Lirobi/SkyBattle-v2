package fr.lirobi.skybattle.game;

import fr.lirobi.skybattle.SkyBattle;
import fr.lirobi.skybattle.WorldInstance;
import fr.lirobi.skybattle.lobby.Lobby;
import fr.lirobi.skybattle.world.SbWorld;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class Game extends WorldInstance {
    private SkyBattle plugin;
    private GameState gameState;
    private List<Team> teams = new ArrayList<>();
    private List<Block> placedBlocks = new ArrayList<>();
    private int gameTimer;
    private int waitingTimer;
    private int maxPlayers;


    public Game(SkyBattle plugin, SbWorld world) {
        new Game(plugin, world, 10);
    }

    public Game(SkyBattle plugin, SbWorld world, int maxPlayers) {
        this.plugin = plugin;
        this.gameState = GameState.WAITING;
        this.maxPlayers = maxPlayers;

        this.teams.add(new Team("blue", ChatColor.BLUE, this));
        this.teams.add(new Team("red", ChatColor.RED, this));
    }

    public void start() {
        this.gameState = GameState.STARTING;
    }


    public void end() {
        this.gameState = GameState.ENDING;

        for (Block block : this.placedBlocks) {
            block.setType(Material.AIR);
            this.placedBlocks.remove(block);
        }
        for (Team team : this.teams) {
            for (SbPlayer player : team.getPlayers()) {
                team.removePlayer(player);
            }
        }
        for (SbPlayer player : this.getWorld().getPlayers()) {
            player.setGame(null);
            this.sendToLobby(player);
        }
    }

    public void startGameTimer() {

    }

    public void startWaitingTimer() {

    }

    @Override
    public boolean join(SbPlayer player) {
        if (this.getPlayers().size() < this.maxPlayers) {

            player.setGame(this);
            this.getPlayers().add(player);
            player.setTeam(this.teams.get(0));
            this.teams.get(0).addPlayer(player);

            return true;
        } else {

            player.getPlayer().sendMessage(ChatColor.RED + "The game is full");
            this.sendToLobby(player);

        }
        return false;
    }

    @Override
    public void leave(SbPlayer player) {
        this.sendToLobby(player);
        player.getTeam().removePlayer(player);
        this.getPlayers().remove(player); //TODO : vérifier si c'est bon ou bien s'il faut modifier des choses
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setGameTimer(int gameTimer) {
        this.gameTimer = gameTimer;
    }

    public void setWaitingTimer(int waitingTimer) {
        this.waitingTimer = waitingTimer;
    }


    public GameState getGameState() {
        return this.gameState;
    }

    public List<Team> getTeams() {
        return this.teams;
    }

    public int getGameTimer() {
        return this.gameTimer;
    }

    public int getWaitingTimer() {
        return this.waitingTimer;
    }

    public SkyBattle getPlugin() {
        return this.plugin;
    }

    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    @Override
    public List<SbPlayer> getPlayers() {
        List<SbPlayer> players = new ArrayList<>();
        for (SbPlayer player : this.getWorld().getPlayers()) {
            if (player.getGame() == this) {
                players.add(player);
            }
        }
        return players;
    }

    public List<Block> getPlacedBlocks() {
        return this.placedBlocks;
    }


    public void sendToLobby(SbPlayer player) {
        for (WorldInstance worldInstance : SkyBattle.getWorldInstances()) {
            if (worldInstance instanceof Lobby) {
                worldInstance.join(player);
            }
        }
    }

}
