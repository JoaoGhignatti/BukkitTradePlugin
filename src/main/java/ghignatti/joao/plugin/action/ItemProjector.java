package ghignatti.joao.plugin.action;

import ghignatti.joao.plugin.utilities.SlotFinder;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ItemProjector {
    private static ItemProjector ourInstance = new ItemProjector();

    public static ItemProjector getInstance() {
        return ourInstance;
    }

    private ItemProjector() {
    }

    public void doProjection(InventoryClickEvent event, Player p, Player t) {

        //int slot = event.getRawSlot();

        ItemStack item = event.getCursor();
        Player aux = (Player) event.getWhoClicked();

        if(p.equals(aux)) {

            /*int amount = p.getOpenInventory().getTopInventory().getItem(slot).getAmount();
            ItemStack item = new ItemStack(event.getCursor().getType(), amount);*/

            p.sendMessage("aux project p");

            int index = SlotFinder.getInstance().getSearchSlot(t, "target", null, false);

            p.sendMessage("index " + index);

            if(index != -1) {

                p.sendMessage("index != -1");

                t.getOpenInventory().getTopInventory().setItem(index, item);
            }

        } else if(t.equals(aux)) {

            /*int amount = t.getOpenInventory().getTopInventory().getItem(slot).getAmount();
            ItemStack item = new ItemStack(event.getCursor().getType(), amount);*/

            t.sendMessage("aux project t");

            int index = SlotFinder.getInstance().getSearchSlot(p, "sender", null, false);

            t.sendMessage("index " + index);

            if(index != -1) {

                t.sendMessage("index != -1");

                p.getOpenInventory().getTopInventory().setItem(index, item);
            }
        }
    }
}
