package fr.namu.pr.listeners;

import fr.namu.pr.MainPR;
import fr.namu.pr.PlayerPR;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageEvent implements Listener {

    private MainPR main;

    public DamageEvent(MainPR main) {
        this.main = main;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player)event.getEntity();
        PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());

        if(ppr.getKit() == null) {
            event.setCancelled(true);
        }
    }
}
