package fr.namu.pr.runnable;

import fr.namu.pr.MainPR;
import org.bukkit.scheduler.BukkitRunnable;

public class PluginRun extends BukkitRunnable {

    private MainPR main;

    public PluginRun(MainPR main) {
        this.main = main;
    }

    public void run() {
        this.main.score.updateBoard();
    }
}
