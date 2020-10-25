package fr.namu.pr.util;

import fr.namu.pr.MainPR;
import fr.namu.pr.party.Party;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PartyUtil {

    private MainPR main;

    private List<Party> partyList = new ArrayList<>();

    public PartyUtil(MainPR main) {
        this.main = main;
    }

    public void createParty(Player player) {
        Party party = new Party(this.main, player);
        partyList.add(party);

        party.init();
    }
}
