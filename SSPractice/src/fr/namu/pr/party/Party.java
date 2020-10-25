package fr.namu.pr.party;

import fr.namu.pr.MainPR;
import fr.namu.pr.PlayerPR;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Party {

    private MainPR main;

    private Player captain;
    private List<Player> members = new ArrayList<>();
    private List<Player> invited = new ArrayList<>();



    public Party(MainPR main, Player captain) {
        this.main = main;
        this.captain = captain;
        this.members.add(captain);
    }

    public void init() {
        PlayerPR ppr = this.main.playerpr.get(captain.getUniqueId());
        ppr.setParty(this);
    }

    public Player getCaptain() {
        return captain;
    }
    public void setCaptain(Player captain) {
        this.captain = captain;
    }
}
