package ghignatti.joao.plugin.verifiers;

import ghignatti.joao.plugin.action.ItemProjector;
import ghignatti.joao.plugin.action.ItemUnprojector;
import ghignatti.joao.plugin.array.ArrayInTrading;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
        ItemStack item = event.getCursor();

        if(aux.equals(p)) {

            p.sendMessage("aux é p");

            if(slot < inv.getSize()) {

                p.sendMessage("Chest click");

                if(item != null) {

                    p.sendMessage("Move to other inventory");

                    ItemProjector.getInstance().doProjection(event, p, t);

                } else {

                    p.sendMessage("Pick all");

                    ItemUnprojector.getInstance().doUnprojection(event, p, t);
                }
            }
        } else if(aux.equals(t)) {

            t.sendMessage("aux é t");

            if(slot < inv.getSize()) {

                t.sendMessage("Chest click");

                if(item != null) {

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
