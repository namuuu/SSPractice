package fr.namu.pr.manager;

import fr.namu.pr.MainPR;
import fr.namu.pr.arenas.ArenaTvT;
import fr.namu.pr.enumpr.KitPR;
import fr.namu.pr.enumpr.MapPR;
import fr.namu.pr.runnable.MMRun;
import fr.namu.pr.runnable.PluginRun;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class SetupManager {

    private MainPR main;

    public SetupManager(MainPR main) {
        this.main = main;
    }

    public void setup() {


        setupArenas();

        MMRun mmRun = new MMRun(this.main);
        mmRun.runTaskTimer(this.main, 0L, 20L);
        PluginRun pluginRun = new PluginRun(this.main);
        pluginRun.runTaskTimer(this.main, 0L, 20L);
    }

    private void setupArenas() {
        ArenaManager arena = this.main.arena;

        arena.putArenasTvT(new ArenaTvT(new Location(Bukkit.getWorld("world"), 4.5, 31,  -63.5), MapPR.TEST));

    }

}
