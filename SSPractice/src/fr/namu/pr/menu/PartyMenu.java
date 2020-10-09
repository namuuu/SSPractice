package fr.namu.pr.menu;

import fr.namu.pr.MainPR;
import fr.namu.pr.enumpr.KitPR;
import fr.namu.pr.manager.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PartyMenu {

    public PartyMenu(MainPR main) {
    }

    public void openMainMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 6*9, "§7Menu de la party !");

        inv.setItem(20, new ItemBuilder(Material.BANNER).setDyeColor(DyeColor.ORANGE).setName("§eSplit").addLoreLine("§7Combat avec 2 équipes !").toItemStack());


        int[] SlotWhiteGlass = {
                0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,44,45,46,47,48,49,50,51,52,53 };
        for (int slotGlass : SlotWhiteGlass)
            inv.setItem(slotGlass, ItemBuilder.glassPane(DyeColor.WHITE));

        player.openInventory(inv);
    }

    public void openPartySplit(Player player) {
        Inventory inv = Bukkit.createInventory(null, 6*9, "§7Lancer un combat en Split ?");

        int slot = 1;
        int line = 1;

        for(KitPR kit : KitPR.values()) {
            inv.setItem(slot + line * 9, new ItemBuilder(kit.getMat(), 1).setName(kit.getKitName()).toItemStack());

            slot = slot + 1;
            if(slot >= 8) {
                line = line + 1;
                slot = 1;
            }
        }

        int[] SlotWhiteGlass = {
                0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,44,45,46,47,48,49,50,51,52,53 };
        for (int slotGlass : SlotWhiteGlass)
            inv.setItem(slotGlass, ItemBuilder.glassPane(DyeColor.RED));

        player.openInventory(inv);
    }
}
