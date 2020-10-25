package fr.namu.pr;

import fr.namu.pr.arena.Arena;
import fr.namu.pr.enumpr.Kit;
import fr.namu.pr.enumpr.Ranks;
import fr.namu.pr.enumpr.State;
import fr.namu.pr.party.Party;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerPR {



    private Kit queuedKit;
    private Kit kit;

    private State state;

    private Arena arena;

    private Ranks rank;
    private Party party;

    private List<Player> opponents = new ArrayList<>();

    public PlayerPR() {
        this.queuedKit = null;
        this.kit = null;
        this.state = State.LOBBY;
    }

    public Kit getKit() {
        return kit;
    }
    public void setKit(Kit kit) {
        this.kit = kit;
    }

    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }


    public List<Player> getOpponents() {
        return opponents;
    }
    public void setOpponents(List<Player> opponents) {
        this.opponents = opponents;
    }

    public Kit getQueuedKit() {
        return queuedKit;
    }
    public void setQueuedKit(Kit queuedKit) {
        this.queuedKit = queuedKit;
    }

    public Arena getArena() {
        return arena;
    }
    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public Ranks getRank() {
        return rank;
    }
    public void setRank(Ranks rank) {
        this.rank = rank;
    }

    public Party getParty() {
        return party;
    }
    public void setParty(Party party) {
        this.party = party;
    }
}

