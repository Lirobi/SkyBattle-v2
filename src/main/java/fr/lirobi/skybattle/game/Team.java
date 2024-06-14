package fr.lirobi.skybattle.game;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<SbPlayer> players = new ArrayList<>();
    private ChatColor color;
    private String name;
    private Game game;
    private int maxPlayers;
    private Location spawnLocation;
    private Location crystalLocation;
    private int crystalHealth;
    private int maxCrystalHealth;


    public Team(String name, ChatColor color, Game game) {
        this.name = name;
        this.color = color;
        this.game = game;
        this.maxPlayers = game.getMaxPlayers() / 2; // Moitié du nombre de joueurs dans la partie.

        //TODO : faire les spawnLocation et crystalLocation en fonction d'un fichier config.
    }

    public void addPlayer(SbPlayer player) {
        if (this.players.size() < this.maxPlayers) {
            // Si le joueur est déjà dans une team, l'enlever de celle ci avant de l'ajouter dans la nouvelle.
            if (player.getTeam() != null) {
                player.getTeam().removePlayer(player);
                player.setTeam(null);
            }

            this.players.add(player);
            player.setTeam(this);
        }
    }

    public void removePlayer(SbPlayer player) {
        this.players.remove(player);
        player.setTeam(null);
    }

    public List<SbPlayer> getPlayers() {
        return this.players;
    }

    public ChatColor getColor() {
        return this.color;
    }

    public String getName() {
        return this.name;
    }

    public Game getGame() {
        return this.game;
    }

    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    public Location getSpawnLocation() {
        return this.spawnLocation;
    }

    public Location getCrystalLocation() {
        return this.crystalLocation;
    }

    public int getCrystalHealth() {
        return this.crystalHealth;
    }

    public int getMaxCrystalHealth() {
        return this.maxCrystalHealth;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    public void setCrystalLocation(Location crystalLocation) {
        this.crystalLocation = crystalLocation;
    }

    public void setCrystalHealth(int crystalHealth) {
        this.crystalHealth = crystalHealth;
    }

    public void setMaxCrystalHealth(int maxCrystalHealth) {
        this.maxCrystalHealth = maxCrystalHealth;
    }

}
