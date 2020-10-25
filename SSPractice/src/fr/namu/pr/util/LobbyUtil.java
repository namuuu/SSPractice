package fr.namu.pr.util;

import fr.namu.pr.MainPR;
import fr.namu.pr.PlayerPR;
import fr.namu.pr.enumpr.State;
import fr.namu.pr.runnable.LobbyRunnable;
import fr.namu.pr.scoreboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class LobbyUtil {

    private MainPR main;

    private Location spawnLoc;

    private List<Player> LobbyPlayers = new ArrayList<>();

    public LobbyUtil(MainPR main) {
        this.main = main;
    }

    public void init() {
        this.spawnLoc = new Location(Bukkit.getWorld("world"), 0.5, 22, 0.5, 0, 0);

        LobbyRunnable startGame = new LobbyRunnable(this.main);
        startGame.runTaskTimer(this.main, 0L, 20L);
    }

    public void sendPlayerToLobby(Player player) {
        player.teleport(spawnLoc);

        PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());
        ppr.setState(State.LOBBY);

        if(!LobbyPlayers.contains(player))
            LobbyPlayers.add(player);

        givePlayerLobbyStuff(player);
    }

    public Boolean isAtLobby(Player player) {
        return LobbyPlayers.contains(player);
    }
    public List<Player> getLobbyPlayers() {
        return this.LobbyPlayers;
    }
    public void removeFromLobbyList(Player player) {
        if(LobbyPlayers.contains(player)) {
            LobbyPlayers.remove(player);
        }
    }

    public void givePlayerLobbyStuff(Player player) {
        Bukkit.getScheduler().runTaskLater(this.main, () -> player.setGameMode(GameMode.ADVENTURE), 3L);
        PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());

        ppr.setState(State.LOBBY);
        ppr.setKit(null);

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setHealth(player.getMaxHealth());
        for(PotionEffect pe : player.getActivePotionEffects()) {
            player.removePotionEffect(pe.getType());
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 0));

        ppr.setState(State.LOBBY);

        Inventory inv = player.getInventory();

        if(ppr.getParty() == null) {
            inv.setItem(2,  new ItemUtil(Material.IRON_SWORD, 1).setName("§eUnranked").toItemStack());
            inv.setItem(3, new ItemUtil(Material.GOLD_SWORD, 1).setName("§eRanked").toItemStack());

            inv.setItem(5, new ItemUtil(Material.LEASH, 1).setName("§eCréer une party").toItemStack());
        } else {
            if(ppr.getParty().getCaptain().equals(player)) {
                inv.setItem(2,  new ItemUtil(Material.SIGN, 1).setName("§eLancer un combat").toItemStack());
            }
        }
    }

    public void updateLobbyBoard() {
        for(Player player : LobbyPlayers) {
            FastBoard board = this.main.boards.get(player.getUniqueId());
            PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());

            String[] score = {
                    "§7§m----------------------",
                    "Joueurs: §9" + Bukkit.getOnlinePlayers().size(),
                    " ",
                    "Votre elo: §9" + this.main.elo.getElo(player),
                    " ",
                    "§7SeaStory",
                    "§7§m----------------------"
            };




            for (int i = 0; i < score.length; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(score[i]);
                if (sb.length() > 30)
                    sb.delete(29, sb.length() - 1);
                score[i] = sb.toString();
            }

            board.updateTitle("§7• §9SEASTORY §7•");
            board.updateLines(score);
        }
    }
}
