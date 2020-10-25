package fr.namu.pr.runnable;

import fr.namu.pr.MainPR;
import fr.namu.pr.arena.Arena;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SumoRunnable extends BukkitRunnable {

    private MainPR main;
    private Arena arena;

    public SumoRunnable(MainPR main, Arena arena) {
        this.main = main;
        this.arena = arena;
    }

    @Override
    public void run() {
        for(Player player : arena.players) {
            if(!player.getGameMode().equals(GameMode.SPECTATOR)) {
                if(player.getLocation().getBlock().getType().equals(Material.WATER) ||
                        player.getLocation().getBlock().getType().equals(Material.LAVA) ||
                        player.getLocation().getBlock().getType().equals(Material.STATIONARY_WATER) ||
                        player.getLocation().getBlock().getType().equals(Material.STATIONARY_LAVA)) {


                    player.setGameMode(GameMode.SPECTATOR);
                    arena.checkEnd();
                }
            }
        }

        if(arena.players.isEmpty()) {
            cancel();
        }
    }
}
