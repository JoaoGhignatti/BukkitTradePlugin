package ghignatti.joao.plugin.commands;

import ghignatti.joao.plugin.array.ArrayIgnoredPlayers;
import ghignatti.joao.plugin.array.ArrayTradeRequisition;
import ghignatti.joao.plugin.utilities.IgnoredPlayers;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TignoreCommand implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage("Você ser um player para ignorar uma troca.");
            return false;
        }

        Player t = (Player) sender;

        int index = new TdeclineCommand().findPositionInRequestArray(t);

        if(index != -1) {

            ArrayTradeRequisition arrayTradeRequisition = ArrayTradeRequisition.getInstance();

            if(arrayTradeRequisition.requisitionArray.get(index).isStatus()) {

                Player p = arrayTradeRequisition.requisitionArray.get(index).getPlayer();

                IgnoredPlayers ignoredPlayers = new IgnoredPlayers(p, t);
                ArrayIgnoredPlayers.getInstance().ignoredPlayers.add(ignoredPlayers);

                p.sendMessage(ChatColor.RED + t.getName() + " ignorou sua solicitação de troca.");
                p.sendMessage(ChatColor.RED + "Você ficará 5 min sem poder solicitar troca com " + t.getName() + ".");

                t.sendMessage(ChatColor.RED + "Você ignorou a solicitação de troca de " + p.getName() + ".");
                t.sendMessage(ChatColor.RED + p.getName() + " ficará 5 min sem poder solicitar troca com você.");

                return true;

            } else {

                return true;
            }
        } else {

            t.sendMessage("Você não foi solicitado a fazer troca.");
            return false;
        }
    }
}
