package fr.namu.pr.manager;

import fr.namu.pr.MainPR;
import fr.namu.pr.PlayerPR;
import fr.namu.pr.enumpr.KitPR;
import fr.namu.pr.enumpr.StatePR;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class StuffManager {

    private MainPR main;

    public StuffManager(MainPR main) {
        this.main = main;
    }

    public void giveLobbyStuff(Player player) {
        Bukkit.getScheduler().runTaskLater(this.main, () -> player.setGameMode(GameMode.ADVENTURE), 3L);
        PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setHealth(player.getMaxHealth());
        for(PotionEffect pe : player.getActivePotionEffects()) {
            player.removePotionEffect(pe.getType());
        }

        ppr.setState(StatePR.LOBBY);

        Inventory inv = player.getInventory();

        if(ppr.getParty() == null) {
            inv.setItem(2,  new ItemBuilder(Material.IRON_SWORD, 1).setName("§eUnranked").toItemStack());
            inv.setItem(3, new ItemBuilder(Material.GOLD_SWORD, 1).setName("§eRanked").toItemStack());

            inv.setItem(5, new ItemBuilder(Material.LEASH, 1).setName("§eCréer une party").toItemStack());
        } else {
            if(ppr.getParty().getCaptain().equals(player)) {
                inv.setItem(2,  new ItemBuilder(Material.SIGN, 1).setName("§eLancer un combat").toItemStack());
            }
        }



        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 0));
    }






    public void giveKitStuff(Player player, KitPR kit) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        Inventory inv = player.getInventory();

        for(PotionEffect pe : player.getActivePotionEffects()) {
            player.removePotionEffect(pe.getType());
        }
        Bukkit.getScheduler().runTaskLater(this.main, () -> player.setGameMode(GameMode.SURVIVAL), 3L);

        switch (kit) {
            case BUILD_UHC:
                inv.addItem(new ItemBuilder(Material.DIAMOND_SWORD, 1).addEnchant(Enchantment.DAMAGE_ALL, 2).toItemStack());
                inv.addItem(new ItemBuilder(Material.FISHING_ROD, 1).toItemStack());
                inv.addItem(new ItemBuilder(Material.WATER_BUCKET, 1).toItemStack());
                inv.addItem(new ItemBuilder(Material.LAVA_BUCKET, 1).toItemStack());
                inv.addItem(new ItemBuilder(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 2).toItemStack());
                inv.addItem(new ItemBuilder(Material.GOLDEN_APPLE, 6).toItemStack());
                inv.addItem(new ItemBuilder(Material.GOLDEN_APPLE, 3).setName("§bGoldean Head").toItemStack());
                inv.addItem(new ItemBuilder(Material.LAVA_BUCKET, 1).toItemStack());
                inv.addItem(new ItemBuilder(Material.WOOD, 64).toItemStack());
                inv.addItem(new ItemBuilder(Material.ARROW, 32).toItemStack());
                inv.addItem(new ItemBuilder(Material.COBBLESTONE, 64).toItemStack());

                player.getInventory().setHelmet(new ItemBuilder(Material.DIAMOND_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
                player.getInventory().setChestplate(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
                player.getInventory().setLeggings(new ItemBuilder(Material.DIAMOND_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
                player.getInventory().setBoots(new ItemBuilder(Material.DIAMOND_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());

                return;
        }
    }
}
