package fr.namu.pr.util;

import fr.namu.pr.MainPR;
import fr.namu.pr.enumpr.Ranks;
import fr.namu.pr.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class SetupUtil {

    private MainPR main;


    public SetupUtil(MainPR main) {
        this.main = main;
    }

    public void init() {
        enableCommands();
        enableListeners();
        resetTeams();

        this.main.lobby.init();
        this.main.elo.init();

        this.main.join.resetPlayers();
    }

    private void enableListeners() {
        PluginManager pm = this.main.getServer().getPluginManager();

        pm.registerEvents(new JoinLeaveEvent(this.main), this.main);
        pm.registerEvents(new InteractEvent(this.main), this.main);
        pm.registerEvents(new ClickEvent(this.main), this.main);
        pm.registerEvents(new DamageEvent(this.main), this.main);
        pm.registerEvents(new DeathEvent(this.main), this.main);
        pm.registerEvents(new ConsumeItemEvent(this.main), this.main);
    }

    private void enableCommands() {

    }

    private void resetTeams() {
        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
        for(Team team : sb.getTeams()) {
            team.unregister();
        }

        for(Ranks rank : Ranks.values()) {
            sb.registerNewTeam(rank.getName());
            sb.getTeam(rank.getName()).setPrefix(rank.getPrefix());
        }
    }
}
