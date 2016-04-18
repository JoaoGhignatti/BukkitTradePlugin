package ghignatti.joao.plugin.commands;

import ghignatti.joao.plugin.action.InvSetter;
import ghignatti.joao.plugin.array.ArrayInTrading;
import ghignatti.joao.plugin.array.ArrayTradeRequisition;
import ghignatti.joao.plugin.utilities.InTrading;
import ghignatti.joao.plugin.verifiers.RequisitionVerifier;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TacceptCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage("Você precisa ser um player para aceitar uma solicitação de troca");
            return false;
        }

        Player target = (Player) sender;

        Player player = RequisitionVerifier.getInstance().searchRequisition(target);

        if(player == null) {

            return false;
        } else {

            player.sendMessage(ChatColor.GREEN + target.getName() + " aceitou sua solicitação de troca.");
            target.sendMessage(ChatColor.GREEN + "Você aceitou a solicitação de troca de " + player.getName() + ".");

            ArrayInTrading arrayInTrading = ArrayInTrading.getInstance();

            InTrading inTrading = new InTrading(player, target, true);

            arrayInTrading.tradingHash.put(player, inTrading);
            arrayInTrading.tradingHash.put(target, inTrading);

            new InvSetter(player, target);
        }

        return true;
    }
}
