package fr.lirobi.skybattle.game;

import fr.lirobi.skybattle.SkyBattle;
import fr.lirobi.skybattle.utils.ActionBarMessages;
import fr.lirobi.skybattle.utils.TitleMessages;
import fr.lirobi.skybattle.world.SbWorld;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SbPlayer {

    private Player player;
    private Game game;
    private SbWorld world;
    private Team team;
    private Kit kit;
    private int kills;
    private int deaths;
    private int coins;
    private SbPlayer lastDamager;
    private SbPlayer lastVictim;

    private List<Kit> kits = new ArrayList<>();

    public SbPlayer(Player player, Game game) {
        this.player = player;
        this.game = game;


        //TODO : Charger les infos depuis la base de données (stats etc...)
        this.loadKits();
    }

    public void spawn() {
        this.getPlayer().teleport(this.getTeam().getSpawnLocation());
        this.getPlayer().getInventory().clear();
        this.getPlayer().setFoodLevel(20);
        this.getPlayer().setHealth(20.0);

        if (this.getPlayer().getGameMode() != GameMode.SURVIVAL) {
            this.getPlayer().setGameMode(GameMode.SURVIVAL);
        }

        this.getKit().giveKit();
    }

    int respawnTime = 5;

    public void die() {
        this.deaths++;

        Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyBattle.getInstance(), () -> {
            if (respawnTime == 0) {
                TitleMessages.sendRepawn(this);
                this.spawn();
            } else {
                TitleMessages.sendRespawnTimer(this, respawnTime);
                respawnTime--;
            }
        }, 0, 1 * 20L);
    }

    public void kill(SbPlayer victim) {
        this.kills++;


        this.getPlayer().getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 1));
        ActionBarMessages.sendEarnCoins(this, 1);
        this.getPlayer().playSound(this.getPlayer().getLocation(), "entity.player.levelup", 10, 10);


        victim.die();
    }

    public void setWorld(SbWorld world) {
        this.world = world;
    }

    public void setKit(Kit kit) {
        this.kit = kit;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setLastDamager(SbPlayer lastDamager) {
        this.lastDamager = lastDamager;
    }

    public void setLastVictim(SbPlayer lastVictim) {
        this.lastVictim = lastVictim;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Game getGame() {
        return this.game;
    }

    public SbWorld getWorld() {
        return this.world;
    }

    public Team getTeam() {
        return this.team;
    }

    public Kit getKit() {
        return this.kit;
    }

    public int getKills() {
        return this.kills;
    }

    public int getDeaths() {
        return this.deaths;
    }

    public SbPlayer getLastDamager() {
        return this.lastDamager;
    }

    public SbPlayer getLastVictim() {
        return this.lastVictim;
    }

    public static SbPlayer getSbPlayerByBukkitPlayer(Player player) {
        for (SbWorld world : SkyBattle.getWorlds()) {
            for (SbPlayer sbPlayer : world.getPlayers()) {
                if (sbPlayer.getPlayer().equals(player)) {
                    return sbPlayer;
                }
            }
        }
        return null;
    }

    public void loadKits() {
        // TODO : Charger les noms des kits du joueur depuis la database puis les instancier puis les mettre dans this.kits
    }


}
