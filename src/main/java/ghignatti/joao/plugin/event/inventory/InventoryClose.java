package ghignatti.joao.plugin.event.inventory;

import ghignatti.joao.plugin.action.InventoryCloseAction;
import ghignatti.joao.plugin.array.ArrayInTrading;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryClose implements Listener{

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {

        if(event.getPlayer() instanceof Player) {

            Player aux = (Player) event.getPlayer();

            ArrayInTrading arrayInTrading = ArrayInTrading.getInstance();

            if(arrayInTrading.tradingHash.containsKey(aux)) {

                Player p = arrayInTrading.tradingHash.get(aux).getPlayer();
                Player t = arrayInTrading.tradingHash.get(aux).getTarget();

                if(event.getInventory().getName().equals("Troca " + p.getName() + "/" + t.getName())) {

                    if ((aux.equals(p) || aux.equals(t)) && arrayInTrading.tradingHash.get(aux).isStatus()) {

                        new InventoryCloseAction(p, t);

                        ArrayInTrading.getInstance().tradingHash.get(p).setStatus(false);
                        ArrayInTrading.getInstance().tradingHash.get(t).setStatus(false);

                        ArrayInTrading.getInstance().tradingHash.remove(p);
                        ArrayInTrading.getInstance().tradingHash.remove(t);

                        if (aux.equals(p)) {

                            t.closeInventory();
                        } else {

                            p.closeInventory();
                        }
                    }
                }
            }
        }
    }
}
