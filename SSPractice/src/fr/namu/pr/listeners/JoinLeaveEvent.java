package fr.namu.pr.listeners;

import fr.namu.pr.MainPR;
import fr.namu.pr.PlayerPR;
import fr.namu.pr.scoreboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveEvent implements Listener {

    private MainPR main;

    public JoinLeaveEvent(MainPR main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);

        newPlayer(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);

        Player player = event.getPlayer();


    }

    private void newPlayer(Player player) {
        this.main.playerpr.put(player.getUniqueId(), new PlayerPR());
        this.main.boards.put(player.getUniqueId(), new FastBoard(player));
        PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());

        this.main.lobby.sendPlayerToLobby(player);
        this.main.elo.updateRank(player);
    }

    public void resetPlayers() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            newPlayer(player);
        }
    }
}
