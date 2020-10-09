package fr.namu.pr;

import fr.namu.pr.command.PartyCMD;
import fr.namu.pr.listener.*;
import fr.namu.pr.manager.*;
import fr.namu.pr.menu.MatchMakingMenu;
import fr.namu.pr.menu.PartyMenu;
import fr.namu.pr.scoreboard.FastBoard;
import fr.namu.pr.scoreboard.ScoreboardPR;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class MainPR extends JavaPlugin {

    public HashMap<UUID, PlayerPR> playerpr = new HashMap<>();
    public HashMap<UUID, FastBoard> boards = new HashMap<>();

    public final ScoreboardPR score = new ScoreboardPR(this);

    public final InfoPR info = new InfoPR(this);
    public final SetupManager setup = new SetupManager(this);
    public final ArenaManager arena = new ArenaManager(this);
    public final StuffManager stuff = new StuffManager(this);
    public final FightManager fight = new FightManager(this);
    public final PartyManager party = new PartyManager(this);
    public final MatchMakingManager matchmaking = new MatchMakingManager(this);
    public final JoinLeaveEvent joinleave = new JoinLeaveEvent(this);

    public final MatchMakingMenu mmmenu = new MatchMakingMenu(this);
    public final PartyMenu partymenu = new PartyMenu(this);

    @Override
    public void onEnable() {
        setup.setup();
        joinleave.resetPlayers();

        enableCommands();
        enableListeners();
    }

    @Override
    public void onDisable() {

    }

    private void enableListeners() {
        PluginManager pm =this.getServer().getPluginManager();
        pm.registerEvents(new JoinLeaveEvent(this), this);
        pm.registerEvents(new InteractEvent(this), this);
        pm.registerEvents(new ClickEvent(this), this);
        pm.registerEvents(new DamageEvent(this), this);
        pm.registerEvents(new DeathEvent(this), this);
    }

    private void enableCommands() {
        getCommand("party").setExecutor(new PartyCMD(this));
    }
}
