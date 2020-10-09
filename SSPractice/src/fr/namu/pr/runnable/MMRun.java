package fr.namu.pr.runnable;

import fr.namu.pr.MainPR;
import org.bukkit.scheduler.BukkitRunnable;

public class MMRun extends BukkitRunnable {

    private MainPR main;

    public MMRun(MainPR main) {
        this.main = main;
    }

    public void run() {
        this.main.matchmaking.queueUnranked();
    }
}
