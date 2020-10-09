package fr.namu.pr.manager;

import fr.namu.pr.InfoPR;
import fr.namu.pr.MainPR;
import fr.namu.pr.PlayerPR;
import fr.namu.pr.party.PartyPR;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PartyManager {

    private List<PartyPR> partys = new ArrayList<>();

    private MainPR main;

    public PartyManager(MainPR main) {
        this.main = main;
    }

    public List<PartyPR> getPartys() {
        return partys;
    }
    public void addParty(PartyPR party) {
        this.partys.add(party);
    }

    public void createParty(Player captain) {
        PlayerPR ppr = this.main.playerpr.get(captain.getUniqueId());

        PartyPR party = new PartyPR(captain);
        addParty(party);

        ppr.setParty(party);

        this.main.stuff.giveLobbyStuff(captain);
    }

    public void invitePlayer(Player player, Player tagged) {
        PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());

        if(ppr.getParty().equals(null)) {
            player.sendMessage(InfoPR.prefix + "§eVous n'avez pas d'équipe ! Pour en créer une, utilisez §7/party create §e!");
        }
        if(!ppr.getParty().getCaptain().equals(player)) {
            player.sendMessage(InfoPR.prefix + "§eVous n'êtes pas capitaine de votre équipe !");
            return;
        }
        ppr.getParty().addInvited(tagged);
        TextComponent msg = new TextComponent(ChatColor.GREEN + "Cliquez ici pour rejoindre la Party !");
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/party join " + player.getName()));
        tagged.sendMessage("§7§m----------------------");
        tagged.sendMessage(" ");
        tagged.spigot().sendMessage(msg);
        tagged.sendMessage("§7Vous avez été invité par " + player.getName());
        tagged.sendMessage(" ");
        tagged.sendMessage("§7§m----------------------");
        player.sendMessage(InfoPR.prefix + "§aLe joueur §e" + tagged.getName() + " §aa été invité !");
        return;
    }

    public void joinParty(Player player, PartyPR party) {
        if(!party.getInvited().contains(player)) {
            player.sendMessage(InfoPR.prefix + "§eVous n'avez pas été invité dans cette équipe !");
            return;
        }
        PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());

        party.addMember(player);
        ppr.setParty(party);

        this.main.stuff.giveLobbyStuff(player);

        for(Player players : party.getMembers()) {
            players.sendMessage(InfoPR.prefix + "§a" + player.getName() + " §ea rejoint la party !");
        }
    }
}
