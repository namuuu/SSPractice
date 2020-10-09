package fr.namu.pr;

import fr.namu.pr.arenas.ArenaTvT;
import fr.namu.pr.enumpr.KitPR;
import fr.namu.pr.enumpr.StatePR;
import fr.namu.pr.party.PartyPR;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerPR {




    private KitPR kit;
    private ArenaTvT arena;

    private StatePR state;

    private List<Player> opponents = new ArrayList<>();

    private PartyPR party = null;

    public PlayerPR() {

    }

    public KitPR getKit() {
        return kit;
    }
    public void setKit(KitPR kit) {
        this.kit = kit;
    }

    public StatePR getState() {
        return state;
    }
    public void setState(StatePR state) {
        this.state = state;
    }

    public ArenaTvT getArena() {
        return arena;
    }
    public void setArena(ArenaTvT arena) {
        this.arena = arena;
    }

    public PartyPR getParty() {
        return party;
    }
    public void setParty(PartyPR party) {
        this.party = party;
    }

    public List<Player> getOpponents() {
        return opponents;
    }
    public void setOpponents(List<Player> opponents) {
        this.opponents = opponents;
    }
}
