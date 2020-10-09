package fr.namu.pr.party;

import fr.namu.pr.enumpr.StatePR;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PartyPR {



    private Player captain;
    private List<Player> members = new ArrayList<>();

    private List<Player> invited = new ArrayList<>();

    private StatePR state;

    public PartyPR(Player captain) {
        this.captain = captain;
        this.members.add(captain);
    }

    public Player getCaptain() {
        return captain;
    }
    public void setCaptain(Player captain) {
        this.captain = captain;
    }

    public StatePR getState() {
        return state;
    }
    public void setState(StatePR state) {
        this.state = state;
    }

    public void addMember(Player player) {this.members.add(player);}
    public void setMembers(List<Player> members) {
        this.members = members;
    }
    public List<Player> getMembers() {return this.members;}

    public List<Player> getInvited() {
        return invited;
    }
    public void addInvited(Player invited) {
        this.invited.add(invited);
    }
    public void removeInvited(Player invited) {this.invited.remove(invited);}
}
