package ghignatti.joao.plugin.action;

import ghignatti.joao.plugin.utilities.SlotFinder;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
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

        ItemStack item;

        Player aux = (Player) event.getWhoClicked();

        if(event.getClick() == ClickType.RIGHT) {

            item = new ItemStack(event.getCursor().getType(), 1);

        } else {

            item = event.getCursor();
        }

        if(p.equals(aux)) {

            int index = SlotFinder.getInstance().getSearchSlot(t, "target", item, true);

            if(index != -1) {

                int amount = t.getOpenInventory().getTopInventory().getItem(index).getAmount();

                t.getOpenInventory().getTopInventory().setItem(index, new ItemStack(item.getType(), amount + item.getAmount()));

            } else {

                index = SlotFinder.getInstance().getSearchSlot(t, "target", null, false);

                if (index != -1) {

                    t.getOpenInventory().getTopInventory().setItem(index, item);
                }
            }
        } else if(t.equals(aux)) {

            int index = SlotFinder.getInstance().getSearchSlot(p, "sender", item, true);

            if(index != -1) {

                int amount = p.getOpenInventory().getTopInventory().getItem(index).getAmount();

                p.getOpenInventory().getTopInventory().setItem(index, new ItemStack(item.getType(), amount + item.getAmount()));

            } else {

                index = SlotFinder.getInstance().getSearchSlot(p, "sender", null, false);

                if (index != -1) {

                    p.getOpenInventory().getTopInventory().setItem(index, item);
                }
            }
        }
    }
}
