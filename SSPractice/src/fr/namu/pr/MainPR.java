package fr.namu.pr;

import fr.namu.pr.arena.Arena;
import fr.namu.pr.listeners.JoinLeaveEvent;
import fr.namu.pr.matchmaking.MatchMaking;
import fr.namu.pr.menu.MenuPR;
import fr.namu.pr.scoreboard.FastBoard;
import fr.namu.pr.util.*;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MainPR extends JavaPlugin {

    public HashMap<UUID, PlayerPR> playerpr = new HashMap<>();
    public HashMap<UUID, FastBoard> boards = new HashMap<>();
    public List<Arena> arenas = new ArrayList<>();

    public final SetupUtil setup = new SetupUtil(this);
    public final LobbyUtil lobby = new LobbyUtil(this);
    public final JoinLeaveEvent join = new JoinLeaveEvent(this);
    public final ArenaUtil arena = new ArenaUtil(this);
    public final KitUtil kit = new KitUtil(this);
    public final EloUtil elo = new EloUtil(this);
    public final PartyUtil party = new PartyUtil(this);

    public MenuPR menu = new MenuPR(this);
    public MatchMaking matchmaking = new MatchMaking(this);

    public static String prefix = "§9SeaStory §7» ";

    @EventHandler
    public void onEnable() {

        setup.init();

    }
}
