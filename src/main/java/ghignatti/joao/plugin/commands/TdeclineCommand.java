package ghignatti.joao.plugin.commands;

import ghignatti.joao.plugin.array.ArrayTradeRequisition;
import ghignatti.joao.plugin.utilities.RequestKnotFinder;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TdeclineCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage("Você precisa ser um player para recusar uma solicitação de troca");
            return false;
        }

        Player t = (Player) sender;

        ArrayTradeRequisition arrayTradeRequisition = ArrayTradeRequisition.getInstance();

        int index = RequestKnotFinder.getInstance().findKnot(t);

        if(index != -1) {
            arrayTradeRequisition.requisitionArray.remove(index);
        } else {
            t.sendMessage("Você não foi solicitado a fazer uma troca.");
        }

        return true;
    }
}
