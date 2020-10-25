package fr.namu.pr.runnable;

import fr.namu.pr.MainPR;
import fr.namu.pr.arena.Arena;
import org.bukkit.scheduler.BukkitRunnable;

public class ArenaRunnable extends BukkitRunnable {

    private MainPR main;
    private Arena arena;

    public ArenaRunnable(MainPR main, Arena arena) {
        this.main = main;
        this.arena = arena;
    }

    @Override
    public void run() {
        arena.timer++;
        arena.updateArenaBoard();
    }

}
