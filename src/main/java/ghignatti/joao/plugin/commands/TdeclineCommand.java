package ghignatti.joao.plugin.commands;

import ghignatti.joao.plugin.array.ArrayTradeRequisition;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TdeclineCommand implements CommandExecutor {

    private ArrayTradeRequisition arrayTradeRequisition = ArrayTradeRequisition.getInstance();

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if(!(sender instanceof Player)) {

            sender.sendMessage("Você precisa ser um player para recusar uma solicitação de troca");
            return false;
        }

        Player t = (Player) sender;

        int index = findPositionInRequestArray(t);

        if(index != -1) {

            Player p = arrayTradeRequisition.requisitionArray.get(index).getPlayer();

            p.sendMessage(ChatColor.RED + t.getName() + " recusou sua solicitação de troca.");
            t.sendMessage(ChatColor.RED + "Você recusou a solicitação de troca de " + p.getName() + ".");

            arrayTradeRequisition.requisitionArray.remove(index);
        } else {

            t.sendMessage("Você não foi solicitado a fazer uma troca.");
        }

        return true;
    }

    int findPositionInRequestArray(Player t) {

        for(int i = arrayTradeRequisition.requisitionArray.size()-1; i>=0; i--) {

            Player target = arrayTradeRequisition.requisitionArray.get(i).getTarget();

            if(t.equals(target)) {

                if(arrayTradeRequisition.requisitionArray.get(i).isStatus()) {

                    if (arrayTradeRequisition.requisitionArray.get(i).getTime() + 30000 > System.currentTimeMillis()) {

                        return i;
                    } else {

                        arrayTradeRequisition.requisitionArray.get(i).setStatus(false);
                        target.sendMessage("Solicitação de troca já expirou.");
                        return i;
                    }
                } else {

                    target.sendMessage("Solicitação de troca já expirou.");
                    return i;
                }
            }
        }

        return -1;
    }
}
