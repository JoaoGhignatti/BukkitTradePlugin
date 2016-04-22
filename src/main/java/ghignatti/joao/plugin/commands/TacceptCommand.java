package ghignatti.joao.plugin.commands;

import ghignatti.joao.plugin.action.InvSetter;
import ghignatti.joao.plugin.array.ArrayInTrading;
import ghignatti.joao.plugin.array.ArrayTradeRequisition;
import ghignatti.joao.plugin.utilities.InTrading;

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

        Player player = searchRequisition(target);

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

    private Player searchRequisition(Player t) {

        ArrayTradeRequisition arrayList = ArrayTradeRequisition.getInstance();

        for(int i = arrayList.requisitionArray.size()-1; i>=0; i--) {

            Player target = arrayList.requisitionArray.get(i).getTarget();

            if(t.equals(target)) {

                if(arrayList.requisitionArray.get(i).isStatus()) {

                    if (arrayList.requisitionArray.get(i).getTime() + 30000 > System.currentTimeMillis()) {

                        Player player = arrayList.requisitionArray.get(i).getPlayer();

                        if(player == null) {

                            target.sendMessage("O player que solicitou a troca não está mais online.");
                            return null;

                        } else {

                            arrayList.requisitionArray.remove(i);
                            return player;
                        }
                    } else {

                        arrayList.requisitionArray.get(i).setStatus(false);
                        target.sendMessage("Solicitação de troca expirou.");
                        return null;
                    }
                } else {

                    target.sendMessage("Solicitação de troca expirou.");
                    return null;
                }
            }
        }

        t.sendMessage("Você não foi solicitado a fazer troca.");
        return null;
    }
}
