package fr.namu.pr.listeners;

import fr.namu.pr.MainPR;
import fr.namu.pr.PlayerPR;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ConsumeItemEvent implements Listener {

    private final MainPR main;

    public ConsumeItemEvent(MainPR main) {
        this.main = main;
    }

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());

        ItemStack item = event.getItem();

        if(!item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) {
            return;
        }

        switch (item.getItemMeta().getDisplayName()) {
            case "§bGoldean Head":
                player.removePotionEffect(PotionEffectType.REGENERATION);
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10*20, 1));
                break;
        }
    }
}
