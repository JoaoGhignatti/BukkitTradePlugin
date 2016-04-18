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
        ItemStack item = event.getCursor();

        if(p.equals(aux)) {

            p.sendMessage("aux unproject p");

            int index = SlotFinder.getInstance().getSearchSlot(t, "target", item);

            p.sendMessage("index " + index);

            if(index != -1) {

                p.sendMessage("index != -1");

                t.getOpenInventory().getTopInventory().remove(index);
            }

        } else if(t.equals(aux)) {

            t.sendMessage("aux unproject t");

            int index = SlotFinder.getInstance().getSearchSlot(p, "sender", item);

            t.sendMessage("index " + index);

            if(index != -1) {

                t.sendMessage("index != -1");

                p.getOpenInventory().getTopInventory().remove(index);
            }

        }
    }
}