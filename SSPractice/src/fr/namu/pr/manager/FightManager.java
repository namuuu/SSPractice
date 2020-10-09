package fr.namu.pr.manager;

import fr.namu.pr.MainPR;
import fr.namu.pr.PlayerPR;
import fr.namu.pr.arenas.ArenaTvT;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;


public class FightManager {

    private MainPR main;

    public FightManager(MainPR main) {
        this.main = main;
    }

    public void endBattle(ArenaTvT arena) {
        System.out.println("The match ended !");
        for(Player player : arena.team1) {
            PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());

            ppr.setOpponents(null);
            player.teleport(this.main.info.getLobby());
            player.setVelocity(new Vector().zero());
            this.main.stuff.giveLobbyStuff(player);
        }
        for(Player player : arena.team2) {
            PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());

            ppr.setOpponents(null);
            player.teleport(this.main.info.getLobby());
            player.setVelocity(new Vector().zero());
            this.main.stuff.giveLobbyStuff(player);
        }

        arena.reset();
    }
}
