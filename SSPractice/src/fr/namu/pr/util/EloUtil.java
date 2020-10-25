package fr.namu.pr.util;

import fr.namu.pr.MainPR;
import fr.namu.pr.PlayerPR;
import fr.namu.pr.enumpr.Ranks;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class EloUtil {

    private MainPR main;

    private File file;
    private FileConfiguration config;

    public EloUtil(MainPR main) {
        this.main = main;
    }

    public void init() {
        if(!this.main.getDataFolder().exists()) {
            this.main.getDataFolder().mkdir();
        }

        File file = new File(this.main.getDataFolder(),  "elo.yml");

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.file = file;
        this.config = (FileConfiguration) YamlConfiguration.loadConfiguration(file);
    }
    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setElo(Player player, int elo) {
        String uuid = player.getUniqueId().toString();

        if(!config.isConfigurationSection(uuid)) {
            config.createSection(uuid + ".elo");
        }

        config.set(uuid + ".elo", elo);
        save();
        updateRank(player);
    }
    public int getElo(Player player) {
        String uuid = player.getUniqueId().toString();

        if(!config.isConfigurationSection(uuid)) {
            setElo(player, 0);
        }

        return config.getInt(uuid + ".elo");
    }

    public void updateRank(Player player) {
        Integer elo = getElo(player);
        if(elo >= 30){
            setRank(player, Ranks.GOLD);
            return;
        }
        if(elo >= 0) {
            setRank(player, Ranks.DEFAULT);
            return;
        }
    }

    private void setRank(Player player, Ranks rank) {
        PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());

        player.setPlayerListName(rank.getFullName() + " " + player.getName());
        ppr.setRank(rank);

        Bukkit.getScoreboardManager().getMainScoreboard().getTeam(rank.getName()).addEntry(player.getName());
    }
}
