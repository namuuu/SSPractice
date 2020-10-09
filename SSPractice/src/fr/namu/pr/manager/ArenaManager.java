package fr.namu.pr.manager;

import fr.namu.pr.MainPR;
import fr.namu.pr.arenas.ArenaTvT;
import fr.namu.pr.enumpr.KitPR;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {

    private List<ArenaTvT> ArenasTvT = new ArrayList<>();

    public ArenaManager(MainPR main) {
    }


    public List<ArenaTvT> getArenasTvT() {
        return ArenasTvT;
    }
    public void putArenasTvT(ArenaTvT arena) {
        this.ArenasTvT.add(arena);
    }


    public ArenaTvT searchArenaTvT() {
        for(ArenaTvT arena : ArenasTvT) {
            if(arena.team1.isEmpty()) {
                return arena;
            }
        }

        return null;
    }
}
