package fr.namu.pr.menu;

import fr.namu.pr.MainPR;
import fr.namu.pr.enumpr.KitPR;
import fr.namu.pr.manager.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MatchMakingMenu {


    public MatchMakingMenu(MainPR main) {
    }

    public void openUnranked(Player player) {
        Inventory inv = Bukkit.createInventory(null, 6*9, "§7Lancer un combat non classé ?");

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
