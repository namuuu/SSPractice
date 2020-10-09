package fr.namu.pr;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class InfoPR {

    private List<Player> lobbyPlayers = new ArrayList<>();

    private Location lobby;

    public static final String prefix = "§9SeaStory §7» ";

    public InfoPR(MainPR main) {
        this.lobby = new Location(Bukkit.getWorld("world"), 0.5, 22, 0.5);
    }


    public List<Player> getLobbyPlayers() {
        return lobbyPlayers;
    }
    public void setLobbyPlayer(Player player) {
        lobbyPlayers.add(player);
    }

    public Location getLobby() {
        return lobby;
    }
}
