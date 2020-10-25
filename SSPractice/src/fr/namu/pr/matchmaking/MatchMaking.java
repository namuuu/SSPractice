package fr.namu.pr.matchmaking;

import fr.namu.pr.MainPR;

public class MatchMaking {

    public MainPR main;

    public MMUnranked unranked = new MMUnranked(this);

    public MatchMaking(MainPR main) {
        this.main = main;
    }
}
