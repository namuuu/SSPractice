package fr.namu.pr.listeners;

import fr.namu.pr.MainPR;
import fr.namu.pr.PlayerPR;

import fr.namu.pr.arena.Arena;
import org.bukkit.GameMode;
import org.bukkit.Material;
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
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());
        event.setDeathMessage(null);

        player.setHealth(20.0);

        if(ppr.getArena() == null) {
            return;
        }

        for(ItemStack item : player.getInventory().getContents()) {
            if(item != null && item.getType() != Material.AIR) {
                player.getWorld().dropItemNaturally(player.getLocation(), item);
                
            }
        }
        for(ItemStack item : player.getInventory().getArmorContents()) {
            if(item != null && item.getType() != Material.AIR) {
                player.getWorld().dropItemNaturally(player.getLocation(), item);
            }
        }

        player.setGameMode(GameMode.SPECTATOR);

        Arena arena = ppr.getArena();

        arena.checkEnd();
    }

}
