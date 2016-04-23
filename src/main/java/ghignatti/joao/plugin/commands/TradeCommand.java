package ghignatti.joao.plugin.commands;

import ghignatti.joao.plugin.array.ArrayIgnoredPlayers;
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

        if(searchPlayerIgnored(player, target)) {

            long millis = ArrayIgnoredPlayers.getInstance().ignoredPlayers.get(i).getTime();
            millis = millis + 300000 - System.currentTimeMillis();

            long second = (millis / 1000) % 60;
            long minute = (millis / (1000 * 60)) % 60;
            long hour = (millis / (1000 * 60 * 60)) % 24;

            player.sendMessage(ChatColor.RED + "Você ainda não está apto a pedir troca para este player.");
            player.sendMessage(ChatColor.RED + "Espere " + hour + "h" + minute + "min" + second + "s.");
            return false;
        }

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
        target.sendMessage(ChatColor.AQUA + "Ou ainda " + ChatColor.DARK_AQUA + "/tignore" +
                ChatColor.AQUA + " para ignorar as solicitações deste player por 5 min.");

        ArrayTradeRequisition.getInstance().requisitionArray.add(new TradeRequisition(player, target, true));

        return true;
    }

    private int i;

    private boolean searchPlayerIgnored(Player player,Player target) {

        ArrayIgnoredPlayers ignoredPlayers = ArrayIgnoredPlayers.getInstance();

        for(i = ignoredPlayers.ignoredPlayers.size()-1; i>=0; i--) {

            if(player.equals(ignoredPlayers.ignoredPlayers.get(i).getPlayer())) {

                if(target.equals(ignoredPlayers.ignoredPlayers.get(i).getTarget())) {

                    if(ignoredPlayers.ignoredPlayers.get(i).getTime() + 300000 > System.currentTimeMillis()) {

                        return true;
                    }
                }
            }
        }

        return false;
    }

    public int getI() {
        return i;
    }
}
