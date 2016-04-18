package ghignatti.joao.plugin.commands;

import ghignatti.joao.plugin.array.ArrayTradeRequisition;
import ghignatti.joao.plugin.utilities.TradeRequisition;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TradeCommand implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if(!(sender instanceof Player)) {

            sender.sendMessage("Você precisa ser um player para solicitar troca.");
            return false;
        }

        Player player = (Player) sender;

        if(args.length == 0) {

            player.sendMessage("Você precisa especificar o player a fazer a troca.");
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if(target == null) {

            player.sendMessage("O player a quem você solicitou troca não foi encontrado ou está offline.");
            return false;

        } else if(target.equals(player)) {

            player.sendMessage("Você não pode trocar com você mesmo.");
            return false;
        }

        player.sendMessage(ChatColor.AQUA + "Você solicitou troca com " + target.getName() + ".");

        target.sendMessage(ChatColor.AQUA + player.getName() + " deseja trocar itens com você.");
        target.sendMessage(ChatColor.AQUA + "Digite " + ChatColor.DARK_AQUA + "/taccept" +
                ChatColor.AQUA + " para aceitar e " + ChatColor.DARK_AQUA + "/tdecline" +
                ChatColor.AQUA + " para declinar oferta.");

        ArrayTradeRequisition.getInstance().requisitionArray.add(new TradeRequisition(player, target, true));

        return true;
    }
}
