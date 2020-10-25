package fr.namu.pr.menu;

import fr.namu.pr.MainPR;

public class MenuPR {

    public MainPR main;

    public MenuUnranked unranked;

    public MenuPR(MainPR main) {
        this.main = main;
        unranked = new MenuUnranked(this);
    }
}
