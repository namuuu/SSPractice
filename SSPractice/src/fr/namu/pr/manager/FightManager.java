package fr.namu.pr.manager;

import fr.namu.pr.MainPR;
import fr.namu.pr.PlayerPR;
import fr.namu.pr.arenas.ArenaSumo;
import fr.namu.pr.arenas.ArenaTvT;
import fr.namu.pr.enumpr.KitPR;
import fr.namu.pr.enumpr.MapPR;
import fr.namu.pr.enumpr.MapTypePR;
import fr.namu.pr.enumpr.StatePR;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;


public class FightManager {

    private MainPR main;

    public FightManager(MainPR main) {
        this.main = main;
    }

    public void startTvT(List<Player> players, KitPR kit) {
        ArenaTvT arena = this.main.arena.searchArenaTvT(MapTypePR.CLASSIC);

        if(arena == null) {
            return;
        }

        MapPR map = arena.getMap();

        List<Player> players1 = new ArrayList<>();
        List<Player> players2 = new ArrayList<>();
        Integer ind = 1;

        for(Player player : players) {
            if(ind.equals(1)) {
                players1.add(player);
                ind = 2;
            } else {
                players2.add(player);
                ind = 1;
            }
        }

        for(Player player : players1) {
            PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());
            ppr.setOpponents(players2);
            ppr.setState(StatePR.FIGHT);
            ppr.setArena(arena);
            arena.team1 = players1;
            player.teleport(new Location(arena.getLoc().getWorld(), arena.getLoc().getBlockX() + map.getX1(), arena.getLoc().getBlockY() + map.getY1(), arena.getLoc().getBlockZ() + map.getZ1()));
            this.main.stuff.giveKitStuff(player, kit);
        }

        for(Player player : players2) {
            PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());
            ppr.setOpponents(players1);
            ppr.setState(StatePR.FIGHT);
            ppr.setArena(arena);
            arena.team2 = players2;
            player.teleport(new Location(arena.getLoc().getWorld(), arena.getLoc().getBlockX() + map.getX2(), arena.getLoc().getBlockY() + map.getY2(), arena.getLoc().getBlockZ() + map.getZ2()));
            this.main.stuff.giveKitStuff(player, kit);
        }

        arena.autoResize();
    }

    public void startSumo(List<Player> players, KitPR kit) {
        ArenaSumo arena = this.main.arena.searchArenaSumo();

        if(arena == null) {
            return;
        }

        MapPR map = arena.getMap();
        Integer indTP = 0;

        for(Player player : players) {
            PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());
            ppr.setOpponents(players);
            ppr.getOpponents().remove(player);
            ppr.setState(StatePR.FIGHT);
            ppr.setArenaSumo(arena);
            arena.team1 = players;

            double a = indTP * 2.0D * Math.PI / Bukkit.getOnlinePlayers().size();
            int x = (int) Math.round(Math.cos(a) + arena.getMap().getX1());
            int z = (int) Math.round(Math.sin(a) + arena.getMap().getX1());

            Location loc = new Location(arena.getLoc().getWorld(), x, arena.getLoc().getY(), z);
        }
    }

    public void endBattleTvT(ArenaTvT arena) {
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
