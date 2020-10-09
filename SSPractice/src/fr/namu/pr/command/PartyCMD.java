package fr.namu.pr.command;

import fr.namu.pr.InfoPR;
import fr.namu.pr.MainPR;
import fr.namu.pr.PlayerPR;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class PartyCMD implements TabExecutor {

    private MainPR main;

    public PartyCMD(MainPR main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player))
            return true;

        Player player = (Player)sender;
        PlayerPR ppr = this.main.playerpr.get(player.getUniqueId());


        if(args.length == 0) {
            player.sendMessage("§7§m----------------------");
            player.sendMessage("§9§lSeaStory: §r§bCommande de Party");
            player.sendMessage("/p create §9Créer une party");
            player.sendMessage("§7§m----------------------");
            return true;
        }

        switch (args[0]) {
            case "create":
                this.main.party.createParty(player);
                return true;
            case "invite":
                if(args.length < 2) {
                    player.sendMessage(InfoPR.prefix + "§eTu dois spécifier un joueur !");
                    return true;
                }
                for(Player tagged : Bukkit.getOnlinePlayers()) {
                    if (args[1].equalsIgnoreCase(tagged.getName())) {
                        this.main.party.invitePlayer(player, tagged);
                        return true;
                    }
                }
                player.sendMessage(InfoPR.prefix + "§eNous n'avons pas trouvé le joueur spécifié !");
                return true;
            case "join":
                if(args.length < 2) {
                    player.sendMessage(InfoPR.prefix + "§eTu dois spécifier un joueur !");
                    return true;
                }
                for(Player tagged : Bukkit.getOnlinePlayers()) {
                    if (args[1].equalsIgnoreCase(tagged.getName())) {
                        PlayerPR tpr = this.main.playerpr.get(tagged.getUniqueId());
                        if(tpr.getParty().equals(null)) {
                            player.sendMessage(InfoPR.prefix + "§eCe joueur ne possède pas d'équipe ! Serait-ce une erreur ?");
                        }
                        this.main.party.joinParty(player, tpr.getParty());

                    }
                }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
