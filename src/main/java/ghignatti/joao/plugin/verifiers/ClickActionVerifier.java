package ghignatti.joao.plugin.verifiers;

import ghignatti.joao.plugin.action.ItemProjector;
import ghignatti.joao.plugin.action.ItemUnprojector;
import ghignatti.joao.plugin.array.ArrayInTrading;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class ClickActionVerifier {
    private static ClickActionVerifier ourInstance = new ClickActionVerifier();

    public static ClickActionVerifier getInstance() {
        return ourInstance;
    }

    private ClickActionVerifier() {
    }

    public void verifyAction(InventoryClickEvent event) {

        if(!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player aux = (Player) event.getWhoClicked();

        ArrayInTrading arrayInTrading = ArrayInTrading.getInstance();

        Player p = arrayInTrading.tradingHash.get(aux).getPlayer();
        Player t = arrayInTrading.tradingHash.get(aux).getTarget();

        if(p == null || t == null) {
            aux.sendMessage("p ou t null");
            return;
        }

        if(!(arrayInTrading.tradingHash.get(p).isStatus())) {
            p.sendMessage("Status false");
            return;
        }

        int slot = event.getRawSlot();
        Inventory inv = event.getInventory();
        InventoryAction inventoryAction = event.getAction();

        if(aux.equals(p)) {

            p.sendMessage("aux é p");

            if(slot < inv.getSize()) {

                p.sendMessage("Chest click");

                if(inventoryAction == InventoryAction.PLACE_ALL ||
                        inventoryAction == InventoryAction.PLACE_ONE ||
                        inventoryAction == InventoryAction.PLACE_SOME) {

                    p.sendMessage("Move to other inventory");

                    ItemProjector.getInstance().doProjection(event, p, t);

                } else if(inventoryAction == InventoryAction.PICKUP_ALL ||
                        inventoryAction == InventoryAction.PICKUP_HALF ||
                        inventoryAction == InventoryAction.PICKUP_ONE ||
                        inventoryAction == InventoryAction.PICKUP_SOME) {

                    p.sendMessage("Pick");

                    ItemUnprojector.getInstance().doUnprojection(event, p, t);
                }
            }
        } else if(aux.equals(t)) {

            t.sendMessage("aux é t");

            if(slot < inv.getSize()) {

                t.sendMessage("Chest click");

                if(inventoryAction == InventoryAction.PLACE_ALL) {

                    t.sendMessage("Move to other inventory");

                    ItemProjector.getInstance().doProjection(event, p, t);

                } else {

                    t.sendMessage("Pick all");

                    ItemUnprojector.getInstance().doUnprojection(event, p, t);
                }
            }
        }
    }
}
