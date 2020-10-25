package fr.namu.pr.arena;

import fr.namu.pr.MainPR;
import fr.namu.pr.PlayerPR;
import fr.namu.pr.enumpr.FightMode;
import fr.namu.pr.enumpr.Kit;
import fr.namu.pr.enumpr.Map;
import fr.namu.pr.enumpr.State;
import fr.namu.pr.runnable.ArenaRunnable;
import fr.namu.pr.runnable.SumoRunnable;
import fr.namu.pr.scoreboard.FastBoard;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Arena {

    private MainPR main;

    public List<Player> team1;
    public List<Player> team2;
    public List<Player> players = new ArrayList<>();

    public List<ItemStack> DroppedItems = new ArrayList<>();

    private ArenaRunnable ar;

    public int timer;

    public Kit kit;
    public FightMode fm;
    public Map map;

    public Arena(MainPR main, List<Player> team1, List<Player> team2, Kit kit, FightMode fm) {
        this.main = main;
        this.team1 = team1;
        this.team2 = team2;
        this.kit = kit;
        this.fm = fm;
    }

    public void init() {
        this.main.arenas.add(this);
        setTeamUp();
        setPlayersLocation();
        setPlayersData();
        setPlayersKit();

        ArenaRunnable startArena = new ArenaRunnable(this.main, this);
        ar = startArena;
        startArena.runTaskTimer(this.main, 0L, 20L);

        if(kit == Kit.SUMO) {
            SumoRunnable sumoDetector = new SumoRunnable(this.main, this);
            ar = startArena;
            sumoDetector.runTaskTimer(this.main, 0L, 20L);
        }
    }

    public void stop() {
        ar.cancel();
        this.main.arenas.remove(this);
        team1.clear();
        team2.clear();
        players.clear();
        for(ItemStack item : DroppedItems) {
            try {
                item.setType(Material.AIR);
            } catch (Exception e) {

            }

        }
        map = null;
    }

    private void setTeamUp() {
        if(team2.isEmpty()) {
            List<Player> players = new ArrayList<>();
            players.addAll(team1);
            team1.clear();
            Integer ind = 1;

            for(Player player : players) {
                this.main.lobby.removeFromLobbyList(player);
                if(ind == 1) {
                    team1.add(player);
                    ind = 2;
                } else {
                    team2.add(player);
                    ind = 1;
                }
            }
        }

        players.addAll(team1);
        players.addAll(team2);
    }

    private void setPlayersLocation() {
        switch (fm) {
            case UNRANKED:
            case RANKED:
                for(Player player : team1) {
                    player.teleport(new Location(player.getWorld(), map.getX1(), map.getY1(), map.getZ1()));
                }
                for(Player player : team2) {
                    player.teleport(new Location(player.getWorld(), map.getX2(), map.getY2(), map.getZ2()));
                }
        }
    }

    private void setPlayersKit() {
        for(Player player : players) {
            this.main.kit.setKit(player, kit);
        }
    }

    private void setPlayersData() {
        for(Player player : players) {
            PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());
            ppr.setState(State.FIGHT);
            ppr.setQueuedKit(null);
            ppr.setKit(kit);
            ppr.setArena(this);
        }

        for(Player player : team1) {
            PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());
            ppr.setOpponents(team2);
        }
        for(Player player : team2) {
            PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());
            ppr.setOpponents(team1);
        }
    }

    public void updateArenaBoard() {
        for(Player player : players) {
            FastBoard board = this.main.boards.get(player.getUniqueId());
            PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());

            String[] score = {
                    "§7§m----------------------",
                    "Kit: §9" + kit.getKitName(),
                    "Temps: §9" + this.main.arena.convertArenaTimer(timer),
                    "",
                    "Alliés",
                    "Ennemis",
                    "",
                    "§7§m----------------------"
            };

            if(team1.size() == 1 && team2.size() == 1) {
                Player teamate1 = team1.get(0);
                Player teamate2 = team2.get(0);
                if(teamate1 == player) {
                    score[4] = "Votre ping: §9" + ((CraftPlayer) player).getHandle().ping;
                    score[5] = "Ping de " + teamate2.getName() + ": §9" + ((CraftPlayer) teamate2).getHandle().ping;
                } else {
                    score[4] = "Votre ping: §9" + ((CraftPlayer) player).getHandle().ping;
                    score[5] = "Ping de " + teamate1.getName() + ": §9" + ((CraftPlayer) teamate1).getHandle().ping;
                }
            } else {
                if(team1.contains(player)) {
                    score[4] = "Alliés: §9" + team1.size();
                    score[5] = "Ennemis: §9" + team2.size();
                } else {
                    score[4] = "Alliés: §9" + team2.size();
                    score[5] = "Ennemis: §9" + team1.size();
                }
            }


            for (int i = 0; i < score.length; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(score[i]);
                if (sb.length() > 30)
                    sb.delete(29, sb.length() - 1);
                score[i] = sb.toString();
            }

            board.updateTitle("§7• §9SEASTORY §7•");
            board.updateLines(score);
        }
    }

    public void checkEnd() {
        Boolean alive = false;
        for(Player player : team1) {
            if(!player.getGameMode().equals(GameMode.SPECTATOR)) {
                alive = true;
            }

            if(alive == false) {
                winArena(team2, team1, this);
                return;
            }
        }
        alive = false;
        for(Player player : team2) {
            if(!player.getGameMode().equals(GameMode.SPECTATOR)) {
                alive = true;
            }

            if(alive == false) {
                winArena(team2, team1, this);
                return;
            }
        }
    }

    private void winArena(List<Player> winners, List<Player> losers, Arena arena) {

        for(Player player : winners) {

        }

        for(Player player : losers) {

        }

        for(Player player : players) {
            player.sendMessage("§7§m-------------------");
            player.sendMessage("§bKit: §F" + kit.getKitName() + "     §bDurée: §f" + this.main.arena.convertArenaTimer(timer));
            player.sendMessage(" ");
            String win = "§9Vainqueurs: §7";
            if(winners.size() == 1) {
                win = "§9Gagnant: §7";
            }
            for(Player pl : winners) {
                win += pl.getName() + " (" + pl.getHealth() + "), ";
            }
            player.sendMessage(win);

            String lose = "§9Perdants: §7";
            if(losers.size() == 1) {
                lose = "§9Perdant: §7";
            }
            for(Player pl : losers) {
                lose += pl.getName() + " (" + pl.getHealth() + "), ";
            }
            player.sendMessage(lose);
            player.sendMessage("§7§m-------------------");
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                arena.leavePlayers();
                arena.stop();
            }
        }.runTaskLater(this.main, 20*3);
    }

    private void leavePlayers() {
        for(Player player : players) {
            this.main.lobby.sendPlayerToLobby(player);
        }
    }
}
