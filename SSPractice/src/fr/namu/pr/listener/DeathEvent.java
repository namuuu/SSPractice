package fr.namu.pr.listener;

import fr.namu.pr.MainPR;
import fr.namu.pr.PlayerPR;
import fr.namu.pr.arenas.ArenaTvT;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DeathEvent implements Listener {

    private MainPR main;

    public DeathEvent(MainPR main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);
        Player player = event.getEntity();
        PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());

        player.setHealth(player.getMaxHealth());
        player.setGameMode(GameMode.SPECTATOR);

        ArenaTvT arena = ppr.getArena();

        arena.autoResize();

        if(arena.team1.contains(player)) {
            if(arena.getTeam1Alive() > 0) {
                World world = player.getWorld();
                for(ItemStack item : player.getInventory().getContents()) {
                    if(item != null && item.getType() != Material.AIR) {
                        world.dropItemNaturally(player.getLocation(), item);
                    }
                }
                for(ItemStack item : player.getInventory().getArmorContents()) {
                    if(item != null && item.getType() != Material.AIR) {
                        world.dropItemNaturally(player.getLocation(), item);
                    }
                }
                return;
            }
            if(arena.getTeam1Alive() == 0) {
                for(Player teamates : arena.getPlayers()) {
                    teamates.sendMessage("§7§m----------------------");
                    teamates.sendMessage(" ");
                    if(arena.team2.contains(teamates)) {
                        teamates.sendMessage("§9Victoire !");
                    } else {
                        teamates.sendMessage("§9Défaite...");
                    }
                    String win = "§7Victoire de ";
                    for(Player winners : arena.team2) {
                        win += winners.getName() + " (" + winners.getHealth() + "), ";
                    }
                    teamates.sendMessage(win);
                    teamates.sendMessage(" ");
                    teamates.sendMessage("§7§m----------------------");
                }
                this.main.fight.endBattle(arena);
            }
        }
        if(arena.team2.contains(player)) {
            if(arena.getTeam2Alive() > 0) {
                World world = player.getWorld();
                for(ItemStack item : player.getInventory().getContents()) {
                    if(item != null && item.getType() != Material.AIR) {
                        world.dropItemNaturally(player.getLocation(), item);
                    }
                }
                for(ItemStack item : player.getInventory().getArmorContents()) {
                    if(item != null && item.getType() != Material.AIR) {
                        world.dropItemNaturally(player.getLocation(), item);
                    }
                }
                return;
            }
            if(arena.getTeam2Alive() == 0) {
                for(Player teamates : arena.getPlayers()) {
                    teamates.sendMessage("§7§m----------------------");
                    teamates.sendMessage(" ");
                    if(arena.team1.contains(teamates)) {
                        teamates.sendMessage("§9Victoire !");
                    } else {
                        teamates.sendMessage("§9Défaite...");
                    }
                    String win = "§7Victoire de ";
                    for(Player winners : arena.team1) {
                        win += winners.getName() + " (" + winners.getHealth() + "), ";
                    }
                    teamates.sendMessage(win);
                    teamates.sendMessage(" ");
                    teamates.sendMessage("§7§m----------------------");
                }
                this.main.fight.endBattle(arena);
            }
        }
    }
}
