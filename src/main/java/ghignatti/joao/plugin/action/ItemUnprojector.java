package ghignatti.joao.plugin.action;

import ghignatti.joao.plugin.utilities.SlotFinder;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ItemUnprojector {
    private static ItemUnprojector ourInstance = new ItemUnprojector();

    public static ItemUnprojector getInstance() {
        return ourInstance;
    }

    private ItemUnprojector() {
    }

    public void doUnprojection(InventoryClickEvent event, Player p, Player t) {

        Player aux = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();

        if(p.equals(aux)) {

            if(SlotFinder.getInstance().getSearchSlot(t, "target", item, true) != -1) {

                t.getOpenInventory().getTopInventory().removeItem(item);
            }
        } else if(t.equals(aux)) {

            if(SlotFinder.getInstance().getSearchSlot(p, "sender", item, true) != -1) {

                p.getOpenInventory().getTopInventory().removeItem(item);
            }
        }
    }
}