package fr.namu.pr.manager;

import fr.namu.pr.InfoPR;
import fr.namu.pr.MainPR;
import fr.namu.pr.PlayerPR;
import fr.namu.pr.arenas.ArenaTvT;
import fr.namu.pr.enumpr.KitPR;
import fr.namu.pr.enumpr.MapPR;
import fr.namu.pr.enumpr.MapTypePR;
import fr.namu.pr.enumpr.StatePR;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MatchMakingManager {

    private List<Player> unrankedMM = new ArrayList<>();

    private MainPR main;

    public MatchMakingManager(MainPR main) {
        this.main = main;
    }

    public List<Player> getUnrankedMM() {
        return unrankedMM;
    }
    public void addUnrankedMM(Player player) {
        this.unrankedMM.add(player);
    }
    public void remUnkrankedMM(Player player) {
        this.unrankedMM.remove(player);
    }

    public void searchUnranked(Player player, KitPR kit) {
        PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());

        ppr.setKit(kit);
        ppr.setState(StatePR.SEARCHING);

        player.sendMessage(InfoPR.prefix + "§aVous avez lancé une partie d'Unranked avec le Kit §e" + ppr.getKit().getKitName() + " §a!");
        player.closeInventory();

        if(!unrankedMM.contains(player)) {
            unrankedMM.add(player);
        }

    }

    public void queueUnranked() {
        for(Player player1 : unrankedMM) {
            PlayerPR ppr1 = this.main.playerpr.get(player1.getUniqueId());

            for(Player player2 : unrankedMM) {
                PlayerPR ppr2 = this.main.playerpr.get(player2.getUniqueId());

                if(player1 != player2 && ppr1.getKit().equals(ppr2.getKit()) && unrankedMM.contains(player1) && unrankedMM.contains(player2)) {
                    startUnranked(player1, player2, ppr1.getKit());
                    ppr1.setState(StatePR.FIGHT);
                    ppr2.setState(StatePR.FIGHT);
                    break;
                }
            }
            if(!unrankedMM.contains(player1)) {
                break;
            }
        }
    }

    public void startUnranked(Player player1, Player player2, KitPR kit) {
        unrankedMM.remove(player1);
        unrankedMM.remove(player2);

        System.out.println("[SSPractice] A match has started : " + player1.getName() + " vs " + player2.getName());

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        this.main.fight.startTvT(players, kit);
    }

    public void startPartySplit(List<Player> players, KitPR kit) {
        if(players.size() == 1) {
            players.get(0).sendMessage(InfoPR.prefix + "§eTu es seul dans la party, il te faudrait au moins un compagnon !");
            return;
        }

        //Si le Kit est SUMO
        if(kit == KitPR.SUMO) {
            this.main.fight.startSumo(players, kit);
            return;
        }

        this.main.fight.startTvT(players, kit);
    }




}
