package fr.namu.pr.scoreboard;

import fr.namu.pr.MainPR;
import fr.namu.pr.PlayerPR;
import fr.namu.pr.enumpr.StatePR;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ScoreboardPR {

    private MainPR main;

    private String title = "§7• §9SEASTORY §7•";

    public ScoreboardPR(MainPR main) {
        this.main = main;
    }

    public void updateBoard() {
        for(FastBoard board : this.main.boards.values()) {
            PlayerPR ppr = this.main.playerpr.get(board.getPlayer().getUniqueId());

            if(ppr.getState().equals(StatePR.LOBBY)) {
                lobbyBoard(board);
            } else if (ppr.getState().equals(StatePR.FIGHT)) {
                fightBoard(board);
            }
        }
    }

    private void lobbyBoard(FastBoard board) {
        Player player = board.getPlayer();
        PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());

        String[] score = {
                "§7§m----------------------",
                "Joueurs: §9" + Bukkit.getOnlinePlayers().size(),
                " ",
                "§7SeaStory",
                "§7§m----------------------"
        };

        if(ppr.getParty() != null) {
            score = new String[] {
                    "§7§m----------------------",
                    "Party Captain:",
                    "   §9" + ppr.getParty().getCaptain().getName(),
                    " ",
                    "Nombre de membres:",
                    "   §9" + ppr.getParty().getMembers().size(),
                    " ",
                    "§7SeaStory",
                    "§7§m----------------------"
            };
        }




        for (int i = 0; i < score.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(score[i]);
            if (sb.length() > 30)
                sb.delete(29, sb.length() - 1);
            score[i] = sb.toString();
        }

        board.updateTitle(title);
        board.updateLines(score);
    }

    private void fightBoard(FastBoard board) {
        Player player = board.getPlayer();
        PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());

        String[] score = {
                "§7§m----------------------",
                " ",
                " ",
                " ",
                "§7SeaStory",
                "§7§m----------------------"
        };

        if (ppr.getOpponents() != null) {

            if(ppr.getArena().team1.contains(player)) {
                score[1] = "Alliés: §9" + ppr.getArena().team1.size();
                score[2] = "Adversaires: §9" + ppr.getArena().team2.size();
                if(ppr.getArena().team2.size() == 1) {
                    score[1] = "Adversaire: §9" + ppr.getOpponents().get(0).getName();
                    score[2] = "Son ping: §9" + ((CraftPlayer) ppr.getOpponents().get(0)).getHandle().ping;
                }

            } else {

                score[1] = "Alliés: §9" + ppr.getArena().team2.size();
                score[2] = "Adversaires: §9" + ppr.getArena().team1.size();
                if(ppr.getArena().team1.size() == 1) {
                    score[1] = "Adversaire: §9" + ppr.getOpponents().get(0).getName();
                    score[2] = "Son ping: §9" + ((CraftPlayer) ppr.getOpponents().get(0)).getHandle().ping;
                }
            }
        }



        for (int i = 0; i < score.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(score[i]);
            if (sb.length() > 30)
                sb.delete(29, sb.length() - 1);
            score[i] = sb.toString();
        }

        board.updateTitle(title);
        board.updateLines(score);
    }
}
