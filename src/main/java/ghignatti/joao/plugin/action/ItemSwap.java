package ghignatti.joao.plugin.action;

import ghignatti.joao.plugin.utilities.SlotFinder;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ItemSwap {
    private static ItemSwap ourInstance = new ItemSwap();

    public static ItemSwap getInstance() {
        return ourInstance;
    }

    private ItemSwap() {
    }

    public void doSwap(InventoryClickEvent event, Player p, Player t) {

        ItemStack cursorItem = event.getCursor();
        ItemStack slotItem = event.getCurrentItem();

        Player aux = (Player) event.getWhoClicked();

        if(aux.equals(p)) {

            if(SlotFinder.getInstance().getSearchSlot(t, "target", slotItem, true) != -1) {

                t.getOpenInventory().getTopInventory().remove(slotItem);

                int index = SlotFinder.getInstance().getSearchSlot(t, "target", null, false);

                if(index != -1) {

                    t.getOpenInventory().getTopInventory().setItem(index, cursorItem);
                }
            }

        } else if(aux.equals(t)) {

            if(SlotFinder.getInstance().getSearchSlot(p, "sender", slotItem, true) != -1) {

                p.getOpenInventory().getTopInventory().remove(slotItem);

                int index = SlotFinder.getInstance().getSearchSlot(p, "sender", null, false);

                if(index != -1) {

                    p.getOpenInventory().getTopInventory().setItem(index, cursorItem);
                }
            }
        }
    }
}
