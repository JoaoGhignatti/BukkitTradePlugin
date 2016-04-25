package ghignatti.joao.plugin.action;

import ghignatti.joao.plugin.array.ArrayInTrading;

import org.bukkit.entity.Player;

public class TradeAction {

    public TradeAction(Player p, Player t) {

        new InventoryCloseAction(t, p);

        p.sendMessage("Troca feita!");
        t.sendMessage("Troca feita!");

        ArrayInTrading.getInstance().tradingHash.get(p).setStatus(false);
        ArrayInTrading.getInstance().tradingHash.get(t).setStatus(false);

        ArrayInTrading.getInstance().tradingHash.remove(p);
        ArrayInTrading.getInstance().tradingHash.remove(t);

        t.closeInventory();
        p.closeInventory();
    }
}
