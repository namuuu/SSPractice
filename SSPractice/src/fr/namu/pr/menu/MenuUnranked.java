package fr.namu.pr.menu;

import fr.namu.pr.MainPR;
import fr.namu.pr.enumpr.Kit;
import fr.namu.pr.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MenuUnranked {

    private MenuPR menu;
    private MainPR main;

    public MenuUnranked(MenuPR menu) {
        this.menu = menu;
        this.main = this.menu.main;
    }

    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 6*9, "§7Lancer un combat non classé ?");

        int slot = 1;
        int line = 1;

        for(Kit kit : Kit.values()) {
            inv.setItem(slot + line * 9, new ItemUtil(kit.getMat(), 1).setName("§fRejoindre: §b" + kit.getKitName()).toItemStack());

            slot = slot + 1;
            if(slot >= 8) {
                line = line + 1;
                slot = 1;
            }
        }

        int[] SlotWhiteGlass = {
                0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,44,45,46,47,48,49,50,51,52,53 };
        for (int slotGlass : SlotWhiteGlass)
            inv.setItem(slotGlass, ItemUtil.glassPane(DyeColor.RED));

        player.openInventory(inv);
    }

    public void click(Player player, String Itemname) {
        for(Kit kit : Kit.values()) {
            if(Itemname.contains(kit.getKitName())) {
                this.main.matchmaking.unranked.addToQueue(player, kit);
                return;
            }
        }
    }
}
