package fr.namu.pr.runnable;

import fr.namu.pr.MainPR;
import fr.namu.pr.util.LobbyUtil;
import org.bukkit.scheduler.BukkitRunnable;

public class LobbyRunnable extends BukkitRunnable {

    private MainPR main;
    private LobbyUtil lobby;

    public LobbyRunnable(MainPR main) {
        this.main = main;
        this.lobby = main.lobby;
    }

    @Override
    public void run() {
        lobby.updateLobbyBoard();
    }
}
