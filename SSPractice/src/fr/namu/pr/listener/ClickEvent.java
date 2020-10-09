package fr.namu.pr.listener;

import fr.namu.pr.MainPR;
import fr.namu.pr.PlayerPR;
import fr.namu.pr.enumpr.KitPR;
import fr.namu.pr.enumpr.StatePR;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ClickEvent implements Listener {

    private MainPR main;

    public ClickEvent(MainPR main) {
        this.main = main;
    }


    @EventHandler
    public void onClick(InventoryClickEvent event) {

        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());
        ItemStack current = event.getCurrentItem();

        if(!ppr.getState().equals(StatePR.FIGHT)) {
            event.setCancelled(true);
        }
        if (current == null || !current.hasItemMeta() || !current.getItemMeta().hasDisplayName()) {
            return;
        }

        String invname = inv.getName();
        Material mat = current.getType();
        String currentName = current.getItemMeta().getDisplayName();
        ClickType click = event.getClick();

        switch (invname) {
            case "§7Lancer un combat non classé ?":
                event.setCancelled(true);
                for(KitPR kit : KitPR.values()) {
                    if(currentName.contains(kit.getKitName())) {
                        this.main.matchmaking.searchUnranked(player, kit);
                    }
                }
                return;
            case "§7Menu de la party !":
                event.setCancelled(true);
                if(currentName.equalsIgnoreCase("§eSplit")) {
                    this.main.partymenu.openPartySplit(player);
                }
                return;
            case "§7Lancer un combat en Split ?":
                event.setCancelled(true);
                for(KitPR kit : KitPR.values()) {
                    if(currentName.contains(kit.getKitName())) {
                        this.main.matchmaking.startPartySplit(ppr.getParty().getMembers(), kit);
                    }
                }
        }
    }
}
