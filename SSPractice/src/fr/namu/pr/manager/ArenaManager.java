package fr.namu.pr.manager;

import fr.namu.pr.MainPR;
import fr.namu.pr.arenas.ArenaSumo;
import fr.namu.pr.arenas.ArenaTvT;
import fr.namu.pr.enumpr.KitPR;
import fr.namu.pr.enumpr.MapTypePR;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {

    private List<ArenaTvT> ArenasTvT = new ArrayList<>();
    private List<ArenaSumo> ArenaSumo = new ArrayList<>();

    public ArenaManager(MainPR main) {
    }


    public List<ArenaTvT> getArenasTvT() {
        return ArenasTvT;
    }
    public void putArenasTvT(ArenaTvT arena) {
        this.ArenasTvT.add(arena);
    }

    public ArenaSumo searchArenaSumo() {
        for(ArenaSumo arena : ArenaSumo) {
            if(arena.team1.isEmpty()) {
                return arena;
            }
        }

        return null;
    }

    public ArenaTvT searchArenaTvT(MapTypePR mt) {
        for(ArenaTvT arena : ArenasTvT) {
            if(mt == arena.getMap().getMaptype() && arena.team1.isEmpty()) {
                return arena;
            }
        }

        return null;
    }
}
