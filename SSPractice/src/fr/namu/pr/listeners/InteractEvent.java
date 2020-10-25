package fr.namu.pr.listeners;

import fr.namu.pr.MainPR;
import fr.namu.pr.PlayerPR;
import fr.namu.pr.enumpr.State;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class  InteractEvent implements Listener {

    private MainPR main;

    public InteractEvent(MainPR main) {
        this.main = main;
    }


    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(player.getItemInHand() == null || !player.getItemInHand().hasItemMeta() || !player.getItemInHand().getItemMeta().hasDisplayName()) {
            return;
        }

        ItemStack item = player.getItemInHand();
        String itemname = item.getItemMeta().getDisplayName();

        switch (itemname) {
            case "§eUnranked":
                event.setCancelled(true);
                this.main.menu.unranked.open(player);

        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        PlayerPR ppr = this.main.playerpr.get(event.getPlayer());
        if(!ppr.getState().equals(State.FIGHT)) {
            event.setCancelled(true);
        }
    }
}