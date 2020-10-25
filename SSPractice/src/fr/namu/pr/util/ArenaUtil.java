package fr.namu.pr.util;

import fr.namu.pr.MainPR;
import fr.namu.pr.arena.Arena;
import fr.namu.pr.enumpr.FightMode;
import fr.namu.pr.enumpr.Kit;
import fr.namu.pr.enumpr.Map;
import fr.namu.pr.enumpr.MapType;
import org.bukkit.entity.Player;

import java.util.List;

public class ArenaUtil {

    private MainPR main;

    public ArenaUtil(MainPR main) {
        this.main = main;
    }

    public void startArena(List<Player> team1, List<Player> team2, Kit kit, FightMode fm, MapType mt) {
        Map map = searchMap(mt);

        this.main.matchmaking.unranked.removeQueued(team1);
        if(map == null) {
            System.out.println("Aucune arène");
            return;
        }

        Arena arena = new Arena(this.main, team1, team2, kit, fm);
        arena.map =  map;
        arena.init();
    }

    public Map searchMap(MapType mt) {
        for(Map map : Map.values()) {
            Boolean correct = true;

            // CHECK IF THE MAP IS ALREADY TAKEN
            for(Arena arena : this.main.arenas) {
                if(arena.map == map) {
                    correct = false;
                }
            }

            // CHECK FOR GOOD MAP TYPE
            if(mt != map.getMapType()) {
                correct = false;
            }

            if(correct == true) {
                System.out.println(map.name());
                return map;
            }
        }
        return null;
    }

    public String convertArenaTimer(int timer) {
        String value;
        if (timer % 60 > 9) {
            value = String.valueOf(timer % 60) + "s";
        } else {
            value = "0" + (timer % 60) + "s";
        }
        if (timer / 3600 > 0) {
            if (timer % 3600 / 60 > 9) {
                value = String.valueOf(timer / 3600) + "h" + (timer % 3600 / 60) + "m" + value;
            } else {
                value = String.valueOf(timer / 3600) + "h0" + (timer % 3600 / 60) + "m" + value;
            }
        } else if (timer / 60 > 0) {
            value = String.valueOf(timer / 60) + "m" + value;
        }
        return value;
    }
}
