package fr.namu.pr.arenas;

import fr.namu.pr.enumpr.KitPR;
import fr.namu.pr.enumpr.MapPR;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ArenaSumo {

    private Location loc;

    private KitPR kit;

    private MapPR map;

    public List<Player> team1 = new ArrayList<>();

    private int team1Alive;

    public ArenaSumo(Location center, MapPR map) {
        this.loc = center;
        this.map = map;
    }

    public KitPR getKit() {
        return kit;
    }
    public void setKit(KitPR kit) {
        this.kit = kit;
    }

    public Location getLoc() {
        return loc;
    }

    public MapPR getMap() {
        return map;
    }

    public void reset() {
        team1.clear();
    }

    public void autoResize() {
        team1Alive = 0;

        for(Player player : team1) {
            if(!player.getGameMode().equals(GameMode.SPECTATOR)) {
                team1Alive += 1;
            }
        }
    }

    public List<Player> getPlayers() {
        List<Player> list = new ArrayList<>();
        list.addAll(team1);
        return list;
    }

    public int getTeam1Alive() {
        return team1Alive;
    }
}
