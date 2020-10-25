package fr.namu.pr.matchmaking;

import fr.namu.pr.MainPR;
import fr.namu.pr.PlayerPR;
import fr.namu.pr.enumpr.FightMode;
import fr.namu.pr.enumpr.Kit;
import fr.namu.pr.enumpr.State;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MMUnranked {

    private MatchMaking matchmaking;

    private List<Player> queued = new ArrayList<>();

    public MMUnranked(MatchMaking matchmaking) {
        this.matchmaking = matchmaking;
    }


    public void addToQueue(Player player, Kit kit) {
        PlayerPR ppr = this.matchmaking.main.playerpr.get(player.getUniqueId());

        if(!ppr.getState().equals(State.LOBBY)) {
            player.sendMessage("§cErreur, tu ne te trouves pas au Lobby !");
            return;
        }

        ppr.setQueuedKit(kit);

        player.sendMessage(matchmaking.main.prefix + "§aVous avez lancé une partie d'Unranked avec le Kit §e" + ppr.getQueuedKit().getKitName() + " §a!");
        player.closeInventory();

        if(!queued.contains(player)) {
            queued.add(player);
        }

        queueUnranked();
    }

    public void queueUnranked() {

        for(Player player1 : queued) {
            PlayerPR ppr1 = this.matchmaking.main.playerpr.get(player1.getUniqueId());

            for(Player player2 : queued) {
                PlayerPR ppr2 = this.matchmaking.main.playerpr.get(player2.getUniqueId());

                if(player1 != player2 && ppr1.getQueuedKit().equals(ppr2.getQueuedKit()) && queued.contains(player1) && queued.contains(player2)) {
                    List<Player> players = new ArrayList<>();
                    players.add(player1);
                    players.add(player2);
                    this.matchmaking.main.arena.startArena(players, new ArrayList<>(), ppr1.getQueuedKit(), FightMode.UNRANKED, ppr1.getQueuedKit().getMt());
                    break;
                }
            }
            if(!queued.contains(player1)) {
                break;
            }
        }
    }

    public void removeQueued(List<Player> players) {
        for(Player player : players) {
            if(queued.contains(player)) {
                queued.remove(player);
            }
        }
    }
}
