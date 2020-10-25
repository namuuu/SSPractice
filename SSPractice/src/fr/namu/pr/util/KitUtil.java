package fr.namu.pr.util;

import fr.namu.pr.MainPR;
import fr.namu.pr.enumpr.Kit;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class KitUtil {

    private MainPR main;

    public KitUtil(MainPR main) {
        this.main = main;
    }


    public void setKit(Player player, Kit kit) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        Inventory inv = player.getInventory();


        for(PotionEffect pe : player.getActivePotionEffects()) {
            player.removePotionEffect(pe.getType());
        }



        Bukkit.getScheduler().runTaskLater(this.main, () -> player.setGameMode(GameMode.SURVIVAL), 3L);

        switch (kit) {
            case BUILD_UHC:
                inv.addItem(new ItemUtil(Material.DIAMOND_SWORD, 1).addEnchant(Enchantment.DAMAGE_ALL, 2).toItemStack());
                inv.addItem(new ItemUtil(Material.FISHING_ROD, 1).toItemStack());
                inv.addItem(new ItemUtil(Material.WATER_BUCKET, 1).toItemStack());
                inv.addItem(new ItemUtil(Material.LAVA_BUCKET, 1).toItemStack());
                inv.addItem(new ItemUtil(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 2).toItemStack());
                inv.addItem(new ItemUtil(Material.GOLDEN_APPLE, 6).toItemStack());
                inv.addItem(new ItemUtil(Material.GOLDEN_APPLE, 3).setName("§bGoldean Head").toItemStack());
                inv.addItem(new ItemUtil(Material.LAVA_BUCKET, 1).toItemStack());
                inv.addItem(new ItemUtil(Material.WOOD, 64).toItemStack());
                inv.addItem(new ItemUtil(Material.ARROW, 32).toItemStack());
                inv.addItem(new ItemUtil(Material.COBBLESTONE, 64).toItemStack());

                player.getInventory().setHelmet(new ItemUtil(Material.DIAMOND_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
                player.getInventory().setChestplate(new ItemUtil(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
                player.getInventory().setLeggings(new ItemUtil(Material.DIAMOND_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
                player.getInventory().setBoots(new ItemUtil(Material.DIAMOND_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
                return;
            case SUMO:
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 255));
                return;
        }
    }
}
